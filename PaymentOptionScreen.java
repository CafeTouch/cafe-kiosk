package screen;

import controller.AppController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentOptionScreen extends JFrame {
    private String selectedPaymentMethod = null;
    private static int currentOrderNumber = 0; //주문번호 초기값

    public PaymentOptionScreen() {
        setTitle("결제 수단 선택");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("결제 수단을 선택해 주세요", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 24));
        add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 20, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JButton cardButton = new JButton("카드 결제");
        cardButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        cardButton.setPreferredSize(new Dimension(200, 60));
        cardButton.addActionListener(e -> {
            selectedPaymentMethod = "카드";
            openReceiptOptionScreen();
        });

        JButton payButton = new JButton("페이 결제");
        payButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        payButton.setPreferredSize(new Dimension(200, 60));
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
        receiptFrame.setSize(500, 300);
        receiptFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        receiptFrame.setLayout(new BorderLayout());

        JLabel label = new JLabel("출력 방식을 선택하세요", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 24));
        receiptFrame.add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 20, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JButton bothButton = new JButton("영수증 출력");
        bothButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        bothButton.setPreferredSize(new Dimension(250, 60));
        bothButton.addActionListener(e -> {
            showConfirmation(selectedPaymentMethod, "영수증 출력");
            receiptFrame.dispose();
        });

        JButton orderOnlyButton = new JButton("주문번호만 인쇄");
        orderOnlyButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        orderOnlyButton.setPreferredSize(new Dimension(250, 60));
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
        currentOrderNumber = (currentOrderNumber + 1) % 201; // 0~200까지 순환

        String message = "주문번호: " + orderNumber + "\n" + paymentMethod + " 결제로 결제되었습니다.\n" + receiptOption + "합니다.";
        JOptionPane.showMessageDialog(null, message);
        new PaymentScreen(null);
    }

    public static void main(String[] args) {
        new PaymentOptionScreen();
    }
}