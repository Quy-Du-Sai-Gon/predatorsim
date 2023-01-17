package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;

import org.quydusaigon.predatorsim.behaviours.animals.Predator;
import org.quydusaigon.predatorsim.behaviours.animals.Prey;
import org.quydusaigon.predatorsim.gameengine.component.Behaviour;
import org.quydusaigon.predatorsim.gameengine.component.Collider;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.util.Distance;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Vision extends Behaviour {

    HashSet<Group> detectedGameObject;

    @Override
    public void start() {
        detectedGameObject = new HashSet<>();
    }

    @Override
    public void onCollisionEnter(Collider<?> collider, Collider<?> other) {
        if (GameObject.getParent(getGameObject()).get() != other.getGameObject()
                & GameObject.getComponent(other.getGameObject(), Vision.class).isEmpty()) {
            var particle = (Circle) other.getNode();
            particle.setStroke(Color.BLACK);
            particle.setStrokeWidth(5);
            detectedGameObject.add(other.getGameObject());
        }
    }

    @Override
    public void onCollisionExit(Collider<?> collider, Collider<?> other) {
        if (GameObject.getParent(getGameObject()).get() != other.getGameObject()
                & GameObject.getComponent(other.getGameObject(), Vision.class).isEmpty()) {
            var particle = (Circle) other.getNode();
            particle.setStroke(null);
            detectedGameObject.remove(other.getGameObject());
        }
    }

    public HashSet<Group> getAllDetectedPredator() {
        HashSet<Group> detectedPredator = new HashSet<Group>();
        Iterator<Group> iter = detectedGameObject.iterator();

        while (iter.hasNext()) {
            Group current = iter.next();
            if (GameObject.getComponent(current, Predator.class).isPresent()) {
                detectedPredator.add(current);
            }
        }
        return detectedPredator;
    }

    public HashSet<Group> getAllDetectedPrey() {
        HashSet<Group> detectedPrey = new HashSet<Group>();
        Iterator<Group> iter = detectedGameObject.iterator();

        while (iter.hasNext()) {
            Group current = iter.next();
            if (GameObject.getComponent(current, Prey.class).isPresent()) {
                detectedPrey.add(current);
            }
        }
        return detectedPrey;
    }

    public Optional<Group> getClosestPredator() {
        HashSet<Group> detectedPredator = getAllDetectedPredator();
        Iterator<Group> iter = detectedPredator.iterator();

        if (!detectedPredator.isEmpty()) {
            double closestDistance = 0;
            Group closestPredator = new Group();

            while (iter.hasNext()) {
                Group current = iter.next();
                if (Distance.calculateDistance(current,
                        GameObject.getParent(getGameObject()).get()) >= closestDistance) {
                    closestPredator = current;
                    closestDistance = Distance.calculateDistance(current, GameObject.getParent(getGameObject()).get());
                }
            }
            return Optional.of(closestPredator);
        }
        return Optional.empty();
    }

    public Optional<Group> getClosestPrey() {
        HashSet<Group> detectedPrey = getAllDetectedPrey();
        Iterator<Group> iter = detectedPrey.iterator();

        if (!detectedPrey.isEmpty()) {
            double closestDistance = 0;
            Group closestPrey = new Group();

            while (iter.hasNext()) {
                Group current = iter.next();
                if (Distance.calculateDistance(current,
                        GameObject.getParent(getGameObject()).get()) >= closestDistance) {
                    closestPrey = current;
                    closestDistance = Distance.calculateDistance(current, GameObject.getParent(getGameObject()).get());
                }
            }
            return Optional.of(closestPrey);
        }
        return Optional.empty();
    }
}
