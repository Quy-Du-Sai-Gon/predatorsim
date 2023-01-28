package org.quydusaigon.predatorsim.util;

import org.quydusaigon.predatorsim.UI;
import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.gameengine.component.Behaviour;
import org.quydusaigon.predatorsim.gameengine.component.NodeComponent;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;

import javafx.beans.value.ChangeListener;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class StatDisplay extends Behaviour {

    private Animal animal;
    private Text text;
    private Group thisAnimalGameObject;

    private ChangeListener<? super Boolean> onShowsStatusChanged;

    @Override
    public void start() {
        thisAnimalGameObject = GameObject.getParent(getGameObject()).orElseThrow();

        animal = GameObject.getComponent(thisAnimalGameObject, Animal.class).get();
        Rectangle box = new Rectangle(130, 80, Color.LIMEGREEN);
        box.setOpacity(0.4);

        text = new Text();
        text.setFont(Font.font("Consolas", FontWeight.MEDIUM, FontPosture.REGULAR, 10));
        updateText();

        var pane = new StackPane();
        pane.getChildren().addAll(box, text);

        addComponent(new NodeComponent<>(pane));

        // visibility
        pane.setVisible(UI.getShowsStatusProperty().get()); // default

        onShowsStatusChanged = (_observable, _oldValue, showsStatus) -> {
            pane.setVisible(showsStatus);
        };

        UI.getShowsStatusProperty().addListener(onShowsStatusChanged);
    }

    @Override
    public void onDestroy() {
        UI.getShowsStatusProperty().removeListener(onShowsStatusChanged);
    }

    public void updateText() {
        text.setText(String.format("%s ID: %d\n",
                animal.animalStat instanceof PredatorStat ? "Predator" : "Prey",
                thisAnimalGameObject.hashCode())
                + animal.animalStat
                + animal.getCurrenState());
    }

    @Override
    public void update() {
        updateText();
    }

}
