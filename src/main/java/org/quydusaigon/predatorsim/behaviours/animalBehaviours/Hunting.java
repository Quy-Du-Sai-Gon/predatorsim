package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

import javafx.scene.Group;

public class Hunting extends SurvivalBehaviour {
    protected Group targetObject;

    @Override
    public void doSurvival() {

    }

    @Override
        public void setTargetObject(Group object) {
                targetObject = object;
        }
}
