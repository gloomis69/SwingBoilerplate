package models;

import java.util.ArrayList;

public class Hotdog {
    public static final String[] AVAILABLE_TOPPINGS = {"Onions", "Ketchup", "Mustard", "Relish", "Chili", "Cheese"};
    private static final double BASE_COST = 1.99;
    private static final double CHEESE_COST = 0.50;
    private static final double CHILI_COST = 0.99;
    private static final double TOPPING_COST = 0.05;
    private final ArrayList<String> toppings;
    public Hotdog(){
        toppings = new ArrayList<>();
    }

    public ArrayList<String> getToppings() {
        return toppings;
    }

    public void addTopping(String topping) {
        if (isAvailable(topping) && !this.toppings.contains(topping)) {
            this.toppings.add(topping);
        }
    }
    
    public void removeTopping(String topping){
        if(toppings.contains(topping)) toppings.remove(topping);
    }

    public void setToppings(ArrayList<String> toppings) {
        for (String topping : toppings) {
            if (isAvailable(topping) && !this.toppings.contains(topping)) {
                this.toppings.add(topping);
            }
        }
    }

    private boolean isAvailable(String topping) {
        for (String t : AVAILABLE_TOPPINGS) {
            if (t.equals(topping)) {
                return true;
            }
        }
        return false;
    }

    public double getPrice(){
        double cost = BASE_COST;
        for(String topping: toppings){
            switch (topping) {
                case "Cheese" -> cost+=CHEESE_COST;
                case "Chili" -> cost+=CHILI_COST;
                default -> cost+=TOPPING_COST;
            }
        }
        return cost;
    }
    
    public double getTax(double price){
        return price * 0.08;
    }
    
    public double getPriceWithTax(){
        double price = getPrice();
        return (price+getTax(price));
    }
    
    @Override
    public String toString() {
        String output = "A hotdog ";
        if (!toppings.isEmpty()) {
            output += "with ";
        }
        for (int i = 0; i < toppings.size() - 1; i++) {
            output += toppings.get(i).toLowerCase() + ", ";
        }
        if (toppings.size() > 1) {
            output += "and ";
        }
        if (!toppings.isEmpty()) {
            output += toppings.get(toppings.size() - 1).toLowerCase();
        }
        output = output.substring(0, output.length());
        output += " on a bun";
        return output;
    }
}
