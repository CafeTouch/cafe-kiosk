package model;

import controller.AppController;
import javax.swing.*;
import java.awt.*;

public class CheckPurchase extends JFrame {
    private AppController controller;
    private Cart cart;
    private JLabel totalLabel;

    public JPanel createCartPanel() {
        Color labelColor1 = new Color(235,244,253);
        Color labelColor2 = new Color(129, 175, 213);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(labelColor1);

        // 패널에 Cart에 있는 아이템의 이름, 수량, 가격 출력
        for (CartItem item : cart.items) {
            // 컨테이너 1
            JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            JLabel nameLabel = new JLabel(item.getName());
            JLabel sizeLabel = new JLabel("사이즈: " + item.getSize());
            JLabel quantityLabel = new JLabel("수량: " + item.getQuantity());
            JLabel priceLabel = new JLabel("가격: " + item.getItemPrice() + "원");

            //------라벨 커스텀
            nameLabel.setOpaque(true);
            sizeLabel.setOpaque(true);
            quantityLabel.setOpaque(true);
            priceLabel.setOpaque(true);

            nameLabel.setBackground(labelColor1);
            sizeLabel.setBackground(labelColor1);
            quantityLabel.setBackground(labelColor1);
            priceLabel.setBackground(labelColor1);

            nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
            sizeLabel.setFont(new Font("<UNK> <UNK>", Font.BOLD, 20));
            quantityLabel.setFont(new Font("<UNK> <UNK>", Font.BOLD, 20));
            priceLabel.setFont(new Font("<UNK>", Font.BOLD, 20));
            

            //-----라벨을 판넬에 더하기
            itemPanel.add(nameLabel);
            itemPanel.add(sizeLabel);
            itemPanel.add(quantityLabel);
            itemPanel.add(priceLabel);

            panel.add(itemPanel);
        }

        // 총 금액 출력 (컨테이너 2)
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        totalLabel = new JLabel("총합: " + cart.getTotalCost() + "원");
        totalLabel.setOpaque(true);
        totalLabel.setBackground(labelColor2);
        totalLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        totalPanel.add(totalLabel);

        panel.add(totalPanel);

        return panel;
    }

    // 모든 컨테이너 통합
    public CheckPurchase(AppController controller, Cart cart) {
        this.controller = controller;
        this.cart = cart;

        Color backGroundColor = new Color(255, 255, 255);

        setTitle("결제 확인");
        setSize(600, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(backGroundColor);
        setLayout(new BorderLayout());

        // 결제 확인 패널
        JPanel checkPanel = createCartPanel();
        add(checkPanel, BorderLayout.CENTER);

        // 결제하기 버튼
        Color btnColor = new Color(184,216,249);

        JButton purchase = new JButton("결제하기");
        purchase.setBackground(btnColor);
        purchase.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        add(purchase, BorderLayout.SOUTH);

        // 결제 버튼 클릭 이벤트 -> 클릭하면 키패드로 넘어감
        purchase.addActionListener(e -> controller.showKeypadScreen());

        setVisible(true);
    }
}
