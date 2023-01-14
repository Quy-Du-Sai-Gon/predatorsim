package org.quydusaigon.predatorsim;

import java.util.function.Supplier;
import java.util.stream.IntStream;

import org.quydusaigon.predatorsim.behaviours.CursorCollider;
import org.quydusaigon.predatorsim.gameengine.component.Collider;
import org.quydusaigon.predatorsim.gameengine.component.NodeComponent;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.gameengine.util.Helper;
import org.quydusaigon.predatorsim.gameengine.util.TransformInit;
import org.quydusaigon.predatorsim.util.Prefabs;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public final class Level {
    private Level() {
    }

    public static final Supplier<Group> main = Helper.lazyValue(() -> {
        var root = Prefabs.ROOT();

        IntStream.range(0, 200).forEach((i) -> {
            GameObject.instantiate(Prefabs.PARTICLE, TransformInit.DEFAULT, root);
        });

        var node = new Rectangle(-150, -150, 300, 300);
        node.setFill(new Color(0, 0, 0, 0.2));
        var nodeComp = new NodeComponent<>(node);
        GameObject.create(TransformInit.DEFAULT, root,
                nodeComp, new Collider<>(nodeComp), new CursorCollider());

        return root;
    });
}
