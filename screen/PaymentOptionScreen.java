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
        getContentPane().setBackground(new Color(235, 244, 253));

        JLabel label = new JLabel("결제 수단을 선택해 주세요", SwingConstants.CENTER);
        label.setFont(new Font("맑은 고딕", Font.BOLD, 36));
        label.setForeground(Color.BLACK);
        add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        buttonPanel.setBackground(new Color(242, 242, 246));

        String[][] options = {
            {"카드/삼성페이", "creditcard.png"},
            {"카카오페이", "kakaopay.png"},
            {"네이버페이", "npay.png"},
            {"애플페이", "apple_pay.png"},
        };

        for (String[] option : options) {
            JButton btn = createRoundedButton(option[0], option[1]);
            buttonPanel.add(btn);
        }

        add(buttonPanel, BorderLayout.CENTER);
        MarqueePanel marquee = new MarqueePanel("현금 결제는 직원에게 문의해 주세요", 10);
        add(marquee, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createRoundedButton(String text, String iconPath) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(getBackground());
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
                g2.dispose();
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(new Color(100, 130, 180));
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 40, 40);
                g2.dispose();
            }
        };

        btn.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        btn.setPreferredSize(new Dimension(180, 130));
        btn.setBackground(new Color(184, 216, 249));
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setOpaque(false);

        if (iconPath != null) {
            ImageIcon icon = new ImageIcon("img/" + iconPath);
            Image scaledImage = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            btn.setIcon(new ImageIcon(scaledImage));
            btn.setHorizontalTextPosition(SwingConstants.CENTER);
            btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        }

        btn.addActionListener(e -> {
            selectedPaymentMethod = text;
            openReceiptOptionScreen();
        });

        return btn;
    }

    private void openReceiptOptionScreen() {
        JFrame receiptFrame = new JFrame("출력 방식 선택");
        receiptFrame.setSize(600, 800);
        receiptFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        receiptFrame.setLayout(new BorderLayout());
        receiptFrame.getContentPane().setBackground(new Color(235, 244, 253));

        JLabel thanksLabel = new JLabel("주문해 주셔서 감사합니다!", SwingConstants.CENTER);
        thanksLabel.setFont(new Font("맑은 고딕", Font.BOLD, 26));
        thanksLabel.setForeground(new Color(60, 60, 80));
        receiptFrame.add(thanksLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(235, 244, 253));

        ImageIcon icon = new ImageIcon("img/noonsong.jpeg");
        Image scaled = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(scaled));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(iconLabel);

        JLabel label = new JLabel("출력 방식을 선택하세요", SwingConstants.CENTER);
        label.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        label.setForeground(Color.BLACK);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(label);

        receiptFrame.add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 40));
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
        receiptFrame.add(buttonPanel, BorderLayout.SOUTH);

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
        new PaymentOptionScreen();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PaymentOptionScreen::new);
    }

    static class MarqueePanel extends JPanel {
        private String text;
        private int x;
        private Timer timer;

        public MarqueePanel(String text, int speed) {
            this.text = text;
            this.x = getWidth();
            setPreferredSize(new Dimension(600, 40));
            setBackground(new Color(215, 230, 245));
            setFont(new Font("맑은 고딕", Font.BOLD, 20));

            timer = new Timer(speed, e -> {
                x -= 2;
                if (x + getFontMetrics(getFont()).stringWidth(text) < 0) {
                    x = getWidth();
                }
                repaint();
            });
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.DARK_GRAY);
            g.setFont(getFont());
            g.drawString(text, x, 25);
        }
    }
}
