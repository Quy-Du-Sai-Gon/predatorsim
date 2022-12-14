package org.quydusaigon.predatorsim.gameengine;

import org.quydusaigon.predatorsim.Animal;
import org.quydusaigon.predatorsim.gameengine.components.colliders.BoxCollider;

public class Prefabs {

    private Prefabs() {}

    public static final Prefab animal = (tf) -> {
        return new GameObject(tf, new BoxCollider(0, 0, 0,0), new Animal());
    };
}
