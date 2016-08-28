package controllers.register;

import classes.Dialog;
import classes.Parser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import models.RegisterModel;
import stages.SceneLoader;
import stages.StageManager;

/**
 * Created by root on 9/05/15.
 */
public class Step1 {

    @FXML
    private Label error;
    @FXML
    private TextField txtStudentNumber;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtLastName;
    @FXML
    private Button btnCancel;

    @FXML
    public void btnContinueAction(ActionEvent event) throws Exception {
        this.error.setText("");
        this.error.setVisible(false);
        Parser parser = new Parser();
        String username = this.txtStudentNumber.getText();
        String name = this.txtName.getText();
        String lastName = this.txtLastName.getText();
        if (!username.isEmpty()) {
            if (!name.isEmpty()) {
                if (!lastName.isEmpty()) {
                    if (username.length() == 10) {
                        if (parser.isNumber(username)) {
                            if (!RegisterModel.studentExists(username)) {
                                Main.data.put("id", username);
                                Main.data.put("name", name);
                                Main.data.put("lastname", lastName);
                                new StageManager().changeScene((Stage) ((Node) event.getSource()).getScene().getWindow(), SceneLoader.SceneSource.REGISTER_STEP_2, SceneLoader.SceneTitles.REGISTER_STEP_2);
                            } else {
                                this.error.setVisible(true);
                                this.error.setText("Este número de boleta ya está registrado, inicia sesión");
                                this.btnCancel.setText("Ir a Login");
                                this.btnCancel.setOnAction(event1 -> {
                                    try {
                                        StageManager.closeStage(event);
                                        Stage stage = new StageManager().createStage(SceneLoader.SceneSource.LOGIN, SceneLoader.SceneTitles.LOGIN, StageManager.StageType.NOT_RESIZABLE);
                                        stage.show();
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                });
                            }
                        } else {
                            this.error.setVisible(true);
                            this.error.setText("Tu número de boleta es inválido: contiene letras");
                        }
                    } else {
                        this.error.setVisible(true);
                        this.error.setText("Tu número de boleta es inválido: no tiene 10 caracteres");
                    }
                } else {
                    this.error.setVisible(true);
                    this.error.setText("Tu apellido está vacío");
                }
            } else {
                this.error.setVisible(true);
                this.error.setText("Tu nombre está vacío");
            }
        } else {
            this.error.setVisible(true);
            this.error.setText("Tu número de boleta está vacío");
        }
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
        if (!Main.data.isEmpty()) {
            this.txtStudentNumber.setText(Main.data.get("id"));
            this.txtName.setText(Main.data.get("name"));
            this.txtLastName.setText(Main.data.get("lastname"));
        }
    }

}
