package org.quydusaigon.predatorsim.util;

import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;

import javafx.scene.Group;

public class Distance {

    public static double calculateDistance(Group object1, Group object2) {
        return Math.sqrt(Math.pow(GameObject.posX(object2).doubleValue() - GameObject.posX(object1).doubleValue(), 2) +
                Math.pow(GameObject.posY(object2).doubleValue() - GameObject.posY(object1).doubleValue(), 2));
    }
}
