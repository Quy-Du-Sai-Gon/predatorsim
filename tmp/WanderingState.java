package org.quydusaigon.predatorsim;

public class WanderingState extends State {

    public WanderingState(Animal animalSM) {
        super(animalSM);
    }

    @Override
    public void update() {
        super.update();
        test();
    }

    public void test() {
        System.out.println("I'm wandering");
    }
}
