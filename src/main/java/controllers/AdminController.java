package controllers;

import entities.User;
import io.javalin.Javalin;
import io.javalin.http.Context;
import persistence.ConnectionPool;
import validators.adminValidator;

public class AdminController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool){

    }

    public static void addToBalance(Context ctx, ConnectionPool connectionPool) {

        if (adminValidator.check(ctx)) {

            int amount = ctx.formParam("addToBalance");
            User costumer = ctx.formParam("customer");

            AdminMapper.addTobalance(amount, costumer, connectionPool);

            String confirmation = amount + " er nu blevet lagt oven i " + costumer + "'s balance!";
            ctx.attribute("msg", confirmation);

            ctx.render("admin.html");

        }

    }

    public static void showPayedOrders(Context ctx, ConnectionPool connectionPool) {

        if (adminValidator.check(ctx)) {

            List<Order> payedOrders = AdminMapper.showPayedOrders(connectionPool);

            ctx.attribute("adminList", payedOrders);

            ctx.render("admin.html");
        }
    }

    public static void showAllOrders(Context ctx, ConnectionPool connectionPool) {

        if (adminValidator.check(ctx)) {

            List<Order> allOrders = AdminMapper.showAllOrders(connectionPool);

            ctx.attribute("adminList", allOrders);

            ctx.render("admin.html");

        }
    }

    public static void removeOrderById(Context ctx, ConnectionPool connectionPool) {

        if (adminValidator.check(ctx)) {

            int id = ctx.formParam("orderId");

            AdminMapper.removeByOrderById(connectionPool);

            String confirmation = "Orderen er nu blevet slettet fra databasen!";
            ctx.attribute("msg", confirmation);

            ctx.render("admin.html");


        }
    }
}
