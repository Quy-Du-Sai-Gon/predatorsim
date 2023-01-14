package org.quydusaigon.predatorsim.behaviours;

import org.quydusaigon.predatorsim.behaviours.animalBehaviours.AnimalBehaviour;
import org.quydusaigon.predatorsim.gameengine.component.Behaviour;

public abstract class State extends Behaviour {
    protected Animal animalSM;
    protected AnimalBehaviour animalBehaviour;
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
