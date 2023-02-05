package org.quydusaigon.predatorsim.behaviours;

import org.quydusaigon.predatorsim.gameengine.component.Behaviour;

/**
 * The StateMachine class represents the state machine behaviour of an object.
 * The state machine behaviour is defined as a series of states that can
 * transition
 * from one to another. The state machine is initialized with a starting state
 * and
 * can change to another state through the changeState() method. The current
 * state
 * of the state machine is updated in the update() method.
 */
public class StateMachine extends Behaviour {

    // The current state of the state machine
    private State currentState;

    /**
     * The initialize method sets the starting state for the state machine.
     * The starting state's enter method is called.
     * 
     * @param startingState - the starting state of the state machine.
     */
    public void initialize(State startingState) {
        currentState = startingState;
        currentState.enter();
    }

    /**
     * The changeState method changes the current state of the state machine
     * to the nextState. The exit method of the current state is called before
     * changing to the next state, and the enter method of the next state is
     * called after the change.
     * 
     * @param nextState - the next state of the state machine.
     */
    public void changeState(State nextState) {
        currentState.exit();
        currentState = nextState;
        currentState.enter();
    }

    /**
     * The update method updates the current state of the state machine.
     * The update method of the current state is called.
     */
    @Override
    public void update() {
        currentState.update();
    }

    /**
     * The getCurrentState method returns the current state of the state machine.
     * 
     * @return currentState - the current state of the state machine.
     */
    public State getCurrentState() {
        return this.currentState;
    }
}
