package org.quydusaigon.predatorsim.util;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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

    private Prefabs() {
    }

    public static final Prefab PREDATOR = (tf, parent) -> {
        var nodeComp = new NodeComponent<>(new Circle(10, Color.RED));
        PredatorStat predatorStat = new PredatorStat(4, 100, 2, 2, 2);

        Group newPredator = GameObject.create(tf, parent,
                nodeComp, new Collider<>(nodeComp),
                new WanderBehaviour(),
                new HuntingAlone(),
                new Predator(predatorStat));

        var circle = new Circle(predatorStat.visionRange, Color.AQUAMARINE);
        circle.setOpacity(0.2);

        var visionNodeComp = new NodeComponent<>(circle);

        GameObject.create(TransformInit.DEFAULT, newPredator,
                visionNodeComp, new Collider<>(visionNodeComp), new Vision());

        return newPredator;
    };

    public static final Prefab SMALL_PREY = (tf, parent) -> {
        var nodeComp = new NodeComponent<>(new Circle(10, Color.GREEN));
        PreyStat smallPreyStat = new PreyStat(3, 75, PreySize.SMALL, 2);

        Group newSmallPrey = GameObject.create(tf, parent,
                nodeComp, new Collider<>(nodeComp),
                new WanderBehaviour(),
                new Evading(),
                new Prey(smallPreyStat));

        var circle = new Circle(smallPreyStat.visionRange, Color.BLUEVIOLET);
        circle.setOpacity(0.2);

        var visionNodeComp = new NodeComponent<>(circle);

        GameObject.create(TransformInit.DEFAULT, newSmallPrey,
                visionNodeComp, new Collider<>(visionNodeComp), new Vision());

        return newSmallPrey;
    };

    public static final Prefab MEDIUM_PREY = (tf, parent) -> {
        var nodeComp = new NodeComponent<>(new Circle(10, Color.GREEN));
        PreyStat mediumPreyStat = new PreyStat(2, 75, PreySize.MEDIUM, 3);

        Group newMediumPrey = GameObject.create(tf, parent,
                nodeComp, new Collider<>(nodeComp),
                new WanderBehaviour(),
                new Evading(),
                new Prey(mediumPreyStat));

        var circle = new Circle(mediumPreyStat.visionRange, Color.BLUEVIOLET);
        circle.setOpacity(0.2);

        var visionNodeComp = new NodeComponent<>(circle);

        GameObject.create(TransformInit.DEFAULT, newMediumPrey,
                visionNodeComp, new Collider<>(visionNodeComp), new Vision());

        return newMediumPrey;
    };

    public static final Prefab LARGE_PREY = (tf, parent) -> {
        var nodeComp = new NodeComponent<>(new Circle(20, Color.GREEN));
        PreyStat largePreyStat = new PreyStat(1, 75, PreySize.LARGE, 4);

        Group newLargePrey = GameObject.create(tf, parent,
                nodeComp, new Collider<>(nodeComp),
                new WanderBehaviour(),
                new Evading(),
                new Prey(largePreyStat));

        var circle = new Circle(largePreyStat.visionRange, Color.BLUEVIOLET);
        circle.setOpacity(0.4);

        var visionNodeComp = new NodeComponent<>(circle);

        GameObject.create(TransformInit.DEFAULT, newLargePrey,
                visionNodeComp, new Collider<>(visionNodeComp), new Vision());

        return newLargePrey;
    };

}
