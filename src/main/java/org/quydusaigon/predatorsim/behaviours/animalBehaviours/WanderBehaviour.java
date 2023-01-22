package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

import org.quydusaigon.predatorsim.gameengine.Time;
import org.quydusaigon.predatorsim.util.PerlinNoise;
import org.quydusaigon.predatorsim.util.Map;

public class WanderBehaviour extends AnimalBehaviour {

    private double seedX;
    private double seedY;

    public void setSeed() {
        seedX = Math.random() * 100;
        seedY = Math.random() * 100;
    }

    @Override
    public void doAction() {
        var x = posX();
        var y = posY();

        double randomX = PerlinNoise.noise(Math.PI, Math.E, seedX) * animalStat.runSpeed * Time.getDeltaTime() * 100;
        double randomY = PerlinNoise.noise(Math.PI, Math.E, seedY) * animalStat.runSpeed * Time.getDeltaTime() * 100;

        seedX += 0.005;
        seedY += 0.005;

        x.set(Map.checkBoundX(posX().get() + randomX));
        y.set(Map.checkBoundY(posY().get() + randomY));
    }

}
