package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

import java.util.ArrayList;

import javafx.scene.Group;

public class Evading extends SurvivalBehaviour {
    private ArrayList<Group> targetObject;

    @Override
    public void doSurvival() {
        var thisX = posX();
        var thisY = posY();
    }

    @Override
    public void setTargetObject(Group object) {
        targetObject.add(object);
    }
}
