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
        app.post("/payOrder", ctx -> payOrder(ctx, connectionPool));
        app.get("/showTempOrder", ctx -> showTempOrder(ctx, connectionPool));
        app.post("/removeItem", ctx -> removeItemFromOrder(ctx, connectionPool));

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

        List<Cupcake> formerOrders = ShoppingCartMapper.showFormerOrders(user, connectionPool);

        ctx.attribute("orderList", formerOrders);

        //kan ændres til korrekte side
        ctx.render("orderSite.html");


    }

    public static void payOrder(Context ctx, ConnectionPool connectionPool) {

        List<Cupcake> tempOrder = ctx.sessionAttribute("sessionOrderList");

        ShoppingCartMapper.payOrder(tempOrder, connectionPool);

        String confirmation = "Din ordre er nu blevet betalt, så den klar til afhentning!";
        ctx.attribute("msg", confirmation);

        ctx.render("index.html");

    }

    public static void showTempOrder(Context ctx, ConnectionPool connectionPool) {

        List<Cupcake> tempOrder = ShoppingCartMapper.showTempOrder(ctx, connectionPool);

        ctx.sessionAttribute("sessionOrderList", tempOrder);

        //ingen confirmation behøvet her

        ctx.render("index.html");
    }

    public static void removeItemFromOrder(Context ctx, ConnectionPool connectionPool) {

        int itemID = ctx.formParam("itemID");

        Cupcake cupcake = ShoppingCartMapper.getItemById(itemID, connectionPool);
        ShoppingCartMapper.removeItemById(itemID, connectionPool);

        String confirmation = cupcake.name+" er nu blevet lagt i kurven!";
        ctx.attribute("msg", confirmation);

        ctx.render("index.html");

    }


}
