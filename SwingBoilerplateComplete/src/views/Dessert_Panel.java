package views;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import models.Dessert;
import models.ImageLoader;

public class Dessert_Panel extends JPanel{
    JRadioButton[] rButtons = new JRadioButton[Dessert.Type.values().length];
    public Dessert_Panel() {
        JLabel lblCake = new JLabel("Chocolate Cake");
        ImageLoader.setThumbnail(lblCake, "/resources/images/cake.jpg", 64, 64);
        add(lblCake);
    }

}
