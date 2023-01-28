package org.quydusaigon.predatorsim.gameengine;

import java.util.*;
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

/**
 * This class extends {@link AnimationTimer} and implements custom
 * {@link AnimationTimer#start() start} and {@link AnimationTimer#handle(long)
 * handle} methods to act as the game loop that updates our application.
 */
public class GameLoop extends AnimationTimer {

    /**
     * Reset Time and start the scene again. To be called in
     * {@link App#load(Runnable)}.
     */
    public static void reset() {
        Time.reset();
        GameObject.start(App.root);
    }

    /**
     * Initializes and starts the game loop.
     */
    @Override
    public void start() {
        super.start();

        // Reset the Time system when restarting
        Time.reset();
    }

    /**
     * Handles updates and collision detection for our {@link App} when called. This
     * method is called every frame after the game loop has been
     * {@linkplain #start() started}.
     * 
     * @param now the timestamp of the current frame given in nanoseconds. This
     *            value will be the same for all {@code GameLoop} called during one
     *            frame. The value passed in can be {@code -1} to indicate a move in
     *            {@linkplain App#getTimeStep() time step}, meaning this would
     *            update the next iteration of the game loop.
     */
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
