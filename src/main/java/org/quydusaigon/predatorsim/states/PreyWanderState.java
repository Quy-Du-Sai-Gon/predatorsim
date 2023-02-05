package org.quydusaigon.predatorsim.states;

import org.quydusaigon.predatorsim.App;
import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.animals.Predator;
import org.quydusaigon.predatorsim.behaviours.animals.Prey;
import org.quydusaigon.predatorsim.gameengine.Time;
import org.quydusaigon.predatorsim.util.Parameter;
import org.quydusaigon.predatorsim.util.PerlinNoise;
import org.quydusaigon.predatorsim.util.PreySize;
import org.quydusaigon.predatorsim.util.PreyStat;

public class PreyWanderState extends WanderState {
    // Constructor for PreyWanderState class
    public PreyWanderState(Animal animal) {
        // Call super class (WanderState) constructor and pass the Animal instance
        super(animal);
    }

    // Override the enter method of the parent class (WanderState)
    @Override
    public void enter() {
        // Call the enter method of the parent class (WanderState)
        super.enter();
    }

    // Method to be called for updating the state
    @Override
    public void update() {
        // Call the doWander method to perform the wandering behavior
        doWander();

        // Check if there is any predator in the detected objects
        if (animal.getVision().getAllDetectedObject(Predator.class).size() != 0) {
            // If there is any predator, change the state of the animal to EvadeState
            animal.changeState(((Prey) animal).getEvadeState());
        }
    }

    // Method to be called when exiting this state
    @Override
    public void exit() {

    }

    // Method to perform the wandering behavior
    private void doWander() {
        // Initialize the variables to store the random values
        double randomX = 0;
        double randomY = 0;

        // Generate the random values using Perlin noise
        noiseX = PerlinNoise.noise(Math.PI, Math.E, seedX);
        noiseY = PerlinNoise.noise(Math.PI, Math.E, seedY);

        // Calculate the randomX and randomY values based on the noise values and the
        // animal's speed
        randomX = noiseX * animal.animalStat.runSpeed * 0.5 * Time.getDeltaTime()
                * Parameter.getRelativeSimulationSpeed();
        randomY = noiseY * animal.animalStat.runSpeed * 0.5 * Time.getDeltaTime()
                * Parameter.getRelativeSimulationSpeed();

        // Adjust the seedX value based on the size of the animal
        if (((PreyStat) animal.animalStat).size == PreySize.SMALL) {
            seedX += 0.007;
        } else if (((PreyStat) animal.animalStat).size == PreySize.MEDIUM) {
            seedX += 0.005;
        } else if (((PreyStat) animal.animalStat).size == PreySize.LARGE) {
            seedX += 0.003;
        }

        // Calculate the offset from the edges of the simulation window
        double mapTendencyOffsetX = Math.abs(Parameter.getWindowWidth() - animal.posX().get());
        double mapTendencyOffsetY = Math.abs(Parameter.getWindowHeight() - animal.posY().get());

        // Adjust the seedX and seedY values based on the offset values
        seedX += 0.005 - mapTendencyOffsetX / 40000;
        seedY += 0.005 - mapTendencyOffsetY / 40000;

        // Update the velocity of the animal with the calculated random values
        animal.velocity.set(randomX, randomY);
    }

    // toString method is called when this state is printed
    @Override
    public String toString() {
        // Return the string representation of this state
        return super.toString() + "Prey Wander";
    }
}
