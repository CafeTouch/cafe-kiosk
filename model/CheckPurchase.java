package model;

import controller.AppController;
import javax.swing.*;
import java.awt.*;

public class CheckPurchase extends JFrame {
    private AppController controller;
    private Cart cart;
    private JLabel totalLabel;

    private JPanel createCartPanel() {
        Color labelColor1 = new Color(184, 216, 249);
        Color labelColor2 = new Color(129, 175, 213);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Cart 아이템의 이름, 수량, 가격 출력
        for (CartItem item : cart.items) {
            JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            itemPanel.setBackground(labelColor1);
            itemPanel.setVisible(true);

            JLabel nameLabel = new JLabel(item.getName());
            JLabel quantityLabel = new JLabel("     수량: " + item.getQuantity());
            JLabel priceLabel = new JLabel("    가격: " + cart.getTotalPrice(item) + "원");

            itemPanel.add(nameLabel);
            itemPanel.add(quantityLabel);
            itemPanel.add(priceLabel);

            JScrollPane scrItemPanel = new JScrollPane(itemPanel);

            panel.add(scrItemPanel);
        }

        // 총 금액 출력
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        totalPanel.setBackground(labelColor2);
        totalLabel = new JLabel("총합: " + cart.getTotalCost() + "원");
        totalPanel.add(totalLabel);

        panel.add(totalPanel);

        return panel;
    }

    public CheckPurchase(AppController controller) {
        this.controller = controller;

        Color backGroundColor = new Color(235, 244, 253);

        setTitle("결제 확인");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(backGroundColor);
        setLayout(new BorderLayout());

        // 결제 확인 패널
        JPanel checkPanel = createCartPanel();
        add(checkPanel, BorderLayout.CENTER);

        // 결제하기 버튼
        Color btnColor = new Color(99, 145, 213);

        JButton purchase = new JButton("결제하기");
        purchase.setBackground(btnColor);

        add(purchase, BorderLayout.SOUTH);

        // 결제 버튼 클릭 이벤트
        purchase.addActionListener(e -> controller.showPaymentScreen());

        setVisible(true);
    }
}


