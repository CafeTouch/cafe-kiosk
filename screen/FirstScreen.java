package screen;

import javax.swing.*;
import java.awt.*;

public class FirstScreen {
	// 데스크탑 크기 얻기
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	int screenWidth = screenSize.width;
	int screenHeight = screenSize.height;

	FirstScreen(String msg) {
		// 프레임 생성
		JFrame f = new JFrame(msg);
		
		// 프레임 사이즈를 데스크탑 크기로 설정, 화면 가운데로 배치
		f.setSize(screenWidth, screenHeight);
		f.setLocationRelativeTo(null);
		f.setLayout(null); // 위치 직접 지정 시 필요
		
		//색상
		Color backGroundColor = new Color(235,244,253);
		f.getContentPane().setBackground(backGroundColor);
		
		
		// 버튼----------
		
		// 버튼 사이즈
		int BW = screenWidth/2;
		int BH = screenHeight/6;
		
		// 버튼에 삽입할 이미지
		ImageIcon hereIcon = new ImageIcon("noun-store.png");
		ImageIcon togoIcon = new ImageIcon("noun-coffee.png");
		Image newHere = hereIcon.getImage().getScaledInstance(BH,BH, Image.SCALE_FAST);
		Image newTogo = togoIcon.getImage().getScaledInstance(BH,BH, Image.SCALE_FAST);
		ImageIcon resizedHere = new ImageIcon(newHere);
		ImageIcon resizedTogo = new ImageIcon(newTogo);
		
		JButton here = new JButton("매장 이용", resizedHere);
		JButton togo = new JButton("포장 하기", resizedTogo);
		
		//버튼 커스터마이징
		Color buttonColor = new Color(184,216,249);
		
		here.setBackground(buttonColor);
		togo.setBackground(buttonColor);
		here.setFont(new Font("Arial", Font.BOLD, 20));
		togo.setFont(new Font("Arial", Font.BOLD, 20));
		
		here.setBounds(screenWidth/2-BW/2, (screenHeight/2 - BH*2)*3, 
			BW, BH);
		togo.setBounds(screenWidth/2-BW/2, (screenHeight/2 - BH)*2, 
			BW, BH);
		
		f.add(here);
		f.add(togo);
		
		// 눈송이 이미지 삽입
		ImageIcon NSIcon = new ImageIcon("sookmyung_noonsong.png");
		Image newNS = NSIcon.getImage().getScaledInstance(BH,BH, Image.SCALE_FAST);
		ImageIcon resizedNS = new ImageIcon(newNS);
		JLabel lb = new JLabel(resizedNS);
		lb.setBounds(screenWidth/4,screenHeight/6, BH, BH);
		f.add(lb);
		
		// 눈송카페 글씨 삽입
		JLabel cafe = new JLabel("눈송 카페");
		cafe.setBounds(screenWidth/2 - 100, screenHeight/6, 500, 100);
		cafe.setFont(new Font("Arial", Font.BOLD, 24));
		f.add(cafe);
		
		// 파이차트
		//PieChart chart = new PieChart();
		//chart.setBounds(screenWidth - 250, 100, 200, 200);
		//f.add(chart);
		
		
		f.setVisible(true);
	}
}
