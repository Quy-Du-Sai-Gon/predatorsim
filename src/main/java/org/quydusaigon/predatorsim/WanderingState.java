package org.quydusaigon.predatorsim;

public class WanderingState extends State {

    public WanderingState(Animal animalSM) {
        super(animalSM);
    }

    public void test() {
        System.out.println("I'm wandering");
    }
}
