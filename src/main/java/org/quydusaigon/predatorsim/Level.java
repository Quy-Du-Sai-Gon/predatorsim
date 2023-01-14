package org.quydusaigon.predatorsim;

import java.util.function.Supplier;
import java.util.stream.IntStream;

import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.gameengine.util.Helper;
import org.quydusaigon.predatorsim.gameengine.util.Prefab;
import org.quydusaigon.predatorsim.gameengine.util.TransformInit;
import org.quydusaigon.predatorsim.util.Prefabs;

import javafx.scene.Group;

public final class Level {
    private Level() {
    }

    public static final Supplier<Group> main = Helper.lazyValue(() -> {
        var root = Prefabs.ROOT();

        IntStream.range(0, 10).forEach((i) -> {
            GameObject.instantiate(Prefabs.PREDATOR, TransformInit.DEFAULT, root);
        });
        
        return root;
    });
}
