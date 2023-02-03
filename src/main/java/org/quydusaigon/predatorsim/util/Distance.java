package org.quydusaigon.predatorsim.util;

import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;

import javafx.scene.Group;

public class Distance {

    /**
     * Return the distance between two instance of type {@link Group}.
     * @param object1 the first object.
     * @param object2 the second object.
     * @return the distance between the two objects.
     */
    public static double calculateDistance(Group object1, Group object2) {
        return Math.sqrt(Math.pow(GameObject.posX(object2).doubleValue() - GameObject.posX(object1).doubleValue(), 2) +
                Math.pow(GameObject.posY(object2).doubleValue() - GameObject.posY(object1).doubleValue(), 2));
    }
}
