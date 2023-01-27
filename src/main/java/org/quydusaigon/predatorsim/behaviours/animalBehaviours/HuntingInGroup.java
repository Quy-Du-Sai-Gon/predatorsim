package org.quydusaigon.predatorsim.behaviours.animalBehaviours;

import java.util.ArrayList;
import java.util.Optional;

import org.quydusaigon.predatorsim.gameengine.component.Collider;
import org.quydusaigon.predatorsim.gameengine.component.NodeComponent;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;
import org.quydusaigon.predatorsim.gameengine.util.TransformInit;
import org.quydusaigon.predatorsim.util.PredatorStat;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class HuntingInGroup extends Hunting {
    private ArrayList<Group> alliesObjects;
    private Optional<Group> leaderObject;
    private int numberOfAllies;
    private boolean isLeader = false;
    private boolean groupFounded = false;

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

    public void setNumberOfAllies(int numberOfAllies) {
        this.numberOfAllies = numberOfAllies;
    }

    public void setIsLeader(boolean isLeader) {
        this.isLeader = isLeader;
    }

    public void setLeader(Group gameObject) {
        leaderObject = Optional.of(gameObject);
    }

    public void startHowling() {
        this.isLeader = true;
        PredatorStat predatorStat = (PredatorStat) animalStat;

        var circleHowling = new Circle(predatorStat.howlingRadius, Color.DARKCYAN);
        circleHowling.setOpacity(0.4);
        var howlingVisionNodeComp = new NodeComponent<>(circleHowling);
        Collider howlCollider = new Collider<>(howlingVisionNodeComp);

        var circleGroup = new Circle(predatorStat.groupRadius, Color.DARKRED);
        circleGroup.setOpacity(0.4);
        var groupVisionNodeComp = new NodeComponent<>(circleGroup);
        Collider groupCollider = new Collider<>(groupVisionNodeComp);
    }

    public void howl() {
        
    }

    public void join() {
    
    }

    public void leaderChase() {

    }

    public void memberChase() {

    }
}
