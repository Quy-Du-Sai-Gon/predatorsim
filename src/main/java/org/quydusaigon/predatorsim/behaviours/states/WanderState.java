package org.quydusaigon.predatorsim.behaviours.states;

import java.util.Optional;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.State;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.HuntingAlone;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.HuntingInGroup;
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
    boolean toSurvivalState = false;
    boolean isJoiningGroup = false;

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
        if (animalSM instanceof Predator && detectTarget(Prey.class)) {
            return;
        } else if (animalSM instanceof Prey && detectTarget(Predator.class)) {
            return;
        }

        wanderBehaviour.doWander();
    }

    @Override
    public void exit() {
        if(toSurvivalState) {
            if (animalSM instanceof Predator) {
                PreyStat preystat = (PreyStat) GameObject.getComponent(foundObject.get(), Prey.class).get().animalStat;

                if (preystat.size == PreySize.SMALL) {
                    animalSM.setSurvivalBehaviour(animalSM.getComponent(HuntingAlone.class).orElseThrow());
                } 
                else if (((preystat.size == PreySize.MEDIUM) || (preystat.size == PreySize.LARGE)) && isJoiningGroup == false) {
                    HuntingInGroup temp = animalSM.getComponent(HuntingInGroup.class).orElseThrow();
                    animalSM.setSurvivalBehaviour(temp);
                    temp.setUpHuntingInGroup(3);
                }

                animalSM.getSurvivalBehaviour().setUpReference(foundObject.orElseThrow());
            }
            else if (animalSM instanceof Prey) {
                animalSM.getSurvivalBehaviour().setUpReference();
            }
            toSurvivalState = false;
            foundObject = Optional.empty();
        }
    }

    private <T extends Animal> boolean detectTarget(Class<T> animalType) {
        if (animalSM.getVision().getAllDetectedObject(animalType).size() != 0) {
            toSurvivalState = true;
            setFoundObject(animalSM.getVision().getClosestObject(animalType).get());
            animalSM.getStateConstructor().getSurvivalState().setNoTarget(false);
            animalSM.changeState(animalSM.getStateConstructor().getSurvivalState());
            return true;
        } else
            return false;
    }

    public void setFoundObject(Group Object) {
        foundObject = Optional.of(Object);
    }

    @Override
    public String toString() {
        return super.toString() + "Wander";
    }
}
