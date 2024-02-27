package utils;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import entities.Entity;
import entities.player.Player;
import maps.MapGenerator;

public class CollisionHandler {
    private MapGenerator mapGenerator;

    public CollisionHandler(MapGenerator mapGenerator) {
        this.mapGenerator = mapGenerator;
    }

    public boolean checkWallCollision(Bounds playerBounds) {
        for (Bounds wallBounds : mapGenerator.getAllWallBounds()) {
            if (playerBounds.intersects(wallBounds)) {
                return true;
            }
        }
        return false;
    }



    public Bounds createOffsetBounds(Bounds playerBounds, double offsetX, double offsetY) {
        return new BoundingBox(
                playerBounds.getMinX() + offsetX,
                playerBounds.getMinY() + offsetY,
                playerBounds.getWidth(),
                playerBounds.getHeight()
        );
    }

}
