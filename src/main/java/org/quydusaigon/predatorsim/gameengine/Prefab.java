package org.quydusaigon.predatorsim.gameengine;

import org.quydusaigon.predatorsim.gameengine.components.Transform;

public interface Prefab {

    public GameObject instantiate(Transform transform);

}
