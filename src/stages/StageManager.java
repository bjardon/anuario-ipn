package stages;

import classes.Dialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StageManager {

    public static class StageType {
        public static final Boolean NOT_RESIZABLE = false;
        public static final Boolean RESIZABLE = true;
    }

    /**
     * Closes the stage that is parent to the ActionEvent's source
     *
     * @param event the ActionEvent
     */
    public static void closeStage(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    /**
     * Closes the stage that is parent to the Node
     *
     * @param node
     */
    public static void closeStage(Node node) {
        node.getScene().getWindow().hide();
    }

    /**
     * Creates a new Stage for a given URL and sets the title
     *
     * @param sceneSource the URL for the FXML file
     * @param title       the Window's title
     * @return a new Scene with this parameters
     * @throws Exception if FXMLLoader does not work properly
     */
    public Stage createStage(String sceneSource, String title) throws Exception {
        return createStage(sceneSource, title, StageType.RESIZABLE);
    }

    /**
     * Creates a new Stage for a given URL and StageStyle and sets the title
     *
     * @param sceneSource the URL for the FXML file
     * @param title       the Window's title
     * @return a new Scene with this parameters
     * @throws Exception if FXMLLoader does not work properly
     */
    public Stage createStage(String sceneSource, String title, Boolean stageType) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(sceneSource));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle(title);
        stage.setOnCloseRequest(event -> {
            if (!new Dialog().confirmationMessage(Dialog.ConfirmType.EXIT_APPLICATION)) {
                event.consume();
            }
        });
        stage.setResizable(stageType);
        stage.getIcons().add(new Image("/resources/img/icon.png"));
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(SceneLoader.Stylesheets.GOOGLE_FONTS, SceneLoader.Stylesheets.BUTTERHEAD);
        stage.setScene(scene);
        return stage;
    }

    /**
     * Changes the current scene for a given Stage
     *
     * @param stage       the stage
     * @param sceneSource the new scene's source
     * @param title       the scene's title
     * @throws Exception
     */
    public void changeScene(Stage stage, String sceneSource, String title) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(sceneSource));
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(SceneLoader.Stylesheets.GOOGLE_FONTS, SceneLoader.Stylesheets.BUTTERHEAD);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

}
