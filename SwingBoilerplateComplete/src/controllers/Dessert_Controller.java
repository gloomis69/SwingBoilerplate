package controllers;

import views.Dessert_Panel;

public class Dessert_Controller {
    Dessert_Panel view;

    public Dessert_Controller(){
        view = new Dessert_Panel();
    }

    public Dessert_Panel getView(){
        return view;
    }
}
