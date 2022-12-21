package org.quydusaigon.predatorsim.gameengine.util;

import javafx.scene.Group;

public interface Prefab {

    public Group instantiate(TransformInit tf, Group parent);
}
