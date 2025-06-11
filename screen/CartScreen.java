// [4] 최종 ui 구현
package Screen;
import javax.swing.*;
import java.awt.*;
import model.Cart;
import model.MenuItem;
import model.CartPanel;

public class CartScreen extends JPanel {
    private Cart cart = new Cart();
    private CartPanel cartPanel;
    private JPanel mediumPanel;

    public CartScreen() {
        this.cart = new Cart();
        cart.addItem(new MenuItem("아메리카노", 3000, "small", 1, 0, 1));
        cart.addItem(new MenuItem("카페라떼", 3500, "Large", 1, 1, 2));

        this.cartPanel = new CartPanel(this.cart);

        setLayout(new BorderLayout());
        add(cartPanel, BorderLayout.CENTER);
        cartPanel.setBackground(Color.WHITE);
        cartPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cartPanel.setOpaque(true);

    }

    public static void main(String[] args) {
        Cart cart = new Cart();
        // 수정

        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Panel Test");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setSize(600, 800);
            f.setLocationRelativeTo(null);

            f.setContentPane(new CartScreen());
            f.setVisible(true);
        });


    }

}
