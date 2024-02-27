package entities.player;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import entities.Entity;
import utils.CollisionHandler;
import utils.TilesetLoader;
import utils.AnimationHandler;

public class Player extends Entity {
    private final ImageView sprite;
    private final AnimationHandler animationHandler;
    private final CollisionHandler collisionHandler;
    private double speed = 3;
    private boolean moving, movingUp, movingDown, movingLeft, movingRight = false;
    private boolean collision = false;
    private double x, y;

    public Player(TilesetLoader tilesetLoader, CollisionHandler collisionHandler) {
        this.animationHandler = new AnimationHandler(tilesetLoader.getAllPlayerAnimations(), 100_000_000);
        this.collisionHandler = collisionHandler;
        this.sprite = new ImageView(animationHandler.getCurrentFrame());
    }

    public void update(long now) {
        moving = movingUp || movingDown || movingLeft || movingRight;
        animationHandler.updateAnimation(now);

        if (movingLeft) {
            if (!collisionHandler.checkWallCollision(collisionHandler.createOffsetBounds(sprite.getBoundsInParent(), -speed, 0))) {
                x -= speed;
            }
            sprite.setScaleX(-1);
        }
        if (movingRight) {
            if (!collisionHandler.checkWallCollision(collisionHandler.createOffsetBounds(sprite.getBoundsInParent(), speed, 0))) {
                x += speed;
            }
            sprite.setScaleX(1);
        }
        if (movingUp) {
            if (!collisionHandler.checkWallCollision(collisionHandler.createOffsetBounds(sprite.getBoundsInParent(), 0, -speed))) {
                y -= speed;
            }
        }
        if (movingDown) {
            if (!collisionHandler.checkWallCollision(collisionHandler.createOffsetBounds(sprite.getBoundsInParent(), 0, speed))) {
                y += speed;
            }
        }
        if (!moving) {
            animationHandler.setAnimationIndex(0);
        }

        sprite.setTranslateX(x);
        sprite.setTranslateY(y);
        sprite.setImage(animationHandler.getCurrentFrame());
    }


    public void setMovingUp(boolean moving) {
        this.movingUp = moving;
        if (moving) animationHandler.setAnimationIndex(1);
    }

    public void setMovingDown(boolean moving) {
        this.movingDown = moving;
        if (moving) animationHandler.setAnimationIndex(1);
    }

    public void setMovingLeft(boolean moving) {
        this.movingLeft = moving;
        if (moving) animationHandler.setAnimationIndex(1);
    }

    public void setMovingRight(boolean moving) {
        this.movingRight = moving;
        if (moving) animationHandler.setAnimationIndex(1);
    }

    public ImageView getSprite() {
        return sprite;
    }

}

