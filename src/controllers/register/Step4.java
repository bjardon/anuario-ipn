package controllers.register;

import classes.Dialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import models.RegisterModel;
import stages.SceneLoader;
import stages.StageManager;

/**
 * Created by root on 10/05/15.
 */
public class Step4 {

    @FXML
    private Label error;

    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtPasswordVerify;

    @FXML
    public void btnBackAction(ActionEvent event) throws Exception {
        new StageManager().changeScene((Stage) ((Node) event.getSource()).getScene().getWindow(), SceneLoader.SceneSource.REGISTER_STEP_3, SceneLoader.SceneTitles.REGISTER_STEP_3);
    }

    @FXML
    public void btnContinueAction(ActionEvent event) throws Exception {
        this.error.setText("");
        this.error.setVisible(false);
        String email = this.txtEmail.getText();
        String password = this.txtPassword.getText();
        String passwordVerify = this.txtPasswordVerify.getText();
        if (!email.isEmpty()) {
            if (email.contains("@")) {
                if (!RegisterModel.emailIsRegistered(email)) {
                    if (!password.isEmpty()) {
                        if (password.length() > 8) {
                            if (password.equals(passwordVerify)) {
                                Main.data.put("email", email);
                                Main.data.put("password", password);
                                RegisterModel.insertStudent(Main.data);
                                new StageManager().changeScene((Stage) ((Node) event.getSource()).getScene().getWindow(), SceneLoader.SceneSource.REGISTER_STEP_5, SceneLoader.SceneTitles.REGISTER_STEP_5);
                            } else {
                                this.error.setVisible(true);
                                this.error.setText("Las contraseñas son diferentes");
                            }
                        } else {
                            this.error.setVisible(true);
                            this.error.setText("Tu contraseña debe tener por lo menos 8 char[]");
                        }
                    } else {
                        this.error.setVisible(true);
                        this.error.setText("Tu contraseña está vacía");
                    }
                } else {
                    this.error.setVisible(true);
                    this.error.setText("Ese email ya está registrado");
                }
            } else {
                this.error.setVisible(true);
                this.error.setText("Tu correo electrónico es incorrecto, revísalo");
            }
        } else {
            this.error.setVisible(true);
            this.error.setText("Tu correo electrónico está vacío");
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
    }

}
