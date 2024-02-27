package game;

import javafx.animation.AnimationTimer;
import entities.player.Camera;
import entities.player.Player;
import utils.CollisionHandler;

public class GameLoop {

    private Player player;
    private Camera camera;
    private CollisionHandler collisionHandler;

    private boolean run = true;
    private long lastUpdateTime = 0;
    private static final long NANOS_PER_FRAME = 1_000_000_000 / 60; // 60 FPS

    public GameLoop(Player player, Camera camera, CollisionHandler collisionHandler) {
        this.player = player;
        this.camera = camera;
        this.collisionHandler = collisionHandler;
    }

    public void run() {
        update();
    }

    private void update() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if ((now - lastUpdateTime) >= NANOS_PER_FRAME) {
                    player.update(now);
                    camera.update();
                    lastUpdateTime = now;
                }
            }
        };
        timer.start();
    }
}
