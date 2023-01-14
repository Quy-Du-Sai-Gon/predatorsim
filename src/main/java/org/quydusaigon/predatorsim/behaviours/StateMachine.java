package org.quydusaigon.predatorsim.behaviours;

import org.quydusaigon.predatorsim.gameengine.component.Behaviour;

public class StateMachine extends Behaviour {

    private State currentState;

    public void initialize(State startingState) {
        currentState = startingState;
        currentState.enter();
    }

    public void changeState(State nextState) {
        currentState.exit();

        currentState = nextState;

        currentState.enter();
    }

    @Override
    public void update() {
        currentState.update();
    }
}
