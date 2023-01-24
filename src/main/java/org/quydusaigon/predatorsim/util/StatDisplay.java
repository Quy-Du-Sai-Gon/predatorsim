package org.quydusaigon.predatorsim.util;

import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.gameengine.component.Behaviour;
import org.quydusaigon.predatorsim.gameengine.component.Component;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;

import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class StatDisplay extends Behaviour{

    public StackPane pane;
    private Group gameObject;
    private Animal animal;
    private AnimalStat stat;
    
    public StatDisplay(AnimalStat stat, Group object) {
        gameObject = object;
        this.stat = stat;
        this.animal = GameObject.getComponent(object, Animal.class).get();
        Rectangle box = new Rectangle(130, 80, Color.LIMEGREEN);        
        box.setOpacity(0.4);
    
        Text text = new Text();
        text.setFont(Font.font("Consolas", FontWeight.MEDIUM, FontPosture.REGULAR, 10));
        if (this.stat instanceof PredatorStat) {
            PredatorStat predatorstat = (PredatorStat) this.stat;
            text.setText("Predator ID: " + this.gameObject.hashCode() + "\n" +
                        "Speed: " + String.format("%.3f", predatorstat.runSpeed) + "\n" +
                        "Vision range: " + String.format("%.3f", predatorstat.visionRange) + "\n" +
                        "Hunger Resilience: " + predatorstat.starvationResilience + "\n" +
                        "Group radius: " + predatorstat.groupRadius + "\n" +
                        "State: ");
        }
        else if (this.stat instanceof PreyStat) {
            PreyStat preyStat = (PreyStat) this.stat;
            text.setText("Prey ID: " + this.gameObject.hashCode() + "\n" +
                        "Speed: " + String.format("%.3f", preyStat.runSpeed) + "\n" +
                        "Vision range: " + String.format("%.3f", preyStat.visionRange) +"\n" +
                        "Nutrition: " + preyStat.nutrition + "\n" +
                        "Defense: " + String.format("%.3f", preyStat.defense)+ "\n" +
                        "State: " + animal.getCurrenState());
        }
    
        this.pane = new StackPane();
        pane.getChildren().addAll(box, text);
    }
    
    @Override
    public void update() {
        pane.getChildren().clear();

        Rectangle box = new Rectangle(140, 80, Color.LIMEGREEN);        
        box.setOpacity(0.4);

        Text text = new Text();
        text.setFont(Font.font("verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 10));
        if (this.stat instanceof PredatorStat) {
            PredatorStat predatorstat = (PredatorStat) this.stat;
            text.setText("Predator ID: " + this.gameObject.hashCode() + "\n" +
                        "Speed: " + String.format("%.3f", predatorstat.runSpeed) + "\n" +
                        "Vision range: " + String.format("%.3f", predatorstat.visionRange) + "\n" +
                        "Hunger Resilience: " + predatorstat.starvationResilience + "\n" +
                        "Group radius: " + predatorstat.groupRadius + "\n" +
                        "State: " + animal.getCurrenState().getClass());
        }
        else if (this.stat instanceof PreyStat) {
            PreyStat preyStat = (PreyStat) this.stat;
            text.setText("Prey ID: " + this.gameObject.hashCode() + "\n" +
                        "Speed: " + String.format("%.3f", preyStat.runSpeed) + "\n" +
                        "Vision range: " + String.format("%.3f", preyStat.visionRange) +"\n" +
                        "Nutrition: " + preyStat.nutrition + "\n" +
                        "Defense: " + String.format("%.3f", preyStat.defense)+ "\n" +
                        "State: " + animal.getCurrenState().getClass());
        }

        pane.getChildren().addAll(box, text);
    }

}
