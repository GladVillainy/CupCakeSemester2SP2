package entities;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private ArrayList<Cupcake> order = new ArrayList<>();


    public Order(List<Cupcake> order){
        this.order = new ArrayList<>(order);

    }

    public ArrayList<Cupcake> getOrder() {
        return order;
    }



}
