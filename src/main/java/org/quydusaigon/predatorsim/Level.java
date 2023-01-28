package org.quydusaigon.predatorsim;

import java.util.stream.IntStream;

import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.gameengine.util.TransformInit;
import org.quydusaigon.predatorsim.util.Parameter;
import org.quydusaigon.predatorsim.util.Prefabs;

public final class Level {
    private Level() {
    }

    public static void main() {
        IntStream.range(0, Parameter.getPredatorCount()).forEach((i) -> {
            GameObject.instantiate(Prefabs.PREDATOR, TransformInit.getRandomTransformInit());
        });

        IntStream.range(0, Parameter.getSmallPreyCount()).forEach((i) -> {
            GameObject.instantiate(Prefabs.SMALL_PREY, TransformInit.getRandomTransformInit());
        });

        IntStream.range(0, Parameter.getMediumPreyCount()).forEach((i) -> {
            GameObject.instantiate(Prefabs.MEDIUM_PREY, TransformInit.getRandomTransformInit());
        });

        IntStream.range(0, Parameter.getLargePreyCount()).forEach((i) -> {
            GameObject.instantiate(Prefabs.LARGE_PREY, TransformInit.getRandomTransformInit());
        });
    };

}
