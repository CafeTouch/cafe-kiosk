// [3] UI 패널 구성
package model;
import javax.swing.*;
import java.awt.*;
import java.util.List;


// 추후 메인프레임 하단 (SOUTH) 영역에 붙여질 예정
public class CartPanel extends JPanel {
    public Cart cart; // 생성자
    private MenuItem item; // 생성자

    //public JPanel mediumPanel;
    private JLabel priceLabel; // 단일 품목
    private JLabel quantityLabel; // 수량
    Font font = new Font("바탕", Font.BOLD, 5);  // 글씨체, 굵기, 크기
    /*public CartFrame(Cart cart, MenuItem item) {
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

    public CartPanel(Cart cart) {
        this.cart = cart;

        JPanel mediumPanel = new JPanel();
        mediumPanel.setLayout(new BoxLayout(mediumPanel, BoxLayout.Y_AXIS));
        mediumPanel.setBackground(Color.GREEN);

        List<MenuItem> items = cart.getItems();
        for (MenuItem item : items) {
            mediumPanel.add(createSmallPanel(item));
        }
        //bigPanel.setLayout(new BoxLayout(mediumPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(mediumPanel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bigPanel = new JPanel();
        bigPanel.setLayout(new BoxLayout(bigPanel, BoxLayout.Y_AXIS));
        bigPanel.setBackground(Color.WHITE);
        bigPanel.setOpaque(true);

        bigPanel.add(mediumPanel);
        bigPanel.add(createPricePanel());
        add(bigPanel, BorderLayout.SOUTH);

    }

    // 결제창 버튼 (아직 미완성)
    public JButton paymentButton() {
        JButton payButton = new JButton("결제");
        payButton.addActionListener(e -> {
            // 결제창으로 넘어가기
        });
        return payButton;
    }
    // 수량 감소 버튼
    private JButton decreaseButton() {
        JButton minusButton = new JButton("-"); // 감소 버튼
        minusButton.setPreferredSize(new Dimension(25, 25));
        minusButton.setFont(font);

        minusButton.addActionListener(e -> {
            cart.decreaseQuantity(item);
            if (item.getQuantity() <= 0) {
                cart.deleteItem(item);
                quantityLabel.setText(String.valueOf(item.getQuantity()));
            }
        });
        return minusButton;
    }
    // 수량 증가 버튼
    private JButton increaseButton() {
        JButton plusButton = new JButton("+"); // 증가 버튼
        plusButton.setPreferredSize(new Dimension(25, 25));
        plusButton.setFont(font);

        // 수량 증가 버튼
        plusButton.addActionListener(e-> {
            {
                cart.increaseQuantity(item);
                quantityLabel.setText(String.valueOf(item.getQuantity()));
            }
        });
        return plusButton;
    }

    // item 패널 생성
    public JPanel createSmallPanel(MenuItem item) { // top + bottom 패널 = medium panel
        JPanel smallPanel = new JPanel(new GridLayout(2, 1));
        //top panel
        JPanel topPanel = new JPanel(new BorderLayout()); // topPanel: 메뉴명, 수량, 가격

        JLabel nameLabel = new JLabel(item.getName()); // 1. 메뉴명
        topPanel.add(nameLabel, BorderLayout.WEST);

        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // 2. 수량 조절
        JLabel quantityLabel = new JLabel(String.valueOf(item.getQuantity()));
        JButton minusButton = decreaseButton(); // 감소 버튼
        JButton plusButton = increaseButton(); // 증가 버튼

        quantityPanel.add(new JLabel("수량: "));
        quantityPanel.add(minusButton); // - 버튼
        quantityPanel.add(quantityLabel); // 수량 표시
        quantityPanel.add(plusButton); // + 버튼
        topPanel.add(quantityPanel, BorderLayout.CENTER);

        JLabel singleTotalLabel = new JLabel("가격: " + item.getItemPrice() + "원"); // 3. 항목 당 가격
        topPanel.add(singleTotalLabel, BorderLayout.EAST);

        // bottom panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel sizeLabel = new JLabel("사이즈: " + item.getSize());
        JLabel disposablesLabel;
        if (item.getDisposables() == 0) {
            disposablesLabel = new JLabel("일회용품: 선택 안 함" );
        }
        else {
            disposablesLabel = new JLabel("일회용품: 받기 선택");
        }

        JLabel extraShotLabel = new JLabel("샷 추가 (+500원): " + item.getExtraShot());
        bottomPanel.add(sizeLabel);
        bottomPanel.add(extraShotLabel);
        bottomPanel.add(disposablesLabel);

        smallPanel.add(topPanel);
        smallPanel.add(bottomPanel);

        return smallPanel;
    }

    // 가격 버튼 있는 패널 생성
    public JPanel createPricePanel() { //top, bottom
        JPanel pricePanel = new JPanel(new BorderLayout());
        priceLabel = new JLabel("총 금액: " + cart.getTotalCost() + "원");
        JButton payButton = paymentButton();

        pricePanel.add(priceLabel, BorderLayout.NORTH);
        pricePanel.add(payButton, BorderLayout.SOUTH);
        return pricePanel;
    }

    // 패널 삭제
    public void deletePanel(JPanel mediumPanel, JPanel smallPanel) {
        mediumPanel.remove(smallPanel);
        mediumPanel.revalidate();
        mediumPanel.repaint();}

}
