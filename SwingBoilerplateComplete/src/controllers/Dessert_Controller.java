package controllers;

import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import models.Dessert;
import swingboilerplate.SwingBoilerplate;
import views.Dessert_Panel;

public class Dessert_Controller {
    private final Dessert_Panel view;
    private Dessert currentOrder;
    private boolean isResetting;

    public Dessert_Controller(){
        view = new Dessert_Panel();

        currentOrder = new Dessert();
        view.rbTypeListener(e -> typeListener(e));
        view.btnCancelListener(e -> cancelListener());
        view.btnSubmitListener(e -> submitListener());
    }

    public Dessert_Panel getView(){
        return view;
    }

    public void typeListener(ActionEvent e){
        if(!isResetting){
            JRadioButton rb = (JRadioButton) e.getSource();
            String text = rb.getText();
            currentOrder.setType(text);
            view.setPrice(currentOrder.getType());
        }
    }

    public void submitListener() {
        isResetting = true;
        SwingBoilerplate.order_panel.addOrder(currentOrder);
        currentOrder = new Dessert();
        view.reset();
        isResetting = false;
    }

    public void cancelListener() {
        isResetting = true;
        currentOrder = new Dessert();
        view.reset();
        isResetting = false;
    }
}
