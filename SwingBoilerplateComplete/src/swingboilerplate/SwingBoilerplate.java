
package swingboilerplate;

import controllers.Body_Controller;
import java.awt.BorderLayout;
import javax.swing.*;
import views.Body_Panel;
import views.Menu_Panel;
import views.Order_Panel;
import views.Title_Panel;


public class SwingBoilerplate {
    public static Order_Panel order_panel = new Order_Panel();
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SwingBoilerplate::createAndShowGUI);
    }
    
    public static void createAndShowGUI(){
        // 1. Create the main application window (JFrame)
        JFrame frame = new JFrame("My App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit the application when the window is closed
        frame.setSize(1000, 800); // Set the initial size
        frame.setLocationRelativeTo(null); //center the frame on the monitor when it opens
        
        //2. Add panels to the frame
        frame.add(new Title_Panel("Food Ordering Wizard"), BorderLayout.NORTH);
        
        String[] menu = {"hot dogs", "hamburgers", "desserts", "drinks"};
        Menu_Panel menuPnl = new Menu_Panel(menu);
        frame.add(menuPnl, BorderLayout.WEST);
        
        Body_Panel bodyPnl = new Body_Panel();
        frame.add(bodyPnl, BorderLayout.CENTER);       
        
        frame.add(order_panel, BorderLayout.SOUTH);
        //3. Display the frame
        frame.setVisible(true);
        
        Body_Controller controller = new Body_Controller();
        controller.attach(bodyPnl, menuPnl);
    }
}
