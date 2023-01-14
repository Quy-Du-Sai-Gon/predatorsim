package org.quydusaigon.predatorsim.util;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.states.DeadState;
import org.quydusaigon.predatorsim.behaviours.states.HowlState;
import org.quydusaigon.predatorsim.behaviours.states.SurvivalState;
import org.quydusaigon.predatorsim.behaviours.states.WanderState;

public class StateConstructor {
    public WanderState getWanderState() {
        return wanderState;
    }

    public SurvivalState getSurvivalState() {
        return survivalState;
    }

    public HowlState getHowlBehaviour() {
        return howlState;
    }

    public DeadState getDeadState() {
        return deadState;
    }

    private WanderState wanderState;
    private SurvivalState survivalState;
    private HowlState howlState;
    private DeadState deadState;

    public StateConstructor(Animal animal) {
        wanderState = new WanderState(animal);
        howlState = new HowlState(animal);
        survivalState = new SurvivalState(animal);
        deadState = new DeadState(animal);
    }

}
