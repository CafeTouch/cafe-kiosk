package controller;

import screen.*;
import model.*;

public class AppController {
    Cart myCart = new Cart();

    public void newScreen() { new FirstScreen(this);}

    public void startApp() {
        new MenuFrame(this);
    }

    public void showCheckPurchaseScreen() {
        new CheckPurchase(this, myCart);}

    public void showPaymentScreen() {
        new PaymentScreen(this); // 결제 화면
    }

    public void showKeypadScreen() {
        new KeypadScreen(this);
    }
}


/*package controller;

import screen.MenuFrame;
import screen.PaymentScreen;
import screen.KeypadScreen;

public class AppController {
    public void startApp() {
        new MenuFrame(this);
    }

    public void showPaymentScreen() {
        new PaymentScreen(this); // 결제 화면
    }

    public void showKeypadScreen() {
        new KeypadScreen(this);
    }
}*/

