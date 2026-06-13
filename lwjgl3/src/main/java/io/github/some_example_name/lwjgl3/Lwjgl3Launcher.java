package io.github.Aldoria.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import io.github.Aldoria.Main;

public class Lwjgl3Launcher {

    public static void main(String[] args) {
        new Lwjgl3Application(new Main(), getConfig());
    }

    private static Lwjgl3ApplicationConfiguration getConfig() {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("RPG Game");
        config.setWindowedMode(1280, 720);
        return config;
    }
}
