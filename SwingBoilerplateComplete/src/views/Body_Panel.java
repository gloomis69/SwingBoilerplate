package views;

import controllers.Dessert_Controller;
import controllers.Drink_Controller;
import controllers.Hamburger_Controller;
import controllers.Hotdog_Controller;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Body_Panel extends JPanel{
    CardLayout layout;
    
    public Body_Panel(){
        super();
        layout = new CardLayout();
        setLayout(layout);
        
        //"hot dogs", "hamburgers", "desserts", "drinks"
        //Create a controller for each, then get the view from it that it will control
        //The view is a type of JPanel
        
        Hotdog_Controller hotdogController = new Hotdog_Controller();
        Hotdog_Panel hotdog_Panel = hotdogController.getView();
        
        Hamburger_Controller hamburgerController = new Hamburger_Controller();
        Hamburger_Panel hamburgerPanel = hamburgerController.getView();
                
        Dessert_Controller dessertController = new Dessert_Controller();
        Dessert_Panel dessertPanel = dessertController.getView();
        
        Drink_Controller drinkController = new Drink_Controller();
        Drink_Panel drinksPanel = drinkController.getView();
        
        add(hotdog_Panel, "hot dogs");
        add(hamburgerPanel, "hamburgers");
        add(dessertPanel, "desserts");
        add(drinksPanel, "drinks");
    }
    
    public void setPanel(String panelName){
        layout.show(this, panelName);
    }
}
