package model;

import controller.AppController;
import javax.swing.*;
import java.awt.*;
import screen.CheckPurchaseScreen;

public class CheckPurchase extends JFrame {
    private AppController controller;
    private Cart cart;

    // 모든 컨테이너 통합
    public CheckPurchase(AppController controller, Cart cart) {
        this.controller = controller;
        this.cart = controller.getCart();

        Color backGroundColor = new Color(255, 255, 255);

        setTitle("결제 확인");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBackground(backGroundColor);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // 결제 확인 패널------
        // 스크린 불러옴
        CheckPurchaseScreen screen = new CheckPurchaseScreen(cart);
        JPanel checkPanel = screen.createCartPanel();
        add(checkPanel, BorderLayout.CENTER);

        // 결제하기 버튼
        Color btnColor = new Color(0,102,204);

        JButton purchase = new JButton("결제하기");
        purchase.setBackground(btnColor);
        purchase.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        purchase.setForeground(Color.WHITE);
        add(purchase, BorderLayout.SOUTH);

        // 결제 버튼 클릭 이벤트 -> 클릭하면 키패드로 넘어감
        purchase.addActionListener(e -> controller.showKeypadScreen());

        setVisible(true);
    }
}
