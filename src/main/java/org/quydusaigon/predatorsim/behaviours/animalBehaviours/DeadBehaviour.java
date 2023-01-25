package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

import org.quydusaigon.predatorsim.behaviours.Animal;

public abstract class DeadBehaviour extends AnimalBehaviour{
    public abstract void doDead(Animal killerAnimal);
}
