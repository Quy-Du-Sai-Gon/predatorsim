package org.quydusaigon.predatorsim.states;

import java.util.HashSet;
import java.util.Set;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.animals.Predator;
import org.quydusaigon.predatorsim.behaviours.animals.Prey;
import org.quydusaigon.predatorsim.gameengine.Time;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.util.Parameter;
import org.quydusaigon.predatorsim.util.PerlinNoise;
import org.quydusaigon.predatorsim.util.PreySize;

import javafx.scene.Group;

public class PredatorWanderState extends WanderState {

    Set<Prey> failedPreyCall = new HashSet<>();
    double coolDownTime = 10;
    double currentCoolDownTime = 0;

    public PredatorWanderState(Animal animalSM) {
        super(animalSM);
    }

    @Override
    public void enter() {
        super.enter();
    }

    @Override
    public void update() {
        doWander();

        if (animal.getVision().getAllDetectedObject(Prey.class).size() != 0) {
            Group target = animal.getVision().getClosestObject(Prey.class).get();
            Prey preyComp = GameObject.getComponent(target, Prey.class).get();

            // avoid catch the same medium/large prey again;
            if (failedPreyCall.contains(preyComp))
                return;

            if (preyComp.preyStat.size == PreySize.SMALL) {
                ((Predator) animal).getHuntAloneState().setTargetPrey(preyComp);
                animal.changeState(((Predator) animal).getHuntAloneState());
            } else if (preyComp.preyStat.size == PreySize.MEDIUM) {
                ((Predator) animal).getHowlState().setTargetPrey(preyComp, 3);
                animal.changeState(((Predator) animal).getHowlState());
            } else if (preyComp.preyStat.size == PreySize.LARGE) {
                ((Predator) animal).getHowlState().setTargetPrey(preyComp, 5);
                animal.changeState(((Predator) animal).getHowlState());
            }
        }

        currentCoolDownTime -= Time.getDeltaTime();
        if (currentCoolDownTime <= 0) {
            failedPreyCall.clear();
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

        randomX = noiseX * animal.animalStat.runSpeed * 0.75 * Time.getDeltaTime()
                * Parameter.getRelativeSimulationSpeed();
        randomY = noiseY * animal.animalStat.runSpeed * 0.75 * Time.getDeltaTime()
                * Parameter.getRelativeSimulationSpeed();
        seedX += 0.005;
        seedY += 0.005;

        animal.velocity.set(randomX, randomY);
    }

    // add new prey that this predator failed to howl for allies and reset timer
    public void getFailedPrey(Prey prey) {
        failedPreyCall.add(prey);
        currentCoolDownTime = coolDownTime;
    }

    @Override
    public String toString() {
        return super.toString() + "Predator Wander";
    }
}
