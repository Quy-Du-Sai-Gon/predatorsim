package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

import java.util.ArrayList;

import javafx.scene.Group;

public abstract class SurvivalBehaviour extends AnimalBehaviour {

    @Override
    void doAction() {
        doSurvival();
    }

    public abstract void doSurvival();

    public void setTargetObject(Group object) {
    }

    public void setAlliesObject(Group objects) {
    }
}
