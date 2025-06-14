/* 구조 설명 : panel>
            (scrollPane > wholeCartPanel >
             (wholeItemPanel > ( itemPanel > 아이템 라벨들 ) + optPane ) )+ totalPanel
            penel 끝나고 맨 밑에 버튼
 */
package screen;

import model.Cart;
import model.CartItem;

import javax.swing.*;
import java.awt.*;

public class CheckPurchaseScreen extends JPanel {
    private Cart cart;
    private JLabel totalLabel;
    private JLabel totalQuanLabel;

    public CheckPurchaseScreen(Cart cart) {this.cart = cart;} // 카트 받아옴

    // 스크린
    public JPanel createCartPanel() {
        Color labelColor1 = new Color(235, 244, 253);
        Color labelColor2 = new Color(184,216,249);

        // penel: 컨테이너 1,2를 묶는 판넬 컨테이너
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        // wholeCartPanel: for문 돌면서 생성된 itemPenel들을 묶는 컨테이너
        JPanel wholeCartPanel = new JPanel();
        wholeCartPanel.setLayout(new BoxLayout(wholeCartPanel, BoxLayout.Y_AXIS));
        wholeCartPanel.setBackground(new Color(242,242,246));

        // 패널에 Cart에 있는 아이템의 이름, 수량, 가격 출력
        for (CartItem item : cart.getItems()) {
            // itemPenel: 아이템의 이름, 수량, 가격 라벨 담음(컨테이너 1-1)
            JPanel itemPanel = new JPanel();
            // 옵션패널이랑 기본패널 묶는 컨테이너 (컨테이너 1)
            JPanel wholeItemPanel = new JPanel();
            wholeItemPanel.setLayout(new BoxLayout(wholeItemPanel, BoxLayout.Y_AXIS));
            wholeItemPanel.setBackground(Color.WHITE);

            // itemPanel 크기 고정
            itemPanel.setMaximumSize(new Dimension(580, 70));
            itemPanel.setPreferredSize(new Dimension(580, 70));
            itemPanel.setMinimumSize(new Dimension(580, 70));
            itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.X_AXIS));
            // 판넬 디자인
            itemPanel.setBackground(labelColor1);
            itemPanel.setBorder(BorderFactory.createLineBorder(labelColor2, 1, false));

            JLabel nameLabel = new JLabel("    "+ item.getName());
            JLabel sizeLabel = new JLabel(" | 사이즈: " + item.getSize());
            JLabel quantityLabel = new JLabel(" | 수량: " + item.getQuantity());
            JLabel priceLabel = new JLabel(" | 가격: " + item.getItemPrice() + "원  ");

            // 옵션 버튼 생성
            ImageIcon Icon = new ImageIcon(getClass().getResource("/model/Images/pplus.jpg"));
            Image newIcon = Icon.getImage().getScaledInstance(40,40, Image.SCALE_FAST);
            ImageIcon resized = new ImageIcon(newIcon);
            JButton optionBtn = new JButton(resized);

            optionBtn.setBackground(labelColor1);
            optionBtn.setOpaque(false);


            //------라벨 커스텀
            nameLabel.setOpaque(true);
            sizeLabel.setOpaque(true);
            quantityLabel.setOpaque(true);
            priceLabel.setOpaque(true);

            nameLabel.setBackground(labelColor1);
            sizeLabel.setBackground(labelColor1);
            quantityLabel.setBackground(labelColor1);
            priceLabel.setBackground(labelColor1);

            nameLabel.setFont(new Font("맑은 고딕", Font.BOLD,20));
            sizeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
            quantityLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
            priceLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));


            //-----옵션(+)버튼 클릭하면 생성되는 판넬(접었다 폈다) : (컨테이너 1-2)
            // 판넬 생성 및 커스텀
            JPanel optPane = new JPanel();
            optPane.setLayout(new FlowLayout(FlowLayout.LEFT));
            optPane.setMaximumSize(new Dimension(580, 70));
            optPane.setBackground(labelColor2);

            // 옵션 별 추가할 라벨들
            String hotText = (item.getHot() == 0) ? "HOT" : "ICE";
            JLabel hotLabel = new JLabel("    "+ hotText);
            String shotText = (item.getExtraShot() == 0) ? " | 샷 추가 |" : " |";
            JLabel shotLabel = new JLabel(" "+ shotText);
            String sweetText = (item.getSweetener() == 0) ? " 시럽 추가 |" : "";
            JLabel sweetLabel = new JLabel(" "+ sweetText);
            String dispoText = (item.getDisposables() == 0) ? " 일회용품 사용" : "";
            JLabel dispoLabel = new JLabel(" "+ dispoText);

            // 라벨 커스텀
            hotLabel.setBackground(labelColor2);
            shotLabel.setBackground(labelColor2);
            sweetLabel.setBackground(labelColor2);
            dispoLabel.setBackground(labelColor2);

            hotLabel.setFont(new Font("맑은 고딕", Font.BOLD,18));
            shotLabel.setFont(new Font("맑은 고딕", Font.BOLD,18));
            sweetLabel.setFont(new Font("맑은 고딕", Font.BOLD,18));
            dispoLabel.setFont(new Font("맑은 고딕", Font.BOLD,18));


            // 라벨 판넬에 더하기
            optPane.add(hotLabel);
            optPane.add(shotLabel);
            optPane.add(sweetLabel);
            optPane.add(dispoLabel);

            // -----다시 기본 판넬
            // 라벨,버튼을 판넬에 더하기
            itemPanel.add(nameLabel);
            itemPanel.add(sizeLabel);
            itemPanel.add(quantityLabel);
            itemPanel.add(priceLabel);
            itemPanel.add(optionBtn);

            // 옵션패널 안보이다가 클릭되면 보임
            optPane.setVisible(false);
            optionBtn.addActionListener(e -> {
                optPane.setVisible(!optPane.isVisible());
                wholeItemPanel.revalidate();
                wholeItemPanel.repaint();
            });

            // wholeItemPanel에 itemPanel, optPane 더함
            wholeItemPanel.add(itemPanel);
            wholeItemPanel.add(optPane);

            // wholeCartPanel에 wholeItemPanel 더함
            wholeCartPanel.add(wholeItemPanel);
        }

        // scrollPane: 상품 수량이 늘어났을 때를 대비해 wholeCartPanel를 스크롤로 묶음
        JScrollPane scrollPane = new JScrollPane(wholeCartPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        // 가로 스크롤 뜨는 오류 때문에 가로스크롤 막음
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(600, 700));
        panel.add(scrollPane, BorderLayout.CENTER);

        // 총 금액, 주문 수량 출력 (컨테이너 2)
        JPanel totalPanel = new JPanel(new BorderLayout());
        totalPanel.setPreferredSize(new Dimension(600, 100));

        totalQuanLabel = new JLabel(" 총 수량: " + cart.getTotalQuantity() + " 잔");
        totalLabel = new JLabel("주문 금액: " + cart.getTotalCost() + " 원");

        totalLabel.setHorizontalAlignment(JLabel.CENTER);
        totalQuanLabel.setHorizontalAlignment(JLabel.CENTER);
        totalLabel.setOpaque(true);
        totalQuanLabel.setOpaque(true);
        totalLabel.setBackground(labelColor2);
        totalQuanLabel.setBackground(labelColor2);
        totalLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        totalQuanLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));

        totalPanel.add(totalQuanLabel, BorderLayout.NORTH);
        totalPanel.add(totalLabel, BorderLayout.CENTER);
        totalPanel.setBackground(labelColor2);

        panel.add(totalPanel);

        return panel;
    }

}
