package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.List;

@Entity
public class Page extends Model {
    private static final long serialVersionUID = 1L;

    @Id
    public Long id;

    @Constraints.Required
    public String title;

    @Column(length=4096)
    public String description;

    public static Model.Finder<Long, Page> finder = new Model.Finder<Long, Page>(Long.class, Page.class);

    public static List<Page> list() {
        return finder.orderBy("id").findList();
    }

    public static Page getById(Long id) {
        return finder.byId(id);
    }

    public static void add(Page page) {
        page.save();
    }

    public static void edit(Long id, Page page) {
        page.update(id);
    }

    public static void delete(Long id) {
        finder.ref(id).delete();
    }
}
