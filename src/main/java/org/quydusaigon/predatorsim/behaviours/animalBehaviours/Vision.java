package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.quydusaigon.predatorsim.UI;
import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.State;
import org.quydusaigon.predatorsim.behaviours.StateMachine;
import org.quydusaigon.predatorsim.gameengine.component.Behaviour;
import org.quydusaigon.predatorsim.gameengine.component.Collider;
import org.quydusaigon.predatorsim.gameengine.component.NodeComponent;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.states.HowlState;
import org.quydusaigon.predatorsim.states.WanderState;
import org.quydusaigon.predatorsim.util.Distance;

import javafx.beans.value.ChangeListener;
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

        initializeCircleNode();
    }

    private ChangeListener<? super Boolean> onShowsVisionChanged;

    private void initializeCircleNode() {
        var circle = (Circle) getComponent(NodeComponent.class).orElseThrow().getNode();
        circle.setOpacity(0.2);

        // visibility
        circle.setVisible(UI.getShowsVisionProperty().get()); // default

        onShowsVisionChanged = (_observable, _oldValue, showsVision) -> {
            circle.setVisible(showsVision);
        };

        UI.getShowsVisionProperty().addListener(onShowsVisionChanged);
    }

    @Override
    public void onDestroy() {
        UI.getShowsVisionProperty().removeListener(onShowsVisionChanged);
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
        if (GameObject.getParent(getGameObject()).get() != other.getGameObject()) {
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

    public <T extends Animal, L extends State> Optional<T> getClosestDetectedAnimalInState(Class<T> animalClass,
            Class<L> stateClass) {
        return detectedGameObject.stream()
                .map(go -> GameObject.getComponent(go, animalClass))
                .filter(Optional::isPresent)
                .map(Optional::orElseThrow)
                .filter(animal -> stateClass.isInstance(animal.getCurrentState()))
                .min((obj1, obj2) -> Double.compare(
                        Distance.calculateDistance(obj1.getGameObject(), thisAnimalGameObject),
                        Distance.calculateDistance(obj2.getGameObject(), thisAnimalGameObject)));
    }

    // public <T extends Animal, L extends State> Optional<T>
    // getAllDetectedAnimalsInState(Class<T> animalClass,
    // Class<L> stateClass) {
    // return detectedGameObject.stream()
    // .map(go -> GameObject.getComponent(go, animalClass))
    // .filter(Optional::isPresent)
    // .map(Optional::orElseThrow)
    // .filter(animal -> stateClass.isInstance(animal.getCurrentState();))
    // }

    public <T extends Animal> Optional<Group> getClosestObject(Class<T> animal) {
        return getAllDetectedObject(animal).stream()
                .min((obj1, obj2) -> Double.compare(
                        Distance.calculateDistance(obj1, thisAnimalGameObject),
                        Distance.calculateDistance(obj2, thisAnimalGameObject)));

    }
}
