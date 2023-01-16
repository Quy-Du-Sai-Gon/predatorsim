package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

import org.quydusaigon.predatorsim.gameengine.Time;
import org.quydusaigon.predatorsim.util.PerlinNoise;

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

        double randomx = PerlinNoise.noise(Math.PI, Math.E, seedX) * animalStat.runSpeed * Time.getDeltaTime() * 100;
        double randomy = PerlinNoise.noise(Math.PI, Math.E, seedY) * animalStat.runSpeed * Time.getDeltaTime() * 100;

        seedX += 0.01;
        seedY += 0.01;

        x.set(x.get() + randomx);
        y.set(y.get() + randomy);

    }

}
