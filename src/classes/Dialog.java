package classes;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.stage.StageStyle;
import stages.SceneLoader;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Created by root on 8/05/15.
 */
public class Dialog {

    /**
     * Predefined error types
     */
    public static class ErrorType {
        public static final Integer UPLOAD_UNSUCCESSFUL = 1;
    }

    /**
     * Predefined confirmation types
     */
    public static class ConfirmType {

        public static final Integer EXIT_APPLICATION = 1;
        public static final Integer EMPTY_FIELDS = 2;
        public static final Integer CANCEL_REGISTER = 3;
        public static final Integer DELETE_USER = 4;

    }

    /**
     * Predefined success types
     */
    public static class SuccessType {
        public static final Integer UPLOAD_SUCCESSFUL = 1;
    }

    /**
     * Predefined info types
     */
    public static class InfoType {

        public static final Integer SUPERUSER_MODE = 1;

    }

    // Alert for all Dialogs
    private static Alert alert = null;

    /**
     * Shows a dialog with hardcoded strings
     *
     * @param shortTitle the Stage's title bar
     * @param title      the header
     * @param message    the message
     * @param level      the type of error (0: no error, 1: low, 2: mid, 3: hi)
     */
    public void errorMessage(String shortTitle, String title, String message, Integer level) {

        switch (level) {
            case 0:
                alert = new Alert(Alert.AlertType.NONE);
                break;
            case 1:
                alert = new Alert(Alert.AlertType.INFORMATION);
                break;
            case 2:
                alert = new Alert(Alert.AlertType.WARNING);
                break;
            case 3:
                alert = new Alert(Alert.AlertType.ERROR);
                break;
            default:
                break;
        }

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().addAll(SceneLoader.Stylesheets.GOOGLE_FONTS, SceneLoader.Stylesheets.BUTTERHEAD);

        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(shortTitle);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();

    }

    /**
     * Shows a dialog for a given error type
     *
     * @param errorType the reference to Dialogs.ErrorType.<"whatever">
     */
    public void errorMessage(Integer errorType) {

        String shortTitle = "";
        String title = "";
        String message = "";

        switch (errorType) {
            case 1:
                shortTitle = "Error al subir";
                title = "Error al subir el archivo al servidor";
                message = "Se presentó un error al subir el archivo al servidor. Quizás su conexión no está establecida. El registro continuará de todos modos";
            default:
                break;
        }

        this.errorMessage(shortTitle, title, message, 2);

    }

    /**
     * Shows a Dialog for an SQLException
     *
     * @param ex the SQLException
     */
    public void errorMessage(SQLException ex) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR SQL: " + ex.getSQLState() + " - " + ex.getErrorCode());
        alert.setHeaderText(ex.getMessage());
        alert.setContentText("Si está viendo este error, por favor contacte al proveedor del servicio. " + ex.toString());
        alert.showAndWait();
    }

    /**
     * Shows a confirmation Dialog and returns the user's choice for hardcoded strings
     *
     * @param shortTitle the Stage's title bar
     * @param title      the header
     * @param message    the message
     * @return the user's choice (false: no, true: yes)
     */
    public Boolean confirmationMessage(String shortTitle, String title, String message) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(shortTitle);
        alert.setHeaderText(title);
        alert.setContentText(message);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().addAll(SceneLoader.Stylesheets.GOOGLE_FONTS, SceneLoader.Stylesheets.BUTTERHEAD);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Shows a dialog for a given ConfirmationType
     *
     * @param confirmationType the reference to Dialogs.ConfirmType.<"whatever">
     * @return the user's choice
     */
    public Boolean confirmationMessage(Integer confirmationType) {
        String shortTitle = "";
        String title = "";
        String message = "";
        switch (confirmationType) {
            case 1:
                shortTitle = "¿Cerrar la aplicación?";
                title = "¿Está seguro que desea cerrar la aplicación?";
                message = "Si selecciona Aceptar, la aplicación se cerrará por completo.";
                break;
            case 2:
                shortTitle = "¿Dejar vacíos?";
                title = "¿Estás seguro de que quieres dejar vacíos estos campos?";
                message = "No es obligatorio poner un comentario y una foto, pero te recomendamos que lo hagas para mejorar la experiencia de usuario.";
                break;
            case 3:
                shortTitle = "¿Cancelar registro?";
                title = "¿Estás seguro de que deseas cancelar tu registro?";
                message = "No se hará ningún registro y tus datos serán limpiados.";
                break;
            case 4:
                shortTitle = "¿Borrar usuario?";
                title = "¿Estás seguro de que desea borrarlo?";
                message = "Esta acción no se puede deshacer";
                break;
            default:
                break;
        }
        return this.confirmationMessage(shortTitle, title, message);
    }

    /**
     * Shows a success message for hardcoded strings
     *
     * @param shortTitle the Stage's title bar
     * @param title      the header
     * @param message    the message
     */
    public void successMessage(String shortTitle, String title, String message) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(shortTitle);
        alert.setHeaderText(title);
        alert.setContentText(message);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().addAll(SceneLoader.Stylesheets.GOOGLE_FONTS, SceneLoader.Stylesheets.BUTTERHEAD);
        alert.showAndWait();
    }

    /**
     * Shows a success message for a given success type
     *
     * @param successType the reference to Dialogs.SuccessType.<"whatever">
     */
    public void successMessage(Integer successType) {
        String shortTitle = "";
        String title = "";
        String message = "";
        switch (successType) {
            case 1:
                shortTitle = "¡Imagen subida!";
                title = "Tu imagen fue subida correctamente";
                message = "Haz clic en aceptar para continuar";
            default:
                break;
        }
        this.successMessage(shortTitle, title, message);
    }

    /**
     * Shows an information dialog for a given title, header and message
     *
     * @param shortTitle the Stage's title bar
     * @param title      the header
     * @param message    the message
     */
    public void informationMessage(String shortTitle, String title, String message) {
        this.successMessage(shortTitle, title, message);
    }

    /**
     * Shows an information dialog for a given InfoType
     *
     * @param infoType the reference to Dialogs.SuccessType.<"whatever">
     */
    public void informationMessage(Integer infoType) {
        String shortTitle = "";
        String title = "";
        String message = "";
        switch (infoType) {
            case 1:
                shortTitle = "¡Modo superusuario!";
                title = "¡Está ingresando como superusuario!";
                message = "Ésta sesión es en modo Superusuario: tenga cuidado con lo que hace.";
                break;
            default:
                break;
        }
        this.informationMessage(shortTitle, title, message);
    }

}

