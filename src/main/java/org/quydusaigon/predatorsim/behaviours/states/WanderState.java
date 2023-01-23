package org.quydusaigon.predatorsim.behaviours.states;

import java.util.Optional;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.State;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.Evading;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.Hunting;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.HuntingAlone;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.SurvivalBehaviour;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.WanderBehaviour;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;

import javafx.scene.Group;

public class WanderState extends State {
    WanderBehaviour wanderBehaviour;
    private Optional<Group> foundObject;

    public WanderState(Animal animalSM) {
        super(animalSM);
    }

    @Override
    public void enter() {
        wanderBehaviour = animalSM.getWanderBehaviour();
        wanderBehaviour.setSeed();
        foundObject = Optional.empty();
    }

    @Override
    public void update() {
        if (foundObject.isPresent()) {
            animalSM.getStateConstructor().getSurvivalState().setNoTarget(false);
            animalSM.changeState(animalSM.getStateConstructor().getSurvivalState());
            return;
        }

        wanderBehaviour.doAction();
    }

    @Override
    public void exit() {
        SurvivalBehaviour survivalBehavior = animalSM.getSurvivalBehaviour();
        if (survivalBehavior instanceof Hunting)
            survivalBehavior.setTargetObject(foundObject.get());
        else if (survivalBehavior instanceof Evading) {
            
        }
        foundObject = Optional.empty();
    }

    public void setFoundObject(Group Object) {
        foundObject = Optional.of(Object);
    }
}
