package org.quydusaigon.predatorsim.behaviours.states;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.State;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;

public class DeadState extends State {
    public DeadState(Animal animalSM) {
        super(animalSM);
    }

    @Override
    public void enter() {
        // super.enter();
        // System.out.println("Dead");
        // System.out.println(animalSM.getGameObject());
        // GameObject.destroy(animalSM.getGameObject());
    }

    @Override
    public String toString() {
        return super.toString() + "Dead";
    }
}
