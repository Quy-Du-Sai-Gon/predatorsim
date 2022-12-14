package org.quydusaigon.predatorsim.gameengine.collision;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.quydusaigon.predatorsim.gameengine.components.Collider;
import org.quydusaigon.predatorsim.gameengine.components.colliders.BoxCollider;

public class Quadtree {

    private BoxCollider bound;
    private int capacity;
    private List<Collider> colliders;
    private List<Quadtree> children;

    public Quadtree(BoxCollider bound, int capacity) {
        this.bound = bound;
        this.capacity = capacity;
        this.colliders = new ArrayList<>(capacity);
    }

    public Quadtree(BoxCollider bound) {
        this(bound, 4);
    }

    private void subdivide() {
        double x = bound.getX();
        double y = bound.getY();
        double w = bound.getWidth() / 2;
        double h = bound.getHeight() / 2;

        /*children = List.of(
                new BoxCollider(x, y, w, h),
                new BoxCollider(x + w, y, w, h),
                new BoxCollider(x + w, y + h, w, h),
                new BoxCollider(x, y + h, w, h))
                .stream()
                .map(b -> new Quadtree(b, capacity))
                .toList();*/
    }

    public boolean insert(Collider collider) {
        if (!collider.collides(bound)) {
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

    public void query(Collider collider, Set<Collider> found) {
        if (!collider.collides(bound)) {
            return;
        }

        /*found.addAll(colliders.stream()
                .filter(collider::collides)
                .toList());*/

        if (children != null) {
            children.forEach(q -> q.query(collider, found));
        }
    }

}
