package org.quydusaigon.predatorsim;

import java.util.stream.IntStream;

import org.quydusaigon.Output;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.gameengine.util.TransformInit;
import org.quydusaigon.predatorsim.util.Parameter;
import org.quydusaigon.predatorsim.util.Prefabs;

public final class Level {
    private Level() {
    }

    public static void main() {
        IntStream.range(0, Parameter.getPredatorCount()).forEach((i) -> {
            Output.getInstance().predatorCount++;
            GameObject.instantiate(Prefabs.PREDATOR, TransformInit.getRandomTransformInit());
        });

        IntStream.range(0, Parameter.getSmallPreyCount()).forEach((i) -> {
            Output.getInstance().smallPreyCount++;
            GameObject.instantiate(Prefabs.SMALL_PREY, TransformInit.getRandomTransformInit());
        });

        IntStream.range(0, Parameter.getMediumPreyCount()).forEach((i) -> {
            Output.getInstance().mediumPreyCount++;
            GameObject.instantiate(Prefabs.MEDIUM_PREY, TransformInit.getRandomTransformInit());
        });

        IntStream.range(0, Parameter.getLargePreyCount()).forEach((i) -> {
            Output.getInstance().largePreyCount++;
            GameObject.instantiate(Prefabs.LARGE_PREY, TransformInit.getRandomTransformInit());
        });
    };

}
