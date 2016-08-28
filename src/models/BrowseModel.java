package models;

import classes.Dialog;
import classes.SQLInstance;
import classes.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

/**
 * Created by root on 13/05/15.
 */
public class BrowseModel {

    private static SQLInstance instance = new SQLInstance();

    public static ObservableList<Student> getAll(String id) {
        ObservableList<Student> data = FXCollections.observableArrayList();
        instance.connect();
        try {
            instance.ps = instance.con.prepareStatement("SELECT * FROM students WHERE id != ?");
            instance.ps.setString(1, id);
            instance.rs = instance.ps.executeQuery();
            while (instance.rs.next()) {
                data.add(new Student(instance.rs.getString("id")));
            }
        } catch (SQLException ex) {
            new Dialog().errorMessage(ex);
        }
        instance.close();
        return data;
    }

    public static ObservableList<Student> getMatches(String q, String id) {
        ObservableList<Student> data = FXCollections.observableArrayList();
        instance.connect();
        try {
            instance.ps = instance.con.prepareStatement("SELECT id FROM students WHERE (id LIKE ? OR fname LIKE ? OR lastname LIKE ? OR email LIKE ?) AND id != ?");
            instance.ps.setString(1, "%" + q + "%");
            instance.ps.setString(2, "%" + q + "%");
            instance.ps.setString(3, "%" + q + "%");
            instance.ps.setString(4, "%" + q + "%");
            instance.ps.setString(5, id);
            instance.rs = instance.ps.executeQuery();
            while (instance.rs.next()) {
                data.add(new Student(instance.rs.getString("id")));
            }
        } catch (SQLException ex) {
            new Dialog().errorMessage(ex);
        }
        instance.close();
        return data;
    }

}
