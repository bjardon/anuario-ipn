package controllers;

import classes.Dialog;
import classes.Parser;
import classes.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.Main;
import models.AdminModel;
import models.BrowseModel;
import stages.SceneLoader;
import stages.StageManager;

import java.net.URL;
import java.time.Year;

/**
 * Created by root on 9/05/15.
 */
public class AdminController {

    @FXML
    private Label error;

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
    private TextField txtName;
    @FXML
    private TextField txtLastname;
    @FXML
    private TextField txtGroup;
    @FXML
    private TextField txtArea;
    @FXML
    private TextField txtYearBegin;
    @FXML
    private TextField txtYearEnd;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextArea txtComment;
    @FXML
    private ImageView imgUser;

    String id = "";

    private void showDetails(Student student) {
        if (student != null) {
            this.id = student.getId();
            this.vbxResultContainer.setVisible(true);
            this.txtName.setText(student.getName());
            this.txtLastname.setText(student.getLastname());
            this.txtGroup.setText(student.getGroup());
            this.txtArea.setText(student.getArea());
            this.txtYearBegin.setText(student.getGeneration().substring(0, 4));
            this.txtYearEnd.setText(student.getGeneration().substring(7, 11));
            this.txtEmail.setText(student.getEmail());
            this.txtComment.setText(student.getComment());
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
            this.tbvResult.setItems(BrowseModel.getMatches(query, ""));
        }
    }

    @FXML
    public void btnResetSearch(ActionEvent event) {
        this.tbvResult.setItems(BrowseModel.getAll(""));
        this.txtQuery.setText("");
    }

    @FXML
    public void btnSaveAction(ActionEvent event) {
        Main.data.clear();

        String name = this.txtName.getText();
        String lastname = this.txtLastname.getText();
        String group = this.txtGroup.getText();
        String area = this.txtArea.getText();
        String yearBegin = this.txtYearBegin.getText();
        String yearEnd = this.txtYearEnd.getText();
        String email = this.txtEmail.getText();
        String comment = this.txtComment.getText();

        if (!name.isEmpty()) {
            if (!lastname.isEmpty()) {
                if (!group.isEmpty()) {
                    if (group.length() >= 4) {
                        if (!area.isEmpty()) {
                            if (!yearBegin.isEmpty()) {
                                if (!yearEnd.isEmpty()) {
                                    if (yearBegin.length() == 4) {
                                        if (yearEnd.length() == 4) {
                                            if (new Parser().isInt(yearBegin)) {
                                                if (new Parser().isInt(yearEnd)) {
                                                    if (Integer.parseInt(yearBegin) < Integer.parseInt(yearEnd)) {
                                                        if (!(Integer.parseInt(yearBegin) > Year.now().getValue())) {
                                                            if (!(Integer.parseInt(yearEnd) > Year.now().getValue())) {
                                                                if (!group.isEmpty()) {
                                                                    if (group.length() >= 4) {
                                                                        if (!area.isEmpty()) {
                                                                            Main.data.put("id", this.id);
                                                                            Main.data.put("name", name);
                                                                            Main.data.put("lastname", lastname);
                                                                            Main.data.put("group", group);
                                                                            Main.data.put("area", area);
                                                                            Main.data.put("yearBegin", yearBegin);
                                                                            Main.data.put("yearEnd", yearEnd);
                                                                            Main.data.put("email", email);
                                                                            Main.data.put("comment", comment);

                                                                            AdminModel.updateData(Main.data);
                                                                            this.initialize();
                                                                        } else {
                                                                            this.error.setVisible(true);
                                                                            this.error.setText("Indica tu carrera, por favor");
                                                                        }
                                                                    } else {
                                                                        this.error.setVisible(true);
                                                                        this.error.setText("El grupo debe tener por lo menos 4 char[]");
                                                                    }
                                                                } else {
                                                                    this.error.setVisible(true);
                                                                    this.error.setText("Debes especificar un grupo");
                                                                }
                                                            } else {
                                                                this.error.setVisible(true);
                                                                this.error.setText("¿Eres del futuro? Ese año no es válido");
                                                            }
                                                        } else {
                                                            this.error.setVisible(true);
                                                            this.error.setText("¿Eres del futuro? Ese año no es válido");
                                                        }
                                                    } else {
                                                        this.error.setVisible(true);
                                                        this.error.setText("El año de término no puede ser mayor que el de inicio");
                                                    }
                                                } else {
                                                    this.error.setVisible(true);
                                                    this.error.setText("El año de término de tu generación es no es válido");
                                                }
                                            } else {
                                                this.error.setVisible(true);
                                                this.error.setText("El año de inicio de tu generación no es válido");
                                            }
                                        } else {
                                            this.error.setVisible(true);
                                            this.error.setText("El año de término de tu generación no es válido");
                                        }
                                    } else {
                                        this.error.setVisible(true);
                                        this.error.setText("El año de inicio de tu generación es inválido");
                                    }
                                } else {
                                    this.error.setVisible(true);
                                    this.error.setText("El año de término de tu generación está vacío");
                                }
                            } else {
                                this.error.setVisible(true);
                                this.error.setText("El año de inicio de tu generación está vacío");
                            }
                        } else {
                            this.error.setVisible(true);
                            this.error.setText("Tu área o carrera está vacía");
                        }
                    } else {
                        this.error.setVisible(true);
                        this.error.setText("Tu grupo debe tener por lo menos 4 char[]");
                    }
                } else {
                    this.error.setVisible(true);
                    this.error.setText("Tu grupo está vacío");
                }
            } else {
                this.error.setVisible(true);
                this.error.setText("Tus apellidos están vacíos");
            }
        } else {
            this.error.setVisible(true);
            this.error.setText("Tu nombre está vacío");
        }

    }

    @FXML
    public void btnDeleteAction(ActionEvent event) {
        if (new Dialog().confirmationMessage(Dialog.ConfirmType.DELETE_USER)) {
            AdminModel.delete(id);
            this.initialize();
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
        this.tbvResult.setItems(BrowseModel.getAll(""));
        this.tbvResult.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDetails(newValue));
        this.vbxResultContainer.setVisible(false);
        this.error.setVisible(false);
    }

}
