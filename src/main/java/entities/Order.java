package entities;

import java.util.ArrayList;

public class Order {
    private ArrayList<Cupcake> order = new ArrayList<>();

    public Order(ArrayList<Cupcake> order){

        this.order = order;
    }

    public ArrayList<Cupcake> getOrder() {
        return order;
    }
}
