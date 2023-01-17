package org.quydusaigon.predatorsim.behaviours;

import org.quydusaigon.predatorsim.behaviours.animalBehaviours.DeadBehaviour;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.HowlBehaviour;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.SurvivalBehaviour;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.WanderBehaviour;
import org.quydusaigon.predatorsim.util.AnimalStat;
import org.quydusaigon.predatorsim.util.StateConstructor;

public abstract class Animal extends StateMachine {

    protected StateConstructor stateConstructor;
    public AnimalStat animalStat;

    public WanderBehaviour getWanderBehaviour() {
        return wanderBehaviour;
    }

    public HowlBehaviour getHowlBehaviour() {
        return howlBehaviour;
    }

    public SurvivalBehaviour getSurvivalBehaviour() {
        return survivalBehaviour;
    }

    public DeadBehaviour getDeadBehaviour() {
        return deadBehaviour;
    }

    protected WanderBehaviour wanderBehaviour;
    protected HowlBehaviour howlBehaviour;
    protected SurvivalBehaviour survivalBehaviour;
    protected DeadBehaviour deadBehaviour;

    public Animal(AnimalStat animalStat) {
        this.animalStat = animalStat;
    }

    @Override
    public void start() {
        stateConstructor = new StateConstructor(this);

        wanderBehaviour = getComponent(WanderBehaviour.class).orElseThrow();
        wanderBehaviour.setAnimalStat(animalStat);

        initialize(this.stateConstructor.getWanderState());
    }
}
