package utils;

import javafx.scene.input.KeyEvent;
import entities.player.Player;

public class InputHandler {
    private Player player;

    public InputHandler(Player player) {
        this.player = player;
    }

    public void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                player.setMovingUp(true);
                break;
            case S:
                player.setMovingDown(true);
                break;
            case A:
                player.setMovingLeft(true);
                break;
            case D:
                player.setMovingRight(true);
                break;
        }
    }

    public void handleKeyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                player.setMovingUp(false);
                break;
            case S:
                player.setMovingDown(false);
                break;
            case A:
                player.setMovingLeft(false);
                break;
            case D:
                player.setMovingRight(false);
                break;
        }
    }
}

