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
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
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

        var area = new Collider<>(new NodeComponent<>(
                new Circle(100, 50, 40)));
        q.query(area, res);
        assertTrue(res.contains(c));
    }

    void nodeOfAColliderShouldTakeTheTransformsOfItsAncestorsIntoAccount(Node node) {
        node.setTranslateX(0 + 230 - 520 - 30 + 45);
        node.setTranslateY(-40 - 75 + 600 - 25 + 50);

        var p1 = new Group();
        p1.getChildren().add(node);
        p1.setTranslateX(-230);
        p1.setTranslateY(75);

        var p2 = new Group();
        p2.getChildren().add(p1);
        p2.setTranslateX(520);
        p2.setTranslateY(-600);

        var p3 = new Group();
        p3.getChildren().add(p2);
        p3.setTranslateX(30);
        p3.setTranslateY(25);

        // node's global pos = (45, 50)

        var c = new Collider<>(new NodeComponent<>(node));
        q.insert(c);

        // ---------------------------

        var areaNode = new Circle(-50, -25, 40);
        var areaParent = new Group();
        areaParent.getChildren().add(areaNode);
        areaParent.setTranslateX(50 + 100);
        areaParent.setTranslateY(25 + 50);

        // areaNode's global center = (100, 50)

        var area = new Collider<>(new NodeComponent<>(areaNode));

        q.query(area, res);
        assertTrue(res.contains(c));
    }

    @Test
    void shapeNodeOfAColliderShouldTakeTheTransformsOfItsAncestorsIntoAccount() {
        var node = new Rectangle(0, 40, 20, 20);
        nodeOfAColliderShouldTakeTheTransformsOfItsAncestorsIntoAccount(node);
    }

    @Test
    void nonShapeNodeOfAColliderShouldTakeTheTransformsOfItsAncestorsIntoAccount() {
        var node = new ImageView();
        node.setX(0);
        node.setY(40);
        node.setFitWidth(20);
        node.setFitHeight(20);
        nodeOfAColliderShouldTakeTheTransformsOfItsAncestorsIntoAccount(node);
    }

    @Test
    void collisionShouldTakeTheNodesShapeIntoAccount() {
        double r = 0.99 * Math.sqrt(2) / 2;
        var c1 = new Collider<>(new NodeComponent<>(
                new Circle(0, 0, r)));
        var c2 = new Collider<>(new NodeComponent<>(
                new Circle(1, 1, r)));

        q.insert(c1);
        q.query(c2, res);
        assertFalse(res.contains(c1));
    }

    @Test
    void insertColliderAndQueryAreaNotContainingItShouldWork() {
        var c = new Collider<>(new NodeComponent<>(
                new Rectangle(45, 50, 20, 20)));
        q.insert(c);

        var area = new Collider<>(new NodeComponent<>(
                new Circle(100, 50, 30)));
        q.query(area, res);
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

        var area = new Collider<>(new NodeComponent<>(
                new Rectangle(80, 35, 90, 40)));
        q.query(area, res);
        assertFalse(res.contains(c1));
        assertTrue(res.containsAll(List.of(c2, c3)));
    }
}
