package org.quydusaigon.predatorsim;

public class SurvivalState extends State {

    public SurvivalState(Animal animalSM) {
        super(animalSM);
    }
    public void update() {
        super.update();
        test();
    }
    public void test() {
        System.out.println("I'm surviving");
    }
}
