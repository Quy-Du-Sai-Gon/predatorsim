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

public class StatDisplay extends Behaviour {

    public StackPane pane;
    private Group gameObject;
    private Animal animal;
    private AnimalStat stat;
    private Text text;

    public StatDisplay(AnimalStat stat, Group object) {
        gameObject = object;
        this.stat = stat;
        this.animal = GameObject.getComponent(object, Animal.class).get();
        Rectangle box = new Rectangle(130, 80, Color.LIMEGREEN);
        box.setOpacity(0.4);

        text = new Text();
        text.setFont(Font.font("Consolas", FontWeight.MEDIUM, FontPosture.REGULAR, 10));
        updateText();

        this.pane = new StackPane();
        pane.getChildren().addAll(box, text);
    }

    public void updateText() {
        text.setText(String.format("%s ID: %d\n",
                stat instanceof PredatorStat ? "Predator" : "Prey",
                gameObject.hashCode())
                + stat
                + animal.getCurrenState());
    }

    @Override
    public void update() {
        updateText();
    }

}
