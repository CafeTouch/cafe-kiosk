package screen;

import javax.swing.*;
import java.awt.*;

public class PaymentOptionScreen extends JFrame {
    private String selectedPaymentMethod = null;
    private static int currentOrderNumber = 0; // 주문번호 초기값

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

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 60, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 100, 100));
        buttonPanel.setBackground(new Color(242, 242, 246));

        JButton cardButton = new JButton("카드");
        cardButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        cardButton.setBackground(new Color(184, 216, 249));
        cardButton.setForeground(Color.BLACK);
        cardButton.setIcon(new ImageIcon("img/card.png"));
        cardButton.setHorizontalTextPosition(SwingConstants.CENTER);
        cardButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        cardButton.addActionListener(e -> {
            selectedPaymentMethod = "카드";
            openReceiptOptionScreen();
        });

        JButton payButton = new JButton("페이");
        payButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        payButton.setBackground(new Color(184, 216, 249));
        payButton.setForeground(Color.BLACK);
        payButton.setIcon(new ImageIcon("img/pay.png"));
        payButton.setHorizontalTextPosition(SwingConstants.CENTER);
        payButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        payButton.addActionListener(e -> {
            selectedPaymentMethod = "페이";
            openReceiptOptionScreen();
        });

        buttonPanel.add(cardButton);
        buttonPanel.add(payButton);
        add(buttonPanel, BorderLayout.CENTER);

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

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        buttonPanel.setBackground(new Color(242, 242, 246));

        JButton bothButton = new JButton("영수증 출력");
        bothButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        bothButton.setBackground(new Color(184, 216, 249));
        bothButton.setForeground(Color.BLACK);
        bothButton.addActionListener(e -> {
            showConfirmation(selectedPaymentMethod, "영수증 출력");
            receiptFrame.dispose();
        });

        JButton orderOnlyButton = new JButton("주문번호만 인쇄");
        orderOnlyButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        orderOnlyButton.setBackground(new Color(184, 216, 249));
        orderOnlyButton.setForeground(Color.BLACK);
        orderOnlyButton.addActionListener(e -> {
            showConfirmation(selectedPaymentMethod, "주문번호만 인쇄");
            receiptFrame.dispose();
        });

        buttonPanel.add(bothButton);
        buttonPanel.add(orderOnlyButton);
        receiptFrame.add(buttonPanel, BorderLayout.CENTER);
        receiptFrame.setVisible(true);
        dispose();
    }

    private void showConfirmation(String paymentMethod, String receiptOption) {
        int orderNumber = currentOrderNumber;
        currentOrderNumber = (currentOrderNumber + 1) % 201;

        String message = "주문번호: " + orderNumber + "\n" + paymentMethod + " 결제로 결제되었습니다.\n" + receiptOption + "합니다.";
        JOptionPane.showMessageDialog(null, message);
        // 다시 처음 화면으로 돌아가도록
        new PaymentOptionScreen();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaymentOptionScreen());
    }
}
