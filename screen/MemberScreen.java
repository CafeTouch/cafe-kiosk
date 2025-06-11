package screen;

import controller.AppController;
import model.Customer;
import model.CustomerManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemberScreen extends JFrame {
    private JTextField phoneField;
    private AppController controller;

    public MemberScreen(AppController controller) {
        this.controller = controller;

        setTitle("전화번호 입력");
        setSize(600, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        JLabel label = new JLabel("전화번호를 입력하세요", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(label, BorderLayout.NORTH);

        phoneField = new JTextField();
        phoneField.setFont(new Font("SansSerif", Font.PLAIN, 30));
        phoneField.setHorizontalAlignment(JTextField.CENTER);
        add(phoneField, BorderLayout.CENTER);

        JButton confirmBtn = new JButton("확인");
        confirmBtn.setFont(new Font("SansSerif", Font.BOLD, 26));
        confirmBtn.setBackground(new Color(0, 102, 204));
        confirmBtn.setForeground(Color.WHITE);

        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phone = phoneField.getText().trim();
                if (phone.length() < 4) {
                    JOptionPane.showMessageDialog(MemberScreen.this, "전화번호를 정확히 입력하세요.");
                    return;
                }

                // 전화번호 재확인 팝업
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "입력하신 번호는\n" + phone + "\n맞습니까?",
                        "전화번호 확인",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm != JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(MemberScreen.this, "전화번호를 다시 입력해주세요.");
                    return;
                }

                Customer customer = CustomerManager.getOrCreate(phone);

                if (customer.getCouponCount() > 0) {
                    int result = JOptionPane.showConfirmDialog(MemberScreen.this,
                            "쿠폰이 있습니다. 사용하시겠습니까?", "쿠폰 사용", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(MemberScreen.this,
                                "<html><center>쿠폰 사용 완료<br><font color='red'>직원에게 문의해주세요.</font></center></html>");
                        customer.useCoupon();
                    }
                } else {
                    customer.addStamp();
                    JOptionPane.showMessageDialog(MemberScreen.this,
                            "<html><center>스탬프 1개 적립 완료<br>현재 스탬프 수: " + customer.getStampCount() + "</center></html>");
                    if (customer.getCouponCount() > 0) {
                        JOptionPane.showMessageDialog(MemberScreen.this,
                                "<html><center>🎉 쿠폰이 발급되었습니다!<br><font color='red'>직원에게 문의해주세요.</font></center></html>");
                    }
                }

                //적립사용 내역 저장
                CustomerManager.saveReadable();

                controller.showPaymentScreen();
                dispose();
            }
        });

        add(confirmBtn, BorderLayout.SOUTH);
        setVisible(true);
    }
}
