package views;

import controllers.Hamburger_Controller;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import models.Hamburger;

public final class Hamburger_Panel extends JPanel {

    private JCheckBox[] toppingsCbxs;
    private JPanel formPanel;
    private JPanel ordersPlaced;

    public Hamburger_Panel(Hamburger_Controller controller) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        add(createTitlePanel());
        add(createFormPanel(controller));
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel lblTitle = new JLabel("Order a Hamburger", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 36));
        titlePanel.add(lblTitle);

        titlePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        Dimension pref = titlePanel.getPreferredSize();
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, pref.height));
        titlePanel.setBorder(new EmptyBorder(30, 15, 0, 10));

        return titlePanel;
    }
    
    private JPanel createFormPanel(Hamburger_Controller controller) {
        formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.setBorder(new EmptyBorder(0, 15, 0, 10));
                
        formPanel.add(createSubtitle());
        formPanel.add(createToppingsPanel(controller));
        formPanel.add(createButtonPanel(controller));

        return formPanel;
    }

    

    private JLabel createSubtitle() {
        JLabel subTitle = new JLabel("Place your order: ", JLabel.LEFT);
        subTitle.setBorder(new EmptyBorder(20, 5, 10, 10));
        subTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        return subTitle;
    }

    private JPanel createToppingsPanel(Hamburger_Controller controller) {
        JPanel toppingsPanel = new JPanel(new GridLayout(3, 3, 10, 10));

        String[] availableToppings = Hamburger.AVAILABLE_TOPPINGS;
        toppingsCbxs = new JCheckBox[availableToppings.length];

        for (int i = 0; i < toppingsCbxs.length; i++) {
            toppingsCbxs[i] = new JCheckBox(availableToppings[i]);
            controller.toppingListener(toppingsCbxs[i]);
            toppingsPanel.add(toppingsCbxs[i]);
        }

        Dimension pref = toppingsPanel.getPreferredSize();
        toppingsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, pref.height));
        toppingsPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
        toppingsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        return toppingsPanel;
    }

    private JPanel createButtonPanel(Hamburger_Controller controller) {
        JPanel buttonPnl = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton btnSubmit = new JButton("Submit");
        controller.submitListenter(btnSubmit);

        JButton btnCancel = new JButton("Cancel");
        controller.cancelListenter(btnCancel);

        buttonPnl.add(btnSubmit);
        buttonPnl.add(btnCancel);
        buttonPnl.setAlignmentX(Component.LEFT_ALIGNMENT);

        return buttonPnl;
    }
    
   

    

    public ArrayList<String> getSelectedToppings() {
        ArrayList<String> toppings = new ArrayList<>();
        for (JCheckBox box : toppingsCbxs) {
            if (box.isSelected()) {
                toppings.add(box.getText());                
            }
        }
        return toppings;
    }

   

    public void reset() {        
        for (JCheckBox bx : toppingsCbxs) {
            bx.setSelected(false);
        }
    }

    

    public void addOrder(String name, String description, double cost, double tax, double total) {
        // Order container
        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
        orderPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Content labels
        JLabel nameLabel = new JLabel("Name: " + name);
        JLabel descLabel = new JLabel("Description: " + description);
        JLabel costLabel = new JLabel(String.format("Base Price: $%.2f", cost));
        JLabel taxLabel = new JLabel(String.format("Tax: $%.2f", tax));
        JLabel totalLabel = new JLabel(String.format("Total: $%.2f", total));

        // Left align all labels
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        costLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        taxLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        totalLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Add labels
        orderPanel.add(nameLabel);
        orderPanel.add(descLabel);
        orderPanel.add(costLabel);
        orderPanel.add(taxLabel);
        orderPanel.add(totalLabel);

        // Divider line
        JSeparator separator = new JSeparator();
        separator.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Remove bottom glue, insert order, re-add glue
        ordersPlaced.remove(ordersPlaced.getComponentCount() - 1);

        ordersPlaced.add(Box.createVerticalStrut(8));
        ordersPlaced.add(orderPanel);
        ordersPlaced.add(Box.createVerticalStrut(8));
        ordersPlaced.add(separator);

        ordersPlaced.add(Box.createVerticalGlue());

        //tell the graphics to re-draw the panel with the new information
        ordersPlaced.revalidate();
        ordersPlaced.repaint();
    }

}
