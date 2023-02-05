package org.quydusaigon.predatorsim.behaviours;

import org.quydusaigon.predatorsim.behaviours.animalBehaviours.*;
import org.quydusaigon.predatorsim.behaviours.util.GridDisplay;
import org.quydusaigon.predatorsim.behaviours.util.Velocity;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.util.AnimalStat;

public abstract class Animal extends StateMachine {
    // Animal class variables
    public AnimalStat animalStat;
    protected Vision vision;
    public Velocity velocity;

    // Getter for the vision variable
    public Vision getVision() {
        return vision;
    }

    // Parameterized constructor
    public Animal(AnimalStat animalStat) {
        this.animalStat = animalStat;
    }

    // Override the start method in the StateMachine class
    @Override
    public void start() {
        // Initialize the vision variable using the GameObject class's getComponent()
        vision = GameObject.getComponent(GameObject.getChildren(getGameObject()).get(0), Vision.class).orElseThrow();

        // Initialize the velocity variable using the GameObject class's getComponent()
        velocity = GameObject.getComponent(getGameObject(), Velocity.class).get();

        addComponent(new GridDisplay());
    }

}
