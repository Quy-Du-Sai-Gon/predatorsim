package org.quydusaigon.predatorsim.behaviours.states;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.State;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.HuntingAlone;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.SurvivalBehaviour;

public class SurvivalState extends State {

    SurvivalBehaviour huntingAlone;
    public SurvivalState(Animal animalSM) {
        super(animalSM);
    }

    @Override
    public void enter() {
        huntingAlone = animalSM.getSurvivalBehaviour();
    }

    @Override
    public void update() {
        huntingAlone.doSurvival();
    }
}
