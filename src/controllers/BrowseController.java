package controllers;

import classes.Dialog;
import classes.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.Main;
import models.BrowseModel;
import stages.SceneLoader;
import stages.StageManager;

import java.net.URL;

/**
 * Created by root on 9/05/15.
 */
public class BrowseController {

    @FXML
    private TextField txtQuery;

    @FXML
    private HBox vbxResultContainer;

    @FXML
    private TableView<Student> tbvResult;
    @FXML
    private TableColumn<Student, String> tbcLastName;
    @FXML
    private TableColumn<Student, String> tbcName;
    @FXML
    private Label lblFullName;
    @FXML
    private Label lblGroup;
    @FXML
    private Label lblArea;
    @FXML
    private Label lblGeneration;
    @FXML
    private Label lblEmail;
    @FXML
    private ImageView imgUser;

    private void showDetails(Student student) {
        if (student != null) {
            this.vbxResultContainer.setVisible(true);
            this.lblFullName.setText(student.getName() + " " + student.getLastname());
            this.lblGroup.setText(student.getGroup());
            this.lblArea.setText(student.getArea());
            this.lblGeneration.setText(student.getGeneration());
            this.lblEmail.setText(student.getEmail());
            try {
                URL url = new URL("http://localhost/projects/yearbook/" + student.getId() + ".jpg");
                this.imgUser.setImage(new Image(url.getFile()));
            } catch (Exception ex) {
            }
        } else {
            this.vbxResultContainer.setVisible(false);
        }
    }

    @FXML
    public void btnSearchAction(ActionEvent event) {
        String query = this.txtQuery.getText();
        if (!query.isEmpty()) {
            this.tbvResult.setItems(BrowseModel.getMatches(query, Main.getCurrentStudent().getId()));
        }
    }

    @FXML
    public void btnResetSearch(ActionEvent event) {
        this.tbvResult.setItems(BrowseModel.getAll(Main.getCurrentStudent().getId()));
        this.txtQuery.setText("");
    }

    @FXML
    public void btnShowCommentAction(ActionEvent event) {
        String name = this.tbvResult.getSelectionModel().getSelectedItem().getName();
        String comment = this.tbvResult.getSelectionModel().getSelectedItem().getComment();
        if (!comment.isEmpty()) {
            new Dialog().informationMessage("Comentario personal", "Comentario personal de " + name, comment);
        } else {
            new Dialog().informationMessage("Comentario personal", name + " no dejó un comentario personal", "Supongamos que no tiene nada que decir...");
        }
    }

    @FXML
    public void btnCloseSession(ActionEvent event) throws Exception {
        StageManager.closeStage(event);
        Stage stage = new StageManager().createStage(SceneLoader.SceneSource.LOGIN, SceneLoader.SceneTitles.LOGIN, StageManager.StageType.NOT_RESIZABLE);
        stage.show();
    }

    @FXML
    public void initialize() {
        this.tbcLastName.setCellValueFactory(cvf -> cvf.getValue().getLastnameProperty());
        this.tbcName.setCellValueFactory(cvf -> cvf.getValue().getNameProperty());
        this.tbvResult.setItems(BrowseModel.getAll(Main.getCurrentStudent().getId()));
        this.vbxResultContainer.setVisible(false);
    }

}
