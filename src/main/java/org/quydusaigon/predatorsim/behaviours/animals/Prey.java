package org.quydusaigon.predatorsim.behaviours.animals;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.gameengine.component.Collider;
import org.quydusaigon.predatorsim.util.PreyStat;

/**
 * Prey
 */
public class Prey extends Animal {

    public Prey(PreyStat stat) {
        super(stat);
    }

    @Override
    public void onCollisionEnter(Collider<?> collider, Collider<?> other) {
        super.onCollisionEnter(collider, other);
        if(other.getComponent(Predator.class).isPresent()){
            stateConstructor.getDeadState().setKillerAnimal(other.getComponent(Predator.class).get());
            changeState(stateConstructor.getDeadState());
        }
    }
}