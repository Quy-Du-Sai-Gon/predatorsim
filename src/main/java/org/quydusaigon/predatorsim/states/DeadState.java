package org.quydusaigon.predatorsim.states;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.State;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;

public class DeadState extends State {
    public DeadState(Animal animalSM) {
        super(animalSM);
    }

    @Override
    public void enter() {
        GameObject.destroy(animal.getGameObject());
    }

    @Override
    public String toString() {
        return super.toString() + "Dead";
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void exit() {
        // TODO Auto-generated method stub

    }
}
