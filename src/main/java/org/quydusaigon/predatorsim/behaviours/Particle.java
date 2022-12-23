package org.quydusaigon.predatorsim.behaviours;

import org.quydusaigon.predatorsim.gameengine.component.Behaviour;
import org.quydusaigon.predatorsim.gameengine.component.Collider;
import org.quydusaigon.predatorsim.gameengine.component.NodeComponent;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Particle extends Behaviour {

    @Override
    public void start() {
        var circle = (Circle) getComponent(NodeComponent.class).orElseThrow().getNode();
        circle.setRadius(Math.random() * 10 + 10);
    }

    @Override
    public void update() {
        var x = posX();
        var y = posY();
        x.set(x.get() + Math.random() * 10 - 5);
        y.set(y.get() + Math.random() * 10 - 5);
    }

    @Override
    public void onCollisionEnter(Collider<?> collider, Collider<?> other) {
        var circle = (Circle) getComponent(NodeComponent.class).orElseThrow().getNode();
        circle.setFill(Color.RED);
    }

    @Override
    public void onCollisionExit(Collider<?> collider, Collider<?> other) {
        var circle = (Circle) getComponent(NodeComponent.class).orElseThrow().getNode();
        circle.setFill(Color.GREEN);
    }
}
