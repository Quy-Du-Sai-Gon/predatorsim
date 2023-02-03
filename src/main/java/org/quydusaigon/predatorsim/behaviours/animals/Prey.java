package org.quydusaigon.predatorsim.behaviours.animals;

import org.quydusaigon.Output;
import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.gameengine.component.Collider;
import org.quydusaigon.predatorsim.states.DeadState;
import org.quydusaigon.predatorsim.states.EvadeState;
import org.quydusaigon.predatorsim.states.PreyWanderState;
import org.quydusaigon.predatorsim.util.PreySize;
import org.quydusaigon.predatorsim.util.PreyStat;

public class Prey extends Animal {
    public PreyStat preyStat = (PreyStat) animalStat;

    private PreyWanderState preyWanderState;
    private EvadeState evadeState;
    private DeadState deadState;

    private boolean canCollide = true;

    public Prey(PreyStat stat) {
        super(stat);
    }

    @Override
    public void start() {
        super.start();
        preyWanderState = new PreyWanderState(this);
        evadeState = new EvadeState(this);
        deadState = new DeadState(this);

        initialize(preyWanderState);
    }

    @Override
    public void onCollisionEnter(Collider<?> collider, Collider<?> other) {
        super.onCollisionEnter(collider, other);
        if (other.getComponent(Predator.class).isPresent() && canCollide) {
            canCollide = false;
            changeState(deadState);
        }
    }

    public PreyWanderState getPreyWanderState() {
        return preyWanderState;
    }

    public EvadeState getEvadeState() {
        return evadeState;
    }

    public DeadState getDeadState() {
        return deadState;
    }
}