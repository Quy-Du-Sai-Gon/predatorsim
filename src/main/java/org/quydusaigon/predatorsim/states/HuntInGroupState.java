package org.quydusaigon.predatorsim.states;

import java.util.List;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.State;
import org.quydusaigon.predatorsim.behaviours.animals.Predator;
import org.quydusaigon.predatorsim.behaviours.animals.Prey;
import org.quydusaigon.predatorsim.gameengine.Time;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.util.Parameter;
import org.quydusaigon.predatorsim.util.PredatorStat;
import org.quydusaigon.predatorsim.util.PreyStat;

import javafx.geometry.Point2D;

public class HuntInGroupState extends State {
    double targetX, targetY;
    Point2D targetDir;
    Prey targetPrey;

    List<Predator> alliesPredators;

    public HuntInGroupState(Animal animal) {
        super(animal);
    }

    @Override
    public void enter() {
    }

    @Override
    public void update() {
        if (targetPrey.getGameObject() == null) {
            animal.changeState(((Predator) animal).getPredatorWanderState());
            return;
        }

        doHuntInGroup();
    }

    @Override
    public void exit() {
        alliesPredators.clear();
    }

    public void getFood() {
        for (Predator allyPredator : alliesPredators) {
            allyPredator.predatorStat.starvationResilience += ((PreyStat) targetPrey.animalStat).nutrition
                    / alliesPredators.size();
        }
        ((PredatorStat) animal.animalStat).starvationResilience += ((PreyStat) targetPrey.animalStat).nutrition
                / alliesPredators.size();
    }

    public void setTargetPrey(Prey prey, List<Predator> alliesPredators) {
        targetPrey = prey;
        this.alliesPredators = alliesPredators;
    }

    void doHuntInGroup() {
        targetX = GameObject.posX(targetPrey.getGameObject()).get();
        targetY = GameObject.posY(targetPrey.getGameObject()).get();

        targetDir = new Point2D(targetX - animal.posX().get(), targetY - animal.posY().get());

        targetDir = targetDir.normalize();

        animal.velocity.set(targetDir.multiply(animal.animalStat.runSpeed * Time.getDeltaTime()
                * Parameter.getRelativeSimulationSpeed()));
    }

    @Override
    public String toString() {
        return super.toString() + "Hunt In Group - " + alliesPredators.size();
    }
}
