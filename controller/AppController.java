package controller;

import screen.*;
import model.*;

public class AppController {
    private Cart myCart = new Cart();

    // 다른 클래스에서 모두 myCart 쓸 수 있또록
    public Cart getCart() { return myCart;}



    public void newScreen() { new FirstScreen(this);}

    public void startApp() {new MainFrame(this);}

    //public void showCart() { new CartPanel(this,myCart);}

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

