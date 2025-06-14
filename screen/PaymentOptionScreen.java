package screen;

import javax.swing.*;
import java.awt.*;
import java.net.URL;          // 🔄 getResource() 가 반환한 URL 처리를 위해 추가
import controller.AppController;

public class PaymentOptionScreen extends JFrame {

    private String selectedPaymentMethod = null;    // 어떤 결제수단 골랐는지 저장
    private static int currentOrderNumber = 0;      // 주문 번호 (0~200 순환)

    private AppController controller;

    // ──────────────────────────────────────────
    // 1) 생성자
    // ──────────────────────────────────────────
    public PaymentOptionScreen(AppController controller) {
        this.controller = controller;

        setTitle("결제 수단 선택");
        setSize(600, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(235, 244, 253));

        // 상단 제목
        JLabel title = new JLabel("결제 수단을 선택해 주세요", SwingConstants.CENTER);
        title.setFont(new Font("맑은 고딕", Font.BOLD, 36));
        add(title, BorderLayout.NORTH);

        // 버튼 영역
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        buttonPanel.setBackground(new Color(242, 242, 246));

        // 텍스트 + 파일명
        String[][] options = {
            {"카드/삼성페이", "creditcard.png"},
            {"카카오페이",    "kakaopay.png"},
            {"네이버페이",    "npay.png"},
            {"애플페이",      "apple_pay.png"},
        };

        for (String[] opt : options) {
            buttonPanel.add(createRoundedButton(opt[0], opt[1]));  // 🔄
        }

        add(buttonPanel, BorderLayout.CENTER);

        // 하단 마르퀴
        add(new MarqueePanel("현금 결제는 직원에게 문의해 주세요", 10), BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // ──────────────────────────────────────────
    // 2) 둥근 버튼 생성 (이미지 로딩 방식 변경)
    // ──────────────────────────────────────────
    private JButton createRoundedButton(String text, String fileName) { // 🔄 파라미터명 변경
        JButton btn = new JButton(text) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(getBackground());
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
                g2.dispose();
                super.paintComponent(g);
            }
            @Override protected void paintBorder(Graphics g) {
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

        /* 🔄  (중요) 클래스패스에서 이미지 읽어오기  */
        URL imgURL = getClass().getResource("/model/Images/" + fileName);
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image scaledImg = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            btn.setIcon(new ImageIcon(scaledImg));
            btn.setHorizontalTextPosition(SwingConstants.CENTER);
            btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        } else {
            System.err.println("이미지 파일을 찾을 수 없습니다: " + fileName);
        }

        btn.addActionListener(e -> {
            selectedPaymentMethod = text;
            openReceiptOptionScreen();
        });

        return btn;
    }

    // ──────────────────────────────────────────
    // 3) 출력 방식 선택 화면 (눈송이 이미지 경로 수정)
    // ──────────────────────────────────────────
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

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(new Color(235, 244, 253));

        /* 🔄 클래스패스 자원으로 교체 */
        URL imgURL = getClass().getResource("/model/Images/sookmyung_noonsong.png");
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image scaled = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            center.add(Box.createRigidArea(new Dimension(0, 20)));
            JLabel iconLabel = new JLabel(new ImageIcon(scaled));
            iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            center.add(iconLabel);
        }

        JLabel msg = new JLabel("출력 방식을 선택하세요", SwingConstants.CENTER);
        msg.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        msg.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(Box.createRigidArea(new Dimension(0, 20)));
        center.add(msg);
        receiptFrame.add(center, BorderLayout.CENTER);

        // 버튼 두 개
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 40));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        btnPanel.setBackground(new Color(242, 242, 246));

        JButton bothBtn = new JButton("영수증 출력");
        JButton orderOnlyBtn = new JButton("주문번호만 인쇄");
        for (JButton b : new JButton[]{bothBtn, orderOnlyBtn}) {
            b.setFont(new Font("맑은 고딕", Font.BOLD, 20));
            b.setPreferredSize(new Dimension(200, 60));
            b.setBackground(new Color(184, 216, 249));
            b.setForeground(Color.BLACK);
        }

        bothBtn.addActionListener(e -> {
            showConfirmation(selectedPaymentMethod, "영수증 출력");
            receiptFrame.dispose();
        });
        orderOnlyBtn.addActionListener(e -> {
            showConfirmation(selectedPaymentMethod, "주문번호만 인쇄");
            receiptFrame.dispose();
        });

        btnPanel.add(bothBtn);
        btnPanel.add(orderOnlyBtn);
        receiptFrame.add(btnPanel, BorderLayout.SOUTH);

        receiptFrame.setLocationRelativeTo(null);
        receiptFrame.setVisible(true);
        dispose();
    }

    // ──────────────────────────────────────────
    // 4) 결제 완료 팝업
    // ──────────────────────────────────────────
    private void showConfirmation(String paymentMethod, String receiptOption) {
        int orderNumber = currentOrderNumber;
        currentOrderNumber = (currentOrderNumber + 1) % 201;

        String msg = "주문번호: " + orderNumber + "\n"
                   + paymentMethod + "(으)로 결제되었습니다.\n"
                   + receiptOption + "합니다.";
        JOptionPane.showMessageDialog(null, msg);
        controller.newScreen();   // 처음 화면으로 복귀
    }

    // ──────────────────────────────────────────
    // 5) 하단 마르퀴 패널 (변경 없음)
    // ──────────────────────────────────────────
    class MarqueePanel extends JPanel {
        private final String text;
        private int x;
        private final Timer timer;
        public MarqueePanel(String text, int speed) {
            this.text = text;
            x = getWidth();
            setPreferredSize(new Dimension(600, 40));
            setBackground(new Color(215, 230, 245));
            setFont(new Font("맑은 고딕", Font.BOLD, 20));
            timer = new Timer(speed, e -> {
                x -= 2;
                if (x + getFontMetrics(getFont()).stringWidth(text) < 0) x = getWidth();
                repaint();
            });
            timer.start();
        }
        @Override protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.DARK_GRAY);
            g.drawString(text, x, 25);
        }
    }
}


