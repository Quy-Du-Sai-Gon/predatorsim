package org.quydusaigon.predatorsim.behaviours.util;

import org.quydusaigon.predatorsim.UI;
import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.gameengine.component.Behaviour;
import org.quydusaigon.predatorsim.gameengine.component.NodeComponent;

import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * Handles the grid display logic for {@link Animal}.
 */
public class GridDisplay extends Behaviour {

    private Node normalDisplayNode;
    private Rectangle gridDisplayNode;
    private NodeComponent<?> gridDisplayNodeComp;
    private ChangeListener<? super Boolean> onShowsGridChanged;
    private ChangeListener<? super Number> onPosXChanged;
    private ChangeListener<? super Number> onPosYChanged;

    @Override
    public void start() {
        normalDisplayNode = getComponent(NodeComponent.class).orElseThrow().getNode();
        gridDisplayNode = createGridDisplayNode();
        gridDisplayNodeComp = addComponent(new NodeComponent<>(gridDisplayNode));

        // default show
        setGridDisplay(UI.getShowsGridProperty().get());

        // toggle show
        onShowsGridChanged = (obs, oldVal, newVal) -> setGridDisplay(newVal);
        UI.getShowsGridProperty().addListener(onShowsGridChanged);

        // snap on pos changed
        posX().addListener(onPosXChanged = (obs, oldVal, newVal) -> snapGridX());
        posY().addListener(onPosYChanged = (obs, oldVal, newVal) -> snapGridY());

        // snap on start up (parent changed = added to the hierarchy = ready to snap)
        gridDisplayNode.parentProperty().addListener((obs, oldVal, newVal) -> {
            snapGridX();
            snapGridY();
        });
    }

    public Rectangle createGridDisplayNode() {
        var circle = (Circle) normalDisplayNode;

        var size = Math.ceil(circle.getRadius() * 2 / UI.GRID_SIZE) * UI.GRID_SIZE;
        var res = new Rectangle(size, size, circle.getFill());
        res.setStroke(Color.BLACK);
        res.setStrokeWidth(2);

        halfSize = size / 2;

        return res;
    }

    @Override
    public void onDestroy() {
        UI.getShowsGridProperty().removeListener(onShowsGridChanged);
        posX().removeListener(onPosXChanged);
        posY().removeListener(onPosYChanged);

        destroy(gridDisplayNodeComp);
    }

    private void setGridDisplay(boolean isDisplayingGrid) {
        normalDisplayNode.setVisible(!isDisplayingGrid);
        gridDisplayNode.setVisible(isDisplayingGrid);
        if (isDisplayingGrid) {
            snapGridX();
            snapGridY();
        }
    };

    private double halfSize;

    /**
     * Update the x coordinate of {@link #gridDisplayNode} to snap to grid cell.
     */
    private void snapGridX() {
        if (!UI.getShowsGridProperty().get())
            return;

        var scenePos = gridDisplayNode.localToScene(-halfSize, 0);
        var snappedScenePosX = Math.round(scenePos.getX() / UI.GRID_SIZE) * UI.GRID_SIZE;
        var snappedLocalPos = gridDisplayNode.sceneToLocal(snappedScenePosX, 0);
        gridDisplayNode.setX(snappedLocalPos.getX());
    }

    /**
     * Update the y coordinate of {@link #gridDisplayNode} to snap to grid cell.
     */
    private void snapGridY() {
        if (!UI.getShowsGridProperty().get())
            return;

        var scenePos = gridDisplayNode.localToScene(0, -halfSize);
        var snappedScenePosY = Math.round(scenePos.getY() / UI.GRID_SIZE) * UI.GRID_SIZE;
        var snappedLocalPos = gridDisplayNode.sceneToLocal(0, snappedScenePosY);
        gridDisplayNode.setY(snappedLocalPos.getY());
    }
}
