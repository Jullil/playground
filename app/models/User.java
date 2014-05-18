package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="user")
public class User extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    @Constraints.Required
    @Formats.NonEmpty
    public String email;

    @Constraints.Required
    public String name;

    @Constraints.Required
    public String password;

    public static Model.Finder<String,User> find = new Model.Finder<String,User>(String.class, User.class);

    public static List<User> all() {
        return find.all();
    }

    public static User getByEmail(String email) {
        return find.where().eq("email", email).findUnique();
    }

    public static User auth(String email, String password) {

        return find.where()
                   .eq("email", email)
                   .eq("password", password)
                   .findUnique();
    }

    public String toString() {
        return name + " (" + email + ")";
    }

}

