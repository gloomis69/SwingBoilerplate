package models;

public class Drink extends Food{
    public enum Type {
        COKE("Coke"),
        DIET_COKE("Coke Zero"),
        SPRITE("Sprite"),
        ROOT_BEER("Root Beer"),
        WATER("Water");

        private final String description;

        private Type(String description){
            this.description = description;
        }

        public String getDescription(){
            return description;
        }
    }

    public enum Size {
        SM("Small", 1.99),
        MD("Medium", 2.99),
        LG("Large", 3.99),
        XL("Extra Large", 4.99);

        private final String description;
        private final double price;

        private Size(String description, double price){
            this.description = description;
            this.price = price;
        }

        public String getDescription(){
            return description;
        }

        public double getPrice(){
            return price;
        }
    }
    
    private Size size = Size.MD;
    private Type type;

    public Drink(Type type, Size size) {
        this.type = type;
        this.size = size;
    }

    public Drink(Type type) {
        this.type = type;
    }

    public Drink(Size size) {
        this(Type.COKE, size);
    }

    public Drink() {
        this(Type.COKE, Size.MD);
    }

    public void setType(String type){
        for(Type t:Type.values()){
            if(t.getDescription().equals(type)) this.type = t;
        }
    }

    public void setSize(String size){
        for(Size s:Size.values()){
            if(s.getDescription().equals(size)) this.size = s;
        }
    }

    public String getType(){
        return this.type.getDescription();
    }

    public String getSize(){
        return this.size.getDescription();
    }
    
    @Override
    public double getPrice(){      
        if(type == Type.WATER) return 0;  
        return this.size.getPrice();
    }
    
    @Override
    public String toString() {
        return "A "+size.getDescription()+" "+type.getDescription();        
    }
}
