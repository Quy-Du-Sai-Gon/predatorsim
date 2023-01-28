package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

import java.net.HttpURLConnection;
import org.quydusaigon.predatorsim.gameengine.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.animals.Predator;
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
    private List<Group> alliesObjects = new ArrayList<Group>();
    private Group leaderObject;
    private int numberOfAllies;
    private boolean isLeader = false;
    private boolean groupFounded = false;

    private Vision groupVision;
    private Vision howlVision;

    private PredatorStat predatorStat;
    @Override
    public void start() {
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
        isLeader = true;
        this.numberOfAllies = numberOfAllies;
    }

    public void setUpHuntingInGroup(Group leaderObject) {
        isLeader = false;
        groupFounded = false;
        this.leaderObject = leaderObject;
    }

    public void doSurvival() {
        if (groupFounded) {
            if (isLeader) {
                leaderChase();
                return;
            } else {
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

    private void howl() {
        if (groupVision.getAllDetectedObject(Predator.class).size() >= numberOfAllies) {
            groupFounded = true;
            return;
        }
        if (howlVision.getAllDetectedObject(Predator.class).size() >= numberOfAllies) {
            for (int i = 0; i < numberOfAllies; i++) {
                alliesObjects.add(howlVision.getAllDetectedObject(Predator.class)
                        .stream().toList().get(i));
            }

            for (int i = 0; i < numberOfAllies; i++) {
                Animal animal = GameObject.getComponent(alliesObjects.get(i), Animal.class).get();
                animal.setSurvivalBehaviour(animal.getComponent(HuntingInGroup.class).get());
                ((HuntingInGroup) animal.getSurvivalBehaviour()).setUpHuntingInGroup(getGameObject());
                animal.changeState(animal.getStateConstructor().getSurvivalState());
                animal.getComponent(HuntingInGroup.class).orElseThrow().setUpReference(targetObject);
            }
        }
        stalk();
    }

    private void join() {
        if (Distance.calculateDistance(leaderObject, getGameObject()) <= predatorStat.groupRadius) {
            groupFounded = true;
            return;
        }
        double targetX, targetY;
        Point2D targetDir;
        Component component = GameObject.getComponent(leaderObject, Component.class).get();


        targetX = component.posX().get();
        targetY = component.posY().get();

        targetDir = new Point2D(targetX - posX().get(), targetY - posY().get());

        targetDir = targetDir.normalize();

        posX().set(posX().get() + targetDir.getX() * animalStat.runSpeed * Time.getDeltaTime()
                * Parameter.getRelativeSimulationSpeed());
        posY().set(posY().get() + targetDir.getY() * animalStat.runSpeed * Time.getDeltaTime()
                * Parameter.getRelativeSimulationSpeed());
    }

    private void leaderChase() {
        double targetX, targetY;
        Point2D targetDir;
        Component component = GameObject.getComponent(targetObject, Component.class).get();


        targetX = component.posX().get();
        targetY = component.posY().get();

        targetDir = new Point2D(targetX - posX().get(), targetY - posY().get());

        targetDir = targetDir.normalize();

        posX().set(posX().get() + targetDir.getX() * animalStat.runSpeed * Time.getDeltaTime()
                * Parameter.getRelativeSimulationSpeed());
        posY().set(posY().get() + targetDir.getY() * animalStat.runSpeed * Time.getDeltaTime()
                * Parameter.getRelativeSimulationSpeed());

    }

    private void memberChase() {
        Animal animal = GameObject.getComponent(targetObject, Animal.class).orElseThrow();
        WanderBehaviour targetMovement = animal.getComponent(WanderBehaviour.class).orElseThrow();
        
        double targetX, targetY;
        Point2D targetDir;
        Component component = GameObject.getComponent(targetObject, Component.class).orElseThrow();


        targetX = component.posX().get();
        targetY = component.posY().get();

        targetDir = new Point2D(targetX - posX().get(), targetY - posY().get());

        targetDir = targetDir.normalize();

        posX().set(posX().get() + (targetMovement.noiseX + targetDir.getX()) * animalStat.runSpeed * Time.getDeltaTime()
                * Parameter.getRelativeSimulationSpeed());
        posY().set(posY().get() + (targetMovement.noiseY + targetDir.getY()) * animalStat.runSpeed * Time.getDeltaTime()
                * Parameter.getRelativeSimulationSpeed());
    }

    private void stalk() {
        Animal animal = GameObject.getComponent(targetObject, Animal.class).get();
        WanderBehaviour targetMovement = animal.getComponent(WanderBehaviour.class).get();

        
        posX().set(Map.checkBoundX(posX().get() + targetMovement.noiseX * animalStat.runSpeed * 0.75 * Time.getDeltaTime()
                * Parameter.getRelativeSimulationSpeed()));
        posY().set(Map.checkBoundY(posY().get() + targetMovement.noiseY * animalStat.runSpeed * 0.75 * Time.getDeltaTime()
                * Parameter.getRelativeSimulationSpeed()));
    }
}
