package org.quydusaigon.predatorsim.gameengine;

import java.util.HashSet;

import org.quydusaigon.predatorsim.App;
import org.quydusaigon.predatorsim.gameengine.component.Collider;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.gameengine.util.Quadtree;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {

    @Override
    public void handle(long now) {
        /*
         * update scene
         */
        GameObject.update(App.root);

        /*
         * collision detection
         */

        // quadtree for optimization
        var q = new Quadtree(App.root.getBoundsInParent());

        // insert all colliders into the quadtree
        for (var go : GameObject.iter(App.root)) {
            GameObject.getComponents(go, Collider.class)
                    .forEach(q::insert);
        }

        // collision detection
        for (var go : GameObject.iter(App.root)) {
            GameObject.getComponents(go, Collider.class).forEach(collider -> {
                // for each collider
                // query for the colliders it collides with
                var others = new HashSet<Collider<?>>();
                q.query(collider, others);

                for (var other : others) {
                    // self check
                    if (collider == other)
                        continue;

                    // trigger collision events for the `collider` - `other` pair
                }
            });
        }
    }

}
