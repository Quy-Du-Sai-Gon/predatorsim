package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.quydusaigon.predatorsim.UI;
import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.State;
import org.quydusaigon.predatorsim.gameengine.component.Behaviour;
import org.quydusaigon.predatorsim.gameengine.component.Collider;
import org.quydusaigon.predatorsim.gameengine.component.NodeComponent;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.util.Distance;

import javafx.beans.value.ChangeListener;
import javafx.scene.Group;
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

    // Add a new game object to the set of detected game object of the vision
    // instance of a game object if the collider
    // of the vision collides with the new game object
    @Override
    public void onCollisionEnter(Collider<?> collider, Collider<?> other) {
        // Get the game object of the other collider
        Group otherGameObject = other.getGameObject();

        // Check if the other game object is also not the game object of this collider
        // and
        // if the other game object of have a vision class
        if (thisAnimalGameObject != otherGameObject
                && GameObject.getComponent(otherGameObject, Vision.class).isEmpty()) {

            // Add the other game object to the set of all detected game object of this
            // vision instance
            detectedGameObject.add(otherGameObject);
        }
    }

    // Remove a game object from the set of detected game object of the vision
    // instance of a game object if the collider
    // of the vision no longer collides with the game object
    @Override
    public void onCollisionExit(Collider<?> collider, Collider<?> other) {
        // Check if the game object of the other collider is not the same game object as
        // this collider
        if (GameObject.getParent(getGameObject()).get() != other.getGameObject()) {
            // Remove the game object of the other collider from the set of all detected
            // game object of this vision instance
            detectedGameObject.remove(other.getGameObject());
        }
    }

    /**
     * Return a {@link Set} of {@link Group} containing an {@link Animal} instance
     * of type {@code animal} that is currently
     * colliding with this vision instance.
     * 
     * @param <T>    the type of {@link Animal} to return.
     * @param animal class instance of the type to return.
     * @return a {@link Set} of {@link Group} colliding with this vision instance
     *         that containing the instance of type {@code animal}.
     */
    public <T extends Animal> Set<Group> getAllDetectedObject(Class<T> animal) {
        return detectedGameObject.stream()
                .filter(go -> GameObject.getComponent(go, animal).isPresent())
                .collect(Collectors.toSet());
    }

    /**
     * Return the closest {@link Group} containing an {@link Animal} instance of
     * type {@code animalClass} and its {@link Animal}'s
     * {@code currentState} instance is of type {@code stateClass} (if any), that is
     * colliding with this vision instance
     * 
     * @param <T>         the type of {@link Animal} to return.
     * @param <L>         the type of {@link State} to return.
     * @param animalClass class instance of the {@link Animal} type to return.
     * @param stateClass  class instance of the {@link State} type to return.
     * @return an {@link Optional} containing the closest {@link Group} that
     *         contains an {@link Animal}
     *         instance of type {@code animalClass} and its {@link Animal}'s
     *         {@code currentState} instance is of type {@code stateClass}.
     */
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

    /**
     * Return the closest {@link Group} containing an {@link Animal} instance of
     * type {@code animal}
     * 
     * @param <T>    the type of {@link Animal} to return.
     * @param animal class instance of the {@link Animal} type to return.
     * @return an {@link Optional} containing the closest {@link Group} that
     *         contains an {@link Animal}
     *         instance of type {@code animal}.
     */
    public <T extends Animal> Optional<Group> getClosestObject(Class<T> animal) {
        return getAllDetectedObject(animal).stream()
                .min((obj1, obj2) -> Double.compare(
                        Distance.calculateDistance(obj1, thisAnimalGameObject),
                        Distance.calculateDistance(obj2, thisAnimalGameObject)));

    }
}
