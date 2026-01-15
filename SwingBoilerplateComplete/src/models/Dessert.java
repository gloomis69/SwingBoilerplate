package models;

public class Dessert {
    public enum Type {
        CAKE("Chocolate Cake", 5.49, "/resources/images/cake.jpg"),
        PIE("Key Lime Pie", 5.49, "/resources/images/cake.jpg"),
        ICE_CREAM("Vanilla Ice Cream", 5.49, "/resources/images/cake.jpg");

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
}
