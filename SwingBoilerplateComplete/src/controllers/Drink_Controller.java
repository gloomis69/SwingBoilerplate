package controllers;

import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import models.Drink;
import swingboilerplate.SwingBoilerplate;
import views.Drink_Panel;

public class Drink_Controller {
    private Drink currentOrder;
    private final Drink_Panel view;
    private boolean isResetting = false;

    public Drink_Controller() {
        currentOrder = new Drink();
        view = new Drink_Panel();
        view.setPrice(currentOrder.getPrice());
        view.rbSizeListener(e -> sizeListener(e));
        view.rbTypeListener(e -> typeListener(e));
        view.btnCancelListener(e -> cancelListener());
        view.btnSubmitListener(e -> submitListener());
    }

    public Drink_Panel getView() {
        return view;
    }

    public void typeListener(ActionEvent e) {
        if (!isResetting) {
            JRadioButton rb = (JRadioButton) e.getSource();
            String text = rb.getText();
            currentOrder.setType(text);
            view.setPrice(currentOrder.getPrice());
        }
    }

    public void sizeListener(ActionEvent e) {
        if (!isResetting) {
            JRadioButton rb = (JRadioButton) e.getSource();
            String text = rb.getText();
            currentOrder.setSize(text);
            view.setPrice(currentOrder.getPrice());
        }
    }

    public void submitListener() {
        isResetting = true;
        SwingBoilerplate.order_panel.addOrder(currentOrder);
        currentOrder = new Drink();
        view.reset();
        isResetting = false;
    }

    public void cancelListener() {
        isResetting = true;
        currentOrder = new Drink();
        view.reset();
        isResetting = false;
    }
}
