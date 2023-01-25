package org.quydusaigon.predatorsim.behaviours;

import org.quydusaigon.predatorsim.behaviours.animalBehaviours.*;
import org.quydusaigon.predatorsim.behaviours.animals.Prey;
import org.quydusaigon.predatorsim.gameengine.component.Component;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.util.AnimalStat;
import org.quydusaigon.predatorsim.util.StateConstructor;

import java.util.ArrayList;
import java.util.List;

public abstract class Animal extends StateMachine {

    protected StateConstructor stateConstructor;
    public AnimalStat animalStat;
    private Vision vision;

    public Vision getVision() {
        return vision;
    }

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

    public StateConstructor getStateConstructor() {
        return stateConstructor;
    }

    public Animal(AnimalStat animalStat) {
        this.animalStat = animalStat;
    }

    @Override
    public void start() {
        stateConstructor = new StateConstructor(this);

        vision = GameObject.getComponent(GameObject.getChildren(getGameObject()).get(0), Vision.class).orElseThrow();

        wanderBehaviour = getComponent(WanderBehaviour.class).orElseThrow();
        survivalBehaviour = getComponent(SurvivalBehaviour.class).orElseThrow();
        deadBehaviour = getComponent(DeadBehaviour.class).orElseThrow();
        setUp();

        initialize(this.stateConstructor.getWanderState());

    }

    private void setUp() {
        getComponents(AnimalBehaviour.class).forEach(
                animal -> animal.setAnimalStat(animalStat));
    }
}
