package org.quydusaigon.predatorsim.states;

import java.util.ArrayList;
import java.util.List;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.State;
import org.quydusaigon.predatorsim.behaviours.animals.Predator;
import org.quydusaigon.predatorsim.behaviours.animals.Prey;
import org.quydusaigon.predatorsim.gameengine.Time;
import org.quydusaigon.predatorsim.gameengine.component.Component;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.util.Parameter;

import javafx.beans.property.DoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.Group;

public class EvadeState extends State {
    DoubleProperty x, y;
    double targetX, targetY;
    Point2D targetDir;
    double coolDownTime = 1;
    double currentCoolDownTime = 0;
    boolean foundPredator = true;

    List<Group> targetObjects = new ArrayList<Group>();

    public EvadeState(Animal animal) {
        super(animal);
    }

    @Override
    public void enter() {
        targetDir = new Point2D(0, 0);
    }

    @Override
    public void update() {
        if (animal.getVision().getClosestObject(Predator.class).isEmpty() && foundPredator) {
            currentCoolDownTime = coolDownTime;
            foundPredator = false;
        } else if (animal.getVision().getClosestObject(Predator.class).isPresent() &&
                !foundPredator) {
            foundPredator = true;
            currentCoolDownTime = 0;
        }

        if (currentCoolDownTime > 0) {
            currentCoolDownTime -= Time.getDeltaTime();
        } else if (currentCoolDownTime <= 0 && !foundPredator) {
            animal.changeState(((Prey) animal).getPreyWanderState());
            return;
        }

        doEvade();
    }

    @Override
    public void exit() {

    }

    private void doEvade() {
        if (animal.getVision().getClosestObject(Predator.class).isEmpty() && foundPredator) {
            currentCoolDownTime = coolDownTime;
            foundPredator = false;
        } else if (animal.getVision().getClosestObject(Predator.class).isPresent() &&
                !foundPredator) {
            foundPredator = true;
            currentCoolDownTime = 0;
        }

        x = animal.posX();
        y = animal.posY();

        if (foundPredator) {
            targetObjects = animal.getVision().getAllDetectedObject(Predator.class).stream().toList();
            Point2D res = Point2D.ZERO;
            double minLen = Double.MAX_VALUE;

            for (int i = 0; i < targetObjects.size(); i++) {
                targetX = GameObject.getComponent(targetObjects.get(i),
                        Component.class).get().posX().get();
                targetY = GameObject.getComponent(targetObjects.get(i),
                        Component.class).get().posY().get();

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

        animal.velocity.set(targetDir.multiply(animal.animalStat.runSpeed * Time.getDeltaTime()
                * Parameter.getRelativeSimulationSpeed()));
    }

    @Override
    public String toString() {
        return super.toString() + "Evade";
    }
}
