package controllers;

import classes.Dialog;
import classes.Parser;
import classes.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import models.LoginModel;
import stages.SceneLoader;
import stages.StageManager;

public class LoginController {

    @FXML
    private Label error;

    @FXML
    private TextField txtStNumber;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    int count = 0;

    @FXML
    public void btnRegisterAction(ActionEvent event) throws Exception {
        StageManager.closeStage(event);
        Stage stage = new StageManager().createStage(SceneLoader.SceneSource.REGISTER_STEP_1, SceneLoader.SceneTitles.REGISTER_STEP_1);
        stage.show();
    }

    @FXML
    public void btnLoginAction(ActionEvent event) throws Exception {
        this.error.setText("");
        this.error.setVisible(false);
        String username = this.txtStNumber.getText();
        String password = this.txtPassword.getText();
        if (count < 2) {
            if (!username.isEmpty()) {
                if (!password.isEmpty()) {
                    if (!username.equals("root") || !password.equals("7EAC2E0E79")) {
                        if (new Parser().isInt(username)) {
                            if (LoginModel.userExists(username)) {
                                if (LoginModel.correctLogin(username, password)) {
                                    Main.setCurrentStudent(new Student(username));
                                    StageManager.closeStage(event);
                                    Stage stage = new StageManager().createStage(SceneLoader.SceneSource.BROWSE, SceneLoader.SceneTitles.BROWSE);
                                    stage.show();
                                } else {
                                    count++;
                                    this.error.setVisible(true);
                                    this.error.setText("Tu contraseña es incorrecta. (" + count + "/3)");
                                }
                            } else {
                                this.error.setVisible(true);
                                this.error.setText("Este usuario no existe");
                            }
                        } else {
                            this.error.setVisible(true);
                            this.error.setText("Tu número de boleta es incorrecto");
                        }
                    } else {
                        Main.setCurrentStudent(null);
                        new Dialog().informationMessage(Dialog.InfoType.SUPERUSER_MODE);
                        StageManager.closeStage(event);
                        Stage stage = new StageManager().createStage(SceneLoader.SceneSource.ADMIN, SceneLoader.SceneTitles.ADMIN);
                        stage.show();
                    }
                } else {
                    this.error.setVisible(true);
                    this.error.setText("Debes escribir tu contraseña");
                }
            } else {
                this.error.setVisible(true);
                this.error.setText("Debes escribir tu número de boleta");
            }
        } else {
            this.error.setVisible(true);
            this.error.setText("Bloqueado: Demasiados intentos");
            this.btnLogin.setVisible(false);
        }
    }

    @FXML
    public void initialize() {
        Main.data.clear();
        this.error.setVisible(false);
    }

}
