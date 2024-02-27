package game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import entities.player.Camera;
import entities.player.Player;
import maps.MapGenerator;
import utils.CollisionHandler;
import utils.TilesetLoader;
import utils.InputHandler;

import java.util.Objects;

import static utils.GameConstants.WINDOW_HEIGHT;
import static utils.GameConstants.WINDOW_WIDTH;


public class MainGame extends Application {
    private Player player;
    private Pane gameWorld;
    private Camera camera;
    private GameLoop gameLoop;
    private InputHandler inputHandler;
    private CollisionHandler collisionHandler;
    private String tilesetPath;
    private String playerPath;

    @Override
    public void start(Stage primaryStage) {
        gameWorld = new Pane();
        gameWorld.setPrefSize(WINDOW_WIDTH * 2, WINDOW_HEIGHT * 2);
        Scene scene = new Scene(gameWorld, WINDOW_WIDTH, WINDOW_HEIGHT);

        tilesetPath = Objects.requireNonNull(getClass().getResource("/tileset.png")).toExternalForm();
        playerPath = Objects.requireNonNull(getClass().getResource("/player.png")).toExternalForm();
        TilesetLoader tilesetLoader = new TilesetLoader(tilesetPath, playerPath);

        MapGenerator mapGenerator = new MapGenerator(tilesetLoader);
        mapGenerator.generateMap(40, 22);
        mapGenerator.displayMap(gameWorld);

        collisionHandler = new CollisionHandler(mapGenerator);
        player = new Player(tilesetLoader,collisionHandler);
        player.getSprite().setX(150);
        player.getSprite().setY(150);
        gameWorld.getChildren().add(player.getSprite());
        camera = new Camera(player, gameWorld,1.5);
        collisionHandler = new CollisionHandler(mapGenerator);
        gameLoop = new GameLoop(player, camera, collisionHandler);
        inputHandler = new InputHandler(player);


        scene.setOnKeyPressed(inputHandler::handleKeyPressed);
        scene.setOnKeyReleased(inputHandler::handleKeyReleased);

        primaryStage.setTitle("Roguelike Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        gameLoop.run();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
