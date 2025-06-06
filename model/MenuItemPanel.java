import javax.swing.*;
import java.awt.*;
//java swing으로 메뉴 아이템을 GUI로 나타내는 JPanel //각 패널은 이미지, 이름, 가격 보여주며 클릭 가능
//메뉴 아이템 하나를 표현하는 패널 클래스
public class MenuItemPanel extends JPanel {
    //생성자: MenuItem 객체와 클릭 시 실행할 작업 전달받음
    public MenuItemPanel(MenuItem item, Runnable onClick) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));//컴포넌트 수직 배치(이미지-이름-가격 순)
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(new Color(100, 149, 237)));
        setCursor(new Cursor(Cursor.HAND_CURSOR));//마우스 올리면 커서 모양 손 모양으로 변경

        // 이미지 설정 //이미지 경로받아 ImagdIco 생성
        ImageIcon icon = new ImageIcon("Images/" + item.getImagePath());
        Image scaledImage = icon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);//크기 설정
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        imageLabel.setAlignmentX(CENTER_ALIGNMENT);//라벨에 이미지 추가하고 가운데 정렬

        // 메뉴 이름 라벨 설정
        JLabel nameLabel = new JLabel(item.getName());
        nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        nameLabel.setForeground(new Color(0x003366));
        nameLabel.setAlignmentX(CENTER_ALIGNMENT);

        // 가격 라벨 설정
        JLabel priceLabel = new JLabel(item.getPrice() + "원");
        priceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        priceLabel.setForeground(new Color(0x1E90FF));
        priceLabel.setAlignmentX(CENTER_ALIGNMENT);

        //마우스 클릭 이벤트 등록 //마우스로 패널 클릭하면 onClick.run()실행됨
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                onClick.run();
            }
        });
        //요소들 수직으로 배치하면서 여백 추가
        add(Box.createVerticalStrut(10));
        add(imageLabel);
        add(Box.createVerticalStrut(10));
        add(nameLabel);
        add(priceLabel);
        add(Box.createVerticalStrut(10));
    }
}
