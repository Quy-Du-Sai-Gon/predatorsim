package org.quydusaigon.predatorsim.gameengine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

import org.quydusaigon.predatorsim.App;
import org.quydusaigon.predatorsim.gameengine.component.Behaviour;
import org.quydusaigon.predatorsim.gameengine.component.Collider;
import org.quydusaigon.predatorsim.gameengine.event.CollisionEvent;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.gameengine.util.Helper;
import org.quydusaigon.predatorsim.gameengine.util.Quadtree;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.util.Pair;

public class GameLoop extends AnimationTimer {

    @Override
    public void start() {
        super.start();

        // Reset the Time system when restarting
        Time.reset();

        /*
         * start scene
         */
        GameObject.start(App.root);
    }

    @Override
    public void handle(long now) {
        // Update Time
        Time.update(now);

        /*
         * update scene
         */
        GameObject.update(App.root);

        /*
         * collision detection
         */
        detectAndRaiseCollisionEvents(App.root);
    }

    private Map<Group, Set<Pair<Collider<?>, Collider<?>>>> previousCollisions = Map.of();

    private static final Set<Pair<Collider<?>, Collider<?>>> NO_PAIR = new HashSet<>();

    private void detectAndRaiseCollisionEvents(Group root) {
        // quadtree for optimization
        var q = new Quadtree(root.getLayoutBounds());

        // insert all colliders into the quadtree
        for (var go : GameObject.iter(root)) {
            GameObject.getComponents(go, Collider.class)
                    .forEach(q::insert);
        }

        /*
         * collision detection and event raising
         */

        Map<Group, Set<Pair<Collider<?>, Collider<?>>>> currentCollisions = new HashMap<>();

        // for each GameObject
        for (var go : GameObject.iter(root)) {

            // the Behaviours on which to raise collision events, if any
            var behaviours = Helper.lazyValue(() -> GameObject.getComponents(go, Behaviour.class).toList());

            // lambda for raising a collision event with a particular Collider pair for this
            // GameObject
            var raiseEvent = (BiConsumer<CollisionEvent, Pair<Collider<?>, Collider<?>>>)

            (event, pair) -> behaviours.get().forEach(b -> event.raise(b, pair.getKey(), pair.getValue()));

            // sets to store colliding pairs
            var prevPairs = previousCollisions.getOrDefault(go, NO_PAIR);
            var currPairs = Helper.lazyValue(() -> {
                Set<Pair<Collider<?>, Collider<?>>> pairs = new HashSet<>();
                currentCollisions.put(go, pairs);
                return pairs;
            });

            // for each collider
            GameObject.getComponents(go, Collider.class).forEach(collider -> {
                // query for the colliders it collides with
                var others = new HashSet<Collider<?>>();
                q.query(collider, others);

                for (var other : others) {
                    // for each colliding pair
                    var pair = new Pair<Collider<?>, Collider<?>>(collider, other);
                    currPairs.get().add(pair);

                    CollisionEvent event = prevPairs.remove(pair)
                            // successfully removed `pair` from `prevPairs` => `pair` was colliding in
                            // previous frame, and is currently also => collision stay
                            ? Behaviour::onCollisionStay
                            // failed to remove `pair` from `prevPairs` => `pair` was not colliding in
                            // previous frame, but is now => collision enter
                            : Behaviour::onCollisionEnter;

                    raiseEvent.accept(event, pair);
                }
            }); // for each collider

            // after every colliding pairs in the current frame have been removed from
            // `prevPairs`, the remaining pairs in `prevPairs` are those colliding in the
            // previous frame but not anymore in this frame => collision exit
            prevPairs.forEach(pair -> raiseEvent.accept(Behaviour::onCollisionExit, pair));

        } // for each GameObject

        previousCollisions = currentCollisions;
    } // detectAndRaiseCollisionEvents

} // GameLoop
