package org.quydusaigon.predatorsim.behaviours;

import org.quydusaigon.predatorsim.gameengine.component.Behaviour;

public abstract class State extends Behaviour {
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
}
