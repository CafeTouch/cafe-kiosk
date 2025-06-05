package model;

import javax.swing.*;
import java.awt.*;

// 추후 메인프레임 하단 (SOUTH) 영역에 붙여질 예정
public class CartFrame extends JFrame {
    private Cart cart; // 생성자
    private CartItem item; // 생성자

    private JLabel totalLabel;
    private JLabel priceLabel;
    private JLabel quantityLabel;

    public CartFrame(Cart cart, CartItem item) {
        this.cart = cart;
        this.item = item;

        quantityLabel = new JLabel(String.valueOf(item.getQuantity()));
        priceLabel = new JLabel("가격: " + cart.getTotalPrice(item) + "원");

        JPanel cartPanel = createCartPanel();
        this.add(cartPanel, BorderLayout.CENTER); // frame에 붙여야 오류 픽스

        JButton payButton = paymentButton();
        this.add(payButton, BorderLayout.SOUTH);

        setVisible(true);
    }
    
    // 패널 하나의 구성
    /*
    아이스 아메리카노   수량: - (2) +    가격: 5000 원
    사이즈: L  샷추가: 0  일회용품: 선택 안 함
                                    총 금액: 5000 원
                                    [결제하기]
     */
    private JPanel createCartPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1)); // top , bottom

        JPanel topPanel = new JPanel(new BorderLayout()); // topPanel: 이름, 수량, 가격
        JLabel nameLabel = new JLabel(item.getName());
        topPanel.add(nameLabel, BorderLayout.WEST);

        JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pricePanel.add(new JLabel("수량: "));

        JButton minusButton = new JButton("-"); // 감소 버튼
        JButton plusButton = new JButton("+"); // 증가 버튼
        totalLabel = new JLabel("총합: " + cart.getTotalCost() + "원");
        this.add(totalLabel, BorderLayout.SOUTH);

        // 수량 감소 버튼
        minusButton.addActionListener(e -> {
            cart.decreaseQuantity(item);
            if (item.getQuantity() <= 0) {
                cart.deleteItem(item);
                quantityLabel.setText(String.valueOf(item.getQuantity()));
                priceLabel.setText("  가격: " + cart.getTotalPrice(item) + "원");
                totalLabel.setText("  총합: " + cart.getTotalCost() + "원");
            }
        });
        // 수량 증가 버튼
        plusButton.addActionListener(e-> {
            {
                cart.increaseQuantity(item);
                quantityLabel.setText(String.valueOf(item.getQuantity()));
                priceLabel.setText("  가격: " + cart.getTotalPrice(item) + "원");
                totalLabel.setText("  총합: " + cart.getTotalPrice(item) + "원");
            }
        });

        pricePanel.add(minusButton); // - 버튼
        pricePanel.add(quantityLabel); // 수량 표시
        pricePanel.add(plusButton); // + 버튼
        pricePanel.add(priceLabel); // 가격 표시
        topPanel.add(pricePanel, BorderLayout.EAST);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.add(new JLabel("사이즈: " + item.getOptionSize() + "  옵션: " + (item.getOptionShot() == 1 ? " 샷 추가 (+500" : " 선택 안 함")));

        panel.add(topPanel);
        panel.add(bottomPanel);

        return panel;
    }

    // 결제창 버튼 (아직 미완성)
    private JButton paymentButton() {
        JButton payButton = new JButton("결제");
        payButton.addActionListener(e -> {
            // 결제창으로 넘어가기
        });
        return payButton;
    }
}
