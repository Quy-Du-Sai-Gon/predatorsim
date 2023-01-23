package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

import org.quydusaigon.predatorsim.behaviours.animals.Predator;
import org.quydusaigon.predatorsim.behaviours.animals.Prey;
import org.quydusaigon.predatorsim.gameengine.Time;
import org.quydusaigon.predatorsim.gameengine.component.Component;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;

import javafx.geometry.Point2D;
import javafx.scene.Group;

public class HuntingAlone extends Hunting {

        public void doSurvival() {
                var x = posX();
                var y = posY();

                Group thisObject = getGameObject();

                double thisX = GameObject.getComponent(thisObject,
                                Component.class).get().posX().get();
                double thisY = GameObject.getComponent(thisObject,
                                Component.class).get().posY().get();

                double targetX = GameObject.getComponent(targetObject,
                                Component.class).get().posX().get();
                double targetY = GameObject.getComponent(targetObject,
                                Component.class).get().posY().get();

                Point2D Vector = new Point2D(targetX - thisX, targetY - thisY);

                Vector.normalize();

                x.set(x.get() + Vector.getX() * animalStat.runSpeed * Time.getDeltaTime());
                y.set(y.get() + Vector.getY() * animalStat.runSpeed * Time.getDeltaTime());
        }
}
