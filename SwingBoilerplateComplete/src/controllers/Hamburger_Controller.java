package controllers;

import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import models.Hamburger;
import swingboilerplate.SwingBoilerplate;
import views.Hamburger_Panel;

//A class that will track a list of hamburger orders
public class Hamburger_Controller {

    private final Hamburger_Panel view;
    private Hamburger currentOrder;
    private boolean isResetting = false;

    public Hamburger_Controller() {
        view = new Hamburger_Panel();
        currentOrder = new Hamburger();
        view.setPrice(currentOrder.getPrice());

        // listeners
        view.btnSubmitListener(e -> submitListenter());
        view.btnCancelListener(e -> cancelListenter());
        view.cbxToppingListener(e -> toppingListener(e));
    }

    public Hamburger_Panel getView() {
        return view;
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
        view.setPrice(currentOrder.getPrice());
    }

    public void submitListenter() {
        isResetting = true;
        SwingBoilerplate.order_panel.addOrder(currentOrder);
        currentOrder = new Hamburger();
        view.reset();
        isResetting = false;
        view.setPrice(currentOrder.getPrice());

    }

    public void cancelListenter() {
        currentOrder = new Hamburger();
        isResetting = true;
        view.reset();
        isResetting = false;
        view.setPrice(currentOrder.getPrice());

    }
}
