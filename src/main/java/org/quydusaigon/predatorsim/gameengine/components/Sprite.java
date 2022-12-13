package org.quydusaigon.predatorsim.gameengine.components;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import org.quydusaigon.predatorsim.gameengine.Component;
import org.quydusaigon.predatorsim.App;

public class Sprite extends Component {
    Rectangle renderedBox;
    Image renderedImage;

    public Sprite(Image image) {
        this.renderedImage = image;
    }
    @Override
    public void start() {
        App tempApp = new App();
        this.renderedBox = new Rectangle(tempApp.getWindowHeight()/30
                                        ,tempApp.getWindowHeight()/30);
        ImagePattern pattern = new ImagePattern(this.renderedImage, 20, 20, 40, 40, false);
        this.renderedBox.setFill(pattern);
    }

    @Override
    public void update() {

    }
}
