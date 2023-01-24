package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

import java.util.ArrayList;

import javafx.beans.property.DoubleProperty;
import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.animals.Predator;
import org.quydusaigon.predatorsim.gameengine.Time;
import org.quydusaigon.predatorsim.gameengine.component.Component;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.util.Map;

import javafx.geometry.Point2D;
import javafx.scene.Group;

public class Evading extends SurvivalBehaviour {
    DoubleProperty x,y;
    Vision vision;
    double targetX, targetY;
    Point2D targetDir;
    @Override
    public void setUpReference(){
        vision = getComponent(Animal.class).get().getVision();
    }
    @Override
    public void doSurvival() {
        if(vision.getClosestObject(Predator.class).isEmpty()) {
            GameObject.getComponent(getGameObject(), Animal.class).get().getStateConstructor().getSurvivalState().setNoTarget(true);
            return;
        }

        Group targetObject = vision.getClosestObject(Predator.class).get();

        x = posX();
        y = posY();

        targetX = GameObject.getComponent(targetObject, Component.class).get().posX().get();
        targetY = GameObject.getComponent(targetObject, Component.class).get().posX().get();

        targetDir = new Point2D(x.get() - targetX, y.get() - targetY);

        targetDir.normalize();

        x.set(Map.checkBoundX(x.get() + targetDir.getX() * animalStat.runSpeed * Time.getDeltaTime()));
        y.set(Map.checkBoundY(y.get() + targetDir.getY() * animalStat.runSpeed * Time.getDeltaTime()));
        
    }
}
