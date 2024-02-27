package utils;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;


public class TilesetLoader {
    private Image tilesetImage;
    private int tileSize = 16;
    private List<Tile> tiles = new ArrayList<>();
    private Image playerImage;
    private List<Image[]> playerAnimations;

    public TilesetLoader(String tilesetPath, String playerPath) {
        this.tilesetImage = new Image(tilesetPath);
        this.playerImage = new Image(playerPath);

        loadAllTiles();
        loadPlayerAnimations();
    }

    private void loadAllTiles() {
        if (tilesetImage == null) {
            return;
        }

        int numTilesX = (int) tilesetImage.getWidth() / tileSize;
        int numTilesY = (int) tilesetImage.getHeight() / tileSize;
        PixelReader tileReader = tilesetImage.getPixelReader();

        for (int y = 0; y < numTilesY; y++) {
            for (int x = 0; x < numTilesX; x++) {
                WritableImage tileImage = new WritableImage(tileReader, x * tileSize, y * tileSize, tileSize, tileSize);

                Tile tile = new Tile(tileImage);
                tiles.add(tile);
            }
        }
    }

    public Tile getTile(int index) {
        if (index < 0 || index >= tiles.size()) {
            return null;
        }
        return tiles.get(index);
    }

    public Tile getTile(int column, int row) {
        int index = row * (int) (tilesetImage.getWidth() / tileSize) + column;
        return getTile(index);
    }

    private void loadPlayerAnimations() {
        playerAnimations = new ArrayList<>();
        final int framesPerAnimation = 6;
        final int animationCount = 6;
        PixelReader reader = playerImage.getPixelReader();

        for (int i = 0; i < animationCount; i++) {
            Image[] animation = new Image[framesPerAnimation];
            for (int j = 0; j < framesPerAnimation; j++) {
                animation[j] = new WritableImage(reader, j * 32, i * 32, 32, 32);
            }
            playerAnimations.add(animation);
        }
    }

    public Image[] getPlayerAnimation(int index) {
        if (index < 0 || index >= playerAnimations.size()) {
            return null;
        }
        return playerAnimations.get(index);
    }

    public Image[][] getAllPlayerAnimations() {
        Image[][] allAnimations = new Image[playerAnimations.size()][];
        for (int i = 0; i < playerAnimations.size(); i++) {
            allAnimations[i] = getPlayerAnimation(i);
        }
        return allAnimations;
    }

}
