package it.polimi.ingsw.client.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * JavaFX Main Thread.
 */
public class GuiMain extends Application {

    private static final BlockingQueue<Object> queue = new LinkedBlockingDeque<>();
    private static Stage initStage;

    public static BlockingQueue<Object> getQueue() {
        return queue;
    }

    public static Stage getStage() {
        return initStage;
    }


    /**
     * Entry point of JavaFX
     *
     * @param primaryStage First Window created by JavaFX
     */
    @Override
    public void start(Stage primaryStage) {
        initStage = primaryStage;
        primaryStage.setResizable(false);
        getStage().setTitle("ERIANTIS");
        queue.add(new Object());

        // Exit the program when pressing X button
        getStage().setOnCloseRequest(windowEvent -> {
            if (windowEvent.getEventType().equals(WindowEvent.WINDOW_CLOSE_REQUEST)) {
                System.exit(0);
            }
        });

        getStage().show();
    }



    /**
     * Used by other threads to change the scene of the stage
     */
    static public void updateScene(Scene newScene, String title) {
        getStage().setScene(newScene);
        getStage().setTitle(title);
        getStage().sizeToScene();
        getStage().show();
        getStage().setAlwaysOnTop(true);
        getStage().setAlwaysOnTop(false);
    }
}