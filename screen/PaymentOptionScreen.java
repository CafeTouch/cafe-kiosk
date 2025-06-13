package screen;

import javax.swing.*;
import java.awt.*;

public class PaymentOptionScreen extends JFrame {
    private String selectedPaymentMethod = null;
    private static int currentOrderNumber = 0;

    public PaymentOptionScreen() {
        setTitle("결제 수단 선택");
        setSize(600, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(235, 244, 253)); // 연한 파랑

        JLabel label = new JLabel("결제 수단을 선택해 주세요", SwingConstants.CENTER);
        label.setFont(new Font("맑은 고딕", Font.BOLD, 36));
        label.setForeground(Color.BLACK);
        add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 20, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        buttonPanel.setBackground(new Color(242, 242, 246));

        String[][] options = {
            {"카드", "card.png"},
            {"삼성페이", "SamsungPay_Logo.png"},
            {"카카오페이", "kakaopay.png"},
            {"네이버페이", "npay.png"},
            {"애플페이", "apple_pay.png"},
            {"현금", null}
        };

        for (String[] option : options) {
            JButton btn = new JButton(option[0]);
            btn.setFont(new Font("맑은 고딕", Font.BOLD, 18));
            btn.setBackground(new Color(184, 216, 249));
            btn.setForeground(Color.BLACK);

            if (option[1] != null) {
                ImageIcon icon = new ImageIcon("img/" + option[1]);
                Image scaledImage = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                btn.setIcon(new ImageIcon(scaledImage));
                btn.setHorizontalTextPosition(SwingConstants.CENTER);
                btn.setVerticalTextPosition(SwingConstants.BOTTOM);
            }

            btn.addActionListener(e -> {
                selectedPaymentMethod = option[0];
                openReceiptOptionScreen();
            });

            buttonPanel.add(btn);
        }

        add(buttonPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null); // 화면 중앙 정렬
        setVisible(true);
    }

    private void openReceiptOptionScreen() {
        JFrame receiptFrame = new JFrame("출력 방식 선택");
        receiptFrame.setSize(600, 800);
        receiptFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        receiptFrame.setLayout(new BorderLayout());
        receiptFrame.getContentPane().setBackground(new Color(235, 244, 253));

        JLabel label = new JLabel("출력 방식을 선택하세요", SwingConstants.CENTER);
        label.setFont(new Font("맑은 고딕", Font.BOLD, 28));
        label.setForeground(Color.BLACK);
        receiptFrame.add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 50));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        buttonPanel.setBackground(new Color(242, 242, 246));

        JButton bothButton = new JButton("영수증 출력");
        bothButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        bothButton.setPreferredSize(new Dimension(200, 60));
        bothButton.setBackground(new Color(184, 216, 249));
        bothButton.setForeground(Color.BLACK);
        bothButton.addActionListener(e -> {
            showConfirmation(selectedPaymentMethod, "영수증 출력");
            receiptFrame.dispose();
        });

        JButton orderOnlyButton = new JButton("주문번호만 인쇄");
        orderOnlyButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        orderOnlyButton.setPreferredSize(new Dimension(200, 60));
        orderOnlyButton.setBackground(new Color(184, 216, 249));
        orderOnlyButton.setForeground(Color.BLACK);
        orderOnlyButton.addActionListener(e -> {
            showConfirmation(selectedPaymentMethod, "주문번호만 인쇄");
            receiptFrame.dispose();
        });

        buttonPanel.add(bothButton);
        buttonPanel.add(orderOnlyButton);
        receiptFrame.add(buttonPanel, BorderLayout.CENTER);
        receiptFrame.setLocationRelativeTo(null);
        receiptFrame.setVisible(true);
        dispose();
    }

    private void showConfirmation(String paymentMethod, String receiptOption) {
        int orderNumber = currentOrderNumber;
        currentOrderNumber = (currentOrderNumber + 1) % 201;

        String message = "주문번호: " + orderNumber + "\n" +
                         paymentMethod + "(으)로 결제되었습니다.\n" +
                         receiptOption + "합니다.";
        JOptionPane.showMessageDialog(null, message);
        new PaymentOptionScreen(); // 다시 처음 화면으로
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PaymentOptionScreen::new);
    }
}

