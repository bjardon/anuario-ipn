package controllers.register;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import main.Main;
import stages.SceneLoader;
import stages.StageManager;

/**
 * Created by root on 11/05/15.
 */
public class Step5 {

    @FXML
    private Label lblId;
    @FXML
    private Label lblName;
    @FXML
    private Label lblLastName;
    @FXML
    private Label lblUnit;
    @FXML
    private Label lblGroup;
    @FXML
    private Label lblArea;
    @FXML
    private Label lblGen;
    @FXML
    private Label lblComment;
    @FXML
    private ImageView imgUserProfile;

    @FXML
    public void initialize() {
        this.lblId.setText(Main.data.get("id"));
        this.lblName.setText(Main.data.get("name"));
        this.lblLastName.setText(Main.data.get("lastname"));
        this.lblUnit.setText(Main.data.get("unit"));
        this.lblGroup.setText(Main.data.get("group"));
        this.lblArea.setText(Main.data.get("area"));
        this.lblGen.setText(Main.data.get("yearBegin") + " - " + Main.data.get("yearEnd"));
        if (!Main.data.get("comment").isEmpty()) {
            this.lblComment.setText(Main.data.get("comment"));
        } else {
            this.lblComment.setText("Ninguno");
        }
        if (!Main.data.get("imagePath").isEmpty()) {
            this.imgUserProfile.setImage(new Image("file:" + Main.data.get("imagePath")));
        }
    }

    @FXML
    public void btnExitAction(ActionEvent event) throws Exception {
        StageManager.closeStage(event);
        Stage stage = new StageManager().createStage(SceneLoader.SceneSource.LOGIN, SceneLoader.SceneTitles.LOGIN, StageManager.StageType.NOT_RESIZABLE);
        stage.show();
    }

}
