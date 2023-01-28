package org.quydusaigon.predatorsim.behaviours.util;

import java.util.Map;

import org.quydusaigon.predatorsim.gameengine.component.Behaviour;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;

public class Draggable extends Behaviour {

    private static class MutablePoint2D {
        public double x, y;
    }

    private Map<EventType<MouseEvent>, EventHandler<MouseEvent>> handlers;

    @Override
    public void start() {
        var mouseToObjDistance = new MutablePoint2D();
        var x = posX();
        var y = posY();

        handlers = Map.of(
                MouseEvent.MOUSE_PRESSED, event -> {
                    mouseToObjDistance.x = x.get() - event.getScreenX();
                    mouseToObjDistance.y = y.get() - event.getScreenY();
                    draggingEffect();
                },
                MouseEvent.MOUSE_RELEASED, event -> {
                    hoverEffect();
                },
                MouseEvent.MOUSE_DRAGGED, event -> {
                    x.set(event.getScreenX() + mouseToObjDistance.x);
                    y.set(event.getScreenY() + mouseToObjDistance.y);
                },
                MouseEvent.MOUSE_ENTERED, event -> {
                    if (!event.isPrimaryButtonDown())
                        hoverEffect();
                },
                MouseEvent.MOUSE_EXITED, event -> {
                    if (!event.isPrimaryButtonDown())
                        clearEffect();
                });

        handlers.forEach(getGameObject()::addEventHandler);
    }

    @Override
    public void onDestroy() {
        handlers.forEach(getGameObject()::removeEventHandler);
    }

    private void hoverEffect() {
        var go = getGameObject();
        go.getScene().setCursor(Cursor.HAND);
        go.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0), 10, 0, 0, 0);");
    }

    private void draggingEffect() {
        var go = getGameObject();
        go.getScene().setCursor(Cursor.MOVE);
        go.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0), 7, 0.8, 0, 0);");
    }

    private void clearEffect() {
        var go = getGameObject();
        go.getScene().setCursor(Cursor.DEFAULT);
        go.setStyle("-fx-effect: null;");
    }
}
