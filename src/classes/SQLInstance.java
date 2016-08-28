package classes;

import java.sql.*;

/**
 * Created by root on 8/05/15.
 */
public class SQLInstance {

    // SQL Connection properties definition
    protected final String url = "jdbc:sqlite:database.db";

    // SQL Structure
    public Connection con = null;
    public Statement st = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;

    /**
     * Connects to the database
     */
    public void connect() {
        try {
            con = DriverManager.getConnection(url);
            String sql = "CREATE TABLE IF NOT EXISTS `students` (" +
                    "  `id` INTEGER PRIMARY KEY," +
                    "  `password` VARCHAR(255)," +
                    "  `fname` VARCHAR(255)," +
                    "  `lastname` VARCHAR(255)," +
                    "  `stgroup` VARCHAR(255)," +
                    "  `area` VARCHAR(255)," +
                    "  `generation` VARCHAR(255)," +
                    "  `email` VARCHAR(255)," +
                    "  `comment` TEXT" +
                    ")";
            st = con.createStatement();
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            new Dialog().errorMessage(ex);
        }
    }

    /**
     * Closes the connection to the database
     */
    public void close() {
        try {
            con.close();
            con = null;
            ps = null;
            rs = null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            new Dialog().errorMessage(ex);
        }
    }

}
