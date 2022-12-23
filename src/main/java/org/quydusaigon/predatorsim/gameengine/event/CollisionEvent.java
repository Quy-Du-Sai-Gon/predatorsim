package org.quydusaigon.predatorsim.gameengine.event;

import org.quydusaigon.predatorsim.gameengine.component.Behaviour;
import org.quydusaigon.predatorsim.gameengine.component.Collider;

public interface CollisionEvent {

    public void raise(Behaviour behaviour, Collider<?> collider, Collider<?> other);
}
