package org.quydusaigon.predatorsim.behaviours.states;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.State;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.WanderBehaviour;

public class WanderState extends State {
    WanderBehaviour wanderBehaviour;

    public WanderState(Animal animalSM) {
        super(animalSM);
    }

    @Override
    public void enter() {
        wanderBehaviour = animalSM.getWanderBehaviour();
        wanderBehaviour.setSeed();
    }

    @Override
    public void update() {
        wanderBehaviour.doAction();
    }

    @Override
    public void exit() {

    }
}
