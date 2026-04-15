package controllers;

import entities.ShoppingCart;
import entities.User;
import io.javalin.Javalin;
import io.javalin.http.Context;
import persistence.ConnectionPool;
import persistence.PaymentMapper;

public class PaymentController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool){
        app.post("/pay", ctx -> pay(ctx, connectionPool));
    }

    public static void pay(Context ctx, ConnectionPool connectionPool) {

        double priceForCostumer = ShoppingCart.getTotalPrice();
        User costumer = ctx.sessionAttribute("currentUser");

        //PaymentMapper.pay(priceForCostumer, costumer, connectionPool);

        String confirmation = priceForCostumer+" er nu blevet trukket fra "+costumer+"'s balance!";
        ctx.attribute("msg", confirmation);
        ctx.render("index.html");
    }
}
