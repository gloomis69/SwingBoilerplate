package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import models.Drink;

public class Drink_Panel extends JPanel {
    JRadioButton[] rbSizes = new JRadioButton[Drink.Size.values().length];
    JRadioButton[] rbTypes = new JRadioButton[Drink.Type.values().length];
    private JButton btnSubmit;
    private JButton btnCancel;
    private JLabel lblPrice;

    public Drink_Panel() {
        super(new BorderLayout());
        JPanel outerPanel = new JPanel();
        outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.Y_AXIS));

        JPanel pnlType = makeTypePanel();
        JPanel pnlSize = makeSizePanel();
        JPanel pnlPrice = subtotalPanel();
        JPanel pnlSubmit = createButtonPanel();

        pnlType.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlSize.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlPrice.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlSubmit.setAlignmentX(Component.LEFT_ALIGNMENT);

        outerPanel.add(createTitlePanel());
        outerPanel.add(pnlType);
        outerPanel.add(pnlSize);
        outerPanel.add(pnlPrice);
        outerPanel.add(pnlSubmit);
        outerPanel.setBorder(new EmptyBorder(0, 15, 0, 10));
        add(outerPanel, BorderLayout.NORTH);
    }

     private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel lblTitle = new JLabel("Order a Drink", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 36));
        titlePanel.add(lblTitle);

        titlePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        Dimension pref = titlePanel.getPreferredSize();
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, pref.height));
        titlePanel.setBorder(new EmptyBorder(30, 15, 0, 10));

        return titlePanel;
    } 
   
    private JPanel makeTypePanel() {
        JPanel panel = new JPanel();
        ButtonGroup typeGroup = new ButtonGroup();
        int index = 0;
        for(Drink.Type type : Drink.Type.values()){
            rbTypes[index] = new JRadioButton(type.getDescription());
            typeGroup.add(rbTypes[index]);
            panel.add(rbTypes[index]);
            if(type==Drink.Type.COKE) rbTypes[index].setSelected(true);
            index++;
        }
        
        return panel;
    }

    private JPanel makeSizePanel() {
        JPanel panel = new JPanel();
        ButtonGroup sizeGroup = new ButtonGroup();
        int index = 0;
        for(Drink.Size size : Drink.Size.values()){
            rbSizes[index] = new JRadioButton(size.getDescription());
            sizeGroup.add(rbSizes[index]);
            panel.add(rbSizes[index]);
            if(size==Drink.Size.MD) rbSizes[index].setSelected(true);
            index++;
        }
        
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

    public void reset(){
        rbSizes[1].setSelected(true);
        rbTypes[0].setSelected(true);
    }

    public void setPrice(double price){
        lblPrice.setText(String.format("Subtotal: $%.2f", price));
    }
    
    //register listeners
    public void rbTypeeListener(ActionListener listener) {
        for(JRadioButton rb: rbTypes){
            rb.addActionListener(listener);
        }        
    }

    public void rbSizeListener(ActionListener listener) {
        for(JRadioButton rb: rbSizes){
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
