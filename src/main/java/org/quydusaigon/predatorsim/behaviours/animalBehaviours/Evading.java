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
    double coolDownTime = 1;
    double currentCoolDownTime = 0;
    boolean foundPredator = true;
    Group targetObject;
    @Override
    public void setUpReference(){
        vision = getComponent(Animal.class).get().getVision();
        foundPredator = true;
    }
    @Override
    public void doSurvival() {
        if(vision.getClosestObject(Predator.class).isEmpty() && foundPredator) {
            currentCoolDownTime = coolDownTime;
            foundPredator = false;
        }
        else if(vision.getClosestObject(Predator.class).isPresent() && !foundPredator){
            foundPredator = true;
            currentCoolDownTime = 0;
        }

        if(currentCoolDownTime > 0){
            currentCoolDownTime -= Time.getDeltaTime();
        }
        else if(currentCoolDownTime <= 0 && !foundPredator){
            GameObject.getComponent(getGameObject(), Animal.class).get().getStateConstructor().getSurvivalState().setNoTarget(true);
            return;
        }

        x = posX();
        y = posY();

        if(foundPredator) {
            targetObject = vision.getClosestObject(Predator.class).get();
            targetX = GameObject.getComponent(targetObject, Component.class).get().posX().get();
            targetY = GameObject.getComponent(targetObject, Component.class).get().posX().get();
        }


        targetDir = new Point2D(x.get() - targetX, y.get() - targetY);

        targetDir = targetDir.normalize();

        x.set(Map.checkBoundX(x.get() + targetDir.getX() * 50 * animalStat.runSpeed * Time.getDeltaTime()));
        y.set(Map.checkBoundY(y.get() + targetDir.getY() * 50 * animalStat.runSpeed * Time.getDeltaTime()));
    }

    private void escape(){

    }
}
