package org.quydusaigon.predatorsim.behaviours;

// Abstract class representing the state of an animal
public abstract class State {
    // Reference to the animal this state is associated with
    protected Animal animal;

    // Constructor for the state
    public State(Animal animal) {
        // Set the animal reference
        this.animal = animal;
    }

    // Method called when entering this state
    abstract public void enter();

    // Method called every update cycle while in this state
    abstract public void update();

    // Method called when exiting this state
    abstract public void exit();

    // Return a string representation of the state
    @Override
    public String toString() {
        return "State: ";
    }
}