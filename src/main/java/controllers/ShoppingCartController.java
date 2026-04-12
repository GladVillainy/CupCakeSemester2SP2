package controllers;

import entities.User;
import io.javalin.Javalin;
import io.javalin.http.Context;
import persistence.ConnectionPool;

import java.util.List;

public class ShoppingCartController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool){
        app.post("/addToCart", ctx -> addToCart(ctx, connectionPool));
        app.get("/showFormerOrders", ctx -> showFormerOrders(ctx, connectionPool));

    }

    public static void addToCart(Context ctx, ConnectionPool connectionPool) {

        //form til at hive cupcake ud og indstancere den --- HVIS DET ER EN COSTUM
        String top =  ctx.formParam("topping");
        String bottom = ctx.formParam("bottom");
        Cupcake cupcake = new Cupcake(top, bottom);

        //Hvis ikke Custom
        Cupcake cupcake1 = ctx.formParam("cupcake");



        ShoppingCartMapper.addToCart(cupcake, connectionPool);
        // måske noget med en arraylist fra en entities(shoppingcart)
        ShoppingCart.addCupcake(cupcake);
        String confirmation = cupcake.name+" er nu blevet lagt i kurven!";
        ctx.attribute("msg", confirmation);
        ctx.render("index.html");

    }

    public static void showFormerOrders(Context ctx, ConnectionPool connectionPool) {

        User user = ctx.sessionAttribute("currentUser");

        List<Order> formerOrders = ShoppingCartMapper.showFormerOrders(user);

        ctx.attribute("orderList", formerOrders);

        //kan ændres til korrekte side
        ctx.render("orderSite.html");


    }


}
