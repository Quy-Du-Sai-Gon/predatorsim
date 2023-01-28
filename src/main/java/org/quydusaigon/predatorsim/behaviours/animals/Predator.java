package org.quydusaigon.predatorsim.behaviours.animals;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.HuntingAlone;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.HuntingInGroup;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.WanderBehaviour;
import org.quydusaigon.predatorsim.gameengine.Time;
import org.quydusaigon.predatorsim.gameengine.component.Collider;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.util.AnimalStat;
import org.quydusaigon.predatorsim.util.PredatorStat;
import org.quydusaigon.predatorsim.util.PreyStat;

/**
 * Predator
 */
public class Predator extends Animal {
    PredatorStat predatorStat;
    public int hungryRate = 1;
    public Predator(PredatorStat stat) {
        super(stat);
    }

    @Override
    public void start() {
        super.start();
        predatorStat = (PredatorStat) animalStat;
    }

    @Override
    public void update() {
        super.update();

        predatorStat.starvationResilience -= hungryRate * Time.getDeltaTime();

        if(predatorStat.starvationResilience <= 0){
            changeState(stateConstructor.getDeadState());
        }
    }

    @Override
    public void onCollisionEnter(Collider<?> collider, Collider<?> other) {
        super.onCollisionEnter(collider, other);

        if(other.getComponent(Prey.class).isPresent()){
            if(survivalBehaviour instanceof HuntingAlone){
                predatorStat.starvationResilience += ((PreyStat)other.getComponent(Prey.class).get().animalStat).nutrition;
            }
            else if(survivalBehaviour instanceof HuntingInGroup){
                PreyStat temp = (PreyStat)other.getComponent(Prey.class).get().animalStat;
                predatorStat.starvationResilience += temp.nutrition / (((HuntingInGroup)survivalBehaviour).getNumOfAllies() + 1);
                stateConstructor.getSurvivalState().setNoTarget(true);
                ((HuntingInGroup)survivalBehaviour).divideFood(temp.nutrition);
            }
            
            System.out.println("collided");
            changeState(stateConstructor.getWanderState());
        }
    }
}