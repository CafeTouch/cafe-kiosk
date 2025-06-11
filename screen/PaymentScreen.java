package screen;

import controller.AppController;

import javax.swing.*;
import java.awt.*;

public class PaymentScreen extends JFrame {
    public PaymentScreen(AppController controller) {
        setTitle("결제 완료");
        setSize(600, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("결제가 완료되었습니다!", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 40));
        add(label, BorderLayout.CENTER);

        setVisible(true);
    }
}
