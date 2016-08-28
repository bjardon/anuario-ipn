package models;

import classes.Dialog;
import classes.SQLInstance;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by root on 28/05/15.
 */
public class AdminModel {

    private static SQLInstance instance = new SQLInstance();

    public static void updateData(HashMap<String, String> data) {
        instance.connect();
        try {
            instance.ps = instance.con.prepareStatement("UPDATE students SET fname = ?, lastname = ?, stgroup = ?, area = ?, " +
                    "generation = ?, email = ?, comment = ? WHERE id = ?");
            instance.ps.setString(1, data.get("name"));
            instance.ps.setString(2, data.get("lastname"));
            instance.ps.setString(3, data.get("group"));
            instance.ps.setString(4, data.get("area"));
            instance.ps.setString(5, data.get("yearBegin") + " - " + data.get("yearEnd"));
            instance.ps.setString(6, data.get("email"));
            instance.ps.setString(7, data.get("comment"));
            instance.ps.setString(8, data.get("id"));
            instance.ps.execute();
        } catch (SQLException ex) {
            new Dialog().errorMessage(ex);
        }
        instance.close();
    }

    public static void delete(String id) {
        instance.connect();
        try {
            instance.ps = instance.con.prepareStatement("DELETE FROM students WHERE id = ?");
            instance.ps.setString(1, id);
            instance.ps.execute();
        } catch (SQLException ex) {
            new Dialog().errorMessage(ex);
        }
        instance.close();
    }

}
