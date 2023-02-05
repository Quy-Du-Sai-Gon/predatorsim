package org.quydusaigon.predatorsim.behaviours.util;

import java.util.List;

import org.quydusaigon.predatorsim.UI;
import org.quydusaigon.predatorsim.behaviours.Animal;
import org.quydusaigon.predatorsim.gameengine.component.Behaviour;
import org.quydusaigon.predatorsim.gameengine.component.NodeComponent;
import org.quydusaigon.predatorsim.util.Parameter;

import javafx.beans.value.ChangeListener;
import javafx.scene.Node;

/**
 * Handles the grid display logic for {@link Animal}.
 */
public class GridDisplay extends Behaviour {

    private List<Node> normalDisplayNodes;
    private final Node gridDisplayNode;
    private NodeComponent<?> gridDisplayNodeComp;
    private ChangeListener<? super Boolean> onShowsGridChanged;
    private ChangeListener<? super Number> onPosXChanged;
    private ChangeListener<? super Number> onPosYChanged;

    public GridDisplay(Node gridDisplayNode, double nodeSize) {
        this.gridDisplayNode = gridDisplayNode;
        halfSize = nodeSize / 2;
    }

    @Override
    public void start() {
        normalDisplayNodes = getComponents(NodeComponent.class)
                .map(NodeComponent::getNode)
                .filter(Node::isVisible)
                .toList();
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

    @Override
    public void onDestroy() {
        UI.getShowsGridProperty().removeListener(onShowsGridChanged);
        posX().removeListener(onPosXChanged);
        posY().removeListener(onPosYChanged);

        destroy(gridDisplayNodeComp);
    }

    private void setGridDisplay(boolean isDisplayingGrid) {
        normalDisplayNodes.forEach(node -> node.setVisible(!isDisplayingGrid));
        gridDisplayNode.setVisible(isDisplayingGrid);
        if (isDisplayingGrid) {
            snapGridX();
            snapGridY();
        }
    };

    private final double halfSize;

    /**
     * Update the x coordinate of {@link #gridDisplayNode} to snap to grid cell.
     */
    private void snapGridX() {
        if (!gridDisplayNode.isVisible())
            return;

        gridDisplayNode.setTranslateX(0);
        var scenePos = gridDisplayNode.localToScene(-halfSize, 0);
        var snappedScenePosX = customRound(
                scenePos.getX() / UI.GRID_SIZE,
                scenePos.getX() < Parameter.getWindowWidth() / 2)
                * UI.GRID_SIZE;
        var snappedLocalPos = gridDisplayNode.sceneToLocal(snappedScenePosX, 0);
        gridDisplayNode.setTranslateX(snappedLocalPos.getX());
    }

    /**
     * Update the y coordinate of {@link #gridDisplayNode} to snap to grid cell.
     */
    private void snapGridY() {
        if (!gridDisplayNode.isVisible())
            return;

        gridDisplayNode.setTranslateY(0);
        var scenePos = gridDisplayNode.localToScene(0, -halfSize);
        var snappedScenePosY = customRound(
                scenePos.getY() / UI.GRID_SIZE,
                scenePos.getY() < Parameter.getWindowHeight() / 2)
                * UI.GRID_SIZE;
        var snappedLocalPos = gridDisplayNode.sceneToLocal(0, snappedScenePosY);
        gridDisplayNode.setTranslateY(snappedLocalPos.getY());
    }

    private double customRound(double x, boolean normalRound) {
        return normalRound
                ? Math.round(x)
                : Math.abs(x - Math.floor(x)) <= 0.5 ? Math.floor(x) : Math.ceil(x);
    }
}
