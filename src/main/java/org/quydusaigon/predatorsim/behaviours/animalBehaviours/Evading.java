package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

import java.util.ArrayList;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.animals.Predator;
import org.quydusaigon.predatorsim.gameengine.Time;
import org.quydusaigon.predatorsim.gameengine.component.Component;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.util.Map;

import javafx.geometry.Point2D;
import javafx.scene.Group;

public class Evading extends SurvivalBehaviour {

    @Override
    public void doSurvival() {
        var x = posX();
        var y = posY();
        
        Group thisObject = getGameObject();
        Vision vision = GameObject.getComponent(GameObject.getChildren(thisObject).get(0), Vision.class).get();

        if(vision.getClosestObject(Predator.class).isEmpty()) {
            GameObject.getComponent(thisObject, Animal.class).get().getStateConstructor().getSurvivalState().setNoTarget(true);
            return;
        }
        else {
            Group targetObject = vision.getClosestObject(Predator.class).get();
            double targetX = GameObject.getComponent(targetObject,
                                Component.class).get().posX().get();
            double targetY = GameObject.getComponent(targetObject,
                                Component.class).get().posY().get();

            Point2D Vector = new Point2D(x.get() - targetX, y.get() - targetY);

            Vector.normalize();
                
            x.set(Map.checkBoundX(x.get() + Vector.getX() * animalStat.runSpeed * Time.getDeltaTime()));
            y.set(Map.checkBoundY(y.get() + Vector.getY() * animalStat.runSpeed * Time.getDeltaTime()));
        }
        
    }
}
