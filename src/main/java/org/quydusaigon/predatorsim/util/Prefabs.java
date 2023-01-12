package org.quydusaigon.predatorsim.util;

import org.quydusaigon.predatorsim.behaviours.Animal;
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
        return GameObject.create(tf,parent,new Animal());
    }

}
