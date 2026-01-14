package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

public class Order_Panel extends JPanel {

    private JPanel ordersPlaced= new JPanel();
    private double subtotal = 0;
    private double taxes = 0;
    private JLabel lblSubtotal = new JLabel("Sub-total: ");
    private JLabel lblTaxes = new JLabel("Tax: ");
    private JLabel lblTotal = new JLabel("Total: ");
    public Order_Panel(){
        super(new BorderLayout());
        add(createOrdersPlacedPanel(), BorderLayout.CENTER);
        JPanel totals_panel = new JPanel(new BorderLayout());
        totals_panel.add(lblSubtotal, BorderLayout.NORTH);
        totals_panel.add(lblTaxes, BorderLayout.CENTER);
        totals_panel.add(lblTotal, BorderLayout.SOUTH);
        add(totals_panel, BorderLayout.SOUTH);
        
    }

    private JScrollPane createOrdersPlacedPanel() {
        
        ordersPlaced.setLayout(new BoxLayout(ordersPlaced, BoxLayout.Y_AXIS));
        ordersPlaced.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Title
        JLabel title = new JLabel("Orders Placed");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        ordersPlaced.add(title);
        ordersPlaced.add(Box.createVerticalStrut(10));

        // This glue ensures extra space expands downward
        ordersPlaced.add(Box.createVerticalGlue());

        // Wrap in scroll pane
        JScrollPane scrollPane = new JScrollPane(ordersPlaced);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Prevent horizontal scrolling
        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        return scrollPane;
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
