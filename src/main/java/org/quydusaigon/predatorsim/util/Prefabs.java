package org.quydusaigon.predatorsim.util;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

import org.quydusaigon.predatorsim.behaviours.animalBehaviours.*;
import org.quydusaigon.predatorsim.behaviours.animals.Predator;
import org.quydusaigon.predatorsim.behaviours.animals.Prey;
import org.quydusaigon.predatorsim.behaviours.util.Draggable;
import org.quydusaigon.predatorsim.behaviours.util.Velocity;
import org.quydusaigon.predatorsim.gameengine.component.Collider;
import org.quydusaigon.predatorsim.gameengine.component.NodeComponent;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.gameengine.util.Prefab;
import org.quydusaigon.predatorsim.gameengine.util.TransformInit;

import javafx.scene.Group;

public final class Prefabs {


        public static void setPredatorColor(Color predatorColor) {
                Prefabs.predatorColor = predatorColor;
        }

        public static void setSmallPreyColor(Color smallPreyColor) {
                Prefabs.smallPreyColor = smallPreyColor;
        }

        public static void setMediumPreyColor(Color mediumPreyColor) {
                Prefabs.mediumPreyColor = mediumPreyColor;
        }

        public static void setLargePreyColor(Color largePreyColor) {
                Prefabs.largePreyColor = largePreyColor;
        }

        private static Color predatorColor = Color.RED;
        private static Color smallPreyColor = Color.GREEN;
        private static Color mediumPreyColor = Color.GREEN;
        private static Color largePreyColor = Color.GREEN;

        private static Random random = new Random();

        private Prefabs() {
        }

        public static final Prefab STATUS_DISPLAY = (tf, parent) -> {
                return GameObject.create(TransformInit.DEFAULT, parent, new StatDisplay());
        };

        public static final Prefab PREDATOR = (tf, parent) -> {
                var nodeComp = new NodeComponent<>(new Circle(10, predatorColor));
                PredatorStat predatorStat = new PredatorStat(
                                (Parameter.getPredatorSpeedMaximumRange() - Parameter.getPredatorSpeedMinimumRange())
                                                * random.nextDouble() + Parameter.getPredatorSpeedMinimumRange(),
                                (Parameter.getPredatorVisionMaximumRange() - Parameter.getPredatorVisionMinimumRange())
                                                * random.nextDouble() + Parameter.getPredatorVisionMinimumRange(),
                                (int) ((Parameter.getPredatorStarvationResillienceMaximumRange()
                                                - Parameter.getPredatorStarvationResillienceMinimumRange())
                                                * random.nextDouble()
                                                + Parameter.getPredatorStarvationResillienceMinimumRange()),
                                Parameter.getPredatorGroupRadius(),
                                400);

                Group newPredator = GameObject.create(tf, parent,
                                new Velocity(), nodeComp, new Collider<>(nodeComp),
                                new WanderBehaviour(),
                                new HuntingInGroup(),
                                new HuntingAlone(),
                                new PredatorDead(),
                                new Predator(predatorStat),
                                new Draggable());

                var visionNodeComp = new NodeComponent<>(new Circle(predatorStat.visionRange, Color.AQUAMARINE));
                GameObject.create(TransformInit.DEFAULT, newPredator,
                                visionNodeComp, new Collider<>(visionNodeComp), new Vision());

                GameObject.instantiate(STATUS_DISPLAY, newPredator);

                return newPredator;
        };

        public static final Prefab SMALL_PREY = (tf, parent) -> {
                var nodeComp = new NodeComponent<>(new Circle(10, smallPreyColor));
                PreyStat smallPreyStat = new PreyStat(
                                (Parameter.getSmallPreySpeedMaximumRange() - Parameter.getSmallPreySpeedMinimumRange())
                                                * random.nextDouble() + Parameter.getSmallPreySpeedMinimumRange(),
                                (Parameter.getSmallPreyVisionMaximumRange()
                                                - Parameter.getSmallPreyVisionMinimumRange()) * random.nextDouble()
                                                + Parameter.getSmallPreyVisionMinimumRange(),
                                PreySize.SMALL,
                                (int) ((Parameter.getSmallPreyNutritionMaximumRange()
                                                - Parameter.getSmallPreyNutritionMinimumRange()) * random.nextDouble()
                                                + Parameter.getSmallPreyNutritionMinimumRange()),
                                (Parameter.getSmallPreyDefenseMaximumRange()
                                                - Parameter.getSmallPreyDefenseMinimumRange()) * random.nextDouble()
                                                + Parameter.getSmallPreyDefenseMinimumRange());

                Group newSmallPrey = GameObject.create(tf, parent,
                                new Velocity(), nodeComp, new Collider<>(nodeComp),
                                new WanderBehaviour(),
                                new Evading(),
                                new PreyDead(),
                                new Prey(smallPreyStat),
                                new Draggable());

                var visionNodeComp = new NodeComponent<>(new Circle(smallPreyStat.visionRange, Color.BLUEVIOLET));
                GameObject.create(TransformInit.DEFAULT, newSmallPrey,
                                visionNodeComp, new Collider<>(visionNodeComp), new Vision());

                GameObject.instantiate(STATUS_DISPLAY, newSmallPrey);

                return newSmallPrey;
        };

        public static final Prefab MEDIUM_PREY = (tf, parent) -> {
                var nodeComp = new NodeComponent<>(new Circle(20, mediumPreyColor));
                PreyStat mediumPreyStat = new PreyStat(
                                (Parameter.getMediumPreySpeedMaximumRange()
                                                - Parameter.getMediumPreySpeedMinimumRange()) * random.nextDouble()
                                                + Parameter.getMediumPreySpeedMinimumRange(),
                                (Parameter.getMediumPreyVisionMaximumRange()
                                                - Parameter.getMediumPreyVisionMinimumRange()) * random.nextDouble()
                                                + Parameter.getMediumPreyVisionMinimumRange(),
                                PreySize.MEDIUM,
                                (int) ((Parameter.getMediumPreyNutritionMaximumRange()
                                                - Parameter.getMediumPreyNutritionMinimumRange()) * random.nextDouble()
                                                + Parameter.getMediumPreyNutritionMinimumRange()),
                                (Parameter.getMediumPreyDefenseMaximumRange()
                                                - Parameter.getMediumPreyDefenseMinimumRange()) * random.nextDouble()
                                                + Parameter.getMediumPreyDefenseMinimumRange());

                Group newMediumPrey = GameObject.create(tf, parent,
                                new Velocity(), nodeComp, new Collider<>(nodeComp),
                                new WanderBehaviour(),
                                new Evading(),
                                new PreyDead(),
                                new Prey(mediumPreyStat),
                                new Draggable());

                var visionNodeComp = new NodeComponent<>(new Circle(mediumPreyStat.visionRange, Color.BLUEVIOLET));
                GameObject.create(TransformInit.DEFAULT, newMediumPrey,
                                visionNodeComp, new Collider<>(visionNodeComp), new Vision());

                GameObject.instantiate(STATUS_DISPLAY, newMediumPrey);

                return newMediumPrey;
        };

        public static final Prefab LARGE_PREY = (tf, parent) -> {
                var nodeComp = new NodeComponent<>(new Circle(30, largePreyColor));
                PreyStat largePreyStat = new PreyStat(
                                (Parameter.getLargePreySpeedMaximumRange() - Parameter.getLargePreySpeedMinimumRange())
                                                * random.nextDouble() + Parameter.getLargePreySpeedMinimumRange(),
                                (Parameter.getLargePreyVisionMaximumRange()
                                                - Parameter.getLargePreyVisionMinimumRange()) * random.nextDouble()
                                                + Parameter.getLargePreyVisionMinimumRange(),
                                PreySize.LARGE,
                                (int) ((Parameter.getLargePreyNutritionMaximumRange()
                                                - Parameter.getLargePreyNutritionMinimumRange()) * random.nextDouble()
                                                + Parameter.getLargePreyNutritionMinimumRange()),
                                (Parameter.getLargePreyDefenseMaximumRange()
                                                - Parameter.getLargePreyDefenseMinimumRange()) * random.nextDouble()
                                                + Parameter.getLargePreyDefenseMinimumRange());

                Group newLargePrey = GameObject.create(tf, parent,
                                new Velocity(), nodeComp, new Collider<>(nodeComp),
                                new WanderBehaviour(),
                                new Evading(),
                                new PreyDead(),
                                new Prey(largePreyStat),
                                new Draggable());

                var visionNodeComp = new NodeComponent<>(new Circle(largePreyStat.visionRange, Color.BLUEVIOLET));
                GameObject.create(TransformInit.DEFAULT, newLargePrey,
                                visionNodeComp, new Collider<>(visionNodeComp), new Vision());

                GameObject.instantiate(STATUS_DISPLAY, newLargePrey);

                return newLargePrey;
        };

}
