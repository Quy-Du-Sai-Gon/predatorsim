package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

import javafx.scene.Group;
import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.gameengine.component.Component;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;

public class Hunting extends SurvivalBehaviour {
    protected Group targetObject;
    protected Component targetComponent;
    protected Vision vision;

    @Override
    public void doSurvival() {

    }

    @Override
    public void setUpReference(Group targetObject){
        this.targetObject = targetObject;
        targetComponent = GameObject.getComponent(targetObject, Component.class).get();
        vision = getComponent(Animal.class).get().getVision();
    }
}
