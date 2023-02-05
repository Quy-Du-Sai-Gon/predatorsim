package org.quydusaigon.predatorsim.util;

import org.quydusaigon.Output;
import org.quydusaigon.predatorsim.gameengine.Time;
import org.quydusaigon.predatorsim.gameengine.component.Behaviour;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.gameengine.util.TransformInit;

public class AutoSpawn extends Behaviour {

    double predatorCoolDownTime = 5;
    double predatorCurrentCoolDownTime = 5;

    double smallPreyCoolDownTime = 5;
    double smallPreyCurrentCoolDownTime = 5;

    double mediumPreyCoolDownTime = 5;
    double mediumPreyCurrentCoolDownTime = 5;

    double largePreyCoolDownTime = 5;
    double largePreyCurrentCoolDownTime = 5;

    static boolean isPredatorAutoSpawnEnable = false;
    static boolean isSmallPreyAutoSpawnEnable = false;
    static boolean isMediumPreyAutoSpawnEnable = false;
    static boolean isLargePreyAutoSpawnEnable = false;

    public static void setPredatorAutoSpawnEnable(boolean isPredatorAutoSpawnEnable) {
        AutoSpawn.isPredatorAutoSpawnEnable = isPredatorAutoSpawnEnable;
    }

    public static void setSmallPreyAutoSpawnEnable(boolean isSmallPreyAutoSpawnEnable) {
        AutoSpawn.isSmallPreyAutoSpawnEnable = isSmallPreyAutoSpawnEnable;
    }

    public static void setMediumPreyAutoSpawnEnable(boolean isMediumPreyAutoSpawnEnable) {
        AutoSpawn.isMediumPreyAutoSpawnEnable = isMediumPreyAutoSpawnEnable;
    }

    public static void setLargePreyAutoSpawnEnable(boolean isLargePreyAutoSpawnEnable) {
        AutoSpawn.isLargePreyAutoSpawnEnable = isLargePreyAutoSpawnEnable;
    }

    @Override
    public void update() {
        autoSpawnPredator();
        autoSpawnSmallPrey();
        autoSpawnMediumPrey();
        autoSpawnLargePrey();
    }

    void autoSpawnPredator() {
        if (isPredatorAutoSpawnEnable) {
            if (predatorCurrentCoolDownTime <= 0) {
                Output.getInstance().predatorCount++;
                GameObject.instantiate(Prefabs.PREDATOR, TransformInit.getRandomTransformInit());
                predatorCurrentCoolDownTime = predatorCoolDownTime;
            } else {
                predatorCurrentCoolDownTime -= Time.getDeltaTime();
            }
        }
    }

    void autoSpawnSmallPrey() {
        if (isSmallPreyAutoSpawnEnable) {
            if (smallPreyCurrentCoolDownTime <= 0) {
                Output.getInstance().smallPreyCount++;
                GameObject.instantiate(Prefabs.SMALL_PREY, TransformInit.getRandomTransformInit());
                smallPreyCurrentCoolDownTime = smallPreyCoolDownTime;
            } else {
                smallPreyCurrentCoolDownTime -= Time.getDeltaTime();
            }
        }
    }

    void autoSpawnMediumPrey() {
        if (isMediumPreyAutoSpawnEnable) {
            if (mediumPreyCurrentCoolDownTime <= 0) {
                Output.getInstance().mediumPreyCount++;
                GameObject.instantiate(Prefabs.MEDIUM_PREY, TransformInit.getRandomTransformInit());
                mediumPreyCurrentCoolDownTime = mediumPreyCoolDownTime;
            } else {
                mediumPreyCurrentCoolDownTime -= Time.getDeltaTime();
            }
        }
    }

    void autoSpawnLargePrey() {
        if (isLargePreyAutoSpawnEnable) {
            if (largePreyCurrentCoolDownTime <= 0) {
                Output.getInstance().largePreyCount++;
                GameObject.instantiate(Prefabs.LARGE_PREY, TransformInit.getRandomTransformInit());
                largePreyCurrentCoolDownTime = largePreyCoolDownTime;
            } else {
                largePreyCurrentCoolDownTime -= Time.getDeltaTime();
            }
        }
    }
}
