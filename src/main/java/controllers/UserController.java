package controllers;

import io.javalin.Javalin;
import io.javalin.http.Context;
import exceptions.DatabaseException;
import persistence.ConnectionPool;
import persistence.UserMapper;
import entities.User;

public class UserController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool){
        app.get("/createUser", ctx -> ctx.render("createuser.html"));
        app.post("/createUser", ctx -> createUser(ctx, connectionPool));
        app.get("/login", ctx -> ctx.render("login.html"));
        app.post("/login", ctx -> loginUser(ctx, connectionPool));
        app.get("/logout", ctx -> logoutUser(ctx));
    }

    public static void createUser(Context ctx, ConnectionPool connectionPool){
        //hent data user/pass
        String email = ctx.formParam("email");
        String password1 = ctx.formParam("password1");
        String password2 = ctx.formParam("password2");

        String password = "";
        if(password1.equals(password2)){
            password1 = password;
            //opret user i DB
            try {
                UserMapper.createUser(email, password, connectionPool);
                //alert bruger om at user er blevet lavet
                String createConfirm = email+" er nu blevet oprettet som bruger!";
                ctx.attribute("msg", createConfirm);
                //tilbage til forside
                ctx.render("index.html");
            } catch (DatabaseException e) {
                ctx.attribute("msg", e.getMessage());
                ctx.render("createuser.html");
            }
        } else {
           ctx.attribute("msg", "Your passwords do not match. Please try again");
           ctx.render("createUser.html");
        }
    }

    public static void loginUser(Context ctx, ConnectionPool connectionPool){
        // henter data fra form
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");

        //logger bruger ind
        try {
            User user = UserMapper.login(email, password, connectionPool);
            // sæt session til den brugte user
            ctx.sessionAttribute("currentUser", user);

            //sætter currentUserActive til true
            ctx.sessionAttribute("currentUserActive", "true");

            //send bruger til index
            ctx.render("index.html");
        } catch (DatabaseException e) {
            //hvis fejl skriv på siden
            ctx.attribute("msg", e.getMessage());
            //send bruger tilbage til login siden
            ctx.render("login.html");
        }

    }

    public static void logoutUser(Context ctx){
        // stop session
        ctx.sessionAttribute("currentUser", null);

        //sætter currentUserActive til false
        ctx.sessionAttribute("currentUserActive", "false");

        // alert bruger om at der er blevet logget ud
        String logoutConfirm = "Du er nu logget ud";
        ctx.attribute("msg", logoutConfirm);
        ctx.render("index.html");
    }

}