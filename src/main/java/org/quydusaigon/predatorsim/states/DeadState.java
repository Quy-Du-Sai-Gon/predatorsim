package org.quydusaigon.predatorsim.states;

import org.quydusaigon.Output;
import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.State;
import org.quydusaigon.predatorsim.behaviours.animals.Predator;
import org.quydusaigon.predatorsim.behaviours.animals.Prey;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.util.PreySize;

public class DeadState extends State {
    public DeadState(Animal animalSM) {
        super(animalSM);
    }

    @Override
    public void enter() {
        GameObject.destroy(animal.getGameObject());
        if (animal instanceof Prey) {
            if (((Prey) animal).preyStat.size == PreySize.SMALL) {
                Output.getInstance().smallPreyDeadCount++;
            } else if (((Prey) animal).preyStat.size == PreySize.MEDIUM) {
                Output.getInstance().mediumPreyDeadCount++;
            } else if (((Prey) animal).preyStat.size == PreySize.LARGE) {
                Output.getInstance().largePreyDeadCount++;
            }
        } else if (animal instanceof Predator) {
            Output.getInstance().predatorDeadCount++;
        }
    }

    @Override
    public String toString() {
        return super.toString() + "Dead";
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void exit() {
        // TODO Auto-generated method stub

    }
}
