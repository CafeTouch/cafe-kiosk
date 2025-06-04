package screen;

import controller.AppController;
import javax.swing.*;
import java.awt.*;

public class MenuScreen extends JFrame {
    private AppController controller;

    public MenuScreen(AppController controller) {
        this.controller = controller;

        setTitle("메뉴 선택");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("메뉴 화면입니다", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(label, BorderLayout.CENTER);

        JButton nextBtn = new JButton("결제하기");
        nextBtn.addActionListener(e -> {
            controller.showKeypadScreen();
            dispose();
        });
        add(nextBtn, BorderLayout.SOUTH);

        setVisible(true);
    }
}
