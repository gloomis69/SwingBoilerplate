
package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class Menu_Panel extends JPanel{    
    JButton[] buttons;
    
    public Menu_Panel(String[] menu){
        super(new BorderLayout());  
        setBackground(Color.BLACK);  
        JPanel panel = new JPanel(); 
        panel.setBackground(Color.BLACK);   
        panel.setLayout(new GridLayout(menu.length, 1, 10, 10));
        buttons = new JButton[menu.length];
        
        int i = 0;
        for(String item: menu){
            JButton btn = new JButton(item);
            panel.add(btn);
            buttons[i] = btn;
            i++;
        }
        panel.setBorder(new EmptyBorder(50, 10, 0, 10));
        add(panel, BorderLayout.NORTH);
    }
    
    public JButton[] getButtons(){
        return buttons;
    }
}
