package org.quydusaigon.predatorsim.util;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.Evading;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.HuntingAlone;
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

    public static boolean showVision = Parameter.DEFAULT_SHOW_VISION;
    public static boolean showObjectStat = Parameter.DEFAULT_SHOW_OBJECT_STAT;

    public static double predatorSpeedMin = Parameter.DEFAULT_PREDATOR_SPEED_MINIMUM_RANGE;
    public static double predatorSpeedMax = Parameter.DEFAULT_PREDATOR_SPEED_MAXIMUM_RANGE;
    public static double smallPreySpeedMin = Parameter.DEFAULT_SMALL_PREY_SPEED_MINIMUM_RANGE;
    public static double smallPreySpeedMax = Parameter.DEFAULT_SMALL_PREY_SPEED_MAXIMUM_RANGE;
    public static double mediumPreySpeedMin = Parameter.DEFAULT_MEDIUM_PREY_SPEED_MINIMUM_RANGE;
    public static double mediumPreySpeedMax = Parameter.DEFAULT_MEDIUM_PREY_SPEED_MAXIMUM_RANGE;
    public static double largePreySpeedMin = Parameter.DEFAULT_LARGE_PREY_SPEED_MINIMUM_RANGE;
    public static double largePreySpeedMax = Parameter.DEFAULT_LARGE_PREY_SPEED_MAXIMUM_RANGE;

    public static double predatorVisionRangeMin = Parameter.DEFAULT_PREDATOR_VISION_MINIMUM_RANGE;
    public static double predatorVisionRangeMax = Parameter.DEFAULT_PREDATOR_VISION_MAXIMUM_RANGE;
    public static double smallPreyVisionRangeMin = Parameter.DEFAULT_SMALL_PREY_VISION_MINIMUM_RANGE;
    public static double smallPreyVisionRangeMax = Parameter.DEFAULT_SMALL_PREY_VISION_MAXIMUM_RANGE;
    public static double mediumPreyVisionRangeMin = Parameter.DEFAULT_MEDIUM_PREY_VISION_MINIMUM_RANGE;
    public static double mediumPreyVisionRangeMax = Parameter.DEFAULT_MEDIUM_PREY_VISION_MAXIMUM_RANGE;
    public static double largePreyVisionRangeMin = Parameter.DEFAULT_LARGE_PREY_VISION_MINIMUM_RANGE;
    public static double largePreyVisionRangeMax = Parameter.DEFAULT_LARGE_PREY_VISION_MAXIMUM_RANGE;

    public static int smallPreyNutritionMin = Parameter.DEFAULT_SMALL_PREY_NUTRITION_MINIMUM_RANGE;
    public static int smallPreyNutritionMax = Parameter.DEFAULT_SMALL_PREY_NUTRITION_MAXIMUM_RANGE;
    public static int mediumPreyNutritionMin = Parameter.DEFAULT_MEDIUM_PREY_NUTRITION_MINIMUM_RANGE;
    public static int mediumPreyNutritionMax = Parameter.DEFAULT_MEDIUM_PREY_NUTRITION_MAXIMUM_RANGE;
    public static int largePreyNutritionMin = Parameter.DEFAULT_LARGE_PREY_NUTRITION_MINIMUM_RANGE;
    public static int largePreyNutritionMax = Parameter.DEFAULT_LARGE_PREY_NUTRITION_MAXIMUM_RANGE;

    public static double smallPreyDefenseMin = Parameter.DEFAULT_SMALL_PREY_DEFENSE_MINIMUM_RANGE;
    public static double smallPreyDefenseMax = Parameter.DEFAULT_SMALL_PREY_DEFENSE_MAXIMUM_RANGE;
    public static double mediumPreyDefenseMin = Parameter.DEFAULT_MEDIUM_PREY_DEFENSE_MINIMUM_RANGE;
    public static double mediumPreyDefenseMax = Parameter.DEFAULT_MEDIUM_PREY_DEFENSE_MAXIMUM_RANGE;
    public static double largePreyDefenseMin = Parameter.DEFAULT_LARGE_PREY_DEFENSE_MINIMUM_RANGE;
    public static double largePreyDefenseMax = Parameter.DEFAULT_LARGE_PREY_DEFENSE_MAXIMUM_RANGE;

    public static int predatorStarvationResilienceMin = Parameter.DEFAULT_PREDATOR_STARVATION_RESILLIENCE_MINIMUM_RANGE;
    public static int predatorStarvationResilienceMax = Parameter.DEFAULT_PREDATOR_STARVATION_RESILLIENCE_MAXIMUM_RANGE;
    public static double predatorGroupRadius = Parameter.DEFAULT_PREDATOR_GROUP_RADIUS;
    
    public static Random random = new Random();
    private Prefabs() {
    }
    
    public static final Prefab PREDATOR = (tf, parent) -> {
        var nodeComp = new NodeComponent<>(new Circle(10, Color.RED));
        PredatorStat predatorStat = new PredatorStat(
                random.nextDouble(predatorSpeedMin, predatorSpeedMax),
                random.nextDouble(predatorVisionRangeMin, predatorVisionRangeMax),
                random.nextInt(predatorStarvationResilienceMin, predatorStarvationResilienceMax),
                predatorGroupRadius
        );
        
        Group newPredator = GameObject.create(tf, parent,
                nodeComp, new Collider<>(nodeComp),
                new WanderBehaviour(),
                new HuntingAlone(),
                new Predator(predatorStat));

        var circle = new Circle(predatorStat.visionRange, Color.AQUAMARINE);
        if (showVision) circle.setOpacity(0.4);
        else circle.setOpacity(0);

        var visionNodeComp = new NodeComponent<>(circle);

        GameObject.create(TransformInit.DEFAULT, newPredator,
                visionNodeComp, new Collider<>(visionNodeComp), new Vision());

        return newPredator;
    };

    public static final Prefab SMALL_PREY = (tf, parent) -> {
        var nodeComp = new NodeComponent<>(new Circle(10, Color.GREEN));
        PreyStat smallPreyStat = new PreyStat(
                random.nextDouble(smallPreySpeedMin, smallPreySpeedMax),
                random.nextDouble(smallPreyVisionRangeMin, smallPreyVisionRangeMax),
                PreySize.SMALL,
                random.nextInt(smallPreyNutritionMin, smallPreyNutritionMax),
                random.nextDouble(smallPreyDefenseMin, smallPreyDefenseMax)
        );

        Group newSmallPrey = GameObject.create(tf, parent,
                nodeComp, new Collider<>(nodeComp),
                new WanderBehaviour(),
                new Evading(),
                new Prey(smallPreyStat));

        var circle = new Circle(smallPreyStat.visionRange, Color.BLUEVIOLET);
        if (showVision) circle.setOpacity(0.4);
        else circle.setOpacity(0);

        var visionNodeComp = new NodeComponent<>(circle);

        GameObject.create(TransformInit.DEFAULT, newSmallPrey,
                visionNodeComp, new Collider<>(visionNodeComp), new Vision());

        return newSmallPrey;
    };

    public static final Prefab MEDIUM_PREY = (tf, parent) -> {
        var nodeComp = new NodeComponent<>(new Circle(10, Color.GREEN));
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
        if (showVision) circle.setOpacity(0.4);
        else circle.setOpacity(0);

        var visionNodeComp = new NodeComponent<>(circle);

        GameObject.create(TransformInit.DEFAULT, newMediumPrey,
                visionNodeComp, new Collider<>(visionNodeComp), new Vision());

        return newMediumPrey;
    };

    public static final Prefab LARGE_PREY = (tf, parent) -> {
        var nodeComp = new NodeComponent<>(new Circle(20, Color.GREEN));
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
        if (showVision) circle.setOpacity(0.4);
        else circle.setOpacity(0);

        var visionNodeComp = new NodeComponent<>(circle);

        GameObject.create(TransformInit.DEFAULT, newLargePrey,
                visionNodeComp, new Collider<>(visionNodeComp), new Vision());

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

    public static void setSmallPreyVisionRange(double smallPreyVisionRangeMinInput, double smallPreyVisionRangeMaxInput) {
        smallPreyVisionRangeMin = smallPreyVisionRangeMinInput;
        smallPreyVisionRangeMax = smallPreyVisionRangeMaxInput;
    }

    public static void setMediumPreyVisionRange(double mediumPreyVisionRangeMinInput, double mediumPreyVisionRangeMaxInput) {
        mediumPreyVisionRangeMin = mediumPreyVisionRangeMinInput;
        mediumPreyVisionRangeMax = mediumPreyVisionRangeMaxInput;
    }

    public static void setLargePreyVisionRange(double largePreyVisionRangeMinInput, double largePreyVisionRangeMaxInput) {
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
    
    public static void setPredatorStarvationResilience(int predatorStarvationResilienceMinInput, int predatorStarvationResilienceMaxInput) {
        predatorStarvationResilienceMin = predatorStarvationResilienceMinInput;
        predatorStarvationResilienceMax = predatorStarvationResilienceMaxInput;
    }

    public static void setPredatorGroupRadius(double predatorGroupRadiusInput) {
        predatorGroupRadius = predatorGroupRadiusInput;
    }

    public static void resetParameters() {
        predatorSpeedMin = Parameter.DEFAULT_PREDATOR_SPEED_MINIMUM_RANGE;
        predatorSpeedMax = Parameter.DEFAULT_PREDATOR_SPEED_MAXIMUM_RANGE;
        smallPreySpeedMin = Parameter.DEFAULT_SMALL_PREY_SPEED_MINIMUM_RANGE;
        smallPreySpeedMax = Parameter.DEFAULT_SMALL_PREY_SPEED_MAXIMUM_RANGE;
        mediumPreySpeedMin = Parameter.DEFAULT_MEDIUM_PREY_SPEED_MINIMUM_RANGE;
        mediumPreySpeedMax = Parameter.DEFAULT_MEDIUM_PREY_SPEED_MAXIMUM_RANGE;
        largePreySpeedMin = Parameter.DEFAULT_LARGE_PREY_SPEED_MINIMUM_RANGE;
        largePreySpeedMax = Parameter.DEFAULT_LARGE_PREY_SPEED_MINIMUM_RANGE;

        predatorVisionRangeMin = Parameter.DEFAULT_PREDATOR_VISION_MINIMUM_RANGE;
        predatorVisionRangeMax = Parameter.DEFAULT_PREDATOR_VISION_MAXIMUM_RANGE;
        smallPreyVisionRangeMin = Parameter.DEFAULT_SMALL_PREY_VISION_MINIMUM_RANGE;
        smallPreyVisionRangeMax = Parameter.DEFAULT_SMALL_PREY_VISION_MAXIMUM_RANGE;
        mediumPreyVisionRangeMin = Parameter.DEFAULT_MEDIUM_PREY_VISION_MINIMUM_RANGE;
        mediumPreyVisionRangeMax = Parameter.DEFAULT_MEDIUM_PREY_VISION_MAXIMUM_RANGE;
        largePreyVisionRangeMin = Parameter.DEFAULT_LARGE_PREY_VISION_MINIMUM_RANGE;
        largePreyVisionRangeMax = Parameter.DEFAULT_LARGE_PREY_VISION_MAXIMUM_RANGE;

        smallPreyNutritionMin = Parameter.DEFAULT_SMALL_PREY_NUTRITION_MINIMUM_RANGE;
        smallPreyNutritionMax = Parameter.DEFAULT_SMALL_PREY_NUTRITION_MAXIMUM_RANGE;
        mediumPreyNutritionMin = Parameter.DEFAULT_MEDIUM_PREY_NUTRITION_MINIMUM_RANGE;
        mediumPreyNutritionMax = Parameter.DEFAULT_MEDIUM_PREY_NUTRITION_MAXIMUM_RANGE;
        largePreyNutritionMin = Parameter.DEFAULT_LARGE_PREY_NUTRITION_MINIMUM_RANGE;
        largePreyNutritionMax = Parameter.DEFAULT_LARGE_PREY_NUTRITION_MAXIMUM_RANGE;

        smallPreyDefenseMin = Parameter.DEFAULT_SMALL_PREY_DEFENSE_MAXIMUM_RANGE;
        smallPreyDefenseMax = Parameter.DEFAULT_SMALL_PREY_DEFENSE_MAXIMUM_RANGE;
        mediumPreyDefenseMin = Parameter.DEFAULT_MEDIUM_PREY_DEFENSE_MINIMUM_RANGE;
        mediumPreyDefenseMax = Parameter.DEFAULT_MEDIUM_PREY_DEFENSE_MAXIMUM_RANGE;
        largePreyDefenseMin = Parameter.DEFAULT_LARGE_PREY_DEFENSE_MINIMUM_RANGE;
        largePreyDefenseMax = Parameter.DEFAULT_LARGE_PREY_DEFENSE_MAXIMUM_RANGE;

        predatorStarvationResilienceMin = Parameter.DEFAULT_PREDATOR_STARVATION_RESILLIENCE_MINIMUM_RANGE;
        predatorStarvationResilienceMax = Parameter.DEFAULT_PREDATOR_STARVATION_RESILLIENCE_MAXIMUM_RANGE;
        predatorGroupRadius = Parameter.DEFAULT_PREDATOR_GROUP_RADIUS;
    }
}
