package org.quydusaigon.predatorsim.states;

import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.State;
import org.quydusaigon.predatorsim.behaviours.animals.Predator;
import org.quydusaigon.predatorsim.behaviours.animals.Prey;
import org.quydusaigon.predatorsim.gameengine.Time;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.util.Distance;
import org.quydusaigon.predatorsim.util.Parameter;
import org.quydusaigon.predatorsim.util.PredatorStat;

import javafx.geometry.Point2D;

public class JoinState extends State {

    double targetX, targetY;
    Point2D targetDir;

    Predator targetPredator;
    Prey targetPrey;

    // Indicates if this member has found its group - collides with an other
    // predator object's howling radius
    public boolean groupFounded;

    // Indicates if all members are present in the group radius
    private boolean huntStarted;

    // Position type indicates its position in the formation
    private int positionType = 0;

    public JoinState(Animal animal) {
        super(animal);

    }

    // Initialize all fields to false or defaul values
    @Override
    public void enter() {
        groupFounded = false;
        huntStarted = false;
        positionType = 0;

    }

    @Override
    public void update() {
        if (targetPrey == null || targetPrey.getGameObject() == null || targetPredator.getGameObject() == null) {
            animal.changeState(((Predator) animal).getPredatorWanderState());
            return;
        } else
            doJoin();
    }

    @Override
    public void exit() {
        groupFounded = false;
        targetPredator = null;
        targetPrey = null;
    }

    // Set leader predator object and the targetted prey
    public void setTargetJoin(Predator predator, Prey prey) {
        targetPredator = predator;
        targetPrey = prey;
    }

    void doJoin() {
        // If the huntStarted is set true by the leader, then the member will call
        // corner() function
        if (huntStarted) {
            corner();
            return;
        }

        // If the member is not in the group radius or the intended prey is in its
        // vision, then it call stalk() function
        if (Distance.calculateDistance(targetPredator.getGameObject(),
                animal.getGameObject()) <= ((PredatorStat) animal.animalStat).groupRadius ||
                Distance.calculateDistance(targetPrey.getGameObject(),
                        animal.getGameObject()) <= ((PredatorStat) animal.animalStat).visionRange) {
            stalk();
            return;
        }

        // If the member is in the group radius and the intended prey is not in its
        // vision, then it call follow() function
        follow();
    }

    // Allows the member to corner the prey with its position type
    private void corner() {
        // Get the prey's position
        targetX = targetPrey.posX().get();
        targetY = targetPrey.posY().get();

        // If its position type is 0 or 1
        if (positionType == 0 || positionType == 1) {
            // Get the leader's position
            double leaderX = targetPredator.posX().get();
            double leaderY = targetPredator.posY().get();

            // Get the vector from the leader to the prey
            var leaderToTargetVector = new Point2D(targetX - leaderX, targetY - leaderY);

            // Get the vector from the member to the prey
            var targetVector = new Point2D(targetX - animal.posX().get(), targetY - animal.posY().get());

            // Initialize a vector
            var perpendicularVector = Point2D.ZERO;

            // Get the vector that is perpendicular to the leaderToTargetVector and based on
            // its position type, it will be one of two
            // possible perpendicular vectors (two position types for 2 perpendicular
            // vectors)
            perpendicularVector = new Point2D(Math.pow(-1, 1 + positionType) * leaderToTargetVector.getY(),
                    Math.pow(-1, positionType) * leaderToTargetVector.getX());

            // Perform a vector addition between the perpendicularVector and the
            // targetVector to get the final vector
            targetDir = perpendicularVector.add(targetVector);

            // Normalize vector before move
            targetDir = targetDir.normalize();
        }

        // If its position type is 2 or 3
        else if (positionType == 2 || positionType == 3) {
            // Get all the allies predator set from the leader
            Set<Predator> alliesPredator = targetPredator.getHuntInGroupState().getAlliesPredator();

            // Get the other member to follow based on its position type (type 2 follows
            // type 0, type 3 follow type 1)
            var newPredator = alliesPredator.stream()
                    .filter(go -> (go.getJoinState().positionType == positionType % 2) & (go != targetPredator))
                    .collect(Collectors.toList()).get(0);

            // Get the corresponding member that this member is following
            double newPredatorX = newPredator.posX().get();
            double newPredatorY = newPredator.posY().get();

            // Get the vector from the new member to follow to the prey
            var newPredatorToTargetVector = new Point2D(targetX - newPredatorX, targetY - newPredatorY);

            // Get the vector from this member to the prey
            var targetVector = new Point2D(targetX - animal.posX().get(), targetY - animal.posY().get());

            // Initialize a vector
            var perpendicularVector = Point2D.ZERO;

            // Get the vector that is perpendicular to the newPredatorToTarget and based on
            // its position type and the member to follow,
            // it will be in the same position relatively to the followed member's position
            // to the leader
            perpendicularVector = new Point2D(Math.pow(-1, 1 + positionType) * newPredatorToTargetVector.getY(),
                    Math.pow(-1, positionType) * newPredatorToTargetVector.getX());

            // Perform a vector addition between the perpendicularVector and the
            // targetVector to get the final vector
            targetDir = perpendicularVector.add(targetVector);

            // Normalize vector before move
            targetDir = targetDir.normalize();
        }

        // Set velocity towards the final target direction
        animal.velocity.set(targetDir.multiply(animal.animalStat.runSpeed * Time.getDeltaTime()
                * Parameter.getRelativeSimulationSpeed()));

    }

    // Allows the member to follow the leader movement
    private void follow() {
        // Get the leader's position
        targetX = targetPredator.posX().get();
        targetY = targetPredator.posY().get();

        // Get the vector from the member to the leadder
        targetDir = new Point2D(targetX - animal.posX().get(), targetY - animal.posY().get());

        // Normalize the vector
        targetDir = targetDir.normalize();

        // Set movement by multiply with animal speed, game simulation speed and time
        // frame
        animal.velocity.set(targetDir.multiply(animal.animalStat.runSpeed * Time.getDeltaTime()
                * Parameter.getRelativeSimulationSpeed()));
    }

    // Allows the member to stalk the inteded prey movement
    private void stalk() {
        // Get distance to the prey
        double distanceToTarget = Distance.calculateDistance(animal.getGameObject(), targetPrey.getGameObject());

        // Get the prey's position
        targetX = GameObject.posX(targetPrey.getGameObject()).get();
        targetY = GameObject.posY(targetPrey.getGameObject()).get();

        // Get vector from the member to the prey
        targetDir = new Point2D(targetX - animal.posX().get(), targetY - animal.posY().get());

        // Normalize the vector
        targetDir = targetDir.normalize();

        // Offset for the distance to stay away and stalk the prey
        double offset = 25;

        // If the member distance to the prey is lower than vision range - offset
        if (distanceToTarget <= animal.animalStat.visionRange - offset) {

            // Move to the opposite direction to the prey since the member is too close
            animal.velocity.set(targetDir.multiply(-animal.animalStat.runSpeed / 2 *
                    Time.getDeltaTime()
                    * Parameter.getRelativeSimulationSpeed()));

            // If the member distance to the prey is higher than vision range + offset
        } else if (distanceToTarget >= animal.animalStat.visionRange + offset) {

            // Move to the direction to the prey since the member is too far
            animal.velocity.set(targetDir.multiply(animal.animalStat.runSpeed / 2 * Time.getDeltaTime()
                    * Parameter.getRelativeSimulationSpeed()));

            // Else stay in the same position
        } else {
            animal.velocity.set(Point2D.ZERO);
        }
    }

    public void setHuntStarted(boolean value) {
        this.huntStarted = value;
    }

    public void setPositionType(int positionType) {
        this.positionType = positionType;
    }

    @Override
    public String toString() {
        return super.toString() + "Join - Type " + positionType;
    }
}
