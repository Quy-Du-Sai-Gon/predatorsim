package org.quydusaigon.predatorsim.gameengine.gameobject;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.Stream;

import org.quydusaigon.predatorsim.App;
import org.quydusaigon.predatorsim.gameengine.component.Behaviour;
import org.quydusaigon.predatorsim.gameengine.component.Component;
import org.quydusaigon.predatorsim.gameengine.component.NodeComponent;
import org.quydusaigon.predatorsim.gameengine.util.Prefab;
import org.quydusaigon.predatorsim.gameengine.util.TransformInit;

/**
 * This class contains static methods for operating on GameObjects represented
 * by a special {@link javafx.scene.Group} object. {@link GameObject} is also
 * the internal data structure to store game object related data but outside
 * callers do not need to know about this nor can they instantiate the class
 * themselves.
 *
 * <p>
 * The concept of a {@code GameObject} in the {@code gameengine} package refers
 * to an entity in the scene composing of multiple components.
 * 
 * @see Component
 * @see <a href="https://en.wikipedia.org/wiki/Entity_component_system">Entity
 *      component system</a>
 */
public final class GameObject {

    private final List<Group> children;
    private final List<Component> components;
    private boolean started;

    private GameObject(int initialComponentCount, boolean started) {
        children = new ArrayList<>();
        components = new ArrayList<>(initialComponentCount);
        this.started = started;
    }

    private static GameObject getGameObjectData(Group group) {
        if (group != null) {
            var obj = group.getUserData();
            if (obj instanceof GameObject)
                return (GameObject) obj;
        }
        throw new NotAGameObjectException();
    }

    /*
     * Creation and deletion
     */

    /**
     * Creates a new game object entity composing of specified components. This game
     * object is represented by a {@link Group}.
     * 
     * @param tf         the initial transform data of the game object.
     * @param parent     the parent game object of this newly created game object,
     *                   or {@code null} to create a root game object.
     * @param components a list of components to initialize the game object with.
     * @return the newly created game object (represented by a {@link Group})
     * @throws NotAGameObjectException if {@code parent} is not a valid game object
     *                                 created with {@link GameObject#create}.
     * @see TransformInit
     * @see Component
     */
    public static Group create(TransformInit tf, Group parent, Component... components) {
        // create new Group
        var go = new Group();

        // set Transforms
        posX(go).set(tf.posX);
        posY(go).set(tf.posY);
        rotate(go).set(tf.rotate);
        scaleX(go).set(tf.scaleX);
        scaleY(go).set(tf.scaleY);

        // whether the parent has been started
        boolean started = false;
        if (parent != null) {
            var parentData = getGameObjectData(parent);
            started = parentData.started;

            // add the newly created GameObject to the parent
            parentData.children.add(go);
            parent.getChildren().add(go);
        }

        // create GameObject data
        var data = new GameObject(components.length, started);
        go.setUserData(data);

        // add initial components
        for (var c : components) {
            _addComponent(go, data, c);
        }

        return go;
    }

    /**
     * Remove {@code gameObject} and all of its descendants from their parents while
     * calling {@link #destroy(Component)} on all of their components.
     *
     * @param gameObject the game object to destroy.
     * @throws NotAGameObjectException if {@code gameObject} is not a valid game
     *                                 object created with
     *                                 {@link GameObject#create}.
     */
    public static void destroy(Group gameObject) {
        if (gameObject == null)
            return;
        var data = getGameObjectData(gameObject);

        // recursive call
        data.children.forEach(GameObject::destroy);

        // destroy components
        data.components.forEach(GameObject::destroy);

        // remove GameObject from parent
        getParent(gameObject).ifPresent(parent -> {
            parent.getChildren().remove(gameObject);
        });
    }

    /*
     * GameObject hierarchy
     */

    /**
     * Returns the parent game object of {@code gameObject} if any.
     * 
     * @param gameObject the game object whose parent is to be retrieved.
     * @return an {@link Optional} containing the parent game object if any.
     */
    public static Optional<Group> getParent(Group gameObject) {
        var p = gameObject.getParent();
        return p instanceof Group
                ? Optional.of((Group) p)
                : Optional.empty();
    }

    private static List<Group> _getChildren(Group gameObject) {
        var go = getGameObjectData(gameObject);
        return go.children;
    }

    /**
     * Returns an immutable list of {@code gameObject}'s children game objects.
     * 
     * @param gameObject the game object whose children are to be retrieved.
     * @return an immutable list of the children game objects.
     * @throws NotAGameObjectException if {@code gameObject} is not a valid game
     *                                 object created with
     *                                 {@link GameObject#create}.
     */
    public static List<Group> getChildren(Group gameObject) {
        return Collections.unmodifiableList(
                _getChildren(gameObject));
    }

    /*
     * Instantiation
     */

    /**
     * Instantiates a new game object using {@code prefab}.
     * 
     * @param prefab the prefab for instantiating the new game object.
     * @param tf     the initial transform data of the game object to be created.
     * @param parent the parent of the game object to be created.
     * @see Prefab
     * @see TransformInit
     */
    public static void instantiate(Prefab prefab, TransformInit tf, Group parent) {
        prefab.instantiate(tf, parent);
    }

    /**
     * Instantiates a new game object under {@link App#root} using {@code prefab}.
     * 
     * @param prefab the prefab for instantiating the new game object.
     * @param tf     the initial transform data of the game object to be created.
     * @see Prefab
     * @see TransformInit
     */
    public static void instantiate(Prefab prefab, TransformInit tf) {
        instantiate(prefab, tf, App.root);
    }

    /**
     * Instantiates a new game object under {@link App#root} with
     * {@link TransformInit#DEFAULT} using {@code prefab}.
     * 
     * @param prefab the prefab for instantiating the new game object.
     * @see Prefab
     */
    public static void instantiate(Prefab prefab) {
        instantiate(prefab, TransformInit.DEFAULT);
    }

    /*
     * Components access
     */

    /**
     * Returns the first component of type {@code type} (if any) in
     * {@code gameObject}.
     * 
     * @param <T>        the type of {@link Component} to return.
     * @param gameObject the game object whose component is to be returned.
     * @param type       the class instance of the {@link Component} type to return.
     * @return an {@link Optional} containing the first component matching
     *         {@code type} if any.
     * @throws NotAGameObjectException if {@code gameObject} is not a valid game
     *                                 object created with
     *                                 {@link GameObject#create}.
     * @see Component
     */
    public static <T extends Component> Optional<T> getComponent(Group gameObject, Class<T> type) {
        var go = getGameObjectData(gameObject);

        for (var c : go.components) {
            if (type.isAssignableFrom(c.getClass())) {
                return Optional.of(type.cast(c));
            }
        }
        return Optional.empty();
    }

    /**
     * Returns a stream of components of type {@code type} in {@code gameObject}.
     * 
     * @param <T>        the type of {@link Component} to return.
     * @param gameObject the game object whose components are to be returned.
     * @param type       the class instance of the {@link Component} type to return.
     * @return a {@link Stream} of components in {@code gameObject} that match
     *         {@code type}.
     * @throws NotAGameObjectException if {@code gameObject} is not a valid game
     *                                 object created with
     *                                 {@link GameObject#create}.
     * @see Component
     * @see Stream
     */
    public static <T extends Component> Stream<T> getComponents(Group gameObject, Class<T> type) {
        var go = getGameObjectData(gameObject);

        return go.components.stream()
                .filter(c -> type.isAssignableFrom(c.getClass()))
                .map(type::cast);
    }

    private static <T extends Component> T _addComponent(Group gameObject, GameObject data, T component) {
        // add component to data list
        data.components.add(component);
        // set component's GameObject
        component.setGameObject(gameObject);

        if (component instanceof NodeComponent) {
            // add node to Group if is NodeComponent
            var c = (NodeComponent<?>) component;
            gameObject.getChildren().add(c.getNode());
        } else if (component instanceof Behaviour) {
            // start Behaviour if GameObject has been started
            if (data.started)
                ((Behaviour) component).start();
        }

        return component;
    }

    /**
     * Adds the specified component to {@code gameObject}.
     * 
     * <p>
     * {@code component} is started immediately if it is a {@link Behaviour} and
     * {@code gameObject} is already started.
     * 
     * @param <T>        the type of {@link Component} to add.
     * @param gameObject the game object to add {@code component} to.
     * @param component  the component to add to {@code gameObject}.
     * @return the passed in {@code component}.
     * @throws NotAGameObjectException if {@code gameObject} is not a valid game
     *                                 object created with
     *                                 {@link GameObject#create}.
     * @see Component
     * @see #start(Group)
     */
    public static <T extends Component> T addComponent(Group gameObject, T component) {
        var data = getGameObjectData(gameObject);
        return _addComponent(gameObject, data, component);
    }

    /**
     * Invoke the {@code component}'s {@link Component#onDestroy() onDestroy} event
     * and remove it from its associated game object.
     *
     * @param component the component to be destroyed.
     * @see Component
     */
    public static void destroy(Component component) {
        if (component == null)
            return;
        // invoke onDestroy event
        component.onDestroy();

        var go = component.getGameObject();
        if (go == null)
            return;
        var data = getGameObjectData(go);

        // remove component from data list
        data.components.remove(component);
        // remove component's GameObject
        component.setGameObject(null);

        // remove node from Group if is NodeComponent
        if (component instanceof NodeComponent) {
            var c = (NodeComponent<?>) component;
            go.getChildren().remove(c.getNode());
        }
    }

    /*
     * Transforms
     */

    /**
     * Returns the x position transform property of {@code gameObject} for getting
     * and setting its value.
     * 
     * @param gameObject the game object whose transform property to return.
     * @return the x position transform property of {@code gameObject}.
     * @see Group#translateXProperty()
     */
    public static DoubleProperty posX(Group gameObject) {
        return gameObject.translateXProperty();
    }

    /**
     * Returns the y position transform property of {@code gameObject} for getting
     * and setting its value.
     * 
     * @param gameObject the game object whose transform property to return.
     * @return the y position transform property of {@code gameObject}.
     * @see Group#translateYProperty()
     */
    public static DoubleProperty posY(Group gameObject) {
        return gameObject.translateYProperty();
    }

    /**
     * Returns the rotation transform property of {@code gameObject} for getting
     * and setting its value.
     * 
     * @param gameObject the game object whose transform property to return.
     * @return the rotation transform property of {@code gameObject}.
     * @see Group#rotateProperty()
     */
    public static DoubleProperty rotate(Group gameObject) {
        return gameObject.rotateProperty();
    }

    /**
     * Returns the x scale transform property of {@code gameObject} for getting
     * and setting its value.
     * 
     * @param gameObject the game object whose transform property to return.
     * @return the x scale transform property of {@code gameObject}.
     * @see Group#scaleXProperty()
     */
    public static DoubleProperty scaleX(Group gameObject) {
        return gameObject.scaleXProperty();
    }

    /**
     * Returns the y scale transform property of {@code gameObject} for getting
     * and setting its value.
     * 
     * @param gameObject the game object whose transform property to return.
     * @return the y scale transform property of {@code gameObject}.
     * @see Group#scaleYProperty()
     */
    public static DoubleProperty scaleY(Group gameObject) {
        return gameObject.scaleYProperty();
    }

    /**
     * Iterator for iterating through a GameObject's hierarchy, in a top-down,
     * depth-first-search manner.
     */
    private static class Iterator implements java.util.Iterator<Group> {

        private final Stack<Group> stack;

        private Iterator(Group gameObject) {
            stack = new Stack<>();
            stack.push(gameObject);
        }

        @Override
        public boolean hasNext() {
            return !stack.empty();
        }

        @Override
        public Group next() {
            var go = stack.pop();
            stack.addAll(_getChildren(go));
            return go;
        }

    }

    /**
     * Returns an {@link Iterable} for iterating through {@code gameObject}'s
     * hierarchy (the game object and all of its descendant game objects), in a
     * top-down, depth-first-search manner.
     * 
     * <p>
     * The returned {@link Iterable} may throw {@link NotAGameObjectException} when
     * iterating if the passed in {@code gameObject} is not a valid game object
     * created with {@link GameObject#create}.
     * 
     * @param gameObject the game object whose hierarchy is to be iterated through.
     * @return the {@link Iterable} for iterating through {@code gameObject}'s
     *         hierarchy.
     */
    public static Iterable<Group> iter(Group gameObject) {
        return new Iterable<Group>() {

            @Override
            public java.util.Iterator<Group> iterator() {
                return new Iterator(gameObject);
            }

        };
    }

    /*
     * Iterator operations
     */

    /**
     * Call all of the {@linkplain Behaviour#start() starting scripts} of all
     * {@link Behaviour} components, starting from the {@code root} game object down
     * to its descendants (if {@code root} is not already started).
     * 
     * @param root the game object to start.
     * @throws NotAGameObjectException if {@code root} or any of its descendants are
     *                                 not valid game objects created with
     *                                 {@link GameObject#create}.
     * @see Behaviour
     */
    public static void start(Group root) {
        if (getGameObjectData(root).started)
            return;

        for (var go : iter(root)) {
            // start Behaviours
            getComponents(go, Behaviour.class)
                    .forEach(Behaviour::start);
            getGameObjectData(go).started = true;
        }
    }

    /**
     * Call all of the {@linkplain Behaviour#update() updating scripts} of all
     * {@link Behaviour} components, starting from the {@code root} game object down
     * to its descendants.
     * 
     * @param root the game object to update.
     * @throws NotAGameObjectException if {@code root} or any of its descendants are
     *                                 not valid game objects created with
     *                                 {@link GameObject#create}.
     * @see Behaviour
     */
    public static void update(Group root) {
        for (var go : iter(root)) {
            // update Behaviours
            getComponents(go, Behaviour.class)
                    .forEach(Behaviour::update);
        }
    }
}
