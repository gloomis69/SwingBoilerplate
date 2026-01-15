package controllers;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import models.Hamburger;
import swingboilerplate.SwingBoilerplate;
import views.Hamburger_Panel;
import views.Title_Panel;

//A class that will track a list of hamburger orders
public class Hamburger_Controller {

    ArrayList<Hamburger> hamburgerOrders;
    private final Hamburger_Panel view;
    private Hamburger currentOrder;
    private boolean isResetting = false;

    public Hamburger_Controller() {
        hamburgerOrders = new ArrayList<>();
        view = new Hamburger_Panel(this);
        currentOrder = new Hamburger();
    }

    public Hamburger_Panel getView() {
        return view;
    }

    
    public void toppingListener(JCheckBox cbx) {
        cbx.addActionListener(e -> {
            if (!isResetting) {
                JCheckBox cb = (JCheckBox) e.getSource();
                String topping = cb.getText();
                if (cb.isSelected()) {
                    currentOrder.addTopping(topping);
                } else {
                    currentOrder.removeTopping(topping);
                }
            }
        });
    }

    public void submitListenter(JButton btn) {
        btn.addActionListener(e -> {
            String orderName = Title_Panel.getOrderName();
            hamburgerOrders.add(currentOrder);
            double cost = currentOrder.getPrice();
            double tax = currentOrder.getTax(cost);
            double total = currentOrder.getPriceWithTax();
            isResetting = true;
            SwingBoilerplate.order_panel.addOrder(orderName, currentOrder.toString(), cost, tax, total);
            currentOrder = new Hamburger();
            view.reset();
            isResetting = false;
        });
    }

    public void cancelListenter(JButton btn) {
        btn.addActionListener(e -> {
            currentOrder = new Hamburger();
            isResetting =  true;
            view.reset();
            isResetting = false;
        });
    }
}
