package controllers;

import play.*;
import play.mvc.*;
import play.data.Form;
import static play.data.Form.*;

import models.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }

    public static Result pages() {
        return ok(page_list.render(Page.list()));
    }

    public static Result page(Long id) {
        return ok(page_item.render(Page.getById(id)));
    }


    public static Result login() {
        return ok(login.render(form(Login.class)));
    }

    public static Result authenticate() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        if (!loginForm.hasErrors()) {
            session("email", loginForm.get().email);
            return redirect(routes.Administration.index());
        } else {
            return badRequest(login.render(loginForm));
        }
    }

    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(routes.Application.login());
    }

    public static class Login {
        public String email;
        public String password;

        public String validate() {
            if(User.auth(email, password) == null) {
                return "Invalid user or password";
            }
            return null;
        }
    }
}
