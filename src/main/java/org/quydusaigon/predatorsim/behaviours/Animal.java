package org.quydusaigon.predatorsim.behaviours;

import org.quydusaigon.predatorsim.behaviours.animalBehaviours.*;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
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

    public void setWanderBehaviour(WanderBehaviour wanderBehaviour) {
        this.wanderBehaviour = wanderBehaviour;
    }

    public void setHowlBehaviour(HowlBehaviour howlBehaviour) {
        this.howlBehaviour = howlBehaviour;
    }

    public void setSurvivalBehaviour(SurvivalBehaviour survivalBehaviour) {
        this.survivalBehaviour = survivalBehaviour;
    }

    public void setDeadBehaviour(DeadBehaviour deadBehaviour) {
        this.deadBehaviour = deadBehaviour;
    }

    protected WanderBehaviour wanderBehaviour;
    protected HowlBehaviour howlBehaviour;
    protected SurvivalBehaviour survivalBehaviour;
    protected DeadBehaviour deadBehaviour;
    public StateConstructor getStateConstructor(){return stateConstructor;}

    public Animal(AnimalStat animalStat) {
        this.animalStat = animalStat;
    }

    @Override
    public void start() {
        stateConstructor = new StateConstructor(this);

        wanderBehaviour = getComponent(WanderBehaviour.class).orElseThrow();
        wanderBehaviour.setAnimalStat(animalStat);

        survivalBehaviour = getComponent(SurvivalBehaviour.class).orElseThrow();
        survivalBehaviour.setAnimalStat(animalStat);

        initialize(this.stateConstructor.getWanderState());
    }
}
