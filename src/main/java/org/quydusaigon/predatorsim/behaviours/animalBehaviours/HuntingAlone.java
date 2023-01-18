package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

public class HuntingAlone extends Hunting {

    public void doSurvival() {
        var x = posX();
        var y = posY();

        x.set(x.get() + Math.random());
        y.set(y.get() + Math.random());
    }
}
