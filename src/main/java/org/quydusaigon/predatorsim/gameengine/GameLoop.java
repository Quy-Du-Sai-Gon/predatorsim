package org.quydusaigon.predatorsim.gameengine;

import java.util.HashSet;

import org.quydusaigon.predatorsim.App;
import org.quydusaigon.predatorsim.gameengine.component.Collider;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.gameengine.util.Quadtree;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;

public class GameLoop extends AnimationTimer {

    @Override
    public void start() {
        super.start();

        /*
         * start scene
         */
        GameObject.start(App.root);
    }

    @Override
    public void handle(long now) {
        /*
         * update scene
         */
        GameObject.update(App.root);

        /*
         * collision detection
         */
        detectAndRaiseCollisionEvents(App.root);
    }

    private void detectAndRaiseCollisionEvents(Group root) {
        // quadtree for optimization
        var q = new Quadtree(root.getBoundsInParent());

        // insert all colliders into the quadtree
        for (var go : GameObject.iter(root)) {
            GameObject.getComponents(go, Collider.class)
                    .forEach(q::insert);
        }

        // collision detection
        for (var go : GameObject.iter(root)) {
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
