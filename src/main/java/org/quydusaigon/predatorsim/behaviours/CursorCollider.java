package org.quydusaigon.predatorsim.behaviours;

import org.quydusaigon.predatorsim.App;
import org.quydusaigon.predatorsim.gameengine.component.Behaviour;
import org.quydusaigon.predatorsim.gameengine.component.Collider;

import javafx.scene.paint.Color;
import javafx.scene.robot.Robot;
import javafx.scene.shape.Circle;

public class CursorCollider extends Behaviour {

    private static Robot robot = new Robot();

    @Override
    public void update() {
        var pos = App.root.screenToLocal(robot.getMousePosition());
        posX().set(pos.getX());
        posY().set(pos.getY());
    }

    @Override
    public void onCollisionStay(Collider<?> collider, Collider<?> other) {
        var particle = (Circle) other.getNode();
        particle.setStroke(Color.BLUE);
    }
}
