/* 구조 설명 : panel>
            (scrollPane > wholeCartPanel > itemPanel > 아이템 라벨들) + totalPanel
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

    public CheckPurchaseScreen(Cart cart) {this.cart = cart;} // 카트 받아옴

    // 스크린
    public JPanel createCartPanel() {
        Color labelColor1 = new Color(235, 244, 253);
        Color labelColor2 = new Color(129, 175, 213);

        // penel: 컨테이너 1,2를 묶는 판넬 컨테이너
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(labelColor1);

        // wholeCartPanel: for문 돌면서 생성된 itemPenel들을 묶는 컨테이너
        JPanel wholeCartPanel = new JPanel();
        wholeCartPanel.setLayout(new BoxLayout(wholeCartPanel, BoxLayout.Y_AXIS));
        wholeCartPanel.setBackground(labelColor1);

        // 패널에 Cart에 있는 아이템의 이름, 수량, 가격 출력
        for (CartItem item : cart.getItems()) {
            // itemPenel: 아이템의 이름, 수량, 가격 라벨 담음(컨테이너 1)
            JPanel itemPanel = new JPanel();
            // itemPanel 크기 고정
            itemPanel.setMaximumSize(new Dimension(580, 50));
            itemPanel.setPreferredSize(new Dimension(580, 50));
            itemPanel.setMinimumSize(new Dimension(580, 50));
            itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.X_AXIS));
            itemPanel.setBackground(labelColor1);
            itemPanel.setBorder(BorderFactory.createLineBorder(labelColor2, 5, true));

            JLabel nameLabel = new JLabel("      "+ item.getName());
            JLabel sizeLabel = new JLabel(" | 사이즈: " + item.getSize());
            JLabel quantityLabel = new JLabel("| 수량: " + item.getQuantity());
            JLabel priceLabel = new JLabel("| 가격: " + item.getItemPrice() + "원");

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


            //-----라벨을 판넬에 더하기
            itemPanel.add(nameLabel);
            itemPanel.add(sizeLabel);
            itemPanel.add(quantityLabel);
            itemPanel.add(priceLabel);

            // wholeCartPanel에 itemPanel 더함
            wholeCartPanel.add(itemPanel);
        }
        
        // scrollPane: 상품 수량이 늘어났을 때를 대비해 wholeCartPanel를 스크롤로 묶음
        JScrollPane scrollPane = new JScrollPane(wholeCartPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        // 가로 스크롤 뜨는 오류 때문에 가로스크롤 막음
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(600, 700));
        panel.add(scrollPane, BorderLayout.CENTER);

        // 총 금액 출력 (컨테이너 2)
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        totalPanel.setPreferredSize(new Dimension(600, 100));

        totalLabel = new JLabel("총합: " + cart.getTotalCost() + "원");
        totalLabel.setHorizontalAlignment(JLabel.CENTER);
        totalLabel.setOpaque(true);
        totalLabel.setBackground(labelColor2);
        totalLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        totalPanel.add(totalLabel);
        totalPanel.setBackground(labelColor2);

        panel.add(totalPanel);

        return panel;
    }

}
