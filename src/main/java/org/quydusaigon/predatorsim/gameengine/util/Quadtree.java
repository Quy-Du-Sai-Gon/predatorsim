package org.quydusaigon.predatorsim.gameengine.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.quydusaigon.predatorsim.gameengine.component.Collider;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

/**
 * A Quadtree implementation for collision detection optimization.
 * 
 * @see <a href="https://en.wikipedia.org/wiki/Quadtree">Quadtree wiki</a>
 */
public class Quadtree {

    private Bounds area;
    private int capacity;
    private List<Collider<?>> colliders;
    private List<Quadtree> children;

    /**
     * Creates a new quadtree.
     * 
     * @param area     a {@link Bounds} as the bounding area of the quadtree.
     * @param capacity the total number of items a quadtree node can store. Defaults
     *                 to 4.
     * @see Bounds
     */
    public Quadtree(Bounds area, int capacity) {
        this.area = area;
        this.capacity = capacity;
        this.colliders = new ArrayList<>(capacity);
    }

    /**
     * Creates a new quadtree.
     * 
     * @param area a {@link Bounds} as the bounding area of the quadtree.
     * @see Bounds
     */
    public Quadtree(Bounds area) {
        this(area, 4);
    }

    /**
     * Splits the quad tree into 4 subnodes to store more items.
     */
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

    /**
     * Inserts a new collider into the quadtree. The insertion is only successful if
     * the collider is within the quadtree's bounding area.
     * 
     * <p>
     * This method is used to build up the quadtree for later queries.
     * 
     * @param collider the collider to insert.
     * @return whether the insertion was successful.
     * @see Collider
     * @see #query
     */
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

    /**
     * Queries the quadtree for any collider that collides with the passed in
     * {@code collider}. All colliders in the quadtree that collides with the passed
     * in {@code collider} are added to the {@code found} {@link Set}, with
     * exception to itself if {@code collider} happens to be in the quadtree as
     * well.
     * 
     * @param collider the collider for querying.
     * @param found    the set to which to add any colliding collider found.
     * @see Collider
     * @see #insert
     */
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
