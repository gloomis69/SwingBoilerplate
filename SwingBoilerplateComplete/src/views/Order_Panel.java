package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import models.Food;
import models.Order;

public class Order_Panel extends JPanel {
    private static final int RECEIPT_WIDTH = 400;
    private static double grandSubtotal = 0;
    private static double grandtaxes = 0;
    private static double grandtotal = 0;

    private final JPanel ordersPlaced = new JPanel();
    private final JLabel lblSubtotal = new JLabel("Sub-total: ", JLabel.RIGHT);
    private final JLabel lblTaxes = new JLabel("Tax: ", JLabel.RIGHT);
    private final JLabel lblTotal = new JLabel("Total: ", JLabel.RIGHT);
    private JLabel title;
    private JPanel orderContainer;
    private ArrayList<Order> orders = new ArrayList<>();

    public Order_Panel() {
        super(new BorderLayout());
        add(createOrdersPlacedPanel(), BorderLayout.CENTER);
        JPanel totals_panel = new JPanel(new BorderLayout());
        Font totalFont = new Font("SansSerif", Font.BOLD, 18);
        lblSubtotal.setFont(totalFont);
        lblTaxes.setFont(totalFont);
        lblTotal.setFont(totalFont);
        totals_panel.add(lblSubtotal, BorderLayout.NORTH);
        totals_panel.add(lblTaxes, BorderLayout.CENTER);
        totals_panel.add(lblTotal, BorderLayout.SOUTH);
        JPanel pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnl.add(totals_panel);
        add(pnl, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(400, 300));
        displayGrandTotal();
        Title_Panel.nameListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (Title_Panel.getOrderName().isEmpty()) {
                    title.setText("Orders");
                } else {
                    title.setText("Orders for " + Title_Panel.getOrderName());
                }
            }
        });
    }

    private void displayGrandTotal() {
        if (Title_Panel.getOrderName().isEmpty()) {
            title.setText("Orders");
        } else {
            title.setText("Orders for " + Title_Panel.getOrderName());
        }

        lblSubtotal.setText(String.format("Subtotal: $%.2f", grandSubtotal));
        lblTaxes.setText(String.format("Tax: $%.2f", grandtaxes));
        lblTotal.setText(String.format("Total: $%.2f", grandtotal));
    }

    private JScrollPane createOrdersPlacedPanel() {

        ordersPlaced.setLayout(new BoxLayout(ordersPlaced, BoxLayout.Y_AXIS));
        ordersPlaced.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Title
        title = new JLabel("Orders");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));
        // title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setBorder(new EmptyBorder(5, 5, 5, 5));
        ordersPlaced.add(title);
        ordersPlaced.add(Box.createVerticalStrut(10));

        orderContainer = new JPanel();
        orderContainer.setLayout(new BoxLayout(orderContainer, BoxLayout.Y_AXIS));
        orderContainer.setMaximumSize(new Dimension(RECEIPT_WIDTH, Integer.MAX_VALUE));
        ordersPlaced.add(orderContainer);
        // Wrap in scroll pane
        JScrollPane scrollPane = new JScrollPane(ordersPlaced);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        // Prevent horizontal scrolling
        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        return scrollPane;
    }

    public void addOrder(Food food) {        
        String description = food.toString();
        double price = food.getPrice();
        double tax = food.getTax(price);
        double total = food.getPriceWithTax();

        Order order = new Order(food);
        orders.add(order);

        // update grand totals
        grandSubtotal += price;
        grandtaxes += tax;
        grandtotal += total;
        displayGrandTotal();

        JPanel line = new JPanel();
        order.setLine(line);
        line.setLayout(new BoxLayout(line, BoxLayout.X_AXIS));

        JButton btnDelete = new JButton("X");
        btnDelete.setBorder(new EmptyBorder(2, 2, 2, 2));
        btnDelete.setName(order.getOrderNumber()+"");
        btnDelete.addActionListener((ActionEvent e) -> {
            /*JButton btn = (JButton)e.getSource();
            String id = btn.getName();
            int orderNum = Integer.parseInt(id);
            for(Order order: Orders){

            }*/
           orderContainer.remove(line);
           grandSubtotal-=price;
           grandtaxes -= tax;
           grandtotal -= total;
           displayGrandTotal();
           orders.remove(order);
           orderContainer.revalidate();
           orderContainer.repaint();
        });
        JLabel lblDesc = new JLabel("<html>" + description + "</html>");
        lblDesc.setBorder(new EmptyBorder(0, 20, 0, 20));
        JLabel lblPrice = new JLabel(String.format("$%.2f", price));

        btnDelete.setAlignmentY(Component.CENTER_ALIGNMENT);
        lblDesc.setAlignmentY(Component.CENTER_ALIGNMENT);
        lblPrice.setAlignmentY(Component.CENTER_ALIGNMENT);

        line.add(btnDelete);
        line.add(lblDesc);
        line.add(Box.createHorizontalGlue());
        line.add(lblPrice);

        line.setAlignmentX(Component.LEFT_ALIGNMENT);

        // line.setMaximumSize(new Dimension(RECEIPT_WIDTH, Integer.MAX_VALUE));
        // line.setPreferredSize(new Dimension(RECEIPT_WIDTH, 35));
        line.setBorder(new EmptyBorder(5, 5, 5, 5));

        orderContainer.add(line);
        // tell the graphics to re-draw the panel with the new information
        orderContainer.revalidate();
        orderContainer.repaint();
    }
}
