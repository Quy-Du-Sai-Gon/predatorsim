package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

import java.util.ArrayList;

import javafx.scene.Group;

public class HuntingInGroup extends Hunting {
    private ArrayList<Group> alliesObjects;

    public void doSurvival() {
        var x = posX();
        var y = posY();
    }

    @Override
    public void setTargetObject(Group object) {
        targetObject = object;
    }

    @Override
    public void setAlliesObject(Group object) {
        alliesObjects.add(object);
    }
}
