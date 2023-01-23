package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

import org.quydusaigon.predatorsim.App;
import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.gameengine.Time;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.util.PerlinNoise;
import org.quydusaigon.predatorsim.util.PredatorStat;
import org.quydusaigon.predatorsim.util.PreySize;
import org.quydusaigon.predatorsim.util.PreyStat;

import javafx.scene.Group;

import org.quydusaigon.predatorsim.util.AnimalStat;
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

        double randomX = PerlinNoise.noise(Math.PI, Math.E, seedX) * animalStat.runSpeed/2 * Time.getDeltaTime() * 100;
        double randomY = PerlinNoise.noise(Math.PI, Math.E, seedY) * animalStat.runSpeed/2 * Time.getDeltaTime() * 100;

        Group object = getGameObject();

        AnimalStat stat = GameObject.getComponent(object, Animal.class).get().animalStat;
        if (stat instanceof PreyStat) {
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
    
            seedX += 0.005 - mapTendencyOffsetX/60000;
            seedY += 0.005 - mapTendencyOffsetY/60000;
        }

        else if (stat instanceof PredatorStat) {
            seedX += 0.01;
            seedY += 0.01;
        }

        x.set(Map.checkBoundX(posX().get() + randomX));
        y.set(Map.checkBoundY(posY().get() + randomY));
    }

}
