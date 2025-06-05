import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//StartDialog: 시작하면 띄우는 안내용 다이얼로그
public class StartDialog extends JDialog {
    //생성자: 부모 프레임, 텍스트 색상 전달받음
    public StartDialog(JFrame owner, Color messageColor) {
        super(owner, "안내", true);
        setSize(300, 150);//크기 설정
        setLocationRelativeTo(owner);//위치 설정
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        //메시지 라벨 생성
        JLabel message=new JLabel("메뉴를 선택하세요", SwingConstants.CENTER);
        message.setForeground(messageColor);
        message.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        add(message, BorderLayout.CENTER);
        //확인 버튼 생성
        JButton okBtn=new JButton("확인");
        okBtn.addActionListener(e->dispose());//버튼 클릭하면 다이얼로그 닫음
        //버튼 담을 패널 생성
        JPanel btnPanel=new JPanel();
        btnPanel.setBackground(Color.WHITE);
        btnPanel.add(okBtn);
        add(btnPanel, BorderLayout.SOUTH);
    }
}
