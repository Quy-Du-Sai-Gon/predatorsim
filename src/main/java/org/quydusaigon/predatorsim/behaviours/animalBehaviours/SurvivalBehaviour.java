package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

import javafx.scene.Group;

public abstract class SurvivalBehaviour extends AnimalBehaviour {

    public abstract void doSurvival();

    public void setUpReference(Group targetObject){};
    public void setUpReference(){};

}
