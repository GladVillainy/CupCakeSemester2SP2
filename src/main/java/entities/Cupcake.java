package entities;

public class Cupcake {

    private Topping topping;
    private Bottom bottom;
    private int id;
    private double price;

    public Cupcake(Topping topping, Bottom bottom) {
        this.topping = topping;
        this.bottom = bottom;
        this.price = topping.getPrice()+bottom.getPrice();

    }

    public Topping getTopping() {
        return topping;
    }

    public Bottom getBottom() {
        return bottom;
    }

    public double getPrice(){
        return price;
    }
}