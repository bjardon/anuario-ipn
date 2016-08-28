package main;

import classes.Student;
import javafx.application.Application;
import javafx.stage.Stage;
import stages.SceneLoader;
import stages.StageManager;

import java.util.HashMap;

public class Main extends Application {

    public static HashMap<String, String> data = new HashMap<>();
    private static Student currentStudent;

    public static void setCurrentStudent(Student student) {
        Main.currentStudent = student;
    }

    public static Student getCurrentStudent() {
        return currentStudent;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage = new StageManager().createStage(SceneLoader.SceneSource.LOGIN, SceneLoader.SceneTitles.LOGIN, StageManager.StageType.NOT_RESIZABLE);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
