package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

import javafx.beans.property.DoubleProperty;
import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.animals.Predator;
import org.quydusaigon.predatorsim.behaviours.animals.Prey;
import org.quydusaigon.predatorsim.gameengine.Time;
import org.quydusaigon.predatorsim.gameengine.component.Component;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;

import javafx.geometry.Point2D;
import javafx.scene.Group;

public class HuntingAlone extends Hunting {
    DoubleProperty x, y;
    double targetX, targetY;
    Point2D targetDir;
    public void doSurvival() {
        x = posX();
        y = posY();

        if ((vision.getAllDetectedObject(Prey.class).size() == 0) ||
                (!vision.getAllDetectedObject(Prey.class).contains(targetObject)))
        {
            GameObject.getComponent(getGameObject(), Animal.class).get().getStateConstructor().getSurvivalState()
                                .setNoTarget(true);
                                return;
        }

        targetX = targetComponent.posX().get();
        targetY = targetComponent.posY().get();

        targetDir = new Point2D(targetX - x.get(), targetY - y.get());

        targetDir = targetDir.normalize();

         x.set(x.get() + targetDir.getX() * 50 * animalStat.runSpeed * Time.getDeltaTime());
         y.set(y.get() + targetDir.getY() * 50 * animalStat.runSpeed * Time.getDeltaTime());
        }
}
