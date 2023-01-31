package org.quydusaigon.predatorsim.states;

import org.quydusaigon.Output;
import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.State;
import org.quydusaigon.predatorsim.behaviours.animals.Predator;
import org.quydusaigon.predatorsim.behaviours.animals.Prey;
import org.quydusaigon.predatorsim.gameengine.Time;
import org.quydusaigon.predatorsim.util.Parameter;
import org.quydusaigon.predatorsim.util.PredatorStat;
import org.quydusaigon.predatorsim.util.PreyStat;

import javafx.beans.property.DoubleProperty;
import javafx.geometry.Point2D;

public class HuntAloneState extends State {
    Prey targetPrey;

    DoubleProperty x, y;
    double targetX, targetY;
    Point2D targetDir;

    public void setTargetPrey(Prey targetPrey) {
        this.targetPrey = targetPrey;
    }

    public HuntAloneState(Animal animalSM) {
        super(animalSM);
    }

    @Override
    public void enter() {

    }

    @Override
    public void update() {
        if ((animal.getVision().getAllDetectedObject(Prey.class).size() == 0) ||
                (!animal.getVision().getAllDetectedObject(Prey.class).contains(targetPrey.getGameObject()))) {
            System.out.println("Preint");
            animal.changeState(((Predator) animal).getPredatorWanderState());
            return;
        }

        doHuntAlone();
    }

    @Override
    public void exit() {
        targetPrey = null;
    }

    public void getFood() {
        Double nutrition = ((PreyStat) targetPrey.animalStat).nutrition;
        ((PredatorStat) animal.animalStat).starvationResilience += nutrition;
        Output.getInstance().nutritionGained += nutrition;
    }

    private void doHuntAlone() {
        x = animal.posX();
        y = animal.posY();

        targetX = targetPrey.posX().get();
        targetY = targetPrey.posY().get();

        targetDir = new Point2D(targetX - x.get(), targetY - y.get()).normalize();

        animal.velocity.set(targetDir.multiply(animal.animalStat.runSpeed * Time.getDeltaTime()
                * Parameter.getRelativeSimulationSpeed()));
    }

    @Override
    public String toString() {
        return super.toString() + "Hunt Alone";
    }
}
