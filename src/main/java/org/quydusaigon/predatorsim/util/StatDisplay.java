package org.quydusaigon.predatorsim.util;

import org.quydusaigon.predatorsim.gameengine.component.Component;

import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class StatDisplay extends Component{

    public StackPane pane;
    public Group gameObject;

    public StatDisplay(AnimalStat stat, Group object) {
        gameObject = object;

        Rectangle box = new Rectangle(140, 80, Color.LIMEGREEN);        
        box.setOpacity(0.4);
    
        Text text = new Text();
        text.setFont(Font.font("verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 10));
        if (stat instanceof PredatorStat) {
            PredatorStat predatorstat = (PredatorStat) stat;
            text.setText("Predator ID: " + object.hashCode() + "\n" +
                        "Speed: " + String.format("%.3f", predatorstat.runSpeed) + "\n" +
                        "Vision range: " + String.format("%.3f", predatorstat.visionRange) + "\n" +
                        "Hunger Resilience: " + predatorstat.starvationResilience + "\n" +
                        "Group radius: " + predatorstat.groupRadius);
        }
        else if (stat instanceof PreyStat) {
            PreyStat preyStat = (PreyStat) stat;
            text.setText("Prey ID: " + object.hashCode() + "\n" +
                        "Speed: " + String.format("%.3f", preyStat.runSpeed) + "\n" +
                        "Vision range: " + String.format("%.3f", preyStat.visionRange) +"\n" +
                        "Nutrition: " + preyStat.nutrition + "\n" +
                        "Defense: " + String.format("%.3f", preyStat.defense));
        }
        
        this.pane = new StackPane();
        pane.getChildren().addAll(box, text);
    }

}
