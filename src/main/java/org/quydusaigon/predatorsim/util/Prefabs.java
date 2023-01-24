package org.quydusaigon.predatorsim.util;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.Evading;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.HuntingAlone;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.HuntingInGroup;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.Vision;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.WanderBehaviour;
import org.quydusaigon.predatorsim.behaviours.animals.Predator;
import org.quydusaigon.predatorsim.behaviours.animals.Prey;
import org.quydusaigon.predatorsim.gameengine.component.Collider;
import org.quydusaigon.predatorsim.gameengine.component.NodeComponent;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.gameengine.util.Prefab;
import org.quydusaigon.predatorsim.gameengine.util.TransformInit;

import javafx.scene.Group;

public final class Prefabs {

    private static boolean showVision = false;
    private static boolean showObjectStat = false;

    private static double predatorSpeedMin;
    private static double predatorSpeedMax;
    private static double smallPreySpeedMin;
    private static double smallPreySpeedMax;
    private static double mediumPreySpeedMin;
    private static double mediumPreySpeedMax;
    private static double largePreySpeedMin;
    private static double largePreySpeedMax;

    private static double predatorVisionRangeMin;
    private static double predatorVisionRangeMax;
    private static double smallPreyVisionRangeMin;
    private static double smallPreyVisionRangeMax;
    private static double mediumPreyVisionRangeMin;
    private static double mediumPreyVisionRangeMax;
    private static double largePreyVisionRangeMin;
    private static double largePreyVisionRangeMax;

    private static int smallPreyNutritionMin;
    private static int smallPreyNutritionMax;
    private static int mediumPreyNutritionMin;
    private static int mediumPreyNutritionMax;
    private static int largePreyNutritionMin;
    private static int largePreyNutritionMax;

    private static double smallPreyDefenseMin;
    private static double smallPreyDefenseMax;
    private static double mediumPreyDefenseMin;
    private static double mediumPreyDefenseMax;
    private static double largePreyDefenseMin;
    private static double largePreyDefenseMax;

    private static int predatorStarvationResilienceMin;
    private static int predatorStarvationResilienceMax;
    private static double predatorGroupRadius;

    private static Random random = new Random();

    private Prefabs() {
    }

    public static final Prefab PREDATOR = (tf, parent) -> {
        var nodeComp = new NodeComponent<>(new Circle(10, Color.RED));
        PredatorStat predatorStat = new PredatorStat(
                random.nextDouble(predatorSpeedMin, predatorSpeedMax),
                random.nextDouble(predatorVisionRangeMin, predatorVisionRangeMax),
                random.nextInt(predatorStarvationResilienceMin, predatorStarvationResilienceMax),
                predatorGroupRadius);

        Group newPredator = GameObject.create(tf, parent,
                nodeComp, new Collider<>(nodeComp),
                new WanderBehaviour(),
                new HuntingInGroup(),
                new HuntingAlone(),
                new Predator(predatorStat));

        var circle = new Circle(predatorStat.visionRange, Color.AQUAMARINE);
        if (showVision)
            circle.setOpacity(0.4);
        else
            circle.setOpacity(0);

        var visionNodeComp = new NodeComponent<>(circle);

        GameObject.create(TransformInit.DEFAULT, newPredator,
                visionNodeComp, new Collider<>(visionNodeComp), new Vision());

        if (showObjectStat) {
            StatDisplay stat = new StatDisplay(predatorStat, newPredator);
            var statNodeComp = new NodeComponent<>(stat.pane);
            GameObject.create(TransformInit.DEFAULT, newPredator, statNodeComp, stat);
        }

        return newPredator;
    };

    public static final Prefab SMALL_PREY = (tf, parent) -> {
        var nodeComp = new NodeComponent<>(new Circle(10, Color.GREEN));
        PreyStat smallPreyStat = new PreyStat(
                random.nextDouble(smallPreySpeedMin, smallPreySpeedMax),
                random.nextDouble(smallPreyVisionRangeMin, smallPreyVisionRangeMax),
                PreySize.SMALL,
                random.nextInt(smallPreyNutritionMin, smallPreyNutritionMax),
                random.nextDouble(smallPreyDefenseMin, smallPreyDefenseMax));

        Group newSmallPrey = GameObject.create(tf, parent,
                nodeComp, new Collider<>(nodeComp),
                new WanderBehaviour(),
                new Evading(),
                new Prey(smallPreyStat));

        var circle = new Circle(smallPreyStat.visionRange, Color.BLUEVIOLET);
        if (showVision)
            circle.setOpacity(0.4);
        else
            circle.setOpacity(0);

        var visionNodeComp = new NodeComponent<>(circle);

        GameObject.create(TransformInit.DEFAULT, newSmallPrey,
                visionNodeComp, new Collider<>(visionNodeComp), new Vision());

        if (showObjectStat) {
            StatDisplay stat = new StatDisplay(smallPreyStat, newSmallPrey);
            var statNodeComp = new NodeComponent<>(stat.pane);
            GameObject.create(TransformInit.DEFAULT, newSmallPrey, statNodeComp, stat);
        }

        return newSmallPrey;
    };

    public static final Prefab MEDIUM_PREY = (tf, parent) -> {
        var nodeComp = new NodeComponent<>(new Circle(20, Color.GREEN));
        PreyStat mediumPreyStat = new PreyStat(
                random.nextDouble(mediumPreySpeedMin, mediumPreySpeedMax),
                random.nextDouble(mediumPreyVisionRangeMin, mediumPreyVisionRangeMax),
                PreySize.MEDIUM,
                random.nextInt(mediumPreyNutritionMin, mediumPreyNutritionMax),
                random.nextDouble(mediumPreyDefenseMin, mediumPreyDefenseMax));

        Group newMediumPrey = GameObject.create(tf, parent,
                nodeComp, new Collider<>(nodeComp),
                new WanderBehaviour(),
                new Evading(),
                new Prey(mediumPreyStat));

        var circle = new Circle(mediumPreyStat.visionRange, Color.BLUEVIOLET);
        if (showVision)
            circle.setOpacity(0.4);
        else
            circle.setOpacity(0);

        var visionNodeComp = new NodeComponent<>(circle);

        GameObject.create(TransformInit.DEFAULT, newMediumPrey,
                visionNodeComp, new Collider<>(visionNodeComp), new Vision());

        if (showObjectStat) {
            StatDisplay stat = new StatDisplay(mediumPreyStat, newMediumPrey);
            var statNodeComp = new NodeComponent<>(stat.pane);
            GameObject.create(TransformInit.DEFAULT, newMediumPrey, statNodeComp, stat);
        }

        return newMediumPrey;
    };

    public static final Prefab LARGE_PREY = (tf, parent) -> {
        var nodeComp = new NodeComponent<>(new Circle(30, Color.GREEN));
        PreyStat largePreyStat = new PreyStat(
                random.nextDouble(largePreySpeedMin, largePreySpeedMax),
                random.nextDouble(largePreyVisionRangeMin, largePreyVisionRangeMax),
                PreySize.LARGE,
                random.nextInt(largePreyNutritionMin, largePreyNutritionMax),
                random.nextDouble(largePreyDefenseMin, largePreyDefenseMax));

        Group newLargePrey = GameObject.create(tf, parent,
                nodeComp, new Collider<>(nodeComp),
                new WanderBehaviour(),
                new Evading(),
                new Prey(largePreyStat));

        var circle = new Circle(largePreyStat.visionRange, Color.BLUEVIOLET);
        if (showVision)
            circle.setOpacity(0.4);
        else
            circle.setOpacity(0);

        var visionNodeComp = new NodeComponent<>(circle);

        GameObject.create(TransformInit.DEFAULT, newLargePrey,
                visionNodeComp, new Collider<>(visionNodeComp), new Vision());

        if (showObjectStat) {
            StatDisplay stat = new StatDisplay(largePreyStat, newLargePrey);
            var statNodeComp = new NodeComponent<>(stat.pane);
            GameObject.create(TransformInit.DEFAULT, newLargePrey, statNodeComp, stat);
        }

        return newLargePrey;
    };

    public static void setPredatorSpeed(double predatorSpeedMinInput, double predatorSpeedMaxInput) {
        predatorSpeedMin = predatorSpeedMinInput;
        predatorSpeedMax = predatorSpeedMaxInput;
    }

    public static void setSmallPreySpeed(double smallPreySpeedMinInput, double smallPreySpeedMaxInput) {
        smallPreySpeedMin = smallPreySpeedMinInput;
        smallPreySpeedMax = smallPreySpeedMaxInput;
    }

    public static void setMediumPreySpeed(double mediumPreySpeedMinInput, double mediumPreySpeedMaxInput) {
        mediumPreySpeedMin = mediumPreySpeedMinInput;
        mediumPreySpeedMax = mediumPreySpeedMaxInput;
    }

    public static void setLargePreySpeed(double largePreySpeedMinInput, double largePreySpeedMaxInput) {
        largePreySpeedMin = largePreySpeedMinInput;
        largePreySpeedMax = largePreySpeedMaxInput;
    }

    public static void setPredatorVisionRange(double predatorVisionRangeMinInput, double predatorVisionRangeMaxInput) {
        predatorVisionRangeMin = predatorVisionRangeMinInput;
        predatorVisionRangeMax = predatorVisionRangeMaxInput;
    }

    public static void setSmallPreyVisionRange(double smallPreyVisionRangeMinInput,
            double smallPreyVisionRangeMaxInput) {
        smallPreyVisionRangeMin = smallPreyVisionRangeMinInput;
        smallPreyVisionRangeMax = smallPreyVisionRangeMaxInput;
    }

    public static void setMediumPreyVisionRange(double mediumPreyVisionRangeMinInput,
            double mediumPreyVisionRangeMaxInput) {
        mediumPreyVisionRangeMin = mediumPreyVisionRangeMinInput;
        mediumPreyVisionRangeMax = mediumPreyVisionRangeMaxInput;
    }

    public static void setLargePreyVisionRange(double largePreyVisionRangeMinInput,
            double largePreyVisionRangeMaxInput) {
        largePreyVisionRangeMin = largePreyVisionRangeMinInput;
        largePreyVisionRangeMax = largePreyVisionRangeMaxInput;
    }

    public static void setSmallPreyNutrition(int smallPreyNutritionMinInput, int smallPreyNutritionMaxInput) {
        smallPreyNutritionMin = smallPreyNutritionMinInput;
        smallPreyNutritionMax = smallPreyNutritionMaxInput;
    }

    public static void setMediumPreyNutrition(int mediumPreyNutritionMinInput, int mediumPreyNutritionMaxInput) {
        mediumPreyNutritionMin = mediumPreyNutritionMinInput;
        mediumPreyNutritionMax = mediumPreyNutritionMaxInput;
    }

    public static void setLargePreyNutrition(int largePreyNutritionMinInput, int largePreyNutritionMaxInput) {
        largePreyNutritionMin = largePreyNutritionMinInput;
        largePreyNutritionMax = largePreyNutritionMaxInput;
    }

    public static void setSmallPreyDefense(double smallPreyDefenseMinInput, double smallPreyDefenseMaxInput) {
        smallPreyDefenseMin = smallPreyDefenseMinInput;
        smallPreyDefenseMax = smallPreyDefenseMaxInput;
    }

    public static void setMediumPreyDefense(double mediumPreyDefenseMinInput, double mediumPreyDefenseMaxInput) {
        mediumPreyDefenseMin = mediumPreyDefenseMinInput;
        mediumPreyDefenseMax = mediumPreyDefenseMaxInput;
    }

    public static void setLargePreyDefense(double largePreyDefenseMinInput, double largePreyDefenseMaxInput) {
        largePreyDefenseMin = largePreyDefenseMinInput;
        largePreyDefenseMax = largePreyDefenseMaxInput;
    }

    public static void setPredatorStarvationResilience(int predatorStarvationResilienceMinInput,
            int predatorStarvationResilienceMaxInput) {
        predatorStarvationResilienceMin = predatorStarvationResilienceMinInput;
        predatorStarvationResilienceMax = predatorStarvationResilienceMaxInput;
    }

    public static void setPredatorGroupRadius(double predatorGroupRadiusInput) {
        predatorGroupRadius = predatorGroupRadiusInput;
    }

    public static void setShowVision(boolean value) {
        showVision = value;
    }

    public static void setShowStat(boolean value) {
        showObjectStat = value;
    }
}
