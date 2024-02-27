package entities.player;

import javafx.scene.layout.Pane;

import static utils.GameConstants.WINDOW_HEIGHT;
import static utils.GameConstants.WINDOW_WIDTH;

public class Camera {
    private double x, y;
    private final double zoomFactor;
    private double lerpFactor = 0.1;

    private Player player;
    private Pane gameWorld;

    public Camera(Player player, Pane gameWorld, double zoomFactor) {
        this.player = player;
        this.gameWorld = gameWorld;
        this.zoomFactor = zoomFactor;
    }

    public void update() {
        centerOn(player.getSprite().getTranslateX(), player.getSprite().getTranslateY(), WINDOW_WIDTH, WINDOW_HEIGHT);
        gameWorld.setLayoutX(-getX() * getZoomFactor());
        gameWorld.setLayoutY(-getY() * getZoomFactor());
        gameWorld.setScaleX(getZoomFactor());
        gameWorld.setScaleY(getZoomFactor());
    }

    public void centerOn(double targetX, double targetY, double sceneWidth, double sceneHeight) {
        double targetCamX = targetX - sceneWidth / (2 * zoomFactor);
        double targetCamY = targetY - sceneHeight / (2 * zoomFactor);

        x += (targetCamX - x) * lerpFactor;
        y += (targetCamY - y) * lerpFactor;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZoomFactor() {
        return zoomFactor;
    }
}

