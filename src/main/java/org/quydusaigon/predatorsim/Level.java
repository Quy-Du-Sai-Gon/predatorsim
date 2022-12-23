package org.quydusaigon.predatorsim;

import java.util.function.Supplier;

import org.quydusaigon.predatorsim.gameengine.util.Helper;
import org.quydusaigon.predatorsim.util.Prefabs;

import javafx.scene.Group;

public final class Level {
    private Level() {
    }

    public static final Supplier<Group> main = Helper.lazyValue(() -> {
        var root = Prefabs.ROOT();
        return root;
    });
}
