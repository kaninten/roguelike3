package utils;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import entities.Entity;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class AnimationHandler {
    private Image[][] animations;
    private int currentFrameIndex = 0;
    private long lastUpdate = 0;
    private long frameDuration = 100_000_000; // Beispiel für Frame-Dauer
    private int currentAnimationIndex = 0;

    public AnimationHandler(Image[][] animations, long frameDuration) {
        this.animations = animations;
        this.frameDuration = frameDuration;
    }

    public void updateAnimation(long now) {
        if (now - lastUpdate > frameDuration) {
            currentFrameIndex = (currentFrameIndex + 1) % animations[currentAnimationIndex].length;
            lastUpdate = now;
        }
    }

    public Image getCurrentFrame() {
        return animations[currentAnimationIndex][currentFrameIndex];
    }

    public void setAnimationIndex(int index) {
        if (index != currentAnimationIndex) {
            currentAnimationIndex = index;
            currentFrameIndex = 0; // Zurücksetzen des Frame-Index bei Änderung der Animation
            lastUpdate = System.nanoTime(); // Aktualisiere die Zeit, um sofortiges Frame-Update zu ermöglichen
        }
    }
}


