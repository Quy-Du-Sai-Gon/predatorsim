package org.quydusaigon.predatorsim;

public class StateConstructor {
    public WanderingState wanderingState;
    public SurvivalState survivalState;

    public StateConstructor(Animal animalSM) {
        wanderingState = new WanderingState(animalSM);
        survivalState = new SurvivalState(animalSM);
    }
}
