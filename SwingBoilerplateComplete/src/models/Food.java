package models;

public abstract class Food {
    public abstract double getPrice();
    
    public double getTax(double price){
        return price * 0.08;
    }
    
    public double getPriceWithTax(){
        double price = getPrice();
        return (price+getTax(price));
    }
}
