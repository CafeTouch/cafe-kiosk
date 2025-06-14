// [4] 최종 ui 구현
package screen;
import javax.swing.*;
import java.awt.*;
import model.Cart;
import model.CartItem;
import model.CartPanel;
import controller.AppController;

public class CartScreen extends JPanel {
    private AppController controller;
    private Cart cart = new Cart();
    private CartPanel cartPanel;
    private JPanel mediumPanel;

    public CartScreen() {
        this.controller=new AppController();
        this.cart = new Cart();
        cart.addItem(new CartItem("아메리카노", 3000, 0, "small", 0, 1, 0,1 ));
        cart.addItem(new CartItem("카페라떼", 3500, 1, "big", 1, 2, 0, 1));

        this.cartPanel = new CartPanel( cart);

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
