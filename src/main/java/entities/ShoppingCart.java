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

    public static void removeCupcakeByBothNames(String topName, String bottomName){
        for (int i = 0; i < tempOrderList.size(); i++) {
            Cupcake cupcake = tempOrderList.get(i);
            if (cupcake.getTopping().getName().equals(topName)
                    && cupcake.getBottom().getName().equals(bottomName)) {
                totalPrice = totalPrice-tempOrderList.get(i).getPrice();
                tempOrderList.remove(i);
                return;
            }
        }
    }

    public static double getTotalPrice() {
        return totalPrice;
    }

}
