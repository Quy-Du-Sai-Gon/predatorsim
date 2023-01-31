package org.quydusaigon.predatorsim.behaviours.animals;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.gameengine.Time;
import org.quydusaigon.predatorsim.gameengine.component.Collider;
import org.quydusaigon.predatorsim.states.DeadState;
import org.quydusaigon.predatorsim.states.HowlState;
import org.quydusaigon.predatorsim.states.HuntAloneState;
import org.quydusaigon.predatorsim.states.HuntInGroupState;
import org.quydusaigon.predatorsim.states.JoinState;
import org.quydusaigon.predatorsim.states.PredatorWanderState;
import org.quydusaigon.predatorsim.util.PredatorStat;

/**
 * Predator
 */
public class Predator extends Animal {
    public PredatorStat predatorStat = (PredatorStat) animalStat;

    public int hungryRate = 1;
    public boolean killed = false;

    private PredatorWanderState predatorWanderState;
    private HuntAloneState huntAloneState;
    private HowlState howlState;
    private JoinState joinState;
    private HuntInGroupState huntInGroupState;
    private DeadState deadState;

    public Predator(PredatorStat stat) {
        super(stat);
    }

    @Override
    public void start() {
        super.start();

        predatorWanderState = new PredatorWanderState(this);
        huntAloneState = new HuntAloneState(this);
        howlState = new HowlState(this);
        joinState = new JoinState(this);
        huntInGroupState = new HuntInGroupState(this);
        deadState = new DeadState(this);

        initialize(predatorWanderState);
    }

    @Override
    public void update() {
        super.update();

        predatorStat.starvationResilience -= hungryRate * Time.getDeltaTime();

        if (predatorStat.starvationResilience <= 0) {
            changeState(deadState);
        }
    }

    @Override
    public void onCollisionEnter(Collider<?> collider, Collider<?> other) {
        super.onCollisionEnter(collider, other);

        other.getComponent(Prey.class).ifPresent(otherPrey -> {
            if (getCurrentState() instanceof HuntAloneState) {
                getHuntAloneState().getFood();
            } else if (getCurrentState() instanceof HuntInGroupState) {
                getHuntInGroupState().getFood();
            } else if (getCurrentState() instanceof PredatorWanderState) {
                getPredatorWanderState().getFood(otherPrey);
            }
        });
    }

    public PredatorWanderState getPredatorWanderState() {
        return predatorWanderState;
    }

    public HuntAloneState getHuntAloneState() {
        return huntAloneState;
    }

    public HowlState getHowlState() {
        return howlState;
    }

    public JoinState getJoinState() {
        return joinState;
    }

    public HuntInGroupState getHuntInGroupState() {
        return huntInGroupState;
    }

    public DeadState getDeadState() {
        return deadState;
    }
}