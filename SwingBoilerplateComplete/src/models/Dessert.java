package models;

public class Dessert extends Food{
    
    public enum Type {
        CAKE("Chocolate Cake", 6.49, "/resources/images/cake.jpg"),
        PIE("Key Lime Pie", 5.49, "/resources/images/pie.jpg"),
        ICE_CREAM("Vanilla Ice Cream", 3.99, "/resources/images/ice_cream.jpg");

        private final String description;
        private final double price;
        private final String imagePath;

        private Type(String description, double price, String imagePath){
            this.description = description;
            this.price = price;
            this.imagePath = imagePath;
        }

        public String getDescription(){
            return description;
        }

        public double getPrice(){
            return price;
        }

        public String getImagePath(){
            return imagePath;
        }
    }

    private Type type = Type.CAKE;

    public Dessert(){
        super();
    }

    public void setType(String type){
        for(Type t:Type.values()){
            if(t.getDescription().equals(type)) this.type = t;
        }
    }

    public void setType(Dessert.Type type){
        this.type = type;
    }

    public Type getType(){
        return type;
    }

    @Override
    public double getPrice() {
        return type.getPrice();
    }

    @Override
    public String toString() {
        return type.getDescription();
    }
}
