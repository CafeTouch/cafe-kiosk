package screen;

import controller.AppController;
import model.Customer;
import model.CustomerManager;

import javax.swing.*;
import java.awt.*;

public class KeypadScreen extends JFrame {
    private JTextField display;
    private StringBuilder phoneNumber = new StringBuilder();
    private AppController controller;

    public KeypadScreen(AppController controller) {
        this.controller = controller;

        setTitle("전화번호 입력");
        setSize(600, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        JLabel guide = new JLabel("<html><center>전화번호를 입력하세요<br>쿠폰을 적립하거나 사용할 수 있어요</center></html>", SwingConstants.CENTER);
        guide.setFont(new Font("SansSerif", Font.BOLD, 30));
        guide.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(guide, BorderLayout.NORTH);

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("SansSerif", Font.BOLD, 40));
        display.setHorizontalAlignment(JTextField.CENTER);
        add(display, BorderLayout.CENTER);

        JPanel keypadPanel = new JPanel(new GridLayout(4, 3, 10, 10));
        keypadPanel.setBackground(new Color(200, 220, 255));
        keypadPanel.setPreferredSize(new Dimension(600, 500));
        String[] keys = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "←","정정"};
        for (String key : keys) {
            JButton btn = new JButton(key);
            btn.setFont(new Font("SansSerif", Font.BOLD, 45));
            btn.setPreferredSize(new Dimension(160, 100));
            btn.setBackground(Color.WHITE);
            btn.addActionListener(e -> handleKey(key));
            keypadPanel.add(btn);
        }
        add(keypadPanel, BorderLayout.SOUTH);

        JPanel actionPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        actionPanel.setBackground(Color.WHITE);
        actionPanel.setPreferredSize(new Dimension(200, 220));


        JButton stampBtn = new JButton(" 적립");
        stampBtn.setFont(new Font("SansSerif", Font.BOLD, 20));
        stampBtn.setBackground(new Color(0, 102, 204));
        stampBtn.setForeground(Color.WHITE);
        stampBtn.addActionListener(e -> handleStamp());

        JButton payBtn = new JButton("  결제만 하기");
        payBtn.setFont(new Font("SansSerif", Font.BOLD, 20));
        payBtn.setBackground(new Color(0, 102, 204));
        payBtn.setForeground(Color.WHITE);
        payBtn.addActionListener(e -> {
            controller.showPaymentScreen();
            dispose();
        });

        actionPanel.add(stampBtn);
        actionPanel.add(payBtn);
        add(actionPanel, BorderLayout.EAST);

        setVisible(true);
    }

    private void handleKey(String key) {
        if (key.equals("정정")) {
            phoneNumber.setLength(0);
        } else if (key.equals("←")) {
            if (phoneNumber.length() > 0)
                phoneNumber.setLength(phoneNumber.length() - 1);
        } else {
            phoneNumber.append(key);
        }
        display.setText(phoneNumber.toString());
    }

    private void handleStamp() {
        String phone = phoneNumber.toString();
        if (phone.length() < 4) {
            JOptionPane.showMessageDialog(this, "전화번호를 올바르게 입력하세요.");
            return;
        }
        Customer customer = CustomerManager.getOrCreate(phone);

        if (customer.getCouponCount() > 0) {
            int result = JOptionPane.showConfirmDialog(this, "쿠폰이 있습니다. 사용하시겠습니까?", "쿠폰 사용", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "<html><center>쿠폰 사용 완료<br><font color='red'>직원에게 문의해주세요.</font></center></html>");
                customer.useCoupon();
            }
        } else {
            customer.addStamp();
            JOptionPane.showMessageDialog(this, "<html><center>스탬프 1개 적립 완료<br>현재 스탬프 수: " + customer.getStampCount() + "</center></html>");
            if (customer.getCouponCount() > 0) {
                JOptionPane.showMessageDialog(this, "<html><center> 쿠폰이 발급되었습니다!<br><font color='red'>직원에게 문의해주세요.</font></center></html>");
            }
        }

        controller.showPaymentScreen();  //결제창으로 넘김
        dispose(); //현재 키패드창 닫기
    }
}
