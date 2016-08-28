package controllers.register;

import classes.Dialog;
import classes.Parser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import stages.SceneLoader;
import stages.StageManager;

import java.time.Year;

/**
 * Created by root on 10/05/15.
 */
public class Step2 {

    @FXML
    private Label error;

    @FXML
    private ComboBox cbxUnit;
    @FXML
    private TextField txtYearBegin;
    @FXML
    private TextField txtYearEnd;
    @FXML
    private TextField txtGroup;
    @FXML
    private TextField txtArea;

    @FXML
    public void btnBackAction(ActionEvent event) throws Exception {
        new StageManager().changeScene((Stage) ((Node) event.getSource()).getScene().getWindow(), SceneLoader.SceneSource.REGISTER_STEP_1, SceneLoader.SceneTitles.REGISTER_STEP_1);
    }

    @FXML
    public void btnContinueAction(ActionEvent event) throws Exception {
        System.out.print(Year.now().getValue());
        this.error.setText("");
        this.error.setVisible(false);
        String unit = (String) this.cbxUnit.getValue();
        String yearBegin = this.txtYearBegin.getText();
        String yearEnd = this.txtYearEnd.getText();
        String group = this.txtGroup.getText();
        String area = this.txtArea.getText();
        if (!unit.isEmpty()) {
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
                                                            Main.data.put("unit", unit);
                                                            Main.data.put("yearBegin", yearBegin);
                                                            Main.data.put("yearEnd", yearEnd);
                                                            Main.data.put("group", group);
                                                            Main.data.put("area", area);
                                                            new StageManager().changeScene((Stage) ((Node) event.getSource()).getScene().getWindow(), SceneLoader.SceneSource.REGISTER_STEP_3, SceneLoader.SceneTitles.REGISTER_STEP_3);
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
            this.error.setText("Debes seleccionar un plantel");
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
        String[] elements = {
                "CET 1",
                "CECyT 1",
                "CECyT 2",
                "CECyT 3",
                "CECyT 4",
                "CECyT 5",
                "CECyT 6",
                "CECyT 7",
                "CECyT 8",
                "CECyT 9",
                "CECyT 10",
                "CECyT 11",
                "CECyT 12",
                "CECyT 13",
                "CECyT 14",
                "CECyT 15"
        };
        this.cbxUnit.getItems().addAll(elements);
        if (Main.data.size() != 3) {
            this.cbxUnit.setValue(Main.data.get("unit"));
            this.txtYearBegin.setText(Main.data.get("yearBegin"));
            this.txtYearEnd.setText(Main.data.get("yearEnd"));
            this.txtGroup.setText(Main.data.get("group"));
        }
    }

}
