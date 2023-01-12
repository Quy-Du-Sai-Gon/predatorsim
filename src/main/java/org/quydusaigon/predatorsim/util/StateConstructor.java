package org.quydusaigon.predatorsim.util;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.SurvivalState;
import org.quydusaigon.predatorsim.behaviours.WanderingState;

public class StateConstructor {

    public WanderingState wanderingState;
    public SurvivalState survivalState;

    public StateConstructor(Animal animal) {
        wanderingState = new WanderingState(animal);
        survivalState = new SurvivalState(animal);
    }

}
