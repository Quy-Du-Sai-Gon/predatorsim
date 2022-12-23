package org.quydusaigon.predatorsim.gameengine.component;

public abstract class Behaviour extends Component {

    public void start() {
    }

    public void update() {
    }

    public void onCollisionEnter(Collider<?> collider, Collider<?> other) {
    }

    public void onCollisionStay(Collider<?> collider, Collider<?> other) {
    }

    public void onCollisionExit(Collider<?> collider, Collider<?> other) {
    }
}
