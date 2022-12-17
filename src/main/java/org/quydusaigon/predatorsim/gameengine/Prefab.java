package org.quydusaigon.predatorsim.gameengine;

import javafx.scene.Group;
import org.quydusaigon.predatorsim.gameengine.components.Transform;
import org.quydusaigon.predatorsim.gameengine.components.TransformInit;

public interface Prefab {

    public Group instantiate(TransformInit tf, Group parent);

    //

}
