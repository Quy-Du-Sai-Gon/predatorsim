package org.quydusaigon.predatorsim.behaviours.animals;

import org.quydusaigon.Output;
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
 * The `Predator` class is a subclass of the `Animal` class.
 * It represents the predator character in the simulation.
 * This class defines the specific behavior of the predator, such as hunger,
 * wandering, hunting, and howling.
 */
public class Predator extends Animal {
    // The instance variable for the `PredatorStat` object, which stores
    // the stat of the predator.
    public PredatorStat predatorStat = (PredatorStat) animalStat;

    // The rate of hunger that the predator experiences over time.
    public int hungryRate = 1;

    // The state instances that represent the different states that the predator
    // can be in, such as wandering, hunting alone, howling, joining, hunting
    // in group, and dead.
    private PredatorWanderState predatorWanderState;
    private HuntAloneState huntAloneState;
    private HowlState howlState;
    private JoinState joinState;
    private HuntInGroupState huntInGroupState;
    private DeadState deadState;

    /**
     * The constructor for the `Predator` class that takes in a `PredatorStat`
     * object
     * as its argument.
     *
     * @param stat The `PredatorStat` object representing the stat of the predator.
     */
    public Predator(PredatorStat stat) {
        super(stat);
    }

    /**
     * The `start` method that is called when the predator is instantiated.
     * This method sets up the state instances for the predator and initializes
     * the predator with the `PredatorWanderState`.
     */
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

    /**
     * The `update` method that is called once per frame.
     * This method updates the stat of the predator, such as its starvation
     * resilience,
     * and checks if the predator is dead. If it is dead, the state changes to the
     * `DeadState`.
     */
    @Override
    public void update() {
        super.update();

        double nutritionConsumed = hungryRate * Time.getDeltaTime();
        predatorStat.starvationResilience -= nutritionConsumed;
        Output.getInstance().nutritionConsumed += nutritionConsumed;

        if (predatorStat.starvationResilience <= 0) {
            changeState(deadState);
        }
    }

    /**
     * The `onCollisionEnter` method that is called when the predator collides
     * with another collider.
     * This method checks if the collider that the predator collided with is a prey.
     * If it is, the predator gets food depending on its current state.
     *
     * @param collider The collider of the predator.
     * @param other    The collider of the other object that the predator collided
     *                 with.
     **/
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

    // Get method for PredatorWanderState
    public PredatorWanderState getPredatorWanderState() {
        return predatorWanderState;
    }

    // Get method for HuntAloneState
    public HuntAloneState getHuntAloneState() {
        return huntAloneState;
    }

    // Get method for HowlState
    public HowlState getHowlState() {
        return howlState;
    }

    // Get method for JoinState
    public JoinState getJoinState() {
        return joinState;
    }

    // Get method for HuntInGroupState
    public HuntInGroupState getHuntInGroupState() {
        return huntInGroupState;
    }

    // Get method for DeadState
    public DeadState getDeadState() {
        return deadState;
    }

}