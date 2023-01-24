package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.gameengine.component.Behaviour;
import org.quydusaigon.predatorsim.gameengine.component.Collider;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.util.Distance;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Vision extends Behaviour {

    HashSet<Group> detectedGameObject;
    boolean showVision = false;
    Group thisAnimalGameObject;

    @Override
    public void start() {
        detectedGameObject = new HashSet<>();
        thisAnimalGameObject = GameObject.getParent(getGameObject()).orElseThrow();
    }

    @Override
    public void onCollisionEnter(Collider<?> collider, Collider<?> other) {
        Group otherGameObject = other.getGameObject();

        if (thisAnimalGameObject != otherGameObject
                && GameObject.getComponent(otherGameObject, Vision.class).isEmpty()) {

            var particle = (Circle) other.getNode();
            particle.setStroke(Color.BLACK);
            particle.setStrokeWidth(5);
            detectedGameObject.add(otherGameObject);
        }
    }

    @Override
    public void onCollisionExit(Collider<?> collider, Collider<?> other) {
        if (GameObject.getParent(getGameObject()).get() != other.getGameObject()
                && GameObject.getComponent(other.getGameObject(), Vision.class).isEmpty()) {
            var particle = (Circle) other.getNode();
            particle.setStroke(null);
            detectedGameObject.remove(other.getGameObject());
        }
    }

    public <T extends Animal> Set<Group> getAllDetectedObject(Class<T> animal) {
        return detectedGameObject.stream()
                .filter(go -> GameObject.getComponent(go, animal).isPresent())
                .collect(Collectors.toSet());
    }

    public <T extends Animal> Optional<Group> getClosestObject(Class<T> animal) {
        return getAllDetectedObject(animal).stream()
                .min((obj1, obj2) -> Double.compare(
                        Distance.calculateDistance(obj1, thisAnimalGameObject),
                        Distance.calculateDistance(obj2, thisAnimalGameObject)));

    }
}
