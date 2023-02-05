package org.quydusaigon.predatorsim.states;

import java.util.HashSet;
import java.util.Set;

import org.quydusaigon.Output;
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

    public PredatorWanderState(Animal animal) {
        super(animal);
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
        // Initialize the variables to store the random values
        double randomX = 0;
        double randomY = 0;

        // Generate the random values using Perlin noise
        noiseX = PerlinNoise.noise(Math.PI, Math.E, seedX);
        noiseY = PerlinNoise.noise(Math.PI, Math.E, seedY);

        // Calculate the randomX and randomY values based on the noise values and the
        // reduced animal's speed
        randomX = noiseX * animal.animalStat.runSpeed * 0.75 * Time.getDeltaTime()
                * Parameter.getRelativeSimulationSpeed();
        randomY = noiseY * animal.animalStat.runSpeed * 0.75 * Time.getDeltaTime()
                * Parameter.getRelativeSimulationSpeed();

        // Adjust the seed values with an small offset
        seedX += 0.005;
        seedY += 0.005;

        // Update the velocity of the animal with the calculated random values
        animal.velocity.set(randomX, randomY);
    }

    // Method to add new prey that this predator failed to howl for allies and reset
    // timer
    public void getFailedPrey(Prey prey) {
        failedPreyCall.add(prey);
        currentCoolDownTime = coolDownTime;
    }

    // Method to get food by chance while wandering but with higher chance to die
    public void getFood(Prey prey) {
        // does not count food gained if killed by prey defense
        double preyDefenseChance = 25 + Math.random() * 100;

        if (preyDefenseChance <= prey.preyStat.defense) {
            // if the prey defends itself successfully, change state of predator to dead
            // state
            animal.changeState(((Predator) animal).getDeadState());
            return;
        }

        // else increase the predator's starvation
        // resilience by the prey's nutrition value
        ((Predator) animal).predatorStat.starvationResilience += prey.preyStat.nutrition;
        Output.getInstance().nutritionGained += prey.preyStat.nutrition;
    }

    // toString method is called when this state is printed
    @Override
    public String toString() {
        // Return the string representation of this state
        return super.toString() + "Predator Wander";
    }
}
