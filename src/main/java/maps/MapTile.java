package maps;

import javafx.scene.image.ImageView;

public class MapTile {

    private final ImageView sprite;
    private final double x, y;
    private final boolean isWalkable;

    public MapTile(ImageView sprite, double x, double y, boolean isWalkable) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.isWalkable = isWalkable;
    }

    public ImageView getSprite() {
        return sprite;
    }

    public boolean isWalkable() {
        return isWalkable;
    }
}
