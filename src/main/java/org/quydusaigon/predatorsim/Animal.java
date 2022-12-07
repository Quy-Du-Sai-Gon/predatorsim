package org.quydusaigon.predatorsim;

public class Animal extends StateMachine {
    public StateConstructor stateConstructor;
    protected AnimalStat animalStat;

    @Override
    public void start() {
        animalStat = new AnimalStat(1, 1);

        stateConstructor = new StateConstructor(this);

        initialize(stateConstructor.wanderingState);
    }
}
