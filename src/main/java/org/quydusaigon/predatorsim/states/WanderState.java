package org.quydusaigon.predatorsim.states;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.State;

public class WanderState extends State {
    // Constructor for WanderState class
    public WanderState(Animal animalSM) {
        // Call super class (State) constructor and pass the Animal instance
        super(animalSM);
    }

    // Variable holding seeds for Perlin's noise
    protected double seedX;
    protected double seedY;
    // Variable holding noises for Perlin's noise
    protected double noiseX;
    protected double noiseY;

    // Method to set random values for seeds, set noise values equal 0
    private void setSeed() {
        seedX = Math.random() * 100;
        seedY = Math.random() * 100;
        noiseX = 0;
        noiseY = 0;
    }

    // Method to be called when entering this state
    @Override
    public void enter() {
        // call setSeed upon entering WanderState
        setSeed();
    }

    // Method to be called for updating the state
    @Override
    public void update() {

    }

    // Method to be called when exiting this state
    @Override
    public void exit() {

    }

    // toString method is called when this state is printed
    @Override
    public String toString() {
        // Return the string representation of this state
        return super.toString() + "Wander";
    }
}
