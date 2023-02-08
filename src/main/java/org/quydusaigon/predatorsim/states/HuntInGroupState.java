package org.quydusaigon.predatorsim.states;

import java.util.Set;

import org.quydusaigon.Output;
import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.State;
import org.quydusaigon.predatorsim.behaviours.animals.Predator;
import org.quydusaigon.predatorsim.behaviours.animals.Prey;
import org.quydusaigon.predatorsim.gameengine.Time;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.util.Parameter;
import org.quydusaigon.predatorsim.util.PreyStat;

import javafx.geometry.Point2D;

public class HuntInGroupState extends State {
    // variable to store the x and y position of the target prey
    double targetX, targetY;

    // variable to store the direction vector towards the target prey
    Point2D targetDir;

    // variable to store the target prey for hunting
    Prey targetPrey;

    // Stores a set of allied predators in the hunt
    private Set<Predator> alliesPredators;

    // Constructor for HuntInGroup class
    public HuntInGroupState(Animal animal) {
        // Call super class (State) constructor and pass the Animal instance
        super(animal);
    }

    // Method to be called when entering this state
    @Override
    public void enter() {
    }

    // Method to be called for updating the state
    @Override
    public void update() {
        // If the target prey is null or has been deleted from the simulation
        if (targetPrey == null || targetPrey.getGameObject() == null) {
            // Change the state of the predator to the predatorWanderState
            animal.changeState(((Predator) animal).getPredatorWanderState());
            // Return from the method
            return;
        }

        // Do the hunting in group’s behavivors
        doHuntInGroup();
    }

    // Method to be called when exiting this state
    @Override
    public void exit() {
        // Clear the set of allies predators
        alliesPredators.clear();
    }

    // Method to perform the hunting process and get food
    public void getFood() {
        // Generate a random number between 0 and 100, to represent the chance of prey
        // defense success
        double preyDefenseChance = Math.random() * 100;

        // If the prey defense chance is less than or equal to the prey's defense stat
        if (preyDefenseChance <= targetPrey.preyStat.defense) {
            // Remove the predator from the set of allies predators
            alliesPredators.remove(animal);
        }

        // Calculate the nutrition gained by the predator based on the prey's nutrition
        // stat divided by the number of allies predators
        double nutrition = ((PreyStat) targetPrey.animalStat).nutrition / alliesPredators.size();

        // Loop through each ally predator
        for (Predator allyPredator : alliesPredators) {
            // Increment the ally predator's starvation resilience by the nutrition gained
            allyPredator.predatorStat.starvationResilience += nutrition;
            // Increment the output simulation's nutrition gained by the nutrition gained
            Output.getInstance().nutritionGained += nutrition;
        }

        // If the prey defense chance is less than or equal to the prey's defense stat
        if (preyDefenseChance <= targetPrey.preyStat.defense) {
            // Change the state of the predator to the dead state
            animal.changeState(((Predator) animal).getDeadState());
        }
    }

    // Method to sets the target prey and the set of allies predators
    // for the hunt
    public void setTargetPrey(Prey prey, Set<Predator> alliesPredators) {
        targetPrey = prey;
        this.alliesPredators = alliesPredators;
    }

    void doHuntInGroup() {
        // get the x and y position of the target prey
        targetX = GameObject.posX(targetPrey.getGameObject()).get();
        targetY = GameObject.posY(targetPrey.getGameObject()).get();

        // get the direction from the predator to the targetPrey
        targetDir = new Point2D(targetX - animal.posX().get(), targetY - animal.posY().get());

        // Normalize the vector
        targetDir = targetDir.normalize();

        // Set the animal's velocity as the target direction vector multiplied by the
        // animal's run speed,
        // delta time, and relative simulation speed.
        animal.velocity.set(targetDir.multiply(animal.animalStat.runSpeed * Time.getDeltaTime()
                * Parameter.getRelativeSimulationSpeed()));
    }

    // get allies predator
    public Set<Predator> getAlliesPredator() {
        return this.alliesPredators;
    }

    // toString method is called when this state is printed
    @Override
    public String toString() {
        // Return the string representation of this state
        return super.toString() + "Leader - Hunt In Group of " + alliesPredators.size();
    }
}
