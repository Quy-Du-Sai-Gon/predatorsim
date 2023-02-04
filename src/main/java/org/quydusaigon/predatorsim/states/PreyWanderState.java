package org.quydusaigon.predatorsim.states;

import org.quydusaigon.predatorsim.App;
import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.animals.Predator;
import org.quydusaigon.predatorsim.behaviours.animals.Prey;
import org.quydusaigon.predatorsim.gameengine.Time;
import org.quydusaigon.predatorsim.util.Parameter;
import org.quydusaigon.predatorsim.util.PerlinNoise;
import org.quydusaigon.predatorsim.util.PreySize;
import org.quydusaigon.predatorsim.util.PreyStat;

public class PreyWanderState extends WanderState {
    public PreyWanderState(Animal animal) {
        super(animal);
    }

    @Override
    public void enter() {
        super.enter();
    }

    @Override
    public void update() {
        doWander();

        if (animal.getVision().getAllDetectedObject(Predator.class).size() != 0) {
            animal.changeState(((Prey) animal).getEvadeState());
        }
    }

    @Override
    public void exit() {

    }

    private void doWander() {
        double randomX = 0;
        double randomY = 0;

        noiseX = PerlinNoise.noise(Math.PI, Math.E, seedX);
        noiseY = PerlinNoise.noise(Math.PI, Math.E, seedY);

        randomX = noiseX * animal.animalStat.runSpeed * 0.5 * Time.getDeltaTime()
                * Parameter.getRelativeSimulationSpeed();
        randomY = noiseY * animal.animalStat.runSpeed * 0.5 * Time.getDeltaTime()
                * Parameter.getRelativeSimulationSpeed();

        if (((PreyStat) animal.animalStat).size == PreySize.SMALL) {
            seedX += 0.007;
        } else if (((PreyStat) animal.animalStat).size == PreySize.MEDIUM) {
            seedX += 0.005;
        } else if (((PreyStat) animal.animalStat).size == PreySize.LARGE) {
            seedX += 0.003;
        }

        double mapTendencyOffsetX = Math.abs(Parameter.getWindowWidth() - animal.posX().get());
        double mapTendencyOffsetY = Math.abs(Parameter.getWindowHeight() - animal.posY().get());

        seedX += 0.005 - mapTendencyOffsetX / 40000;
        seedY += 0.005 - mapTendencyOffsetY / 40000;

        animal.velocity.set(randomX, randomY);
    }

    @Override
    public String toString() {
        return super.toString() + "Prey Wander";
    }
}
