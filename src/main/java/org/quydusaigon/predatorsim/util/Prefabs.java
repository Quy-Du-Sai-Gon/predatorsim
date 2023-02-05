package org.quydusaigon.predatorsim.util;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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

    /**
     * Add image to animal.
     * 
     * @param normalDisplayNodeComp the {@link NodeComponent} whose game object is
     *                              the target for adding the image.
     * @param imagePath             the image path.
     * @param size                  the size of the animal.
     */
    private static void addImageView(NodeComponent<?> normalDisplayNodeComp, String imagePath, double size) {
        InputStream inputStream;

        try {
            inputStream = new FileInputStream(imagePath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + imagePath);
            return;
        }

        ImageView imageView = new ImageView(new Image(inputStream, size, size, false, false));
        var halfSize = size / 2;
        imageView.setTranslateX(-halfSize);
        imageView.setTranslateY(-halfSize);
        normalDisplayNodeComp.addComponent(new NodeComponent<>(imageView));

        normalDisplayNodeComp.getNode().setVisible(false);
    }

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

        Group newPredator = GameObject.create(tf, null,
                new Velocity(), nodeComp, new Collider<>(nodeComp),
                new Predator(predatorStat),
                new Draggable());

        var visionNodeComp = new NodeComponent<>(new Circle(predatorStat.visionRange, Color.AQUAMARINE));
        GameObject.create(TransformInit.DEFAULT, newPredator,
                visionNodeComp, new Collider<>(visionNodeComp), new Vision());

        GameObject.instantiate(STATUS_DISPLAY, newPredator);

        if (predatorImageURL != null && UI.isPredatorCheckBoxEnable()) {
            addImageView(nodeComp, predatorImageURL, circle.getRadius() * 2);
        }

        GameObject.setParent(newPredator, parent);

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

        Group newSmallPrey = GameObject.create(tf, null,
                new Velocity(), nodeComp, new Collider<>(nodeComp),
                new Prey(smallPreyStat),
                new Draggable());

        var visionNodeComp = new NodeComponent<>(new Circle(smallPreyStat.visionRange, Color.BLUEVIOLET));
        GameObject.create(TransformInit.DEFAULT, newSmallPrey,
                visionNodeComp, new Collider<>(visionNodeComp), new Vision());

        GameObject.instantiate(STATUS_DISPLAY, newSmallPrey);

        if (smallPreyImageURL != null && UI.isSmallPreyCheckBoxEnable()) {
            addImageView(nodeComp, smallPreyImageURL, circle.getRadius() * 2);
        }

        GameObject.setParent(newSmallPrey, parent);

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

        Group newMediumPrey = GameObject.create(tf, null,
                new Velocity(), nodeComp, new Collider<>(nodeComp),
                new Prey(mediumPreyStat),
                new Draggable());

        var visionNodeComp = new NodeComponent<>(new Circle(mediumPreyStat.visionRange, Color.BLUEVIOLET));
        GameObject.create(TransformInit.DEFAULT, newMediumPrey,
                visionNodeComp, new Collider<>(visionNodeComp), new Vision());

        GameObject.instantiate(STATUS_DISPLAY, newMediumPrey);

        if (mediumPreyImageURL != null && UI.isMediumPreyCheckBoxEnable()) {
            addImageView(nodeComp, mediumPreyImageURL, circle.getRadius() * 2);
        }

        GameObject.setParent(newMediumPrey, parent);

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

        Group newLargePrey = GameObject.create(tf, null,
                new Velocity(), nodeComp, new Collider<>(nodeComp),
                new Prey(largePreyStat),
                new Draggable());

        var visionNodeComp = new NodeComponent<>(new Circle(largePreyStat.visionRange, Color.BLUEVIOLET));
        GameObject.create(TransformInit.DEFAULT, newLargePrey,
                visionNodeComp, new Collider<>(visionNodeComp), new Vision());

        GameObject.instantiate(STATUS_DISPLAY, newLargePrey);

        if (largePreyImageURL != null && UI.isLargePreyCheckBoxEnable()) {
            addImageView(nodeComp, largePreyImageURL, circle.getRadius() * 2);
        }

        GameObject.setParent(newLargePrey, parent);

        return newLargePrey;
    };

}
