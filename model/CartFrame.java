package model;

import javax.swing.*;
import java.awt.*;

// 추후 메인프레임 하단 (SOUTH) 영역에 붙여질 예정
public class CartFrame extends JPanel {
    private Cart cart; // 생성자
    private CartItem item; // 생성자

    private JLabel singleTotalLabel; // 총 금액
    private JLabel priceLabel; // 단일 품목
    private JLabel quantityLabel; // 수량

    /*public CartFrame(Cart cart, CartItem item) {
        this.cart = cart;
        this.item = item;

        JPanel itemPanel = createItemPanel();
        JPanel pricePanel = createPricePanel();

        this.add(itemPanel, BorderLayout.CENTER); // frame에 붙여야 오류 픽스
        this.add(pricePanel, BorderLayout.SOUTH);

        setVisible(true);
    }
    
    // 패널 하나의 구성
    /*
    아이스 아메리카노   수량: - (2) +    가격: 5000            (1) top panel
    사이즈: L  샷추가: 0  일회용품: 선택 안 함                  (2) bottom panel
                                    총 금액: 5000 원        (3) 총 금액
                                    [결제하기]              payment button
     */
    
    private JPanel createItemPanel() {
        JPanel mediumPanel = new JPanel(new GridLayout(2, 1)); // top , bottom panel  품목 데이터

        //top panel
        JPanel topPanel = new JPanel(new BorderLayout()); // topPanel: 메뉴명, 수량, 가격

        JLabel nameLabel = new JLabel(item.getName()); // 1. 메뉴명
        topPanel.add(nameLabel, BorderLayout.WEST);

        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // 2. 수량 조절
        //JLabel quantityLabel = new JLabel(String.valueOf(item.getQuantity()));
        JButton minusButton = decreaseButton(); // 감소 버튼
        JButton plusButton = increaseButton(); // 증가 버튼
        quantityPanel.add(new JLabel("수량: "));
        quantityPanel.add(minusButton); // - 버튼
        quantityPanel.add(quantityLabel); // 수량 표시
        quantityPanel.add(plusButton); // + 버튼
        topPanel.add(quantityPanel, BorderLayout.CENTER);

        singleTotalLabel = new JLabel("가격: " + item.getItemPrice() + "원"); // 3. 항목 당 가격
        topPanel.add(singleTotalLabel, BorderLayout.EAST);

        // bottom panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel sizeLabel = new JLabel("사이즈: " + item.getSize());
        JLabel disposablesLabel = new JLabel("일회용품: " + item.getDisposables());
        JLabel extraShotLabel = new JLabel("샷 추가 (+500원)" + item.getExtraShot());
        bottomPanel.add(sizeLabel);
        bottomPanel.add(extraShotLabel);
        bottomPanel.add(disposablesLabel);

        mediumPanel.add(topPanel);
        mediumPanel.add(bottomPanel);

        return mediumPanel;
    }

    private JPanel createPricePanel() { //top, bottom
        JPanel pricePanel = new JPanel(new BorderLayout());
        JLabel priceLabel = new JLabel("총 금액: " + cart.getTotalCost());
        JButton payButton = paymentButton();

        pricePanel.add(priceLabel, BorderLayout.NORTH);
        pricePanel.add(payButton, BorderLayout.SOUTH);
        return pricePanel;
    }

    private JButton decreaseButton() {
        JButton minusButton = new JButton("-"); // 감소 버튼
        minusButton.addActionListener(e -> {
            cart.decreaseQuantity(item);
            if (item.getQuantity() <= 0) {
                cart.deleteItem(item);
                quantityLabel.setText(String.valueOf(item.getQuantity()));
            }
        });
        return minusButton;
    }

    private JButton increaseButton() {
        JButton plusButton = new JButton("+"); // 증가 버튼
        // 수량 증가 버튼
        plusButton.addActionListener(e-> {
            {
                cart.increaseQuantity(item);
                quantityLabel.setText(String.valueOf(item.getQuantity()));
            }
        });
        return plusButton;
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

