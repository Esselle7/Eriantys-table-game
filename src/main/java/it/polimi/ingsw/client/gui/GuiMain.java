package it.polimi.ingsw.client.gui;

import it.polimi.ingsw.client.gui.ScenesController.GuiController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;
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
        getStage().setResizable(false);
        getStage().setTitle("ERIANTIS");
        getQueue().add(new Object());
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/LoadingPage.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        getStage().setScene(new Scene(root));
        getStage().show();
    }



    /**
     * Used by other threads to change the scene of the stage
     */
    static public void updateScene(Parent root, String title) {
        Scene newScene = new Scene(root,800,600);
        getStage().setScene(newScene);
        getStage().centerOnScreen();
        getStage().setTitle(title);
        getStage().sizeToScene();
        getStage().show();
        getStage().setAlwaysOnTop(true);
        getStage().setAlwaysOnTop(false);
    }

}