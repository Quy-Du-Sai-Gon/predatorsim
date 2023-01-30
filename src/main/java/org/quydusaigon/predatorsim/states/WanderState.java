package org.quydusaigon.predatorsim.states;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.State;

public class WanderState extends State {
    public WanderState(Animal animalSM) {
        super(animalSM);
    }

    protected double seedX;
    protected double seedY;
    protected double noiseX;
    protected double noiseY;

    private void setSeed() {
        seedX = Math.random() * 100;
        seedY = Math.random() * 100;
        noiseX = 0;
        noiseY = 0;
    }

    @Override
    public void enter() {
        setSeed();
    }

    @Override
    public void update() {

    }

    @Override
    public void exit() {

    }

    // private <T extends Animal> boolean detectTarget(Class<T> animalType) {
    // if (animalSM.getVision().getAllDetectedObject(animalType).size() != 0) {
    // System.out.println(animalSM.getVision().getAllDetectedObject(animalType).size());
    // toSurvivalState = true;
    // setFoundObject(animalSM.getVision().getClosestObject(animalType).get());
    // animalSM.getStateConstructor().getSurvivalState().setNoTarget(false);
    // animalSM.changeState(animalSM.getStateConstructor().getSurvivalState());
    // return true;
    // } else
    // return false;
    // }

    @Override
    public String toString() {
        return super.toString() + "Wander";
    }
}
