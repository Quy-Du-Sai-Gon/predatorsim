package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

import org.quydusaigon.predatorsim.behaviours.animals.Predator;
import org.quydusaigon.predatorsim.gameengine.component.Collider;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;

public class HowlVision extends Vision {
    Predator predator;

    @Override
    public void onCollisionEnter(Collider<?> collider, Collider<?> other) {
        super.onCollisionEnter(collider, other);

        if (getClosestWanderingObject(Predator.class).isPresent()) {
            Predator otherPredator = GameObject
                    .getComponent(getClosestWanderingObject(Predator.class).get(), Predator.class).get();
            if (predator.getHowlState().getCurrentNumberOfAllies() != predator.getHowlState()
                    .getNumberOfAllies()) {
                otherPredator.getJoinState().setTargetJoin(predator, predator.getHowlState().getTargetPrey());
                otherPredator.changeState(otherPredator.getJoinState());
                predator.getHowlState().addAlly(otherPredator);
            }
        }
    }

    public void SetUpHowlVision(Predator predator) {
        this.predator = predator;
    }
}
