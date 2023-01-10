package org.quydusaigon.predatorsim.behaviours;

import org.quydusaigon.predatorsim.gameengine.component.Behaviour;
import org.quydusaigon.predatorsim.gameengine.component.Collider;
import org.quydusaigon.predatorsim.gameengine.component.NodeComponent;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Particle extends Behaviour {

    @Override
    public void start() {
        // random position
        posX().set(Math.random() * 1000);
        posY().set(Math.random() * 800);

        // random radius
        var circle = (Circle) getComponent(NodeComponent.class).orElseThrow().getNode();
        circle.setRadius(Math.random() * 10 + 10);

        circle.setStrokeWidth(5);
    }

    @Override
    public void update() {
        // random movement
        var x = posX();
        var y = posY();
        x.set(x.get() + Math.random() * 10 - 5);
        y.set(y.get() + Math.random() * 10 - 5);

        // reset color
        var circle = (Circle) getComponent(NodeComponent.class).orElseThrow().getNode();
        circle.setFill(Color.GREEN);
        circle.setStroke(null);
    }

    @Override
    public void onCollisionStay(Collider<?> collider, Collider<?> other) {
        if (other.getComponent(Particle.class).isEmpty())
            // not colliding with another particle
            return;

        var circle = (Circle) collider.getNode();
        circle.setFill(Color.RED);
    }
}
