package controllers;

import entities.Bottom;
import entities.Cupcake;
import entities.Topping;
import entities.User;
import io.javalin.Javalin;
import io.javalin.http.Context;
import persistence.BottomMapper;
import persistence.ConnectionPool;
import persistence.ToppingMapper;

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

        //form til at hive cupcake ud og indstancere den
        String stringTopId =  ctx.formParam("topping");
        String stringBottomId = ctx.formParam("bottom");

        int topId = Integer.parseInt(stringTopId);
        int bottomId = Integer.parseInt(stringBottomId);

        Topping topping = ToppingMapper.getToppingById(topId, connectionPool);
        Bottom bottom = BottomMapper.getBottomById(bottomId, connectionPool);

        Cupcake cupcake = new Cupcake(topping, bottom);

        ShoppingCartMapper.addToCart(cupcake, connectionPool);
        // måske noget med en arraylist fra en entities(shoppingcart)
        ShoppingCart.addCupcake(cupcake);
        String confirmation = "Din cupcake er nu blevet lagt i kurven!";
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

        String stringItemId = ctx.formParam("itemId");

        int itemId = Integer.parseInt(stringItemId);

        Cupcake cupcake = ShoppingCartMapper.getItemById(itemId, connectionPool);
        ShoppingCartMapper.removeItemById(itemId, connectionPool);

        String confirmation = "Din cupcake er nu fjernet fra kurven!";
        ctx.attribute("msg", confirmation);

        ctx.render("index.html");

    }


}
