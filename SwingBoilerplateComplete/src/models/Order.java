package models;

import javax.swing.JPanel;

public class Order {
    private static int orderCount = 0;
    private final int orderNumber;
    private final Food food;
    private JPanel line;

    public Order(Food food){
        orderCount++;
        orderNumber = orderCount;
        this.food = food;
    }

    public void setLine(JPanel pnl){
        line = pnl;
    }

    public JPanel getLine(){
        return line;
    }
    
    public int getOrderNumber(){
        return orderNumber;
    }

    public Food getFood(){
        return food;
    }

    @Override
    public String toString(){
        String output = "Order Number: "+orderNumber+"\n";
        output+=food.toString();
        output+=food.getPrice();
        return output;
    }
}
