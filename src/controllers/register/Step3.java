package controllers.register;

import classes.Dialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.Main;
import stages.SceneLoader;
import stages.StageManager;

import java.awt.*;
import java.io.File;

/**
 * Created by root on 10/05/15.
 */
public class Step3 {

    private final Desktop desktop = Desktop.getDesktop();

    @FXML
    private Label error;

    @FXML
    private TextArea txaComment;
    @FXML
    private TextField txtImagePath;

    @FXML
    public void btnBackAction(ActionEvent event) throws Exception {
        new StageManager().changeScene((Stage) ((Node) event.getSource()).getScene().getWindow(), SceneLoader.SceneSource.REGISTER_STEP_2, SceneLoader.SceneTitles.REGISTER_STEP_2);
    }

    @FXML
    public void btnContinueAction(ActionEvent event) throws Exception {
        this.error.setText("");
        this.error.setVisible(false);
        String comment = this.txaComment.getText();
        String imagePath = this.txtImagePath.getText();
        if (comment == null) {
            comment = "";
        }
        if (imagePath == null) {
            imagePath = "";
        }
        if (!comment.isEmpty() || !imagePath.isEmpty()) {
            Main.data.put("comment", comment);
            Main.data.put("imagePath", imagePath);
            new StageManager().changeScene((Stage) ((Node) event.getSource()).getScene().getWindow(), SceneLoader.SceneSource.REGISTER_STEP_4, SceneLoader.SceneTitles.REGISTER_STEP_4);
        } else {
            if (new Dialog().confirmationMessage(Dialog.ConfirmType.EMPTY_FIELDS)) {
                Main.data.put("comment", "");
                Main.data.put("imagePath", "");
                new StageManager().changeScene((Stage) ((Node) event.getSource()).getScene().getWindow(), SceneLoader.SceneSource.REGISTER_STEP_4, SceneLoader.SceneTitles.REGISTER_STEP_4);
            }
        }
    }

    @FXML
    public void btnExploreAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona una fotografía para el perfil de alumno");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG", "*.jpg"));
        File file = fileChooser.showOpenDialog((Stage) ((Node) event.getSource()).getScene().getWindow());
        this.txtImagePath.setText(file.getPath());
    }

    @FXML
    public void btnCancelAction(ActionEvent event) throws Exception {
        if (new Dialog().confirmationMessage(Dialog.ConfirmType.CANCEL_REGISTER)) {
            StageManager.closeStage(event);
            Stage stage = new StageManager().createStage(SceneLoader.SceneSource.LOGIN, SceneLoader.SceneTitles.LOGIN, StageManager.StageType.NOT_RESIZABLE);
            stage.show();
        }
    }

    @FXML
    public void initialize() {
        this.error.setVisible(false);
        if (Main.data.size() > 7) {
            this.txaComment.setText(Main.data.get("comment"));
            this.txtImagePath.setText(Main.data.get("imagePath"));
        }
    }

}

