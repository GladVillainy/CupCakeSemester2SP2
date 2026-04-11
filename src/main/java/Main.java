
import config.ThymeleafConfig;
import persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;


public class Main {

    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/%s?currentSchema=public";
    private static final String DB = "cupcake";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance(USER, PASSWORD, URL, DB);

    public static void main(String[] args) {
        // Initializing Javalin and Jetty webserver
        Javalin javApp = Javalin.create(config -> {
            config.staticFiles.add("/public");
            config.fileRenderer(new JavalinThymeleaf(ThymeleafConfig.templateEngine()));
            config.staticFiles.add("/templates");
        }).start(7070);


        // Routing
        /*
        UserController.addRoutes(javApp, connectionPool);
        */


        // Start
        javApp.get("/", ctx -> ctx.render("index.html"));
        javApp.get("/Custom", ctx -> ctx.render("custom.html"));

        /*
        //SubStats app - Team - A
        app.controllers.teamA.UserController.addRoutes(javApp, connectionPool);
        app.controllers.teamA.SubscriptionController.addRoutes(javApp, connectionPool);
         */

    }
}