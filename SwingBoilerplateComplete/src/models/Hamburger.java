package models;

import java.util.ArrayList;

//A class that defines a hamburger and it's toppings
//Provides methods for manipulating toppings, calculating the price, and getting the description
public class Hamburger extends Food {
    //Static final variables make it easy to control data that may change once in a while in many places in the program
    public static final String[] AVAILABLE_TOPPINGS = {"Pickles", "Onions", "Lettuce", "Tomato", "Cheese", "Ketchup", "Mustard", "Mayonnaise"};
    private static final double BASE_COST = 2.99;
    private static final double CHEESE_COST = 0.99;
    private static final double TOPPING_COST = 0.05;
    
    private final ArrayList<String> toppings;

    public Hamburger() {
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

    @Override
    public double getPrice(){
        double cost = BASE_COST;
        for(String topping: toppings){
            if(topping.equals("Cheese")){
                cost+=CHEESE_COST;
            }else{
                cost+=TOPPING_COST;
            }           
        }
        return cost;
    }
    
    @Override
    public String toString() {
        String output = "A hamburger ";
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
        output += " on a sesame seed bun";
        return output;
    }
}
