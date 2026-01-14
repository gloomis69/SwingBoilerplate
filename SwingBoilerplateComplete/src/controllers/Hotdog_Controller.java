package controllers;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import models.Hotdog;
import swingboilerplate.SwingBoilerplate;
import views.Hotdog_Panel;

//A class that will track a list of hotdog orders
public class Hotdog_Controller {

    ArrayList<Hotdog> hotdogOrders;
    private final Hotdog_Panel view;
    private Hotdog currentOrder;
    private boolean isResetting = false;

    public Hotdog_Controller() {
        hotdogOrders = new ArrayList<>();
        view = new Hotdog_Panel();

        // set Listeners
        view.btnStartListener(e -> newOrderListener());
        view.btnSubmitListener(e -> submitListenter());
        view.btnCancelListener(e -> cancelListenter());
        view.cbxToppingListener(e-> toppingListener(e));
    }

    public Hotdog_Panel getView() {
        return view;
    }

    public void newOrderListener() {
        currentOrder = new Hotdog();
        view.showOrderForm();
    }

    public void toppingListener(ActionEvent e) {
        if (!isResetting) {
            JCheckBox cb = (JCheckBox) e.getSource();
            String topping = cb.getText();
            if (cb.isSelected()) {
                currentOrder.addTopping(topping);
            } else {
                currentOrder.removeTopping(topping);
            }
        }
    }

    public void submitListenter() {
        String orderName = view.getOrderName();
        hotdogOrders.add(currentOrder);
        double cost = currentOrder.getPrice();
        double tax = currentOrder.getTax(cost);
        double total = currentOrder.getPriceWithTax();
        isResetting = true;
        SwingBoilerplate.order_panel.addOrder(orderName, currentOrder.toString(), cost, tax, total);
        currentOrder = null;
        view.reset();
        isResetting = false;
    }

    public void cancelListenter() {
        currentOrder = null;
        isResetting = true;
        view.reset();
        isResetting = false;
    }

}
