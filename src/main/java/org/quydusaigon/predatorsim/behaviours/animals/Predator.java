package org.quydusaigon.predatorsim.behaviours.animals;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.WanderBehaviour;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.util.AnimalStat;
import org.quydusaigon.predatorsim.util.PredatorStat;

/**
 * Predator
 */
public class Predator extends Animal {

    public Predator(PredatorStat stat) {
        super(stat);
    }

}