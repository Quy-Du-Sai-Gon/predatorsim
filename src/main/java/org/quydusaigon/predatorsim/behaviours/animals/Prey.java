package org.quydusaigon.predatorsim.behaviours.animals;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.gameengine.component.Collider;
import org.quydusaigon.predatorsim.states.DeadState;
import org.quydusaigon.predatorsim.states.EvadeState;
import org.quydusaigon.predatorsim.states.PreyWanderState;
import org.quydusaigon.predatorsim.util.PreyStat;

/**
 * The `Prey` class is a subclass of the `Animal` class.
 * It represents the prey character in the simulation.
 * This class defines the specific behavior of the prey, such as wandering,
 * evading and dead.
 */
public class Prey extends Animal {
    // The instance variable for the `PreyStat` object, which stores
    // the stat of the predator.
    public PreyStat preyStat = (PreyStat) animalStat;

    // The state instances that represent the different states that the prey
    // can be in, such as wandering, evading and dead.
    private PreyWanderState preyWanderState;
    private EvadeState evadeState;
    private DeadState deadState;

    // Flag to check if the prey can collide with other objects
    private boolean canCollide = true;

    /**
     * The constructor for the `Prey` class that takes in a `PreyStat`
     * object
     * as its argument.
     *
     * @param stat The `PreyStat` object representing the stat of the prey.
     */
    public Prey(PreyStat stat) {
        super(stat);
    }

    /**
     * The `start` method that is called when the prey is instantiated.
     * This method sets up the state instances for the prey and initializes
     * the prey with the `PreyWanderState`.
     */
    @Override
    public void start() {
        super.start();
        preyWanderState = new PreyWanderState(this);
        evadeState = new EvadeState(this);
        deadState = new DeadState(this);

        initialize(preyWanderState);
    }

    /**
     * The `onCollisionEnter` method that is called when the prey collides
     * with another collider.
     * This method checks if the collider that the prey collided with is a predator
     * canCollide flag is used to avoid multiple simultaneous colliding
     * If it is a predator, the prey change to deadState.
     *
     * @param collider The collider of the prey.
     * @param other    The collider of the other object that the prey collided
     *                 with.
     **/
    @Override
    public void onCollisionEnter(Collider<?> collider, Collider<?> other) {
        super.onCollisionEnter(collider, other);
        if (other.getComponent(Predator.class).isPresent() && canCollide) {
            canCollide = false;
            changeState(deadState);
        }
    }

    // Get method to return the PreyWanderState of the prey
    public PreyWanderState getPreyWanderState() {
        return preyWanderState;
    }

    // Get method to return the EvadeState of the prey
    public EvadeState getEvadeState() {
        return evadeState;
    }

    // Get method to return the DeadState of the prey
    public DeadState getDeadState() {
        return deadState;
    }
}