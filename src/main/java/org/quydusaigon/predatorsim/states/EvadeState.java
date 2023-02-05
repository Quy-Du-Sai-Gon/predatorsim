package org.quydusaigon.predatorsim.states;

import java.util.ArrayList;
import java.util.List;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.State;
import org.quydusaigon.predatorsim.behaviours.animals.Predator;
import org.quydusaigon.predatorsim.behaviours.animals.Prey;
import org.quydusaigon.predatorsim.gameengine.Time;
import org.quydusaigon.predatorsim.gameengine.component.Component;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.util.Parameter;

import javafx.beans.property.DoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.Group;

public class EvadeState extends State {
    // Property variables to store the current x and y position of the animal
    DoubleProperty x, y;
    // Variables to store the x and y position of the closest predator
    double targetX, targetY;
    // A Point2D object to store the direction in which the animal should move to
    // evade
    Point2D targetDir;
    // A variable to store the cooldown time after which the state should change to
    // preyWanderState if there are no predators
    double coolDownTime = 1;
    // A variable to keep track of the current cooldown time
    double currentCoolDownTime = 0;
    // A flag to keep track of whether a predator has been found or not
    boolean foundPredator = true;
    // A list to store all the detected predator objects
    List<Group> targetObjects = new ArrayList<Group>();

    // Constructor for EvadeState class
    public EvadeState(Animal animal) {
        // Call super class (State) constructor and pass the Animal instance
        super(animal);
    }

    // Enter method is called when this state is entered
    // Initializes the target direction to (0, 0)
    @Override
    public void enter() {
        targetDir = new Point2D(0, 0);
    }

    /**
     * Method called every frame to update the state
     * Checks if there is a predator in the animal's vision and changes the state
     * accordingly
     */
    @Override
    public void update() {
        if (animal.getVision().getClosestObject(Predator.class).isEmpty() && foundPredator) {
            // If the closest predator is not present and the predator was previously found,
            // set the current cooldown time to the cooldown time and update the
            // foundPredator flag
            currentCoolDownTime = coolDownTime;
            foundPredator = false;
        } else if (animal.getVision().getClosestObject(Predator.class).isPresent() &&
                !foundPredator) {
            // If a predator is present and it was not previously found, set the
            // foundPredator flag to true
            // and set the current cooldown time to 0
            foundPredator = true;
            currentCoolDownTime = 0;
        }

        if (currentCoolDownTime > 0) {
            // If the current cooldown time is greater than 0, decrease it by the delta time
            currentCoolDownTime -= Time.getDeltaTime();
        } else if (currentCoolDownTime <= 0 && !foundPredator) {
            // If the current cooldown time is less than or equal to 0 and no predator is
            // found,
            // change the state to the PreyWanderState
            animal.changeState(((Prey) animal).getPreyWanderState());
            return;
        }

        doEvade(); // Call the doEvade method to update the target direction and velocity of the
                   // animal
    }

    // Method called when the state is exited
    @Override
    public void exit() {

    }

    /**
     * Method to calculate the target direction and velocity of the animal
     */
    private void doEvade() {
        // Check if the closest detected object is an instance of predator and whether
        // it has been found
        // If the closest object is not present and foundPredator is true, set the
        // currentCoolDownTime to coolDownTime and foundPredator to false
        if (animal.getVision().getClosestObject(Predator.class).isEmpty() && foundPredator) {
            currentCoolDownTime = coolDownTime;
            foundPredator = false;
            // If the closest object is present and foundPredator is false, set
            // foundPredator to true and currentCoolDownTime to 0
        } else if (animal.getVision().getClosestObject(Predator.class).isPresent() &&
                !foundPredator) {
            foundPredator = true;
            currentCoolDownTime = 0;
        }

        // Get the current position of the prey
        x = animal.posX();
        y = animal.posY();

        // If a predator has been found, do the following
        if (foundPredator) {
            // Get all the objects detected by the animal's vision that are instances of
            // predator
            targetObjects = animal.getVision().getAllDetectedObject(Predator.class).stream().toList();
            Point2D res = Point2D.ZERO;
            double minLen = Double.MAX_VALUE;

            // Loop through all the target objects
            for (int i = 0; i < targetObjects.size(); i++) {
                // Get the position of the current target object
                targetX = GameObject.getComponent(targetObjects.get(i),
                        Component.class).get().posX().get();
                targetY = GameObject.getComponent(targetObjects.get(i),
                        Component.class).get().posY().get();

                // Calculate the direction vector from the prey to the current target object
                Point2D tempDir = new Point2D(x.get() - targetX, y.get() - targetY);

                // Calculate the magnitude of the direction vector
                double len = tempDir.magnitude();
                // Keep track of the minimum magnitude of all the direction vectors
                if (minLen > len) {
                    minLen = len;
                }

                // Add the direction vector to the result, scaled by 1/(len * len)
                res = res.add(tempDir.multiply(1 / (len * len)));

                // Scale the result by the minimum magnitude
                res = res.multiply(minLen);

                // Set the target direction to the result
                targetDir = res;
            }

            // Normalize the target direction vector
            targetDir = targetDir.normalize();

        }

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
        return super.toString() + "Evade";
    }
}
