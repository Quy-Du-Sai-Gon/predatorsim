package org.quydusaigon.predatorsim.states;

import java.util.HashSet;
import java.util.Set;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.State;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.HowlVision;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.Vision;
import org.quydusaigon.predatorsim.behaviours.animals.Predator;
import org.quydusaigon.predatorsim.behaviours.animals.Prey;
import org.quydusaigon.predatorsim.gameengine.Time;
import org.quydusaigon.predatorsim.gameengine.component.Collider;
import org.quydusaigon.predatorsim.gameengine.component.NodeComponent;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.gameengine.util.TransformInit;
import org.quydusaigon.predatorsim.util.Distance;
import org.quydusaigon.predatorsim.util.Parameter;
import org.quydusaigon.predatorsim.util.PredatorStat;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class HowlState extends State {
    // variable to store the target prey for hunting
    Prey targetPrey;
    // variable to store howl vision reference
    HowlVision howlVision;
    // variable to store group vision reference
    Vision groupVision;
    // variable to store the game object holding howl vision component
    Group howlObject
    // variable to store the game object holding group vision component
    Group groupObject;

    // the required number of other predators to form a group
    int numberOfAllies;
    // stores a set of allied predators in the hunt
    Set<Predator> alliesPredators;

    // cool down time for howling duration
    double coolDownTime = 10;
    // the current cool down time of howling duration, decreased over time
    double currentCoolDownTime = 0;

    public int positionTypeGiven = 0;

    Predator animal;

    // Constructor for HowlState class
    public HowlState(Animal animalSM) {
        // Call super class (State) constructor and pass the Animal instance
        super(animalSM);
    }

    // Method to be called when entering this state
    @Override
    public void enter() {
        // Set the predator associated with this state
        animal = (Predator) super.animal;

        // Create the howling radius object
        var howlNodeComp = new NodeComponent<>(
                new Circle(((PredatorStat) (animal.animalStat)).howlingRadius, Color.YELLOW));
        howlObject = GameObject.create(TransformInit.DEFAULT, animal.getGameObject(),
                howlNodeComp, new Collider<>(howlNodeComp), howlVision = new HowlVision());

        // Create the group hunting radius object
        var groupNodeComp = new NodeComponent<>(
                new Circle(((PredatorStat) (animal.animalStat)).groupRadius, Color.RED));
        groupObject = GameObject.create(TransformInit.DEFAULT, animal.getGameObject(),
                groupNodeComp, new Collider<>(groupNodeComp), groupVision = new Vision());

        // Set up the howling vision
        howlVision.SetUpHowlVision((Predator) animal);
        // Initialize the set of ally predators and add the current predator to it
        alliesPredators = new HashSet<>();
        alliesPredators.add(((Predator) animal));
        // Set the current cooldown time
        currentCoolDownTime = coolDownTime;
    }

    // Method to be called for updating the state
    @Override
    public void update() {
        // If the target prey is dead or no longer exists, change state to
        // predatorWanderState
        if (targetPrey == null || targetPrey.getGameObject() == null) {
            animal.changeState(((Predator) animal).getPredatorWanderState());
            return;
        }

        // If the number of predators within the group hunting radius equals the number of allies
        if (groupVision.getAllDetectedObject(Predator.class).size() + 1 == numberOfAllies) {
            // Get the predator component of this game object
            var predator = (Predator) animal;
            // Set target prey and ally predators for HuntInGroup state
            predator.getHuntInGroupState().setTargetPrey(targetPrey, alliesPredators);
            // Change the current state of this predator into HuntInGroupState
            animal.changeState(((Predator) animal).getHuntInGroupState());
            // Set up join state for each ally predator
            alliesPredators.stream()
                    .forEach(go -> {
                        go.getJoinState().setHuntStarted(true);
                        go.getJoinState().setPositionType(this.positionTypeGiven);
                        if (go != animal) {
                            this.positionTypeGiven = this.positionTypeGiven + 1;
                        }
                    });
            return;
        }

        // do howl behaviour
        howl();
        // do stalk behaviour
        stalk();
    }

    // Method to be called when exiting this state
    @Override
    public void exit() {
        // set the target prey to null when exiting this state
        targetPrey = null;
        // destroy the howl and group vision object
        GameObject.destroy(howlObject);
        GameObject.destroy(groupObject);
        // Reset the position type given to 0
        this.positionTypeGiven = 0;
    }

    // Method sets the target prey for the predator to hunt
    public void setTargetPrey(Prey targetPrey, int numberOfAllies) {
        this.targetPrey = targetPrey;
        this.numberOfAllies = numberOfAllies;
    }

    // Method to get the target prey for the predator
    public Prey getTargetPrey() {
        return targetPrey;
    }

    // Method to get the current number of allies of the predator
    public int getCurrentNumberOfAllies() {
        return alliesPredators.size();
    }

    void howl() {
        // Decrement the current cool down time by the delta time
        currentCoolDownTime -= Time.getDeltaTime();
        // Change back to predatorWanderState and set prey for not hunting again with
        // getFailedPrey method
        // when the time is out
        if (currentCoolDownTime <= 0) {
            ((Predator) animal).getPredatorWanderState().getFailedPrey(targetPrey);
            // do the same for each ally predator
            for (var allyPredator : alliesPredators) {
                ((Predator) allyPredator).getPredatorWanderState().getFailedPrey(targetPrey);
                allyPredator.changeState(((Predator) allyPredator).getPredatorWanderState());
            }
            animal.changeState(((Predator) animal).getPredatorWanderState());
        }
    }

    // add ally predator
    public void addAlly(Predator allyPredator) {
        alliesPredators.add(allyPredator);
    }

    private void stalk() {
        // If the target prey is null or has been deleted from the simulation
        if (targetPrey == null || targetPrey.getGameObject() == null)
            // If it is, then immediately return without doing anything
            return;

        // Calculate the distance between the animal and its target prey
        double distanceToTarget = Distance.calculateDistance(animal.getGameObject(), targetPrey.getGameObject());
        double targetX, targetY;
        Point2D targetDir;

        // Get the x and y positions of the target prey
        targetX = GameObject.posX(targetPrey.getGameObject()).get();
        targetY = GameObject.posY(targetPrey.getGameObject()).get();

        // Calculate the direction vector pointing from the animal to its target prey
        targetDir = new Point2D(targetX - animal.posX().get(), targetY - animal.posY().get());
        targetDir = targetDir.normalize();

        // Set an offset to make the animal's behavior a bit more nuanced
        double offset = 25;

        // If the distance to the target prey is within the animal's vision range minus
        // the offset
        if (distanceToTarget <= animal.animalStat.visionRange - offset) {
            // Then make the animal move towards the target prey at a reduced speed
            animal.velocity.set(targetDir.multiply(-animal.animalStat.runSpeed / 2 *
                    Time.getDeltaTime()
                    * Parameter.getRelativeSimulationSpeed()));
        }
        // If the distance to the target prey is greater than the animal's vision range
        // plus the offset
        else if (distanceToTarget >= animal.animalStat.visionRange + offset) {
            // Then make the animal move towards the target prey at full speed
            animal.velocity.set(targetDir.multiply(animal.animalStat.runSpeed / 2 * Time.getDeltaTime()
                    * Parameter.getRelativeSimulationSpeed()));
        } // If the distance to the target prey is between the vision range minus the
          // offset and the vision range plus the offset
        else {
            // Then make the animal stop moving
            animal.velocity.set(Point2D.ZERO);
        }
    }

    // get the number of allies count
    public int getNumberOfAllies() {
        return numberOfAllies;
    }

    // toString method is called when this state is printed
    @Override
    public String toString() {
        // Return the string representation of this state
        return super.toString() + "Howl " + alliesPredators.size() + " "
                + groupVision.getAllDetectedObject(Predator.class).size() + currentCoolDownTime;
    }
}