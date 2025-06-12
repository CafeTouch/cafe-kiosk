package screen;

import model.Cart;
import model.CartItem;

import javax.swing.*;
import java.awt.*;

public class CheckPurchaseScreen extends JPanel {
    private Cart cart;
    private JLabel totalLabel;

    public CheckPurchaseScreen(Cart cart) {
        this.cart = cart; // 카트 받아옴
    }

    // 스크린
    public JPanel createCartPanel() {
        Color labelColor1 = new Color(235, 244, 253);
        Color labelColor2 = new Color(129, 175, 213);

        // 컨테이너 1, 2를 묶는 판넬 컨테이너
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
            sizeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
            quantityLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
            priceLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));


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

}