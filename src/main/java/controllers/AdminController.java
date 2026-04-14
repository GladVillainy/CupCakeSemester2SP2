package controllers;

import entities.ListOfOrders;
import entities.Order;
import entities.User;
import io.javalin.Javalin;
import io.javalin.http.Context;
import persistence.ConnectionPool;
import validators.adminValidator;

import java.util.List;

public class AdminController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool){
        app.get("/admin", ctx -> ctx.render("admin.html"));
        app.post("/addToBalance", ctx -> addToBalance(ctx, connectionPool));
        app.post("/showPayedOrders", ctx -> showPayedOrders(ctx, connectionPool));
        app.post("/showAllOrders", ctx -> showAllOrders(ctx, connectionPool));

    }

    public static void addToBalance(Context ctx, ConnectionPool connectionPool) {

        if (adminValidator.check(ctx)) {

            String stringAmount = ctx.formParam("addToBalance");
            String stringcustomerId = ctx.formParam("customerId");

            int amount = Integer.parseInt(stringAmount);
            int customerId = Integer.parseInt(stringcustomerId);

            User costumer = AdminMapper.addTobalance(amount, customerId, connectionPool);

            String confirmation = amount + " er nu blevet lagt oven i " + costumer.getEmail() + "'s balance!";
            ctx.attribute("msg", confirmation);

            ctx.render("admin.html");

        }

    }

    public static void showPayedOrders(Context ctx, ConnectionPool connectionPool) {

        if (adminValidator.check(ctx)) {

            ListOfOrders payedOrders = AdminMapper.showPayedOrders(connectionPool);

            ctx.attribute("adminList", payedOrders);

            ctx.render("admin.html");
        }
    }

    public static void showAllOrders(Context ctx, ConnectionPool connectionPool) {

        if (adminValidator.check(ctx)) {

            ListOfOrders allOrders = AdminMapper.showAllOrders(connectionPool);

            ctx.attribute("adminList", allOrders);

            ctx.render("admin.html");

        }
    }

    public static void removeOrderById(Context ctx, ConnectionPool connectionPool) {

        if (adminValidator.check(ctx)) {

            //int id = ctx.formParam("orderId");

            //AdminMapper.removeByOrderById(connectionPool);

            String confirmation = "Orderen er nu blevet slettet fra databasen!";
            ctx.attribute("msg", confirmation);

            ctx.render("admin.html");


        }
    }
}
