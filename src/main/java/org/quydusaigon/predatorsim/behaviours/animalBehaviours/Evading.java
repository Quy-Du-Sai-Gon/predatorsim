package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javafx.beans.property.DoubleProperty;
import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.animals.Predator;
import org.quydusaigon.predatorsim.behaviours.util.Velocity;
import org.quydusaigon.predatorsim.gameengine.Time;
import org.quydusaigon.predatorsim.gameengine.component.Component;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.util.Map;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import org.quydusaigon.predatorsim.util.Parameter;

public class Evading extends SurvivalBehaviour {
    DoubleProperty x, y;
    Vision vision;
    double targetX, targetY;
    Point2D targetDir;
    double coolDownTime = 1;
    double currentCoolDownTime = 0;
    boolean foundPredator = true;
    List<Group> targetObjects = new ArrayList<Group>();

    private Velocity velocity;

    @Override
    public void start() {
        velocity = getComponent(Velocity.class).orElseThrow();
    }

    @Override
    public void setUpReference() {
        vision = getComponent(Animal.class).get().getVision();
        foundPredator = true;
    }

    @Override
    public void doSurvival() {
        if (vision.getClosestObject(Predator.class).isEmpty() && foundPredator) {
            currentCoolDownTime = coolDownTime;
            foundPredator = false;
        } else if (vision.getClosestObject(Predator.class).isPresent() && !foundPredator) {
            foundPredator = true;
            currentCoolDownTime = 0;
        }

        if (currentCoolDownTime > 0) {
            currentCoolDownTime -= Time.getDeltaTime();
        } else if (currentCoolDownTime <= 0 && !foundPredator) {
            GameObject.getComponent(getGameObject(), Animal.class).get().getStateConstructor().getSurvivalState()
                    .setNoTarget(true);
            return;
        }

        x = posX();
        y = posY();

        if (foundPredator) {
            targetObjects = vision.getAllDetectedObject(Predator.class).stream().toList();
            Point2D res = Point2D.ZERO;
            double minLen = Double.MAX_VALUE;

            for (int i = 0; i < targetObjects.size(); i++) {
                targetX = GameObject.getComponent(targetObjects.get(i), Component.class).get().posX().get();
                targetY = GameObject.getComponent(targetObjects.get(i), Component.class).get().posY().get();

                Point2D tempDir = new Point2D(x.get() - targetX, y.get() - targetY);

                double len = tempDir.magnitude();
                if (minLen > len) {
                    minLen = len;
                }

                res = res.add(tempDir.multiply(1 / (len * len)));

                res = res.multiply(minLen);

                targetDir = res;
            }

            targetDir = targetDir.normalize();

        }

        velocity.set(targetDir.multiply(animalStat.runSpeed * Time.getDeltaTime()
                * Parameter.getRelativeSimulationSpeed()));
    }

    @Override
    public String toString() {
        return "Evading" + targetDir + "/n" + x.get() + "/n" + y.get();
    }
}
