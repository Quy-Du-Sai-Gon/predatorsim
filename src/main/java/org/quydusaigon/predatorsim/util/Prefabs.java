package org.quydusaigon.predatorsim.util;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.quydusaigon.predatorsim.behaviours.Animal;
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

    public static Group ROOT() {
        return GameObject.create(TransformInit.DEFAULT, null);
    }

    public static final Prefab PREDATOR = (tf, parent) -> {
        var nodeComp = new NodeComponent<>(new Circle(10, Color.RED));
        return GameObject.create(tf,parent,
                nodeComp, new Collider<>(nodeComp),
                new WanderBehaviour(),
                new Predator(new PredatorStat(4,2,2,2,2))
        );
    };

    public static final Prefab SMALL_PREY = (tf, parent) -> {
        var nodeComp = new NodeComponent<>(new Circle(5, Color.GREEN));
        return GameObject.create(tf,parent,
                nodeComp, new Collider<>(nodeComp),
                new WanderBehaviour(),
                new Prey(new PreyStat(3, 2, PreySize.SMALL,2))
        );
    };

    public static final Prefab MEDIUM_PREY = (tf, parent) -> {
        var nodeComp = new NodeComponent<>(new Circle(10, Color.GREEN));
        return GameObject.create(tf,parent,
                nodeComp, new Collider<>(nodeComp),
                new WanderBehaviour(),
                new Prey(new PreyStat(2, 2, PreySize.MEDIUM,2))
        );
    };
    
    public static final Prefab LARGE_PREY = (tf, parent) -> {
        var nodeComp = new NodeComponent<>(new Circle(20, Color.GREEN));
        return GameObject.create(tf,parent,
                nodeComp, new Collider<>(nodeComp),
                new WanderBehaviour(),
                new Prey(new PreyStat(1, 2, PreySize.LARGE,2))
        );
    };

}
