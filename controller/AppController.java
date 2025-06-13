package controller;

import screen.*;
import model.*;

public class AppController {
    // 컨트롤러에서 카트를 생성해서 다른 클래스에서 쭉 사용
    private Cart myCart = new Cart();

    // 다른 클래스에서 모두 myCart 받아와 쓸 수 있도록 설정
    public Cart getCart() { return myCart;}

    public void newScreen() { new FirstScreen(this);} // 초기화면(매장/포장)

    public void startApp() {new MainFrame(this);}// 본격적인 앱 시작(메인프레임)

    public void showCheckPurchaseScreen() {
        new CheckPurchase(this, myCart);} //

    public void showPaymentScreen() {
        new PaymentOptionScreen(this); // 결제 화면
    }

    public void showKeypadScreen() {
        new KeypadScreen(this);
    }

    public void showPurchaseDone() { new PaymentScreen(this);}
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

