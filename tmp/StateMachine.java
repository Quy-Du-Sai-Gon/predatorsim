package org.quydusaigon.predatorsim;

import javafx.geometry.Point2D;
import org.quydusaigon.predatorsim.gameengine.Component;
import org.quydusaigon.predatorsim.gameengine.GameObject;

public class StateMachine extends Component {
    public State currentState;
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
