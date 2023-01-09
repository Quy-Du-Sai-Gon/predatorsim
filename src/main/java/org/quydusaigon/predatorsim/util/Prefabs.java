package org.quydusaigon.predatorsim.util;

import org.quydusaigon.predatorsim.behaviours.Particle;
import org.quydusaigon.predatorsim.gameengine.component.Collider;
import org.quydusaigon.predatorsim.gameengine.component.NodeComponent;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.gameengine.util.Prefab;
import org.quydusaigon.predatorsim.gameengine.util.TransformInit;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public final class Prefabs {

    private Prefabs() {
    }

    public static Group ROOT() {
        return GameObject.create(TransformInit.DEFAULT, null);
    }

    public static final Prefab PARTICLE = (tf, parent) -> {
        var nodeComp = new NodeComponent<>(new Circle(0, Color.GREEN));
        return GameObject.create(tf, parent, nodeComp, new Collider<>(nodeComp), new Particle());
    };

}
