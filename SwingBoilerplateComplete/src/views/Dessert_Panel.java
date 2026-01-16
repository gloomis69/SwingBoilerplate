package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import models.Dessert;
import models.ImageLoader;

public class Dessert_Panel extends JPanel {
    JRadioButton[] rbDesserts = new JRadioButton[Dessert.Type.values().length];
    JLabel[] imageLabels = new JLabel[Dessert.Type.values().length];
    private JLabel lblPrice;
    private JButton btnSubmit;
    private JButton btnCancel;

    public Dessert_Panel() {
        super(new BorderLayout());
        JPanel outerPanel = new JPanel();
        outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.Y_AXIS));

        JPanel pnlPrice = subtotalPanel();
        JPanel pnlType = makeTypePanel();
        JPanel pnlSubmit = createButtonPanel();

        pnlType.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlPrice.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlSubmit.setAlignmentX(Component.LEFT_ALIGNMENT);

        outerPanel.add(createTitlePanel());
        outerPanel.add(pnlType);
        outerPanel.add(pnlPrice);
        outerPanel.add(pnlSubmit);
        outerPanel.setBorder(new EmptyBorder(0, 15, 0, 10));
        add(outerPanel, BorderLayout.NORTH);
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel lblTitle = new JLabel("Desserts", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 36));
        titlePanel.add(lblTitle);

        titlePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        Dimension pref = titlePanel.getPreferredSize();
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, pref.height));
        titlePanel.setBorder(new EmptyBorder(30, 15, 0, 10));

        return titlePanel;
    }

    private JPanel makeTypePanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ButtonGroup typeGroup = new ButtonGroup();
        int index = 0;
        for (Dessert.Type type : Dessert.Type.values()) {
            JPanel pnl = new JPanel();
            pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
            rbDesserts[index] = new JRadioButton(type.getDescription());
            typeGroup.add(rbDesserts[index]);
            imageLabels[index] = new JLabel();
            ImageLoader.setThumbnail(imageLabels[index], type.getImagePath(), 130, 100);
            final int i = index;
            imageLabels[index].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    rbDesserts[i].doClick();
                }
            });
            pnl.add(imageLabels[index]);
            pnl.add(rbDesserts[index]);
            panel.add(pnl);
            index++;
        }
        rbDesserts[0].setSelected(true);
        setPrice(Dessert.Type.CAKE);
        return panel;
    }

    private JPanel subtotalPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lblPrice = new JLabel();
        panel.add(lblPrice);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPnl = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnSubmit = new JButton("Submit");
        btnCancel = new JButton("Cancel");

        buttonPnl.add(btnSubmit);
        buttonPnl.add(btnCancel);
        buttonPnl.setAlignmentX(Component.LEFT_ALIGNMENT);

        return buttonPnl;
    }

    public void reset() {
        rbDesserts[0].setSelected(true);
        setPrice(Dessert.Type.CAKE);
    }

    public void setPrice(Dessert.Type type) {
        lblPrice.setText(String.format("Subtotal: $%.2f", type.getPrice()));
    }

    // register listeners
    public void rbTypeListener(ActionListener listener) {
        for (JRadioButton rb : rbDesserts) {
            rb.addActionListener(listener);
        }
    }

    public void btnSubmitListener(ActionListener listener) {
        btnSubmit.addActionListener(listener);
    }

    public void btnCancelListener(ActionListener listener) {
        btnCancel.addActionListener(listener);
    }
}
