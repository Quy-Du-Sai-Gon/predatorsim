package org.quydusaigon.predatorsim.behaviours;

public abstract class State {
    protected Animal animal;

    public State(Animal animal) {
        this.animal = animal;
    }

    abstract public void enter();

    abstract public void update();

    abstract public void exit();

    @Override
    public String toString() {
        return "State: ";
    }
}
