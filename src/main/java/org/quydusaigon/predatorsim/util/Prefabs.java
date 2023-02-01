package org.quydusaigon.predatorsim.util;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

import org.quydusaigon.predatorsim.UI;
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

        private static Color predatorColor = Color.RED;
        private static Color smallPreyColor = Color.GREEN;
        private static Color mediumPreyColor = Color.GREEN;
        private static Color largePreyColor = Color.GREEN;

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


        private static String predatorImageURL;
        private static String smallPreyImageURL;
        private static String mediumPreyImageURL;
        private static String largePreyImageURL;

        public static void setPredatorImageURL(String predatorImageURL) {
                Prefabs.predatorImageURL = predatorImageURL;
        }

        public static void setSmallPreyImageURL(String smallPreyImageURL) {
                Prefabs.smallPreyImageURL = smallPreyImageURL;
        }

        public static void setMediumPreyImageURL(String mediumPreyImageURL) {
                Prefabs.mediumPreyImageURL = mediumPreyImageURL;
        }

        public static void setLargePreyImageURL(String largePreyImageURL) {
                Prefabs.largePreyImageURL = largePreyImageURL;
        }

        private static Random random = new Random();

        private Prefabs() {
        }

        public static final Prefab STATUS_DISPLAY = (tf, parent) -> {
                return GameObject.create(TransformInit.DEFAULT, parent, new StatDisplay());
        };

        public static final Prefab PREDATOR = (tf, parent) -> {
                var circle = new Circle(10, predatorColor);
                var nodeComp = new NodeComponent<>(circle);
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
                                new Predator(predatorStat),
                                new Draggable());

                var visionNodeComp = new NodeComponent<>(new Circle(predatorStat.visionRange, Color.AQUAMARINE));
                GameObject.create(TransformInit.DEFAULT, newPredator,
                                visionNodeComp, new Collider<>(visionNodeComp), new Vision());

                GameObject.instantiate(STATUS_DISPLAY, newPredator);

                if (predatorImageURL != null && UI.isPredatorCheckBoxEnable()) {
                        ImageView imageView = new ImageView(new Image(predatorImageURL, 20, 20, false, false));
                        imageView.setTranslateX(-10);
                        imageView.setTranslateY(-10);
                        GameObject.addComponent(newPredator, new NodeComponent<>(
                                imageView

                        ));
                        circle.setVisible(false);
                }

                return newPredator;
        };

        public static final Prefab SMALL_PREY = (tf, parent) -> {
                var circle = new Circle(10, smallPreyColor);
                var nodeComp = new NodeComponent<>(circle);
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
                                new Prey(smallPreyStat),
                                new Draggable());

                var visionNodeComp = new NodeComponent<>(new Circle(smallPreyStat.visionRange, Color.BLUEVIOLET));
                GameObject.create(TransformInit.DEFAULT, newSmallPrey,
                                visionNodeComp, new Collider<>(visionNodeComp), new Vision());

                GameObject.instantiate(STATUS_DISPLAY, newSmallPrey);

                if (smallPreyImageURL != null && UI.isSmallPreyCheckBoxEnable()) {
                        ImageView imageView = new ImageView(new Image(smallPreyImageURL, 20, 20, false, false));
                        imageView.setTranslateX(-10);
                        imageView.setTranslateY(-10);
                        GameObject.addComponent(newSmallPrey, new NodeComponent<>(
                                imageView

                        ));
                        circle.setVisible(false);
                }

                return newSmallPrey;
        };

        public static final Prefab MEDIUM_PREY = (tf, parent) -> {
                var circle = new Circle(20, mediumPreyColor);
                var nodeComp = new NodeComponent<>(circle);
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
                                new Prey(mediumPreyStat),
                                new Draggable());

                var visionNodeComp = new NodeComponent<>(new Circle(mediumPreyStat.visionRange, Color.BLUEVIOLET));
                GameObject.create(TransformInit.DEFAULT, newMediumPrey,
                                visionNodeComp, new Collider<>(visionNodeComp), new Vision());

                GameObject.instantiate(STATUS_DISPLAY, newMediumPrey);

                if (mediumPreyImageURL != null && UI.isMediumPreyCheckBoxEnable()) {
                        ImageView imageView = new ImageView(new Image(mediumPreyImageURL, 40, 40, false, false));
                        imageView.setTranslateX(-20);
                        imageView.setTranslateY(-20);
                        GameObject.addComponent(newMediumPrey, new NodeComponent<>(imageView));
                        circle.setVisible(false);
                }
                return newMediumPrey;
        };

        public static final Prefab LARGE_PREY = (tf, parent) -> {
                var circle = new Circle(30, largePreyColor);
                var nodeComp = new NodeComponent<>(circle);
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
                                new Prey(largePreyStat),
                                new Draggable());

                var visionNodeComp = new NodeComponent<>(new Circle(largePreyStat.visionRange, Color.BLUEVIOLET));
                GameObject.create(TransformInit.DEFAULT, newLargePrey,
                                visionNodeComp, new Collider<>(visionNodeComp), new Vision());

                GameObject.instantiate(STATUS_DISPLAY, newLargePrey);

                if (largePreyImageURL != null && UI.isLargePreyCheckBoxEnable()) {
                        ImageView imageView = new ImageView(new Image(largePreyImageURL, 60, 60, false, false));
                        imageView.setTranslateX(-30);
                        imageView.setTranslateY(-30);
                        GameObject.addComponent(newLargePrey, new NodeComponent<>(
                                imageView

                        ));
                        circle.setVisible(false);
                }
                return newLargePrey;
        };

}
