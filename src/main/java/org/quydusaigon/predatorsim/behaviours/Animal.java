package org.quydusaigon.predatorsim.behaviours;

import org.quydusaigon.predatorsim.UI;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.*;
import org.quydusaigon.predatorsim.behaviours.util.GridDisplay;
import org.quydusaigon.predatorsim.behaviours.util.Velocity;
import org.quydusaigon.predatorsim.gameengine.component.NodeComponent;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.util.AnimalStat;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public abstract class Animal extends StateMachine {
    // Animal class variables
    public AnimalStat animalStat;
    protected Vision vision;
    public Velocity velocity;

    // Getter for the vision variable
    public Vision getVision() {
        return vision;
    }

    // Parameterized constructor
    public Animal(AnimalStat animalStat) {
        this.animalStat = animalStat;
    }

    // Override the start method in the StateMachine class
    @Override
    public void start() {
        // Initialize the vision variable using the GameObject class's getComponent()
        vision = GameObject.getComponent(GameObject.getChildren(getGameObject()).get(0), Vision.class).orElseThrow();

        // Initialize the velocity variable using the GameObject class's getComponent()
        velocity = GameObject.getComponent(getGameObject(), Velocity.class).get();

        addGridDisplay();
    }

    private void addGridDisplay() {
        var circle = (Circle) getComponent(NodeComponent.class).orElseThrow().getNode();

        var size = Math.ceil(circle.getRadius() * 2 / UI.GRID_SIZE) * UI.GRID_SIZE;

        getComponents(NodeComponent.class)
                .map(NodeComponent::getNode)
                .filter(node -> node instanceof ImageView)
                .findFirst()
                .map(ImageView.class::cast)
                .ifPresentOrElse((imageView) -> {
                    var node = new ImageView(imageView.getImage());
                    addComponent(new GridDisplay(node, size));
                }, () -> {
                    var node = new Rectangle(size, size, circle.getFill());
                    node.setStroke(Color.BLACK);
                    node.setStrokeWidth(2);
                    addComponent(new GridDisplay(node, size));
                });
    }

}
