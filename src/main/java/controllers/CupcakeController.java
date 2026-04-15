package controllers;

import entities.Bottom;
import entities.Topping;
import io.javalin.Javalin;
import io.javalin.http.Context;
import persistence.BottomMapper;
import persistence.ConnectionPool;
import persistence.ToppingMapper;

import java.util.List;

public class CupcakeController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool){
        app.get("/custom", ctx -> listCupcakes(ctx, connectionPool));
    }

    public static void listCupcakes(Context ctx, ConnectionPool connectionPool){
        List<Bottom> listBottom = BottomMapper.getAllBottoms(connectionPool);
        List<Topping> listTopping = ToppingMapper.getAllToppings(connectionPool);

        ctx.attribute("bottoms", listBottom);
        ctx.attribute("toppings", listTopping);

        ctx.render("custom.html");


    }
}
