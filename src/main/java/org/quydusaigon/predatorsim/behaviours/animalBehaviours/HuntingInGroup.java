package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

import java.net.HttpURLConnection;
import org.quydusaigon.predatorsim.gameengine.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.animals.Predator;
import org.quydusaigon.predatorsim.behaviours.states.WanderState;
import org.quydusaigon.predatorsim.behaviours.util.Velocity;
import org.quydusaigon.predatorsim.gameengine.component.Collider;
import org.quydusaigon.predatorsim.gameengine.component.Component;
import org.quydusaigon.predatorsim.gameengine.component.NodeComponent;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.gameengine.util.TransformInit;
import org.quydusaigon.predatorsim.util.Distance;
import org.quydusaigon.predatorsim.util.Map;
import org.quydusaigon.predatorsim.util.Parameter;
import org.quydusaigon.predatorsim.util.PredatorStat;

import javafx.beans.property.DoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class HuntingInGroup extends Hunting {
    private List<Group> alliesObjects;
    private Group leaderObject;
    private int numberOfAllies;
    private boolean isLeader = false;
    private boolean groupFounded = false;
    public boolean huntSuccessed = false;
    public boolean listened = false;

    private Vision groupVision;
    private Vision howlVision;

    private PredatorStat predatorStat;

    private Velocity velocity;

    @Override
    public void start() {
        velocity = getComponent(Velocity.class).orElseThrow();

        predatorStat = (PredatorStat) getComponent(Predator.class).orElseThrow().animalStat;

        var circleHowling = new Circle(predatorStat.howlingRadius, Color.DARKCYAN);
        var howlingVisionNodeComp = new NodeComponent<>(circleHowling);
        howlVision = new Vision();
        GameObject.create(TransformInit.DEFAULT, getGameObject(),
                howlingVisionNodeComp, new Collider<>(howlingVisionNodeComp), howlVision);

        var circleGroup = new Circle(predatorStat.groupRadius, Color.DARKRED);
        var groupVisionNodeComp = new NodeComponent<>(circleGroup);
        groupVision = new Vision();
        GameObject.create(TransformInit.DEFAULT, getGameObject(),
                groupVisionNodeComp, new Collider<>(groupVisionNodeComp), groupVision);
    }

    public void setUpHuntingInGroup(int numberOfAllies) {
        System.out.println("call1");
        isLeader = true;
        huntSuccessed = false;
        this.leaderObject = getGameObject();
        this.numberOfAllies = numberOfAllies;
    }

    public void setUpHuntingInGroup(Group leaderObject, Group targetObject) {
        System.out.println("call2");
        listened = true;
        isLeader = false;
        groupFounded = false;
        this.leaderObject = leaderObject;
        huntSuccessed = false;
        this.targetObject = targetObject;
    }

    public void doSurvival() {
        if (!huntSuccessed) {
            if (groupFounded) {
                if (isLeader) {
                    System.out.println("Leader chasing now");
                    leaderChase();
                    return;
                } else {
                    System.out.println("Member chasing now");
                    memberChase();
                    return;
                }
            } else {
                if (isLeader) {
                    howl();
                } else {
                    join();
                }
            }
        }

    }

    private void howl() {
        if (groupVision.getAllDetectedObject(Predator.class).size() >= numberOfAllies) {
            groupFounded = true;
            return;
        }

        var predatorsInRange = howlVision.getAllDetectedWanderingObject(Predator.class);
        if (!predatorsInRange.isEmpty()) {
            alliesObjects = predatorsInRange.stream().limit(numberOfAllies).toList();

            for (var ally : alliesObjects) {
                Predator animal = (Predator) GameObject.getComponent(ally, Animal.class).get();
                HuntingInGroup temp = animal.getComponent(HuntingInGroup.class).get();
                if (!temp.listened) {
                    animal.setSurvivalBehaviour(temp);
                    animal.isJoiningGroup = true;
                    temp.setUpHuntingInGroup(getGameObject(), targetObject);
                    animal.changeState(animal.getStateConstructor().getSurvivalState());
                    animal.getComponent(HuntingInGroup.class).orElseThrow().setUpReference(targetObject);
                }
            }
        }

        stalk();
    }

    private void join() {
        if (Distance.calculateDistance(leaderObject, getGameObject()) <= predatorStat.groupRadius) {
            if (GameObject.getComponent(leaderObject, HuntingInGroup.class).get().groupFounded) {
                groupFounded = true;
                return;
            }
            stalk();
            return;
        }
        double targetX, targetY;
        Point2D targetDir;
        Component component = GameObject.getComponent(leaderObject, Component.class).get();

        targetX = component.posX().get();
        targetY = component.posY().get();

        targetDir = new Point2D(targetX - posX().get(), targetY - posY().get());

        targetDir = targetDir.normalize();

        velocity.set(targetDir.multiply(animalStat.runSpeed * Time.getDeltaTime()
                * Parameter.getRelativeSimulationSpeed()));
    }

    private void leaderChase() {
        System.out.println(isLeader);
        if (!huntSuccessed) {
            System.out.println("hunt was not yet succeed");

            double targetX, targetY;
            Point2D targetDir;

            targetX = GameObject.posX(targetObject).get();
            targetY = GameObject.posY(targetObject).get();

            targetDir = new Point2D(targetX - posX().get(), targetY - posY().get());

            targetDir = targetDir.normalize();

            velocity.set(targetDir.multiply(animalStat.runSpeed * Time.getDeltaTime()
                    * Parameter.getRelativeSimulationSpeed()));
        }
    }

    private void memberChase() {
        System.out.println("memberChase");
        if (!huntSuccessed) {

            Animal animal = GameObject.getComponent(targetObject, Animal.class).orElseThrow();
            WanderBehaviour targetMovement = animal.getComponent(WanderBehaviour.class).orElseThrow();

            double targetX, targetY;
            Point2D targetDir;
            Component component = GameObject.getComponent(targetObject, Component.class).orElseThrow();

            targetX = component.posX().get();
            targetY = component.posY().get();

            targetDir = new Point2D(targetX - posX().get(), targetY - posY().get());

            targetDir = targetDir.normalize();

            velocity.set(
                    (targetMovement.noiseX + targetDir.getX()) * animalStat.runSpeed * Time.getDeltaTime()
                            * Parameter.getRelativeSimulationSpeed(),
                    (targetMovement.noiseY + targetDir.getY()) * animalStat.runSpeed * Time.getDeltaTime()
                            * Parameter.getRelativeSimulationSpeed());
        }
    }

    private void stalk() {
        double distanceToTarget = Distance.calculateDistance(getGameObject(), targetObject);
        double targetX, targetY;
        Point2D targetDir;

        targetX = GameObject.posX(targetObject).get();
        targetY = GameObject.posY(targetObject).get();

        targetDir = new Point2D(targetX - posX().get(), targetY - posY().get());
        targetDir = targetDir.normalize();

        double offset = 25;
        if (distanceToTarget <= animalStat.visionRange - offset) {
            velocity.set(targetDir.multiply(-animalStat.runSpeed / 2 *
                    Time.getDeltaTime()
                    * Parameter.getRelativeSimulationSpeed()));
        } else if (distanceToTarget >= animalStat.visionRange + offset) {
            velocity.set(targetDir.multiply(animalStat.runSpeed / 2 * Time.getDeltaTime()
                    * Parameter.getRelativeSimulationSpeed()));
        } else {
            velocity.set(Point2D.ZERO);
        }
    }

    public int getNumOfAllies() {
        return numberOfAllies;
    }

    public void divideFood(double nutrition) {
        if (isLeader) {
            huntSuccessed = true;
            System.out.println(huntSuccessed + " set ==============================================================");

            Predator temp = getComponent(Predator.class).get();

            for (Group ally : alliesObjects) {
                Animal allyAnimal = GameObject.getComponent(ally, Animal.class).get();
                HuntingInGroup HIG = (HuntingInGroup) allyAnimal.getSurvivalBehaviour();
                ((PredatorStat) allyAnimal.animalStat).starvationResilience += nutrition / (numberOfAllies + 1);
                HIG.huntSuccessed = true;
                HIG.listened = false;
                allyAnimal.changeState(allyAnimal.getStateConstructor().getWanderState());

            }

            temp.changeState(temp.getStateConstructor().getWanderState());
        } else {
            System.out.println("MEMBER EAT =============================================================");
            GameObject.getComponent(leaderObject, HuntingInGroup.class).get().divideFood(nutrition);
        }

    }

    @Override
    public String toString() {
        return "Hunting in group\n"
                + (isLeader
                        ? "Is Leader (" + getGameObject() + ")"
                        : "Leader: " + leaderObject);
    }
}
