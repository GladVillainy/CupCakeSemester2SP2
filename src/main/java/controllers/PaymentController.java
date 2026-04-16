package controllers;

import entities.Cupcake;
import entities.Order;
import entities.ShoppingCart;
import entities.User;
import io.javalin.Javalin;
import io.javalin.http.Context;
import persistence.ConnectionPool;
import persistence.OrderMapper;
import persistence.PaymentMapper;

public class PaymentController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool){
        app.post("/pay", ctx -> pay(ctx, connectionPool));
    }

    public static void pay(Context ctx, ConnectionPool connectionPool) {

        double priceForCostumer = ShoppingCart.getTotalPrice();
        User costumer = ctx.sessionAttribute("currentUser");
        int user_id = costumer.getId();

        //tjekker om der er nok penge på user's balance.
        if(costumer.getBalance() > priceForCostumer) {

            //tager pengene fra costumer balance
            PaymentMapper.pay(priceForCostumer, costumer, connectionPool);

            //lav tempList fra shoppingCart til Ordre
            Order newOrder = new Order(ShoppingCart.getTempOrderList());

            //LAV EN ORDRE for costumer I DATABASEN og retunere orderid
            int orderId = OrderMapper.makeOrder(user_id, connectionPool);

            for (Cupcake cupcake : newOrder.getOrder()) {
                //henter id for top/bund
                int cupcake_topping_id = cupcake.getTopping().getId();
                int cupcake_bottom_id = cupcake.getBottom().getId();

                //LAV for hver cupcake i newOrder, en order_item til den nye order med orderId'et
                OrderMapper.addItemToOrder(orderId, cupcake_topping_id, cupcake_bottom_id, connectionPool);

            }

            //slette alt fra shoppingCart (temperary list) for reset
            ShoppingCart.resetAllOrders();
            //updatere ordrelisten i ShoppingcartControlelr
            ShoppingCartController.showTempOrder(ctx);

        }

        String confirmation = priceForCostumer+" er nu blevet trukket fra "+costumer.getEmail()+"'s balance!";
        ctx.attribute("msg", confirmation);


        ctx.render("index.html");
    }
}
