// 패널 하나의 구성
    /*
    아이스 아메리카노   수량: - (2) +    가격: 5000            (1) top panel
    사이즈: L  샷추가: 0  일회용품: 선택 안 함                  (2) bottom panel
                                    총 금액: 5000 원        (3) 총 금액
                                    [결제하기]              payment button
     */
// [3] UI 패널 구성
package model;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import controller.AppController;

// 추후 메인프레임 하단 (SOUTH) 영역에 붙여질 예정
public class CartPanel extends JPanel {
    public Cart cart; // 생성자
    private JPanel mediumPanel;
    private JLabel totalPriceLabel;// 총 금액
    private JLabel totalQuantityLabel;
    private AppController controller;

    Font font = new Font("맑은 고딕", Font.BOLD, 12);
    
    // CART PANEL 객체 생성 + 외부 컨트롤러에서 받아옴
    public CartPanel(AppController controller, Cart cart) {
        this.cart = cart;
        this.controller= controller;

        // mediumPanel 속성
        this.mediumPanel = new JPanel();
        mediumPanel.setLayout(new BoxLayout(mediumPanel, BoxLayout.Y_AXIS));
        mediumPanel.setBackground(new Color(242, 242, 246));
        mediumPanel.setOpaque(true);
        mediumPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mediumPanel.setMaximumSize(new Dimension(490, Integer.MAX_VALUE));

        JPanel pricePanel = createPricePanel();

        // 리스트 items의 객체 item을 small Panel로 생성
        List<CartItem> items = cart.getItems();
        for (CartItem item : items) {
            mediumPanel.add(createSmallPanel(item));
        }

        // BIG PANEL 속성
        JPanel bigPanel = new JPanel(new BorderLayout());
        bigPanel.setPreferredSize(new Dimension(600,200));
        bigPanel.setBackground(Color.WHITE);

        // SCROLL PANE 속성: scrollPane이 mediumPanel 감싸는 구조
        JScrollPane scrollPane = new JScrollPane(mediumPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        bigPanel.add(scrollPane, BorderLayout.CENTER);

        // PRICE PANEL 속성: 금액, 결제버튼 담는 패널
        pricePanel.setPreferredSize(new Dimension(100, 200));
        bigPanel.add(pricePanel, BorderLayout.EAST);
        
        // BIG PANEL 배치
        add(bigPanel, BorderLayout.CENTER);
        bigPanel.setOpaque(true);
    }

    // PAYMENT BUTTON: 결제창 버튼 (아직 미완성)
    public JButton paymentButton() {
        // 1. 디자인 속성
        JButton payButton = new JButton("결제");
        payButton.setFont(font);
        payButton.setForeground(Color.WHITE);
        // 2. 기능 속성
        payButton.addActionListener(e -> {
            controller.showCheckPurchaseScreen();// 결제창으로 넘어가기
        });
        return payButton;
    }
    
    /* ----------------------------------------------------------------------------------------
        BUTTON 생성 -> PANEL 생성 및 관리
   -------------------------------------------------------------------------------------------- */
    // INCREASE BUTTON: 수량 증가 버튼q
    private JButton increaseButton(CartItem item, JLabel quantityLabel, JLabel singleTotalLabel) {
        // 1. 버튼 디자인 속성
        JButton plusButton = new JButton("+"); // 증가 버튼
        plusButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        plusButton.setMargin(new Insets(0, 0, 0, 0));
        plusButton.setPreferredSize(new Dimension(30, 25));
        plusButton.setForeground(Color.WHITE);
        // 2. 버튼 기능 속성: 수량 +, singleTotalLabel +, totalPriceLabel +
        plusButton.addActionListener(e-> {
            {
                cart.increaseQuantity(item);
                quantityLabel.setText(String.valueOf(item.getQuantity()));
                singleTotalLabel.setText(String.valueOf(item.getItemPrice()));
                totalPriceLabel.setText(String.valueOf(cart.getTotalCost()) + "원");
                totalQuantityLabel.setText(String.valueOf(cart.getTotalQuantity()) + "잔");
                refresh();
            }
        });
        return plusButton;
    }

    // DECREASE BUTTON: 수량 증가 버튼
    private JButton decreaseButton(CartItem item, JLabel quantityLabel, JLabel singleTotalLabel) {
        // 1. 버튼 디자인 속성
        JButton minusButton = new JButton("-"); // 감소 버튼
        minusButton.setFont(new Font("맑은 고딕", Font.BOLD, 13));
        minusButton.setMargin(new Insets(0, 0, 0, 0));
        minusButton.setPreferredSize(new Dimension(30, 25));
        minusButton.setForeground(Color.WHITE);
        // 2. 버튼 기능 속성: 수량 -, singleTotalLabel -, totalPriceLabel -
        minusButton.addActionListener(e -> {
            if (item.getQuantity() == 1) {
                int result = JOptionPane.showConfirmDialog(null,
                        "해당 메뉴를 삭제하시겠습니까?",
                        " 삭제 확인", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    cart.deleteItem(item);
                    refresh();
                    return;
                } else { item.setQuantity(1); }
            }
            else {
                cart.decreaseQuantity(item);
            }
            quantityLabel.setText(String.valueOf(item.getQuantity()));
            singleTotalLabel.setText(String.valueOf(item.getItemPrice()));
            totalPriceLabel.setText(String.valueOf(cart.getTotalCost()) + "원");
            totalQuantityLabel.setText(String.valueOf(cart.getTotalQuantity()) + "잔");
            refresh();
        });
        return minusButton;
    }


    // SMALL PANEL 생성: 메뉴 1개 -> ui 구현
    public JPanel createSmallPanel(CartItem item) { // top + bottom 패널 = medium panel
        // 1. SMALL PANEL 디자인 속성
        JPanel smallPanel = new JPanel(new GridLayout(2, 1));
        smallPanel.setLayout(new BoxLayout(smallPanel, BoxLayout.Y_AXIS));
        smallPanel.setBackground(Color.WHITE);
        smallPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        // 1-1. TOP PANEL
        JPanel topPanel = new JPanel(new BorderLayout()); // topPanel: 메뉴명, 수량, 가격
        topPanel.setBackground(new Color(200, 220, 255));

        // 1-1-A. NAMEHOT PANEL 디자인 속성: 이름과 온도를 TOP PANEL 맨 왼쪽에 배치
        JPanel nameHotPanel = new JPanel(new BorderLayout());
        nameHotPanel.setMaximumSize(new Dimension(450, 40));

        // 1-1-A-1. HOT PANEL 디자인 및 기능 속성 -> NAMEHOT PANEL에 ADD
        JLabel hotLabel;
        if (item.getHot() == 1) {
            hotLabel = new JLabel("HOT");
            hotLabel.setPreferredSize(new Dimension(40, 40));
            hotLabel.setFont(font);
            hotLabel.setForeground(Color.WHITE);
            hotLabel.setBackground(Color.RED);
            hotLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        }
        else {
            hotLabel = new JLabel("ICED");
            hotLabel.setPreferredSize(new Dimension(40, 40));
            hotLabel.setFont(font);
            hotLabel.setForeground(Color.WHITE);
            hotLabel.setBackground(new Color(0, 102, 204));
            hotLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        }
        hotLabel.setHorizontalAlignment(SwingConstants.CENTER);
        hotLabel.setVerticalAlignment(SwingConstants.CENTER);
        hotLabel.setOpaque(true);
        nameHotPanel.add(hotLabel, BorderLayout.WEST);

        // 1-1-A-2. NAME PANEL 디자인 및 기능 속성 -> NAMEHOT PANEL에  ADD
        JLabel nameLabel = new JLabel(" " + item.getName(), SwingConstants.CENTER); // 1. 메뉴명
        nameLabel.setBackground((new Color(184,216,249)));
        nameLabel.setPreferredSize(new Dimension(80, 40));
        nameLabel.setOpaque(true);
        nameHotPanel.add(nameLabel);

        // TOP PANEL에 NAMEHOT PANEL 배치
        topPanel.add(nameHotPanel, BorderLayout.WEST);

        // 1-1-B. QUANTITY PANEL
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 5));
        quantityPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        // 1-1-B-1. QUANTITY LABEL 속성:
        JLabel quantityLabel = new JLabel(String.valueOf(item.getQuantity()), SwingConstants.CENTER);
        quantityLabel.setBackground(new Color(242, 242, 246));
        quantityLabel.setOpaque(true);
        quantityLabel.setMinimumSize(new Dimension(60, 25));
        quantityLabel.setPreferredSize(new Dimension(60, 25));
        quantityLabel.setMaximumSize(new Dimension(60, 25));
        quantityLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // 3. 수량 조절 버튼 속성
        JLabel singleTotalLabel = new JLabel(" 가격: " + item.getItemPrice() + "원 ");
        singleTotalLabel.setBackground(new Color(200,220,255));
        singleTotalLabel.setSize(60, 80);
        singleTotalLabel.setOpaque(true);

        JButton minusButton = decreaseButton(item, quantityLabel, singleTotalLabel); // 감소 // 버튼
        minusButton.setPreferredSize(new Dimension(25, 25));
        minusButton.setBackground(new Color(0,102,204));
        minusButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        JButton plusButton = increaseButton(item, quantityLabel, singleTotalLabel); // 증가 버튼
        plusButton.setPreferredSize(new Dimension(25, 25));
        plusButton.setBackground(new Color(0,102,204));
        plusButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        quantityPanel.add(new JLabel("수량: "));
        quantityPanel.add(minusButton); // - 버튼
        quantityPanel.add(quantityLabel); // 수량 표시
        quantityPanel.add(plusButton); // + 버튼
        quantityPanel.add(Box.createRigidArea(new Dimension(6, 0)));
        quantityPanel.setBackground(Color.WHITE);
        topPanel.add(quantityPanel, BorderLayout.CENTER);

        topPanel.add(singleTotalLabel, BorderLayout.EAST);

        // bottom Panel 속성 설정
        Font optionFont = new Font("맑은 고딕", Font.BOLD, 10);
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setBackground(new Color(242, 242, 246));

        // 4. 옵션 선택 속성 (사이즈, 일회용품, 샷 추가, 시럽 추가)
        JLabel sizeLabel = new JLabel(" 사이즈: " + item.getSize() + " ");
        sizeLabel.setBackground(Color.WHITE);
        sizeLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        sizeLabel.setFont(optionFont);
        sizeLabel.setOpaque(true);
        bottomPanel.add(sizeLabel);
        JLabel extraShotLabel;
        if (item.getExtraShot() == 1) {
             extraShotLabel = new JLabel(" 샷 추가 (+500원) ");
             extraShotLabel.setBackground(Color.WHITE);
             extraShotLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
             extraShotLabel.setFont(optionFont);
             bottomPanel.add(extraShotLabel);
             extraShotLabel.setOpaque(true);
        }
        JLabel addSweetenerLabel;
        if (item.getSweetener() == 1) {
            addSweetenerLabel = new JLabel(" 시럽 추가 (+500원) ");
            addSweetenerLabel.setBackground(Color.WHITE);
            addSweetenerLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
            addSweetenerLabel.setFont(optionFont);
            bottomPanel.add(addSweetenerLabel);
            addSweetenerLabel.setOpaque(true);
        }
        JLabel disposablesLabel;
        if (item.getDisposables() == 0) { disposablesLabel = new JLabel(" 일회용품: 받기 안 함 "); }
        else { disposablesLabel = new JLabel(" 일회용품: 받음 "); }
        disposablesLabel.setBackground(Color.WHITE);
        disposablesLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        disposablesLabel.setFont(optionFont);
        bottomPanel.add(disposablesLabel);
        disposablesLabel.setOpaque(true);

        topPanel.setMaximumSize(new Dimension(490, 40));
        bottomPanel.setMaximumSize(new Dimension(490, 25));

        smallPanel.setMaximumSize(new Dimension(490, 65));
        smallPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        smallPanel.setPreferredSize(null); // 레이아웃에 맞게 자동

        smallPanel.add(topPanel);
        smallPanel.add(bottomPanel);

        smallPanel.revalidate();
        smallPanel.repaint();

        return smallPanel;
    }

    // 가격 버튼 있는 패널 생성
    public JPanel createPricePanel() { //top, bottom
        totalPriceLabel = new JLabel(cart.getTotalCost() + "원");
        totalPriceLabel.setBackground(new Color(200, 220, 255));
        totalPriceLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        totalPriceLabel.setOpaque(true);
        totalPriceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel itemCountTitleLabel = new JLabel("담은 개수");
        itemCountTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        totalQuantityLabel = new JLabel(cart.getTotalQuantity() + "개");
        totalQuantityLabel.setBackground(new Color(200, 220, 255));
        totalQuantityLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        totalQuantityLabel.setOpaque(true);
        totalQuantityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel pricePanel = new JPanel();
        pricePanel.setLayout(new BoxLayout(pricePanel, BoxLayout.Y_AXIS));
        pricePanel.setBackground(Color.WHITE);

        JLabel priceTitleLabel = new JLabel("주문 금액");
        priceTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton payButton = paymentButton();
        payButton.setPreferredSize(new Dimension(60, 20));
        payButton.setMaximumSize(new Dimension(60, 25));
        payButton.setBackground(new Color(0, 102, 204));
        payButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        payButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        pricePanel.add(Box.createVerticalStrut(20));
        pricePanel.add(itemCountTitleLabel);
        pricePanel.add(totalQuantityLabel);
        pricePanel.add(Box.createVerticalStrut(10));
        pricePanel.add(priceTitleLabel);
        pricePanel.add(totalPriceLabel);
        pricePanel.add(Box.createVerticalGlue());
        pricePanel.add(payButton);
        pricePanel.add(Box.createVerticalGlue());
        return pricePanel;
    }

    public void refresh() {
        if (totalPriceLabel == null) { return; }

        mediumPanel.removeAll();             // 기존 UI 컴포넌트 제거
        List<CartItem> items = cart.getItems();  // 장바구니 현재 항목 가져오기
        for (CartItem item : items) {
            JPanel itemPanel = createSmallPanel(item);
            mediumPanel.add(itemPanel);
        }
        totalPriceLabel.setText(cart.getTotalCost() + "원");
        totalQuantityLabel.setText(cart.getTotalQuantity() + "잔");
        mediumPanel.revalidate();  // 레이아웃 갱신
        mediumPanel.repaint();     // UI 다시 그림
    }

}
