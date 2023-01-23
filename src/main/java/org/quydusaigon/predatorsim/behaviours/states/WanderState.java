package org.quydusaigon.predatorsim.behaviours.states;

import java.util.Optional;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.State;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.Evading;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.Hunting;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.HuntingAlone;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.HuntingInGroup;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.SurvivalBehaviour;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.Vision;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.WanderBehaviour;
import org.quydusaigon.predatorsim.behaviours.animals.Predator;
import org.quydusaigon.predatorsim.behaviours.animals.Prey;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.util.PreySize;
import org.quydusaigon.predatorsim.util.PreyStat;

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
        Group thisObject = animalSM.getGameObject();
        var vision = GameObject.getComponent(GameObject.getChildren(thisObject).get(0), Vision.class).get();

        if (animalSM instanceof Predator) {
            if (vision.getAllDetectedObject(Prey.class).size() != 0) {
                setFoundObject(vision.getClosestObject(Prey.class).get());
                animalSM.getStateConstructor().getSurvivalState().setNoTarget(false);
                animalSM.changeState(animalSM.getStateConstructor().getSurvivalState());
                return;
            }
        }
        else if (animalSM instanceof Prey) {
            if (vision.getAllDetectedObject(Predator.class).size() != 0) {
                setFoundObject(vision.getClosestObject(Predator.class).get());
                animalSM.getStateConstructor().getSurvivalState().setNoTarget(false);
                animalSM.changeState(animalSM.getStateConstructor().getSurvivalState());
                return;
            }
        }
        wanderBehaviour.doAction();
    }

    @Override
    public void exit() {
        SurvivalBehaviour survivalBehavior = animalSM.getSurvivalBehaviour();
        if (survivalBehavior instanceof Hunting) {
            survivalBehavior.setTargetObject(foundObject.get());

            PreyStat preystat = (PreyStat) GameObject.getComponent(foundObject.get(), Prey.class).get().animalStat;
            if (preystat.size == PreySize.SMALL) {
                animalSM.setSurvivalBehaviour(GameObject.getComponent(animalSM.getGameObject(), HuntingAlone.class).get());
            }
            else if ((preystat.size == PreySize.MEDIUM) || (preystat.size == PreySize.LARGE)) {
                animalSM.setSurvivalBehaviour(GameObject.getComponent(animalSM.getGameObject(), HuntingInGroup.class).get());
            }
        }
        foundObject = Optional.empty();
    }

    public void setFoundObject(Group Object) {
        foundObject = Optional.of(Object);
    }
}
