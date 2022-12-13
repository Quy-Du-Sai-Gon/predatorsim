package org.quydusaigon.predatorsim.gameengine.collision;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.quydusaigon.predatorsim.gameengine.components.Collider;
import org.quydusaigon.predatorsim.gameengine.components.colliders.BoxCollider;
import org.quydusaigon.predatorsim.gameengine.components.colliders.CircleCollider;

public class QuadtreeTest {

    Quadtree q;
    Set<Collider> res;

    @BeforeEach
    void createQuadtreeAndResultSet() {
        q = new Quadtree(new BoxCollider(0, 0, 200, 100));
        res = new HashSet<>();
    }

    @Test
    void insertColliderAndQueryAreaContainingItShouldWork() {
        Collider c = new BoxCollider(45, 50, 20, 20);
        q.insert(c);

        q.query(new CircleCollider(100, 50, 40), res);
        assertTrue(res.contains(c));
    }

    @Test
    void insertColliderAndQueryAreaNotContainingItShouldWork() {
        Collider c = new BoxCollider(45, 50, 20, 20);
        q.insert(c);

        q.query(new CircleCollider(100, 50, 30), res);
        assertFalse(res.contains(c));
    }

    @Test
    void insertThreeCollidersAndQueryAreaContainingTwoShouldWork() {
        Collider c1 = new BoxCollider(45, 50, 20, 20);
        Collider c2 = new BoxCollider(105, 50, 30, 10);
        Collider c3 = new CircleCollider(160, 20, 25);
        q.insert(c1);
        q.insert(c2);
        q.insert(c3);

        q.query(new BoxCollider(80, 35, 90, 40), res);
        assertFalse(res.contains(c1));
        assertTrue(res.containsAll(List.of(c2, c3)));
    }
}
