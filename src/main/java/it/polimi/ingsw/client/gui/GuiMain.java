package it.polimi.ingsw.client.gui;

import it.polimi.ingsw.client.gui.ScenesController.GuiPlaygroundController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
    private static Stage newWindow;

    public static BlockingQueue<Object> getQueue() {
        return queue;
    }

    public static Stage getStage() {
        return initStage;
    }

    public static Stage getNewWindow() {
        return newWindow;
    }

    /**
     * Entry point of JavaFX
     *
     * @param primaryStage First Window created by JavaFX
     */
    @Override
    public void start(Stage primaryStage) {
        newWindow = new Stage();
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
        Scene newScene = new Scene(root,850,600);
        getStage().setScene(newScene);
        getStage().centerOnScreen();
        getStage().setTitle(title);
        getStage().sizeToScene();
        getStage().show();
        getStage().setAlwaysOnTop(true);
        getStage().setAlwaysOnTop(false);
    }
    /**
     * Used by other threads to change the scene of the board stage
     */
    static public void updateSceneBoard(Parent root, String title,int h, int w) {
        Scene newScene = new Scene(root,w,h);
        getStage().setScene(newScene);
        getStage().centerOnScreen();
        getStage().setTitle(title);
        getStage().sizeToScene();
        getStage().show();
        getStage().setAlwaysOnTop(true);
        getStage().setAlwaysOnTop(false);
    }

    /**
     * Used by other threads to change the scene of the custom (width and height) stage
     */
    static public void updateNewWindow(Parent root, String title,int h, int w)
    {
        Scene newScene = new Scene(root,h,w);
        getNewWindow().setScene(newScene);
        getNewWindow().centerOnScreen();
        getNewWindow().setTitle(title);
        getNewWindow().sizeToScene();
        getNewWindow().show();
        getNewWindow().setAlwaysOnTop(true);
        getNewWindow().setAlwaysOnTop(false);
    }



}