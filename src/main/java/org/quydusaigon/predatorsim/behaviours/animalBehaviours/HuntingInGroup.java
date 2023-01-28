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

   public void setUpHuntingInGroup(int numberOfAllies){
        howlVision = GameObject.getComponent(GameObject.getChildren(getGameObject()).get(1), Vision.class).get();
        groupVision = GameObject.getComponent(GameObject.getChildren(getGameObject()).get(2), Vision.class).get();
        isLeader = true;
        this.numberOfAllies = numberOfAllies;
   }

   public void setUpHuntingInGroup(Group leaderObject){
    isLeader = false;
    groupFounded = false;
    this.leaderObject = leaderObject;
}

    public void doSurvival() {
        if (groupFounded) {
            if (isLeader) {
                leaderChase();
                return;
            }
            else {
                memberChase();
                return;
            }
        }
        else {
            if (isLeader) {
                howl();
            }
            else {
                join();
            }
        }
    }

    // private void startHowling() {
    //     this.isLeader = true;
    //     PredatorStat predatorStat = (PredatorStat) animalStat;

    //     var circleHowling = new Circle(predatorStat.howlingRadius, Color.DARKCYAN);
    //     circleHowling.setOpacity(0.4);
    //     var howlingVisionNodeComp = new NodeComponent<>(circleHowling);
    //     Collider howlCollider = new Collider<>(howlingVisionNodeComp);

    //     var circleGroup = new Circle(predatorStat.groupRadius, Color.DARKRED);
    //     circleGroup.setOpacity(0.4);
    //     var groupVisionNodeComp = new NodeComponent<>(circleGroup);
    //     Collider groupCollider = new Collider<>(groupVisionNodeComp);
    // }

    private void howl() {
        if(howlVision.getAllDetectedObject(Predator.class).size() >= numberOfAllies){
            for(int i = 0; i < numberOfAllies; i++){
                alliesObjects.add(howlVision.getAllDetectedObject(Predator.class)
                .stream().toList().get(i));
            }

            for(int i = 0; i < numberOfAllies; i++){
                Animal animal = GameObject.getComponent(alliesObjects.get(i), Animal.class).get();
                animal.setSurvivalBehaviour(animal.getComponent(HuntingInGroup.class).get());
                ((HuntingInGroup)animal.getSurvivalBehaviour()).setUpHuntingInGroup(getGameObject());
                animal.changeState(animal.getStateConstructor().getSurvivalState());
            }
            groupFounded = true;
        }
    }

    private void join() {
        double targetX, targetY;
        Point2D targetDir;
        Component component = GameObject.getComponent(leaderObject ,Component.class).get();

        targetX = component.posX().get();
        targetY = component.posY().get();

        targetDir = new Point2D(targetX - posX().get(), targetY - posY().get());

        targetDir = targetDir.normalize();

         posX().set(posX().get() + targetDir.getX() * animalStat.runSpeed * Time.getDeltaTime() * Parameter.getRelativeSimulationSpeed());
         posY().set(posY().get() + targetDir.getY() * animalStat.runSpeed * Time.getDeltaTime() * Parameter.getRelativeSimulationSpeed());
    }

    private void leaderChase() {

    }

    private void memberChase() {

    }
}
