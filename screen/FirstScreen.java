package screen;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import controller.AppController;
import model.*;

public class FirstScreen {
	private AppController controller;

	int screenWidth = 600;
	int screenHeight = 800;

	public FirstScreen(AppController controller) {
		// 프레임 생성
		JFrame f = new JFrame("FirstScreen");
		this.controller = controller;

		// 화면 가운데로 배치
		f.setSize(screenWidth, screenHeight);
		f.setLocationRelativeTo(null);
		f.setLayout(null); // 위치 직접 지정 시 필요
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//색상
		Color backGroundColor = new Color(235,244,253);
		f.getContentPane().setBackground(backGroundColor);
		
		// 버튼----------
		
		// 버튼 사이즈
		int BW = screenWidth/2;
		int BH = screenHeight/6;
		
		// 버튼에 삽입할 이미지
		ImageIcon hereIcon = new ImageIcon(getClass().getResource("/model/Images/noun-store.png"));
		ImageIcon togoIcon = new ImageIcon(getClass().getResource("/model/Images/noun-coffee.png"));
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
		here.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		togo.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		here.setBounds(screenWidth/2-BW/2, (screenHeight/2 - BH*2)*3, 
			BW, BH);
		togo.setBounds(screenWidth/2-BW/2, (screenHeight/2 - BH)*2, 
			BW, BH);
		
		f.add(here);
		f.add(togo);

		// 버튼 클릭시 MenuFrame으로 넘어감
		here.addActionListener(e -> controller.startApp());
		togo.addActionListener(e -> controller.startApp());

		// ------
		// 눈송이 이미지 삽입
		ImageIcon NSIcon = new ImageIcon(getClass().getResource("/model/Images/sookmyung_noonsong.png"));
		Image newNS = NSIcon.getImage().getScaledInstance(BH,screenWidth/4, Image.SCALE_FAST);
		ImageIcon resizedNS = new ImageIcon(newNS);
		JLabel lb = new JLabel(resizedNS);
		lb.setBounds(350,screenHeight/6, BH, screenWidth/4);
		f.add(lb);
		
		// 눈송카페 글씨 삽입
		JLabel cafe = new JLabel("눈송 카페");
		cafe.setBounds(screenWidth/2 - 100, screenHeight/6, 500, 100);
		//cafe.setHorizontalAlignment(JLabel.CENTER);
		cafe.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		f.add(cafe);

		// 파이차트 새 창에서 띄우고 FirstScreen보다 앞에 띄움
		PieChart pieChart = new PieChart();
		pieChart.setAlwaysOnTop(true);
		
		
		f.setVisible(true);
	}

}
