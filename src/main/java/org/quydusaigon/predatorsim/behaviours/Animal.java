package org.quydusaigon.predatorsim.behaviours;

import org.quydusaigon.predatorsim.behaviours.animalBehaviours.*;
import org.quydusaigon.predatorsim.behaviours.util.GridDisplay;
import org.quydusaigon.predatorsim.behaviours.util.Velocity;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.util.AnimalStat;

public abstract class Animal extends StateMachine {
    public AnimalStat animalStat;
    protected Vision vision;
    public Velocity velocity;

    public Vision getVision() {
        return vision;
    }

    public Animal(AnimalStat animalStat) {
        this.animalStat = animalStat;
    }

    @Override
    public void start() {
        vision = GameObject.getComponent(GameObject.getChildren(getGameObject()).get(0), Vision.class).orElseThrow();
        velocity = GameObject.getComponent(getGameObject(), Velocity.class).get();

        addComponent(new GridDisplay());
    }
}
