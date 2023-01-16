package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

import java.util.HashSet;

import org.quydusaigon.predatorsim.behaviours.animals.Predator;
import org.quydusaigon.predatorsim.behaviours.animals.Prey;
import org.quydusaigon.predatorsim.gameengine.component.Behaviour;
import org.quydusaigon.predatorsim.gameengine.component.Collider;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;

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
        if (GameObject.getParent(getGameObject()).get() != other.getGameObject()) {
            var particle = (Circle) other.getNode();
            particle.setStroke(Color.BLACK);
            particle.setStrokeWidth(5);
            detectedGameObject.add(other.getGameObject());
        }
    }

    @Override
    public void onCollisionExit(Collider<?> collider, Collider<?> other) {
        if (GameObject.getParent(getGameObject()).get() != other.getGameObject()) {
            var particle = (Circle) other.getNode();
            particle.setStroke(null);
            detectedGameObject.remove(other.getGameObject());
        }
    }
}
