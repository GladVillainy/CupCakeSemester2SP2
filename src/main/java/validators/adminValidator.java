package validators;

import entities.User;
import io.javalin.http.Context;

public class adminValidator {
    public static boolean check(Context ctx){
        User user = ctx.sessionAttribute("currentUser");
        if (user.getRole().equals("admin")) {
            return true;
        } else {
            return false;
        }
    }
}
