package org.quydusaigon.predatorsim.behaviours.states;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.State;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.HuntingAlone;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.SurvivalBehaviour;

public class SurvivalState extends State {
    SurvivalBehaviour survivalBehaviour;
    private boolean noTarget;

    public SurvivalState(Animal animalSM) {
        super(animalSM);
    }

    @Override
    public void enter() {
        survivalBehaviour = animalSM.getSurvivalBehaviour();
        noTarget = false;
    }

    @Override
    public void update() {
        if (!noTarget) {
            survivalBehaviour.doSurvival();
        }
        else {
            animalSM.changeState(animalSM.getStateConstructor().getWanderState());
        }
    }

    public void setNoTarget(boolean value) {
        noTarget = value;
    }
}
