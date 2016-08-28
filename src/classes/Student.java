package classes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.SQLException;

/**
 * Created by root on 17/05/15.
 */
public class Student extends User {

    private StringProperty id = new SimpleStringProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty lastname = new SimpleStringProperty();
    private StringProperty group = new SimpleStringProperty();
    private StringProperty area = new SimpleStringProperty();
    private StringProperty generation = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty comment = new SimpleStringProperty();

    public Student(String id) {
        SQLInstance instance = new SQLInstance();
        try {
            instance.connect();
            instance.ps = instance.con.prepareStatement("SELECT * FROM students WHERE id = ?");
            instance.ps.setString(1, id);
            instance.rs = instance.ps.executeQuery();
            this.id.set(instance.rs.getString("id"));
            this.name.set(instance.rs.getString("fname"));
            this.lastname.set(instance.rs.getString("lastname"));
            this.group.set(instance.rs.getString("stgroup"));
            this.area.set(instance.rs.getString("area"));
            this.generation.set(instance.rs.getString("generation"));
            this.email.set(instance.rs.getString("email"));
            this.comment.set(instance.rs.getString("comment"));
            instance.close();
        } catch (SQLException ex) {
            new Dialog().errorMessage(ex);
        }
    }

    public String getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getLastname() {
        return lastname.get();
    }

    public String getGroup() {
        return group.get();
    }

    public String getArea() {
        return area.get();
    }

    public String getGeneration() {
        return generation.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getComment() {
        return comment.get();
    }

    public StringProperty getIdProperty() {
        return this.id;
    }

    public StringProperty getNameProperty() {
        return this.name;
    }

    public StringProperty getLastnameProperty() {
        return this.lastname;
    }

}
