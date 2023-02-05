package org.quydusaigon.predatorsim.states;

import org.quydusaigon.Output;
import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.State;
import org.quydusaigon.predatorsim.behaviours.animals.Predator;
import org.quydusaigon.predatorsim.behaviours.animals.Prey;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.util.PreySize;

public class DeadState extends State {
    // Constructor for DeadState class
    public DeadState(Animal animalSM) {
        // Call super class (State) constructor and pass the Animal instance
        super(animalSM);
    }

    // enter method is called when this state is entered
    @Override
    public void enter() {
        // Call the destroy method on the game object associated with the animal
        GameObject.destroy(animal.getGameObject());
        // Check if the animal instance is a Prey
        if (animal instanceof Prey) {
            // Check the size of the prey and increase the corresponding dead count in the
            // Output class
            if (((Prey) animal).preyStat.size == PreySize.SMALL) {
                Output.getInstance().smallPreyDeadCount++;
            } else if (((Prey) animal).preyStat.size == PreySize.MEDIUM) {
                Output.getInstance().mediumPreyDeadCount++;
            } else if (((Prey) animal).preyStat.size == PreySize.LARGE) {
                Output.getInstance().largePreyDeadCount++;
            }
        }
        // If the animal instance is a Predator
        else if (animal instanceof Predator) {
            // Increase the predator dead count in the Output class
            Output.getInstance().predatorDeadCount++;
        }
    }

    // toString method is called when this state is printed
    @Override
    public String toString() {
        // Return the string representation of this state
        return super.toString() + "Dead";
    }

    // update method is called each frame while in this state
    @Override
    public void update() {
        // TODO: Implement update logic here
    }

    // exit method is called when this state is exited
    @Override
    public void exit() {
        // TODO: Implement exit logic here
    }
}
