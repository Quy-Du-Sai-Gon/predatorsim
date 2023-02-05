package org.quydusaigon.predatorsim.states;

import org.quydusaigon.Output;
import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.State;
import org.quydusaigon.predatorsim.behaviours.animals.Predator;
import org.quydusaigon.predatorsim.behaviours.animals.Prey;
import org.quydusaigon.predatorsim.gameengine.Time;
import org.quydusaigon.predatorsim.util.Parameter;
import org.quydusaigon.predatorsim.util.PredatorStat;
import org.quydusaigon.predatorsim.util.PreyStat;

import javafx.beans.property.DoubleProperty;
import javafx.geometry.Point2D;

public class HuntAloneState extends State {
    // variable to store the target prey for hunting
    Prey targetPrey;

    // variable to store the x and y position of the predator
    DoubleProperty x, y;

    // variable to store the x and y position of the target prey
    double targetX, targetY;

    // variable to store the direction vector towards the target prey
    Point2D targetDir;

    /**
     * Method to set the target prey for the predator
     * 
     * @param targetPrey the target prey to hunt
     */
    public void setTargetPrey(Prey targetPrey) {
        this.targetPrey = targetPrey;
    }

    // Constructor for HuntAloneState class
    public HuntAloneState(Animal animalSM) {
        // Call super class (State) constructor and pass the Animal instance
        super(animalSM);
    }

    // Method to be called when entering this state
    @Override
    public void enter() {
    }

    // Method to be called for updating the state
    @Override
    public void update() {
        // check if the target prey is still within the predator's vision
        if ((animal.getVision().getAllDetectedObject(Prey.class).size() == 0) ||
                (!animal.getVision().getAllDetectedObject(Prey.class).contains(targetPrey.getGameObject()))) {
            // if the target prey is no longer in the predator's vision, change state to
            // predator wander state
            animal.changeState(((Predator) animal).getPredatorWanderState());
            return;
        }

        // call method to perform hunting alone
        doHuntAlone();
    }

    // Method to be called when exiting this state
    @Override
    public void exit() {
        // set the target prey to null when exiting this state
        targetPrey = null;
    }

    // Method to perform the hunting process and get food
    public void getFood() {
        // chance for prey to defend itself and kill the predator
        double preyDefenseChance = Math.random() * 100;

        if (preyDefenseChance <= targetPrey.preyStat.defense) {
            // if the prey defends itself successfully, change state of predator to dead
            // state
            animal.changeState(((Predator) animal).getDeadState());
            return;
        }

        // if the prey is successfully hunted, increase the predator's starvation
        // resilience by the prey's nutrition value
        Double nutrition = ((PreyStat) targetPrey.animalStat).nutrition;
        ((PredatorStat) animal.animalStat).starvationResilience += nutrition;
        Output.getInstance().nutritionGained += nutrition;
    }

    // Method to perform the hunting alone process
    private void doHuntAlone() {
        // get the current x and y position of the predator
        x = animal.posX();
        y = animal.posY();

        // get the x and y position of the target prey
        targetX = targetPrey.posX().get();
        targetY = targetPrey.posY().get();

        // get the direction from the predator to the targetPrey
        targetDir = new Point2D(targetX - x.get(), targetY - y.get()).normalize();

        // Set the animal's velocity as the target direction vector multiplied by the
        // animal's run speed,
        // delta time, and relative simulation speed.
        animal.velocity.set(targetDir.multiply(animal.animalStat.runSpeed * Time.getDeltaTime()
                * Parameter.getRelativeSimulationSpeed()));
    }

    // toString method is called when this state is printed
    @Override
    public String toString() {
        // Return the string representation of this state
        return super.toString() + "Hunt Alone";
    }
}
