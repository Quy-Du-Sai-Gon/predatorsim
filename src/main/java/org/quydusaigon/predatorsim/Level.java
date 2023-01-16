package org.quydusaigon.predatorsim;

import java.util.function.Supplier;
import java.util.stream.IntStream;

import org.quydusaigon.predatorsim.behaviours.animals.Predator;
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

        int numberOfPredator = 1;
        int numberOfSmallPrey = 5;
        int numberOfMediumPrey = 5;
        int numberOfLargePrey = 5;

        IntStream.range(0, numberOfPredator).forEach((i) -> {
            GameObject.instantiate(Prefabs.PREDATOR, TransformInit.getRandomTransformInit(), root);
        });

        IntStream.range(0, numberOfSmallPrey).forEach((i) -> {
            GameObject.instantiate(Prefabs.SMALL_PREY, TransformInit.getRandomTransformInit(), root);
        });

        IntStream.range(0, numberOfMediumPrey).forEach((i) -> {
            GameObject.instantiate(Prefabs.MEDIUM_PREY, TransformInit.getRandomTransformInit(), root);
        });

        IntStream.range(0, numberOfLargePrey).forEach((i) -> {
            GameObject.instantiate(Prefabs.LARGE_PREY, TransformInit.getRandomTransformInit(), root);
        });

        return root;
    });
}
