package controllers;

import models.Page;
import play.data.Form;
import static play.data.Form.*;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import views.html.administration.*;

@Security.Authenticated(Privated.class)
public class Administration extends Controller {

    public static Result index() {
        return ok(index.render());
    }

    public static Result pageList() {
        return ok(page_list.render(Page.list()));
    }

    public static Result pageAdd() {
        Form<Page> pageForm = form(Page.class);

        return ok(page_add.render(pageForm));
    }

    public static Result pageSave() {
        Form<Page> pageForm = form(Page.class).bindFromRequest();
        if(pageForm.hasErrors()) {
            return badRequest(page_add.render(pageForm));
        }
        Page.add(pageForm.get());

        flash("success", "Page " + pageForm.get().title + " has been created");

        return redirect(routes.Administration.pageList());
    }

    public static Result pageEdit(Long id) {
        Form<Page> pageForm = form(Page.class).fill(Page.getById(id));

        return ok(page_edit.render(id, pageForm));
    }

    public static Result pageUpdate(Long id) {
        Form<Page> pageForm = form(Page.class).bindFromRequest();
        if(pageForm.hasErrors()) {
            return badRequest(page_edit.render(id, pageForm));
        }
        Page.edit(id, pageForm.get());

        flash("success", "Page " + pageForm.get().title + " has been updated");

        return redirect(routes.Administration.pageList());
    }

    public static Result pageDelete(Long id) {
        Page.delete(id);

        flash("success", "Page has been deleted");

        return redirect(routes.Administration.pageList());
    }
}
