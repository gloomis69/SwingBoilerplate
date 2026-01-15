package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Title_Panel extends JPanel{
    private static final JTextField nameField = new JTextField(20);;

    public Title_Panel(String title){
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 36));

        add(lblTitle);
        add(createNameRow());
        setBackground(Color.WHITE);
    }

    private JPanel createNameRow() {
        JPanel nameRow = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel nameLabel = new JLabel("Order Name:");
        

        nameRow.add(nameLabel);
        nameRow.add(nameField);

        Dimension pref = nameRow.getPreferredSize();
        nameRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, pref.height));
        nameRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        nameRow.setBackground(Color.WHITE);
        return nameRow;
    }

    public static String getOrderName(){
        return nameField.getText();
    }
}
