package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.animals.Predator;
import org.quydusaigon.predatorsim.behaviours.animals.Prey;
import org.quydusaigon.predatorsim.behaviours.states.WanderState;
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

    @Override
    public void start() {
        detectedGameObject = new HashSet<>();
    }

    @Override
    public void onCollisionEnter(Collider<?> collider, Collider<?> other) {
        Group thisGameObject = GameObject.getParent(getGameObject()).get();
        Group otherGameObject = other.getGameObject();

        if (thisGameObject != otherGameObject
                & GameObject.getComponent(otherGameObject, Vision.class).isEmpty()) {

            var particle = (Circle) other.getNode();
            particle.setStroke(Color.BLACK);
            particle.setStrokeWidth(5);

            detectedGameObject.add(otherGameObject);

            if(GameObject.getComponent(otherGameObject, Prey.class).isPresent()) {
                GameObject.getComponent(thisGameObject, Animal.class).get().getStateConstructor().getWanderState().setFoundObject(true);
            }
            else if(GameObject.getComponent(otherGameObject, Predator.class).isPresent()) {

            }
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

    public HashSet<Group> getAllDetectedObject(Class animal) {
        HashSet<Group> detectedObject = new HashSet<Group>();
        Iterator<Group> iter = detectedGameObject.iterator();

        while (iter.hasNext()) {
            Group current = iter.next();
            if (GameObject.getComponent(current, animal).isPresent()) {
                detectedObject.add(current);
            }
        }
        return detectedObject;
    }

    public Optional<Group> getClosestObject(Class animal) {
        HashSet<Group> detectedObject = getAllDetectedObject(animal);
        Iterator<Group> iter = detectedObject.iterator();

        if (!detectedObject.isEmpty()) {
            double closestDistance = 0;
            Group closestObject = new Group();

            while (iter.hasNext()) {
                Group current = iter.next();
                if (Distance.calculateDistance(current,
                        GameObject.getParent(getGameObject()).get()) >= closestDistance) {
                    closestObject = current;
                    closestDistance = Distance.calculateDistance(current, GameObject.getParent(getGameObject()).get());
                }
            }
            return Optional.of(closestObject);
        }
        return Optional.empty();
    }
}
