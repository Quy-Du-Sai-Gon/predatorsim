package org.quydusaigon.predatorsim;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import org.quydusaigon.predatorsim.behaviours.animals.Predator;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.gameengine.util.Helper;
import org.quydusaigon.predatorsim.gameengine.util.Prefab;
import org.quydusaigon.predatorsim.gameengine.util.TransformInit;
import org.quydusaigon.predatorsim.util.Parameter;
import org.quydusaigon.predatorsim.util.Prefabs;

import javafx.scene.Group;

public final class Level {
    private Level() {
    }

    private static int numberOfPredator;
    private static int numberOfSmallPrey;
    private static int numberOfMediumPrey;
    private static int numberOfLargePrey;
    
    public static void main() {
        IntStream.range(0, numberOfPredator).forEach((i) -> {
            GameObject.instantiate(Prefabs.PREDATOR, TransformInit.getRandomTransformInit());
        });

        IntStream.range(0, numberOfSmallPrey).forEach((i) -> {
            GameObject.instantiate(Prefabs.SMALL_PREY, TransformInit.getRandomTransformInit());
        });

        IntStream.range(0, numberOfMediumPrey).forEach((i) -> {
            GameObject.instantiate(Prefabs.MEDIUM_PREY, TransformInit.getRandomTransformInit());
        });

        IntStream.range(0, numberOfLargePrey).forEach((i) -> {
            GameObject.instantiate(Prefabs.LARGE_PREY, TransformInit.getRandomTransformInit());
        });
    };

    public static void changeAnimalNumber(int predatorNumber, int smallPreyNumber, int mediumPreyNumber, int largePreyNumber) {
        numberOfPredator = predatorNumber;
        numberOfSmallPrey = smallPreyNumber;
        numberOfMediumPrey = mediumPreyNumber;
        numberOfLargePrey = largePreyNumber;
    }
}
