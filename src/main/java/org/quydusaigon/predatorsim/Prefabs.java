package org.quydusaigon.predatorsim;

import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.gameengine.util.TransformInit;

import javafx.scene.Group;

public final class Prefabs {

    private Prefabs() {
    }

    public static Group ROOT() {
        return GameObject.create(TransformInit.ZERO, null);
    }

}
