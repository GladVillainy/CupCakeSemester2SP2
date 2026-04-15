package entities;

import controllers.ShoppingCartController;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private static List<Cupcake> tempOrderList = new ArrayList<>();

    public static void addCupcake(Cupcake cupcake){
        tempOrderList.add(cupcake);
    }

    public static List<Cupcake> getTempOrderList(){
        return tempOrderList;
    }

    public static void removeCupcakeById(Integer cupcakeId){
        Cupcake cupcake = tempOrderList.get(cupcakeId);

        tempOrderList.remove(cupcake);
    }

}
