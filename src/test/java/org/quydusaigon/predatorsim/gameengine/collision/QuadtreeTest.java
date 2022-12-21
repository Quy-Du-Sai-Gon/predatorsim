package org.quydusaigon.predatorsim.gameengine.collision;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.quydusaigon.predatorsim.gameengine.component.Collider;
import org.quydusaigon.predatorsim.gameengine.component.NodeComponent;
import org.quydusaigon.predatorsim.gameengine.util.Quadtree;

import javafx.geometry.BoundingBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class QuadtreeTest {

    Quadtree q;
    Set<Collider<?>> res;

    @BeforeEach
    void createQuadtreeAndResultSet() {
        q = new Quadtree(new BoundingBox(0, 0, 200, 100));
        res = new HashSet<>();
    }

    @Test
    void insertColliderAndQueryAreaContainingItShouldWork() {
        var c = new Collider<>(new NodeComponent<>(
                new Rectangle(45, 50, 20, 20)));
        q.insert(c);

        q.query(new Circle(100, 50, 40).getBoundsInParent(), res);
        assertTrue(res.contains(c));
    }

    @Test
    void insertColliderAndQueryAreaNotContainingItShouldWork() {
        var c = new Collider<>(new NodeComponent<>(
                new Rectangle(45, 50, 20, 20)));
        q.insert(c);

        q.query(new Circle(100, 50, 30).getBoundsInParent(), res);
        assertFalse(res.contains(c));
    }

    @Test
    void insertThreeCollidersAndQueryAreaContainingTwoShouldWork() {
        var c1 = new Collider<>(new NodeComponent<>(
                new Rectangle(45, 50, 20, 20)));
        var c2 = new Collider<>(new NodeComponent<>(
                new Rectangle(105, 50, 30, 10)));
        var c3 = new Collider<>(new NodeComponent<>(
                new Circle(160, 20, 25)));
        q.insert(c1);
        q.insert(c2);
        q.insert(c3);

        q.query(new Rectangle(80, 35, 90, 40).getBoundsInParent(), res);
        assertFalse(res.contains(c1));
        assertTrue(res.containsAll(List.of(c2, c3)));
    }
}
