package org.quydusaigon.predatorsim.util;

import org.quydusaigon.predatorsim.UI;
import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.behaviours.util.Velocity;
import org.quydusaigon.predatorsim.gameengine.component.Behaviour;
import org.quydusaigon.predatorsim.gameengine.component.NodeComponent;
import org.quydusaigon.predatorsim.gameengine.gameobject.GameObject;

import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class StatDisplay extends Behaviour {

    private Animal animal;
    private Text text;
    private Group thisAnimalGameObject;

    private ChangeListener<? super Boolean> onShowsStatusChanged;
    private EventHandler<MouseEvent> onMouseClicked;

    private Velocity velocity;

    @Override
    public void start() {
        thisAnimalGameObject = GameObject.getParent(getGameObject()).orElseThrow();

        velocity = GameObject.getComponent(thisAnimalGameObject, Velocity.class).orElseThrow();
        animal = GameObject.getComponent(thisAnimalGameObject, Animal.class).orElseThrow();

        text = new Text();
        text.setFont(Font.font("Consolas", FontWeight.MEDIUM, FontPosture.REGULAR, 10));
        updateText();

        var pane = new StackPane(text);
        pane.setStyle("-fx-background-color: rgba(50, 205, 50, 0.4);" +
                "-fx-padding: 7px;" +
                "-fx-background-radius: 7px;");

        addComponent(new NodeComponent<>(pane));

        // visibility
        pane.setVisible(UI.getShowsStatusProperty().get()); // default

        // change visibility from settings UI
        onShowsStatusChanged = (_observable, _oldValue, showsStatus) -> {
            pane.setVisible(showsStatus);
        };
        UI.getShowsStatusProperty().addListener(onShowsStatusChanged);

        // change by right-clicking
        onMouseClicked = (event) -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                pane.setVisible(!pane.visibleProperty().get());
            }
        };
        thisAnimalGameObject.addEventHandler(MouseEvent.MOUSE_CLICKED, onMouseClicked);
    }

    @Override
    public void onDestroy() {
        UI.getShowsStatusProperty().removeListener(onShowsStatusChanged);
        thisAnimalGameObject.removeEventHandler(MouseEvent.MOUSE_CLICKED, onMouseClicked);
    }

    public void updateText() {
        text.setText(String.format("%s ID: %d\n",
                animal.animalStat instanceof PredatorStat ? "Predator" : "Prey",
                thisAnimalGameObject.hashCode())
                + velocity + '\n'
                + animal.animalStat
                + animal.getCurrentState());
    }

    @Override
    public void update() {
        updateText();
    }

}
