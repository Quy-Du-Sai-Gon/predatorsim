package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

import org.quydusaigon.predatorsim.App;
import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.gameengine.Time;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.util.*;

import javafx.scene.Group;

public class WanderBehaviour extends AnimalBehaviour {

    private double seedX;
    private double seedY;
    public double noiseX;
    public double noiseY;

    public void setSeed() {
        seedX = Math.random() * 100;
        seedY = Math.random() * 100;
        noiseX = 0;
        noiseY = 0;
    }

    public void doWander() {
        var x = posX();
        var y = posY();

        double randomX = 0;
        double randomY = 0;

        Group object = getGameObject();
        AnimalStat stat = GameObject.getComponent(object, Animal.class).get().animalStat;

        noiseX = PerlinNoise.noise(Math.PI, Math.E, seedX);
        noiseY = PerlinNoise.noise(Math.PI, Math.E, seedY);

        if (stat instanceof PreyStat) {
            randomX = noiseX * animalStat.runSpeed* 0.5 * Time.getDeltaTime() * Parameter.getRelativeSimulationSpeed();
            randomY = noiseY * animalStat.runSpeed* 0.5 * Time.getDeltaTime() * Parameter.getRelativeSimulationSpeed();

            var preyStat = (PreyStat) stat;
            if (preyStat.size == PreySize.SMALL) {
                seedX += 0.007;
            }
            else if (preyStat.size == PreySize.MEDIUM) {
                seedX += 0.005;
            }
            if (preyStat.size == PreySize.LARGE) {
                seedX += 0.003;
            }
            
            double mapTendencyOffsetX = Math.abs(App.simulationWindowWidth - posX().get());
            double mapTendencyOffsetY =  Math.abs(App.simulationWindowHeight - posY().get());
    
            seedX += 0.005 - mapTendencyOffsetX/40000;
            seedY += 0.005 - mapTendencyOffsetY/40000;
        }

        else if (stat instanceof PredatorStat) {
            randomX = noiseX * animalStat.runSpeed * 0.75 * Time.getDeltaTime() * Parameter.getRelativeSimulationSpeed();
            randomY = noiseY * animalStat.runSpeed * 0.75 * Time.getDeltaTime() * Parameter.getRelativeSimulationSpeed();
            seedX += 0.005;
            seedY += 0.005;
        }
        

        x.set(Map.checkBoundX(posX().get() + randomX));
        y.set(Map.checkBoundY(posY().get() + randomY));
    }

}
