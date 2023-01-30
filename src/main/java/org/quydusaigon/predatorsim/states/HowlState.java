package org.quydusaigon.predatorsim.states;

import java.util.ArrayList;
import java.util.List;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.State;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.HowlVision;
import org.quydusaigon.predatorsim.behaviours.animalBehaviours.Vision;
import org.quydusaigon.predatorsim.behaviours.animals.Predator;
import org.quydusaigon.predatorsim.behaviours.animals.Prey;
import org.quydusaigon.predatorsim.gameengine.Time;
import org.quydusaigon.predatorsim.gameengine.component.Collider;
import org.quydusaigon.predatorsim.gameengine.component.NodeComponent;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.gameengine.util.TransformInit;
import org.quydusaigon.predatorsim.util.Distance;
import org.quydusaigon.predatorsim.util.Parameter;
import org.quydusaigon.predatorsim.util.PredatorStat;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class HowlState extends State {
    Prey targetPrey;
    HowlVision howlVision;
    Vision groupVision;
    Group howlObject, groupObject;

    int numberOfAllies;
    List<Predator> alliesPredators;

    public HowlState(Animal animalSM) {
        super(animalSM);
    }

    @Override
    public void enter() {
        var howlNodeComp = new NodeComponent<>(
                new Circle(((PredatorStat) (animal.animalStat)).howlingRadius, Color.YELLOW));
        howlObject = GameObject.create(TransformInit.DEFAULT, animal.getGameObject(),
                howlNodeComp, new Collider<>(howlNodeComp), howlVision = new HowlVision());

        var groupNodeComp = new NodeComponent<>(
                new Circle(((PredatorStat) (animal.animalStat)).groupRadius, Color.RED));
        groupObject = GameObject.create(TransformInit.DEFAULT, animal.getGameObject(),
                groupNodeComp, new Collider<>(groupNodeComp), groupVision = new Vision());

        howlVision.SetUpHowlVision((Predator) animal);
        alliesPredators = new ArrayList<>();
        alliesPredators.add(((Predator) animal));
    }

    @Override
    public void update() {

        if (targetPrey.getGameObject() == null) {
            animal.changeState(((Predator) animal).getPredatorWanderState());
            return;
        } else if (groupVision.getAllDetectedObject(Predator.class).size() + 1 >= numberOfAllies) {
            for (Predator allyPredator : alliesPredators) {
                allyPredator.getJoinState().groupFounded = true;
                allyPredator.getHuntInGroupState().setTargetPrey(targetPrey, alliesPredators);
            }

            animal.changeState(((Predator) animal).getHuntInGroupState());
            return;
        }

        howl();

        stalk();
    }

    @Override
    public void exit() {
        targetPrey = null;
        GameObject.destroy(howlObject);
        GameObject.destroy(groupObject);
    }

    public void setTargetPrey(Prey targetPrey, int numberOfAllies) {
        this.targetPrey = targetPrey;
        this.numberOfAllies = numberOfAllies;
    }

    public Prey getTargetPrey() {
        return targetPrey;
    }

    public int getCurrentNumberOfAllies() {
        return alliesPredators.size();
    }

    void howl() {

    }

    public void addAlly(Predator allyPredator) {
        alliesPredators.add(allyPredator);
    }

    private void stalk() {
        double distanceToTarget = Distance.calculateDistance(animal.getGameObject(), targetPrey.getGameObject());
        double targetX, targetY;
        Point2D targetDir;

        targetX = GameObject.posX(targetPrey.getGameObject()).get();
        targetY = GameObject.posY(targetPrey.getGameObject()).get();

        targetDir = new Point2D(targetX - animal.posX().get(), targetY - animal.posY().get());
        targetDir = targetDir.normalize();

        double offset = 25;
        if (distanceToTarget <= animal.animalStat.visionRange - offset) {
            animal.velocity.set(targetDir.multiply(-animal.animalStat.runSpeed / 2 *
                    Time.getDeltaTime()
                    * Parameter.getRelativeSimulationSpeed()));
        } else if (distanceToTarget >= animal.animalStat.visionRange + offset) {
            animal.velocity.set(targetDir.multiply(animal.animalStat.runSpeed / 2 * Time.getDeltaTime()
                    * Parameter.getRelativeSimulationSpeed()));
        } else {
            animal.velocity.set(Point2D.ZERO);
        }
    }

    public int getNumberOfAllies() {
        return numberOfAllies;
    }

    @Override
    public String toString() {
        return super.toString() + "Howl " + alliesPredators.size() + " "
                + groupVision.getAllDetectedObject(Predator.class).size();
    }
}