package models;

import classes.Dialog;
import classes.SQLInstance;

import java.sql.SQLException;

/**
 * Created by root on 8/05/15.
 */
public class LoginModel {

    private static SQLInstance instance = new SQLInstance();

    public static Boolean userExists(String username) {
        Boolean result = true;
        instance.connect();
        try {
            instance.ps = instance.con.prepareStatement("SELECT COUNT(*) FROM students WHERE id = ?");
            instance.ps.setInt(1, Integer.parseInt(username));
            instance.rs = instance.ps.executeQuery();
            Integer count = instance.rs.getInt(1);
            if (count == 0) {
                result = false;
            }
        } catch (SQLException ex) {
            new Dialog().errorMessage(ex);
        }
        instance.close();
        return result;
    }

    public static Boolean correctLogin(String username, String password) {
        Boolean result = true;
        instance.connect();
        try {
            instance.ps = instance.con.prepareStatement("SELECT COUNT(*) FROM students WHERE id = ? AND password = ?");
            instance.ps.setInt(1, Integer.parseInt(username));
            instance.ps.setString(2, password);
            instance.rs = instance.ps.executeQuery();
            Integer count = instance.rs.getInt(1);
            if (count == 0) {
                result = false;
            }
        } catch (SQLException ex) {
            new Dialog().errorMessage(ex);
        }
        instance.close();
        return result;
    }

}
