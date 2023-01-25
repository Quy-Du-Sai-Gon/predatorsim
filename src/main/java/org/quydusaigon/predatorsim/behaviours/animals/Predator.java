package org.quydusaigon.predatorsim.behaviours.animals;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.WanderBehaviour;
import org.quydusaigon.predatorsim.gameengine.Time;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.util.AnimalStat;
import org.quydusaigon.predatorsim.util.PredatorStat;

/**
 * Predator
 */
public class Predator extends Animal {
    PredatorStat predatorStat;
    public int hungryRate = 1;
    public Predator(PredatorStat stat) {
        super(stat);
    }

    @Override
    public void start() {
        super.start();
        predatorStat = (PredatorStat) animalStat;
    }

    @Override
    public void update() {
        super.update();

        predatorStat.starvationResilience -= Time.getDeltaTime();

        if(predatorStat.starvationResilience <= 0){
            changeState(stateConstructor.getDeadState());
        }
    }

    public void eat(int nutrition){
        predatorStat.starvationResilience += nutrition;
    }
}