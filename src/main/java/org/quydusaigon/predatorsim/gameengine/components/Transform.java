package org.quydusaigon.predatorsim.gameengine.components;

<<<<<<< HEAD
import javafx.geometry.Point2D;
import javafx.scene.effect.Light;
import org.quydusaigon.predatorsim.Animal;
import org.quydusaigon.predatorsim.gameengine.Component;

public class Transform extends Component {
    private final Point2D position;

    public Transform(double initialX, double initialY) {
        this.position = new Point2D(initialX, initialY);
    }

    public Point2D getPosition() {
        return this.position;
    }
=======
import org.quydusaigon.predatorsim.gameengine.Component;

import javafx.geometry.Point2D;

public class Transform extends Component{
    public Point2D position = new Point2D(0,0);
>>>>>>> main
}
