package org.quydusaigon.predatorsim.util;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

import org.quydusaigon.predatorsim.behaviours.animalBehaviours.*;
import org.quydusaigon.predatorsim.behaviours.animals.Predator;
import org.quydusaigon.predatorsim.behaviours.animals.Prey;
import org.quydusaigon.predatorsim.gameengine.component.Collider;
import org.quydusaigon.predatorsim.gameengine.component.NodeComponent;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.gameengine.util.Prefab;
import org.quydusaigon.predatorsim.gameengine.util.TransformInit;

import javafx.scene.Group;

public final class Prefabs {

    private static Random random = new Random();

    private Prefabs() {
    }

    public static final Prefab STATUS_DISPLAY = (tf, parent) -> {
        return GameObject.create(TransformInit.DEFAULT, parent, new StatDisplay());
    };

    public static final Prefab PREDATOR = (tf, parent) -> {
        var nodeComp = new NodeComponent<>(new Circle(10, Color.RED));
        PredatorStat predatorStat = new PredatorStat(
                random.nextDouble(
                        Parameter.getPredatorSpeedMinimumRange(),
                        Parameter.getPredatorSpeedMaximumRange()),
                random.nextDouble(
                        Parameter.getPredatorVisionMinimumRange(),
                        Parameter.getPredatorVisionMaximumRange()),
                random.nextInt(
                        Parameter.getPredatorStarvationResillienceMinimumRange(),
                        Parameter.getPredatorStarvationResillienceMaximumRange()),
                Parameter.getPredatorGroupRadius(),
                400);

        Group newPredator = GameObject.create(tf, parent,
                nodeComp, new Collider<>(nodeComp),
                new WanderBehaviour(),
                new HuntingInGroup(),
                new HuntingAlone(),
                new PredatorDead(),
                new Predator(predatorStat));

        var visionNodeComp = new NodeComponent<>(new Circle(predatorStat.visionRange, Color.AQUAMARINE));
        GameObject.create(TransformInit.DEFAULT, newPredator,
                visionNodeComp, new Collider<>(visionNodeComp), new Vision());

        GameObject.instantiate(STATUS_DISPLAY, newPredator);

        return newPredator;
    };

    public static final Prefab SMALL_PREY = (tf, parent) -> {
        var nodeComp = new NodeComponent<>(new Circle(10, Color.GREEN));
        PreyStat smallPreyStat = new PreyStat(
                random.nextDouble(
                        Parameter.getSmallPreySpeedMinimumRange(),
                        Parameter.getSmallPreySpeedMaximumRange()),
                random.nextDouble(
                        Parameter.getSmallPreyVisionMinimumRange(),
                        Parameter.getSmallPreyVisionMaximumRange()),
                PreySize.SMALL,
                random.nextInt(
                        Parameter.getSmallPreyNutritionMinimumRange(),
                        Parameter.getSmallPreyNutritionMaximumRange()),
                random.nextDouble(
                        Parameter.getSmallPreyDefenseMinimumRange(),
                        Parameter.getSmallPreyDefenseMaximumRange()));

        Group newSmallPrey = GameObject.create(tf, parent,
                nodeComp, new Collider<>(nodeComp),
                new WanderBehaviour(),
                new Evading(),
                new PreyDead(),
                new Prey(smallPreyStat));

        var visionNodeComp = new NodeComponent<>(new Circle(smallPreyStat.visionRange, Color.BLUEVIOLET));
        GameObject.create(TransformInit.DEFAULT, newSmallPrey,
                visionNodeComp, new Collider<>(visionNodeComp), new Vision());

        GameObject.instantiate(STATUS_DISPLAY, newSmallPrey);

        return newSmallPrey;
    };

    public static final Prefab MEDIUM_PREY = (tf, parent) -> {
        var nodeComp = new NodeComponent<>(new Circle(20, Color.GREEN));
        PreyStat mediumPreyStat = new PreyStat(
                random.nextDouble(
                        Parameter.getMediumPreySpeedMinimumRange(),
                        Parameter.getMediumPreySpeedMaximumRange()),
                random.nextDouble(
                        Parameter.getMediumPreyVisionMinimumRange(),
                        Parameter.getMediumPreyVisionMaximumRange()),
                PreySize.MEDIUM,
                random.nextInt(
                        Parameter.getMediumPreyNutritionMinimumRange(),
                        Parameter.getMediumPreyNutritionMaximumRange()),
                random.nextDouble(
                        Parameter.getMediumPreyDefenseMinimumRange(),
                        Parameter.getMediumPreyDefenseMaximumRange()));

        Group newMediumPrey = GameObject.create(tf, parent,
                nodeComp, new Collider<>(nodeComp),
                new WanderBehaviour(),
                new Evading(),
                new PreyDead(),
                new Prey(mediumPreyStat));

        var visionNodeComp = new NodeComponent<>(new Circle(mediumPreyStat.visionRange, Color.BLUEVIOLET));
        GameObject.create(TransformInit.DEFAULT, newMediumPrey,
                visionNodeComp, new Collider<>(visionNodeComp), new Vision());

        GameObject.instantiate(STATUS_DISPLAY, newMediumPrey);

        return newMediumPrey;
    };

    public static final Prefab LARGE_PREY = (tf, parent) -> {
        var nodeComp = new NodeComponent<>(new Circle(30, Color.GREEN));
        PreyStat largePreyStat = new PreyStat(
                random.nextDouble(
                        Parameter.getLargePreySpeedMinimumRange(),
                        Parameter.getLargePreySpeedMaximumRange()),
                random.nextDouble(
                        Parameter.getLargePreyVisionMinimumRange(),
                        Parameter.getLargePreyVisionMaximumRange()),
                PreySize.LARGE,
                random.nextInt(
                        Parameter.getLargePreyNutritionMinimumRange(),
                        Parameter.getLargePreyNutritionMaximumRange()),
                random.nextDouble(
                        Parameter.getLargePreyDefenseMinimumRange(),
                        Parameter.getLargePreyDefenseMaximumRange()));

        Group newLargePrey = GameObject.create(tf, parent,
                nodeComp, new Collider<>(nodeComp),
                new WanderBehaviour(),
                new Evading(),
                new PreyDead(),
                new Prey(largePreyStat));

        var visionNodeComp = new NodeComponent<>(new Circle(largePreyStat.visionRange, Color.BLUEVIOLET));
        GameObject.create(TransformInit.DEFAULT, newLargePrey,
                visionNodeComp, new Collider<>(visionNodeComp), new Vision());

        GameObject.instantiate(STATUS_DISPLAY, newLargePrey);

        return newLargePrey;
    };

}
