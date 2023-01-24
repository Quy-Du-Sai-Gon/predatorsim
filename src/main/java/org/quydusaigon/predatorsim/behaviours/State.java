package org.quydusaigon.predatorsim.behaviours;

import org.quydusaigon.predatorsim.gameengine.component.Behaviour;

public abstract class State {
    protected Animal animalSM;

    public State(Animal animalSM) {
        this.animalSM = animalSM;
    }

    public void enter() {

    }

    public void update() {

    }

    public void exit() {

    }

    @Override
    public String toString() {
        return "State: ";
    }
}
