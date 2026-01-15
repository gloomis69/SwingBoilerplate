package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import views.Body_Panel;
import views.Menu_Panel;

public class Body_Controller {
    private Body_Panel bodyPnl;
    //private final Menu_Panel menuPnl;
    
    public Body_Controller(){  }

    public void attach(Body_Panel bodyPnl, Menu_Panel menuPnl){
        this.bodyPnl = bodyPnl;
        //this.menuPnl = menuPnl;
        JButton[] buttons = menuPnl.getButtons();
        for(JButton btn: buttons){
            btn.addActionListener(new MenuListener());
        }
    }
    
    class MenuListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton)e.getSource();
            //"hot dogs", "hamburgers", "desserts", "drinks"
            String buttonText = btn.getText();
            bodyPnl.setPanel(buttonText);
        }
        
    }
}
