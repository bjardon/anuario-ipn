package models;

import classes.Dialog;
import classes.SQLInstance;
import classes.Uploader;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by root on 10/05/15.
 */
public class RegisterModel {

    private static SQLInstance instance = new SQLInstance();

    public static Boolean studentExists(String stNumber) {
        Integer count;
        Boolean result = true;
        instance.connect();
        try {
            instance.ps = instance.con.prepareStatement("SELECT COUNT(*) FROM students WHERE id = ?");
            instance.ps.setInt(1, Integer.parseInt(stNumber));
            instance.rs = instance.ps.executeQuery();
            count = instance.rs.getInt(1);
            if (count == 0) {
                result = false;
            }
        } catch (SQLException ex) {
            new Dialog().errorMessage(ex);
        }
        instance.close();
        return result;
    }

    public static Boolean emailIsRegistered(String email) {
        Integer count;
        Boolean result = true;
        instance.connect();
        try {
            instance.ps = instance.con.prepareStatement("SELECT COUNT(*) FROM students WHERE email LIKE ?");
            instance.ps.setString(1, email);
            instance.rs = instance.ps.executeQuery();
            count = instance.rs.getInt(1);
            if (count == 0) {
                result = false;
            }
        } catch (SQLException ex) {
            new Dialog().errorMessage(ex);
        }
        instance.close();
        return result;
    }

    public static void insertStudent(HashMap<String, String> data) throws Exception {
        instance.connect();
        try {
            instance.ps = instance.con.prepareStatement("INSERT INTO students (id, password, fname, lastname, stgroup, area, generation, email, comment)" +
                    "VALUES (?,?,?,?,?,?,?,?,?)");
            instance.ps.setString(1, data.get("id"));
            instance.ps.setString(2, data.get("password"));
            instance.ps.setString(3, data.get("name"));
            instance.ps.setString(4, data.get("lastname"));
            instance.ps.setString(5, data.get("group"));
            instance.ps.setString(6, data.get("area"));
            instance.ps.setString(7, data.get("yearBegin") + " - " + data.get("yearEnd"));
            instance.ps.setString(8, data.get("email"));
            instance.ps.setString(9, data.get("comment"));
            instance.ps.execute();
            if (!data.get("imagePath").isEmpty()) {
                Uploader.uploadFile(data.get("imagePath"), data.get("id"));
            }
        } catch (SQLException ex) {
            new Dialog().errorMessage(ex);
        }
        instance.close();
    }

}
