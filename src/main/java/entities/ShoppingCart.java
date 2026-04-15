package entities;

import controllers.ShoppingCartController;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private static List<Cupcake> tempOrderList = new ArrayList<>();
    private static double totalPrice = 0;


    public static void addCupcake(Cupcake cupcake){
        tempOrderList.add(cupcake);
        totalPrice = 0;
        for (Cupcake cupcake1: tempOrderList) {
            totalPrice += cupcake1.getPrice();
        }

    }

    public static List<Cupcake> getTempOrderList(){
        return tempOrderList;
    }

    public static void removeCupcakeById(Integer cupcakeId){
        Cupcake cupcake = tempOrderList.get(cupcakeId);

        tempOrderList.remove(cupcake);
    }

    public static double getTotalPrice() {
        return totalPrice;
    }

}
