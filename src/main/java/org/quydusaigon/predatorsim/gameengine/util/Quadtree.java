package org.quydusaigon.predatorsim.gameengine.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.quydusaigon.predatorsim.gameengine.component.Collider;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

public class Quadtree {

    private Bounds area;
    private int capacity;
    private List<Collider<?>> colliders;
    private List<Quadtree> children;

    public Quadtree(Bounds area, int capacity) {
        this.area = area;
        this.capacity = capacity;
        this.colliders = new ArrayList<>(capacity);
    }

    public Quadtree(Bounds area) {
        this(area, 4);
    }

    private void subdivide() {
        double x = area.getMinX();
        double y = area.getMinY();
        double w = area.getWidth() / 2;
        double h = area.getHeight() / 2;

        children = List.of(
                new BoundingBox(x, y, w, h),
                new BoundingBox(x + w, y, w, h),
                new BoundingBox(x + w, y + h, w, h),
                new BoundingBox(x, y + h, w, h))
                .stream()
                .map(b -> new Quadtree(b, capacity))
                .toList();
    }

    public boolean insert(Collider<?> collider) {
        if (!collider.collides(area)) {
            return false;
        }

        if (colliders.size() < capacity) {
            colliders.add(collider);
            return true;
        }

        if (children == null) {
            subdivide();
        }

        return children.stream()
                .anyMatch((quadrant) -> quadrant.insert(collider));
    }

    public void query(Collider<?> collider, Set<Collider<?>> found) {
        if (!collider.collides(area)) {
            return;
        }

        colliders.stream()
                .filter(c -> c != collider && c.collides(collider))
                .forEach(found::add);

        if (children != null) {
            children.forEach(q -> q.query(collider, found));
        }
    }

}
