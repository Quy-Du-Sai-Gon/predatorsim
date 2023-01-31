package org.quydusaigon.predatorsim.states;

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

    public boolean groupFounded;

    public JoinState(Animal animal) {
        super(animal);

    }

    @Override
    public void enter() {
        groupFounded = false;
    }

    @Override
    public void update() {
        if (targetPrey != null ||targetPrey.getGameObject() == null) {
            animal.changeState(((Predator) animal).getPredatorWanderState());
            return;
        }
        if (groupFounded) {
            animal.changeState(((Predator) animal).getHuntInGroupState());
            return;
        }

        doJoin();
    }

    @Override
    public void exit() {
        groupFounded = false;
        targetPredator = null;
        targetPrey = null;
    }

    public void setTargetJoin(Predator predator, Prey prey) {
        targetPredator = predator;
        targetPrey = prey;
    }

    void doJoin() {
        // if this object is within the group radius or it found target prey in it
        // vision => stalk
        if (Distance.calculateDistance(targetPredator.getGameObject(),
                animal.getGameObject()) <= ((PredatorStat) animal.animalStat).groupRadius ||
                Distance.calculateDistance(targetPrey.getGameObject(),
                        animal.getGameObject()) <= ((PredatorStat) animal.animalStat).visionRange) {
            stalk();
            return;
        }

        targetX = targetPredator.posX().get();
        targetY = targetPredator.posY().get();

        targetDir = new Point2D(targetX - animal.posX().get(), targetY - animal.posY().get());

        targetDir = targetDir.normalize();

        animal.velocity.set(targetDir.multiply(animal.animalStat.runSpeed * Time.getDeltaTime()
                * Parameter.getRelativeSimulationSpeed()));
    }

    private void stalk() {
        double distanceToTarget = Distance.calculateDistance(animal.getGameObject(), targetPrey.getGameObject());
        double targetX, targetY;
        Point2D targetDir;

        targetX = GameObject.posX(targetPrey.getGameObject()).get();
        targetY = GameObject.posY(targetPrey.getGameObject()).get();

        targetDir = new Point2D(targetX - animal.posX().get(), targetY - animal.posY().get());
        targetDir = targetDir.normalize();

        double offset = 25;
        if (distanceToTarget <= animal.animalStat.visionRange - offset) {
            animal.velocity.set(targetDir.multiply(-animal.animalStat.runSpeed / 2 *
                    Time.getDeltaTime()
                    * Parameter.getRelativeSimulationSpeed()));
        } else if (distanceToTarget >= animal.animalStat.visionRange + offset) {
            animal.velocity.set(targetDir.multiply(animal.animalStat.runSpeed / 2 * Time.getDeltaTime()
                    * Parameter.getRelativeSimulationSpeed()));
        } else {
            animal.velocity.set(Point2D.ZERO);
        }
    }

    @Override
    public String toString() {
        return super.toString() + "Join";
    }
}
