package screen;

import javax.swing.*;
import java.awt.*;
import java.net.URL; // 이미지 리소스를 불러오기 위해 URL 사용함
import controller.AppController;

public class PaymentOptionScreen extends JFrame {

    private String selectedPaymentMethod = null;    // 선택된 결제 수단을 저장함
    private static int currentOrderNumber = 0;      // 주문 번호 (0~200까지 순환됨)

    private AppController controller;

    // 생성자 - 결제 수단 선택 화면 초기 구성
    public PaymentOptionScreen(AppController controller) {
        this.controller = controller;

        setTitle("결제 수단 선택");
        setSize(600, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(235, 244, 253)); // 연한 파란 배경 지정함

        // 상단 안내 문구 구성함
        JLabel title = new JLabel("결제 수단을 선택해 주세요", SwingConstants.CENTER);
        title.setFont(new Font("맑은 고딕", Font.BOLD, 36));
        add(title, BorderLayout.NORTH);

        // 결제 버튼들이 들어갈 영역 생성함
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        buttonPanel.setBackground(new Color(242, 242, 246));

        // 버튼에 표시될 텍스트와 이미지 파일명을 정의함
        String[][] options = {
            {"카드/삼성페이", "creditcard.png"},
            {"카카오페이",    "kakaopay.png"},
            {"네이버페이",    "npay.png"},
            {"애플페이",      "apple_pay.png"},
        };

        // 각 결제 수단에 대해 버튼 생성 및 패널에 추가함
        for (String[] opt : options) {
            buttonPanel.add(createRoundedButton(opt[0], opt[1]));
        }

        add(buttonPanel, BorderLayout.CENTER);

        // 하단 안내 문구 마르퀴 패널 추가함
        add(new MarqueePanel("현금 결제는 직원에게 문의해 주세요", 10), BorderLayout.SOUTH);

        setLocationRelativeTo(null); // 창을 화면 중앙에 위치시킴
        setVisible(true);            // 창을 보이게 설정함
    }

    // 둥근 스타일의 버튼을 생성하는 메서드
    private JButton createRoundedButton(String text, String fileName) {
        JButton btn = new JButton(text) {
            // 배경을 둥글게 그리는 설정
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(getBackground());
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
                g2.dispose();
                super.paintComponent(g);
            }

            // 테두리를 둥글게 그리는 설정
            @Override protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(new Color(100, 130, 180));
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 40, 40);
                g2.dispose();
            }
        };

        // 버튼 스타일 지정함
        btn.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        btn.setPreferredSize(new Dimension(180, 130));
        btn.setBackground(new Color(184, 216, 249));
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setOpaque(false);

        // 이미지 리소스를 클래스패스를 통해 불러와 버튼에 아이콘으로 설정함
        URL imgURL = getClass().getResource("/model/Images/" + fileName);
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image scaledImg = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            btn.setIcon(new ImageIcon(scaledImg));
            btn.setHorizontalTextPosition(SwingConstants.CENTER);
            btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        } else {
            System.err.println("이미지를 찾을 수 없음: " + fileName);
        }

        // 버튼 클릭 시 결제 수단 저장 및 다음 화면으로 전환함
        btn.addActionListener(e -> {
            selectedPaymentMethod = text;
            openReceiptOptionScreen();
        });

        return btn;
    }

    // 영수증 출력 방식 선택 화면 구성함
    private void openReceiptOptionScreen() {
        JFrame receiptFrame = new JFrame("출력 방식 선택");
        receiptFrame.setSize(600, 800);
        receiptFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        receiptFrame.setLayout(new BorderLayout());
        receiptFrame.getContentPane().setBackground(new Color(235, 244, 253));

        // 상단 감사 문구 설정함
        JLabel thanksLabel = new JLabel("주문해 주셔서 감사합니다!", SwingConstants.CENTER);
        thanksLabel.setFont(new Font("맑은 고딕", Font.BOLD, 26));
        thanksLabel.setForeground(new Color(60, 60, 80));
        receiptFrame.add(thanksLabel, BorderLayout.NORTH);

        // 중앙 안내 및 이미지 구성함
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(new Color(235, 244, 253));

        // 눈송이 이미지 삽입함
        URL imgURL = getClass().getResource("/model/Images/sookmyung_noonsong.png");
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image scaled = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            center.add(Box.createRigidArea(new Dimension(0, 20)));
            JLabel iconLabel = new JLabel(new ImageIcon(scaled));
            iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            center.add(iconLabel);
        }

        // 출력 방식 안내 문구
        JLabel msg = new JLabel("출력 방식을 선택하세요", SwingConstants.CENTER);
        msg.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        msg.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(Box.createRigidArea(new Dimension(0, 20)));
        center.add(msg);
        receiptFrame.add(center, BorderLayout.CENTER);

        // 하단 버튼 영역 설정함
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 40));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        btnPanel.setBackground(new Color(242, 242, 246));

        // 두 개의 버튼 구성함
        JButton bothBtn = new JButton("영수증 출력");
        JButton orderOnlyBtn = new JButton("주문번호만 인쇄");

        for (JButton b : new JButton[]{bothBtn, orderOnlyBtn}) {
            b.setFont(new Font("맑은 고딕", Font.BOLD, 20));
            b.setPreferredSize(new Dimension(200, 60));
            b.setBackground(new Color(184, 216, 249));
            b.setForeground(Color.BLACK);
        }

        // 각 버튼 클릭 시 동작 지정함
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
        dispose(); // 이전 창 닫기
    }

    // 결제 완료 안내창 출력 및 다음 동작 수행함
    private void showConfirmation(String paymentMethod, String receiptOption) {
        int orderNumber = currentOrderNumber;
        currentOrderNumber = (currentOrderNumber + 1) % 201;

        String msg = "주문번호: " + orderNumber + "\n"
                   + paymentMethod + "(으)로 결제되었습니다.\n"
                   + receiptOption + "합니다.";
        JOptionPane.showMessageDialog(null, msg);
        controller.newScreen(); // 다시 초기 화면으로 이동함
    }

    // 하단 텍스트가 흘러가는 마르퀴 패널 정의함
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

            // 텍스트가 왼쪽으로 이동하는 애니메이션 구성함
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


