package models;

public class Order {
    private static int orderCount = 0;
    private final int orderNumber;
    private final String name;
    private final Food food;

    public Order(String name, Food food){
        orderCount++;
        orderNumber = orderCount;
        this.name = name;
        this.food = food;
    }

    @Override
    public String toString(){
        String output = "Order Number: "+orderNumber+"\n";
        output+="Order for: "+name+"\n";
        output+=food.toString();
        output+=food.getPrice();
        return output;
    }
}
