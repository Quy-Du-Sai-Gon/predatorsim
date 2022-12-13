package org.quydusaigon.predatorsim.gameengine;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameObject {

    private final List<Component> components;

    @SafeVarargs
    public GameObject(Component... components) {
        this.components = new ArrayList<>();
        for (var c : components) {
            addComponent(c);
        }
    }

    public <T extends Component> Optional<T> getComponent(Class<T> type) {
        for (Component c : components) {
            if (type.isAssignableFrom(c.getClass())) {
                return Optional.of(type.cast(c));
            }
        }
        return Optional.empty();
    }

    public <T extends Component> T addComponent(T c) {
        c.setGameObject(this);

        components.add(c);
        c.onAdded();

        return c;
    }

    public <T extends Component> void removeComponent(Class<T> type) {
        for (int i = 0; i < components.size(); i++) {
            var c = components.get(i);
            if (type.isAssignableFrom(c.getClass())) {
                c.onRemoved();
                components.remove(i);
                return;
            }
        }
    }

    public void awake() {
        for (Component c : components) {
            c.awake();
        }
    }

    public void start() {
        for (Component c : components) {
            c.start();
        }
    }

    public void update() {
        for (Component c : components) {
            c.update();
        }
    }
}
