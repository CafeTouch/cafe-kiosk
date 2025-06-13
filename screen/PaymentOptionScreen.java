package screen;

// 스윙 GUI랑 그래픽 관련 패키지 불러옴
import javax.swing.*;
import java.awt.*;

public class PaymentOptionScreen extends JFrame {
    private String selectedPaymentMethod = null; // 어떤 결제수단 골랐는지 저장하는 변수
    private static int currentOrderNumber = 0; // 주문 번호 (0부터 시작해서 200까지만 반복됨)

    public PaymentOptionScreen() {
        // 창 제목, 크기, 닫기 동작 등 기본 세팅
        setTitle("결제 수단 선택");
        setSize(600, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(235, 244, 253)); // 전체 배경 연한 파랑

        // 상단 제목 라벨
        JLabel label = new JLabel("결제 수단을 선택해 주세요", SwingConstants.CENTER);
        label.setFont(new Font("맑은 고딕", Font.BOLD, 36));
        label.setForeground(Color.BLACK);
        add(label, BorderLayout.NORTH);

        // 결제 버튼들이 들어갈 영역
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        buttonPanel.setBackground(new Color(242, 242, 246));

        // 텍스트 + 아이콘 이미지 파일 이름으로 구성된 결제 수단 배열
        String[][] options = {
            {"카드/삼성페이", "creditcard.png"},
            {"카카오페이", "kakaopay.png"},
            {"네이버페이", "npay.png"},
            {"애플페이", "apple_pay.png"},
        };

        // 각 결제 수단에 대해 버튼 만들고 버튼 패널에 추가
        for (String[] option : options) {
            JButton btn = createRoundedButton(option[0], option[1]);
            buttonPanel.add(btn);
        }

        // 가운데 버튼 패널 추가
        add(buttonPanel, BorderLayout.CENTER);

        // 하단에 마르퀴 텍스트 (흘러가는 안내문)
        MarqueePanel marquee = new MarqueePanel("현금 결제는 직원에게 문의해 주세요", 10);
        add(marquee, BorderLayout.SOUTH);

        // 화면 가운데로 정렬 + 보이기
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // 버튼을 둥글게 만들고 이미지랑 텍스트 넣는 메서드
    private JButton createRoundedButton(String text, String iconPath) {
        JButton btn = new JButton(text) {
            // 배경 둥글게 칠하기
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(getBackground());
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
                g2.dispose();
                super.paintComponent(g);
            }

            // 테두리 둥글게 그리기
            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(new Color(100, 130, 180)); // 약간 파란 테두리
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 40, 40);
                g2.dispose();
            }
        };

        // 버튼 스타일 지정
        btn.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        btn.setPreferredSize(new Dimension(180, 130));
        btn.setBackground(new Color(184, 216, 249)); // 밝은 파랑
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setOpaque(false);

        // 아이콘 이미지 삽입
        if (iconPath != null) {
            ImageIcon icon = new ImageIcon("img/" + iconPath); // 이미지 경로
            Image scaledImage = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            btn.setIcon(new ImageIcon(scaledImage));
            btn.setHorizontalTextPosition(SwingConstants.CENTER);
            btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        }

        // 버튼 누르면 결제수단 저장하고 출력화면 열기
        btn.addActionListener(e -> {
            selectedPaymentMethod = text;
            openReceiptOptionScreen();
        });

        return btn;
    }

    // 출력 방식 선택하는 새 창 열기
    private void openReceiptOptionScreen() {
        JFrame receiptFrame = new JFrame("출력 방식 선택");
        receiptFrame.setSize(600, 800);
        receiptFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        receiptFrame.setLayout(new BorderLayout());
        receiptFrame.getContentPane().setBackground(new Color(235, 244, 253));

        // 상단: 감사 인사
        JLabel thanksLabel = new JLabel("주문해 주셔서 감사합니다!", SwingConstants.CENTER);
        thanksLabel.setFont(new Font("맑은 고딕", Font.BOLD, 26));
        thanksLabel.setForeground(new Color(60, 60, 80));
        receiptFrame.add(thanksLabel, BorderLayout.NORTH);

        // 중앙: 눈송이 캐릭터 이미지 + 안내문
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

        // 아래쪽: 두 개 버튼 (영수증 출력 / 주문번호만 인쇄)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 40));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        buttonPanel.setBackground(new Color(242, 242, 246));

        // 영수증 출력 버튼
        JButton bothButton = new JButton("영수증 출력");
        bothButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        bothButton.setPreferredSize(new Dimension(200, 60));
        bothButton.setBackground(new Color(184, 216, 249));
        bothButton.setForeground(Color.BLACK);
        bothButton.addActionListener(e -> {
            showConfirmation(selectedPaymentMethod, "영수증 출력");
            receiptFrame.dispose();
        });

        // 주문번호만 인쇄 버튼
        JButton orderOnlyButton = new JButton("주문번호만 인쇄");
        orderOnlyButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        orderOnlyButton.setPreferredSize(new Dimension(200, 60));
        orderOnlyButton.setBackground(new Color(184, 216, 249));
        orderOnlyButton.setForeground(Color.BLACK);
        orderOnlyButton.addActionListener(e -> {
            showConfirmation(selectedPaymentMethod, "주문번호만 인쇄");
            receiptFrame.dispose();
        });

        // 버튼들 붙이기
        buttonPanel.add(bothButton);
        buttonPanel.add(orderOnlyButton);
        receiptFrame.add(buttonPanel, BorderLayout.SOUTH);

        receiptFrame.setLocationRelativeTo(null);
        receiptFrame.setVisible(true);
        dispose(); // 이전 창은 닫아줌
    }

    // 결제 완료 알림창 띄우고 다시 첫 화면으로 돌아가기
    private void showConfirmation(String paymentMethod, String receiptOption) {
        int orderNumber = currentOrderNumber; // 현재 주문번호 저장
        currentOrderNumber = (currentOrderNumber + 1) % 201; // 다음 주문번호 설정 (201까지만 반복)

        String message = "주문번호: " + orderNumber + "\n" +
                         paymentMethod + "(으)로 결제되었습니다.\n" +
                         receiptOption + "합니다.";
        JOptionPane.showMessageDialog(null, message); // 팝업 띄우기
        new PaymentOptionScreen(); // 다시 첫 화면으로 돌아감
    }

    // 프로그램 실행 시작
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PaymentOptionScreen::new);
    }

    // 하단에 텍스트가 움직이는 마르퀴 패널
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

            // 텍스트를 왼쪽으로 계속 이동시키는 타이머
            timer = new Timer(speed, e -> {
                x -= 2;
                if (x + getFontMetrics(getFont()).stringWidth(text) < 0) {
                    x = getWidth(); // 텍스트가 다 지나가면 다시 오른쪽에서 시작
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
            g.drawString(text, x, 25); // 흘러가는 글씨 그리기
        }
    }
}
