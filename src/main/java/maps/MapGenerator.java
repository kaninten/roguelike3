package maps;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import utils.Tile;
import utils.TilesetLoader;

import java.util.ArrayList;
import java.util.List;

public class MapGenerator {
    private final TilesetLoader tilesetLoader;
    private MapTile[][] mapTiles;
    private Tile floorTile;
    private Tile wallTile;
    private final int tileSize = 16;

    public MapGenerator(TilesetLoader tilesetLoader) {
        this.tilesetLoader = tilesetLoader;
    }

    public void generateMap(int width, int height) {
        mapTiles = new MapTile[width][height];
        floorTile = tilesetLoader.getTile(11, 0);
        wallTile = tilesetLoader.getTile(14, 0);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                ImageView floorView = new ImageView(floorTile.getImage());
                mapTiles[x][y] = new MapTile(floorView, x * tileSize, y * tileSize, true);
            }
        }

        for (int x = 0; x < width; x++) {
            mapTiles[x][0] = new MapTile(new ImageView(wallTile.getImage()), x * tileSize, 0, false);
            mapTiles[x][height - 1] = new MapTile(new ImageView(wallTile.getImage()), x * tileSize, (height - 1) * tileSize, false);
        }
        for (int y = 0; y < height; y++) {
            mapTiles[0][y] = new MapTile(new ImageView(wallTile.getImage()), 0, y * tileSize, false);
            mapTiles[width - 1][y] = new MapTile(new ImageView(wallTile.getImage()), (width - 1) * tileSize, y * tileSize, false);
        }
    }

    public void displayMap(Pane root) {
        for (int x = 0; x < mapTiles.length; x++) {
            for (int y = 0; y < mapTiles[x].length; y++) {
                ImageView sprite = mapTiles[x][y].getSprite();
                sprite.setX(x * tileSize);
                sprite.setY(y * tileSize);
                root.getChildren().add(sprite);
            }
        }
    }

    public List<Bounds> getAllWallBounds() {
        List<Bounds> wallBounds = new ArrayList<>();
        for (MapTile[] row : mapTiles) {
            for (MapTile tile : row) {
                if (!tile.isWalkable()) {
                    wallBounds.add(tile.getSprite().getBoundsInParent());
                }
            }
        }
        return wallBounds;
    }


}

