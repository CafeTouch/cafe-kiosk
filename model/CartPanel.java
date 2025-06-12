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


// 추후 메인프레임 하단 (SOUTH) 영역에 붙여질 예정
public class CartPanel extends JPanel {
    public Cart cart; // 생성자
    private CartItem item; // 생성자
    private JPanel mediumPanel;

    //public JPanel mediumPanel;
    private JLabel priceLabel; // 단일 품목
    private JLabel quantityLabel; // 수량
    Font font = new Font("맑은 고딕", Font.BOLD, 12);

    public CartPanel(Cart cart) {
        this.cart = cart;

        this.mediumPanel = new JPanel();
        mediumPanel.setLayout(new BoxLayout(mediumPanel, BoxLayout.Y_AXIS));
        mediumPanel.setBackground(new Color(200, 200, 255));
        mediumPanel.setOpaque(true);
        mediumPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mediumPanel.setMaximumSize(new Dimension(490, Integer.MAX_VALUE));

        // 객체 리스트
        List<CartItem> items = cart.getItems();
        for (CartItem item : items) {
            mediumPanel.add(createSmallPanel(item));
        }

        JPanel bigPanel = new JPanel(new BorderLayout());
        bigPanel.setPreferredSize(new Dimension(600,200));
        bigPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(mediumPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        bigPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel pricePanel = createPricePanel();
        pricePanel.setPreferredSize(new Dimension(100, 200));
        bigPanel.add(pricePanel, BorderLayout.EAST);

        add(bigPanel, BorderLayout.CENTER);
        bigPanel.setOpaque(true);
    }

    // 결제창 버튼 (아직 미완성)
    public JButton paymentButton() {
        JButton payButton = new JButton("결제");
        payButton.setFont(font);
        payButton.setForeground(Color.WHITE);
        payButton.addActionListener(e -> {
            // 결제창으로 넘어가기
        });
        return payButton;
    }

    // 수량 감소 버튼
    private JButton decreaseButton(CartItem item, JLabel quantityLabel) {
        JButton minusButton = new JButton("-"); // 감소 버튼
        minusButton.setFont(new Font("맑은 고딕", Font.BOLD, 13));
        minusButton.setMargin(new Insets(0, 0, 0, 0));
        minusButton.setPreferredSize(new Dimension(30, 25));

        minusButton.setForeground(Color.WHITE);
        minusButton.addActionListener(e -> {
            cart.decreaseQuantity(item);
            if (item.getQuantity() <= 0) {
                cart.deleteItem(item);
                quantityLabel.setText("0");}
            else {
                quantityLabel.setText(String.valueOf(item.getQuantity()));
            }
            priceLabel.setText("총 금액: " + cart.getTotalCost() + "원");
        });
        return minusButton;
    }

    // 수량 증가 버튼
    private JButton increaseButton(CartItem item, JLabel quantityLabel) {
        JButton plusButton = new JButton("+"); // 증가 버튼
        plusButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        plusButton.setMargin(new Insets(0, 0, 0, 0));
        plusButton.setPreferredSize(new Dimension(30, 25));
        plusButton.setForeground(Color.WHITE);
        // 수량 증가 버튼
        plusButton.addActionListener(e-> {
            {
                cart.increaseQuantity(item);
                quantityLabel.setText(String.valueOf(item.getQuantity()));
                priceLabel.setText("총 금액: " + cart.getTotalCost() + "원");
            }

        });
        return plusButton;
    }

    // item 패널 생성
    public JPanel createSmallPanel(CartItem item) { // top + bottom 패널 = medium panel
        JPanel smallPanel = new JPanel(new GridLayout(2, 1));
        smallPanel.setLayout(new BoxLayout(smallPanel, BoxLayout.Y_AXIS));
        smallPanel.setBackground(Color.WHITE);
        smallPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        //top panel
        JPanel topPanel = new JPanel(new BorderLayout()); // topPanel: 메뉴명, 수량, 가격
        topPanel.setBackground(new Color(200, 220, 255));

        JPanel nameHotPanel = new JPanel(new BorderLayout());
        nameHotPanel.setMaximumSize(new Dimension(450, 40));

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

        // 1. 메뉴명 nameLabel
        JLabel nameLabel = new JLabel(" " + item.getName(), SwingConstants.CENTER); // 1. 메뉴명
        nameLabel.setBackground((new Color(184,216,249)));
        nameLabel.setPreferredSize(new Dimension(80, 40));
        nameLabel.setOpaque(true);
        nameHotPanel.add(nameLabel);

        topPanel.add(nameHotPanel, BorderLayout.WEST);

        // 2. 수량 조절 quantityLabel -> quantityPanel
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JLabel quantityLabel = new JLabel(String.valueOf(item.getQuantity()), SwingConstants.CENTER);
        quantityLabel.setBackground(Color.RED);

        quantityLabel.setPreferredSize(new Dimension(40, 20));

        // 3. 수량 조절 버튼 속성
        JButton minusButton = decreaseButton(item, quantityLabel); // 감소 버튼
        minusButton.setPreferredSize(new Dimension(25, 25));
        minusButton.setBackground(new Color(0,102,204));
        minusButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        JButton plusButton = increaseButton(item, quantityLabel); // 증가 버튼
        plusButton.setPreferredSize(new Dimension(25, 25));
        plusButton.setBackground(new Color(0,102,204));
        plusButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        quantityPanel.add(new JLabel("수량: "));
        quantityPanel.add(minusButton); // - 버튼
        quantityPanel.add(quantityLabel); // 수량 표시
        quantityPanel.add(plusButton); // + 버튼
        quantityPanel.setBackground(Color.WHITE);
        topPanel.add(quantityPanel, BorderLayout.CENTER);

        // 3. 항목 당 가격
        JLabel singleTotalLabel = new JLabel(" 가격: " + item.getItemPrice() + "원 ");
        singleTotalLabel.setBackground(new Color(200,220,255));
        singleTotalLabel.setSize(60, 80);
        singleTotalLabel.setBackground(Color.RED);
        topPanel.add(singleTotalLabel, BorderLayout.EAST);

        // bottom Panel 속성 설정
        Font optionFont = new Font("맑은 고딕", Font.BOLD, 10);
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setBackground(new Color(242, 242, 246));

        // 4. 옵션 선택 속성 (사이즈, 일회용품, 샷 추가, 시럽 추가)
        JLabel sizeLabel = new JLabel(" 사이즈: " + item.getSize() + " ");
        sizeLabel.setBackground(Color.WHITE);
        sizeLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        sizeLabel.setFont(optionFont);
        sizeLabel.setOpaque(true);
        bottomPanel.add(sizeLabel);
        JLabel extraShotLabel;
        if (item.getExtraShot() == 1) {
             extraShotLabel = new JLabel(" 샷 추가 (+500원) ");
             extraShotLabel.setBackground(Color.WHITE);
             extraShotLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
             extraShotLabel.setFont(optionFont);
             bottomPanel.add(extraShotLabel);
             extraShotLabel.setOpaque(true);
        }
        JLabel addSweetenerLabel;
        if (item.getSweetener() == 1) {
            addSweetenerLabel = new JLabel(" 시럽 추가 (+500원) ");
            addSweetenerLabel.setBackground(Color.WHITE);
            addSweetenerLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            addSweetenerLabel.setFont(optionFont);
            bottomPanel.add(addSweetenerLabel);
            addSweetenerLabel.setOpaque(true);
        }
        JLabel disposablesLabel;
        if (item.getDisposables() == 0) { disposablesLabel = new JLabel(" 일회용품: 받기 안 함 "); }
        else { disposablesLabel = new JLabel(" 일회용품: 받음 "); }
        disposablesLabel.setBackground(Color.WHITE);
        disposablesLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        disposablesLabel.setFont(optionFont);
        bottomPanel.add(disposablesLabel);
        disposablesLabel.setOpaque(true);

        topPanel.setMaximumSize(new Dimension(490, 40));
        bottomPanel.setMaximumSize(new Dimension(490, 25));

        smallPanel.setMaximumSize(new Dimension(490, 100));
        smallPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        smallPanel.add(topPanel);
        smallPanel.add(bottomPanel);

        smallPanel.revalidate();
        smallPanel.repaint();

        return smallPanel;
    }

    // 가격 버튼 있는 패널 생성
    public JPanel createPricePanel() { //top, bottom
        JPanel pricePanel = new JPanel();
        pricePanel.setLayout(new BoxLayout(pricePanel, BoxLayout.Y_AXIS));
        pricePanel.setBackground(Color.WHITE);

        JLabel priceLabel1 = new JLabel("주문 금액");
        JLabel priceLabel2 = new JLabel(cart.getTotalCost() + "원");
        priceLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        priceLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton payButton = paymentButton();
        payButton.setPreferredSize(new Dimension(60, 20));
        payButton.setMaximumSize(new Dimension(60, 25));
        payButton.setBackground(new Color(0, 102, 204));
        payButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        pricePanel.add(Box.createVerticalStrut(20));
        pricePanel.add(priceLabel1);
        pricePanel.add(priceLabel2);
        pricePanel.add(Box.createVerticalGlue());
        pricePanel.add(payButton);
        pricePanel.add(Box.createVerticalGlue());
        return pricePanel;
    }

    public void refresh() {
        mediumPanel.removeAll();             // 기존 UI 컴포넌트 제거
        List<CartItem> items = cart.getItems();  // 장바구니 현재 항목 가져오기

        for (CartItem item : items) {
            JPanel itemPanel = createSmallPanel(item);
            mediumPanel.add(itemPanel);
        }
        mediumPanel.revalidate();  // 레이아웃 갱신
        mediumPanel.repaint();     // UI 다시 그림
    }

    // 패널 삭제
    public void deletePanel(JPanel mediumPanel, JPanel smallPanel) {
        mediumPanel.remove(smallPanel);
        mediumPanel.revalidate();
        mediumPanel.repaint();}

}
