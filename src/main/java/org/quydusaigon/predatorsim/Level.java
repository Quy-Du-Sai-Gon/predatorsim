package org.quydusaigon.predatorsim;

import java.util.function.Supplier;
import java.util.stream.IntStream;

import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.gameengine.util.Helper;
import org.quydusaigon.predatorsim.gameengine.util.TransformInit;
import org.quydusaigon.predatorsim.util.Prefabs;

import javafx.scene.Group;

public final class Level {
    private Level() {
    }

    public static final Supplier<Group> main = Helper.lazyValue(() -> {
        var root = Prefabs.ROOT();

        var tf = new TransformInit(500, 400);
        GameObject.instantiate(Prefabs.PARTICLE, tf, root);
        // IntStream.range(0, 1000).forEach((i) -> {
        // var tf = new TransformInit(Math.random() * 1000, Math.random() * 800);
        // GameObject.instantiate(Prefabs.PARTICLE, tf, root);
        // });

        return root;
    });
}
