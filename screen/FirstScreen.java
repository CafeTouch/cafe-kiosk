package screen;

import javax.swing.*;
import java.awt.*;
import controller.AppController;

public class FirstScreen {
	// 데스크탑 크기 얻기
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private AppController controller;

	int screenWidth = screenSize.width;
	int screenHeight = screenSize.height;

	public FirstScreen(AppController controller) {
		// 프레임 생성
		JFrame f = new JFrame("FirstScreen");
		this.controller = controller;

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
		ImageIcon hereIcon = new ImageIcon("/model/Images/noun-store.png");
		ImageIcon togoIcon = new ImageIcon("/model/Images/noun-coffee.png");
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
		ImageIcon NSIcon = new ImageIcon("/model/Images/sookmyung_noonsong.png");
		Image newNS = NSIcon.getImage().getScaledInstance(BH,BH, Image.SCALE_FAST);
		ImageIcon resizedNS = new ImageIcon(newNS);
		JLabel lb = new JLabel(resizedNS);
		lb.setBounds(screenWidth/4,screenHeight/6, BH, BH);
		f.add(lb);
		
		// 눈송카페 글씨 삽입
		JLabel cafe = new JLabel("눈송 카페");
		cafe.setBounds(screenWidth/2 - 100, screenHeight/6, 500, 100);
		cafe.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		f.add(cafe);
		
		// 파이차트
		PieChart chart = new PieChart();
		chart.setBounds(screenWidth - 250, 100, 200, 200);
		f.add(chart);
		
		f.setVisible(true);
	}
	// 파이차트 구현 (음료 판매 수는 임의로 지정)
	public class PieChart extends JPanel {
		int ice_americano = 30;
		int hot_americano = 10;
		int ice_latte = 15;
		int hot_latte = 5;
		int green = 7;
		int yug = 3;
		int misu = 2;
		int iced = 8;

		double ia_angle, ha_angle, il_angle, hl_angle, green_angle, yug_angle, misu_angle,iced_angle;

		// 파이차트 비율 계산
		public double getRatio(int numWhole, int numPart){
			double ratio = ((double)numWhole/(double)numPart) * 100;
			return ratio;
		}

		// 파이차트 비율별 각도 계산
		public double getAngle(double ratio){
			double angle = 3.6 * ratio;
			return angle;
		}

		// 각 음료 별 각도
		public void newAngel() {
			int total = ice_americano + hot_americano + ice_latte + hot_latte + green + yug + misu + iced;

			ia_angle = getAngle(getRatio(ice_americano, total));
			ha_angle = getAngle(getRatio(hot_americano, total));
			il_angle = getAngle(getRatio(ice_latte, total));
			hl_angle = getAngle(getRatio(hot_latte, total));
			green_angle = getAngle(getRatio(green, total));
			yug_angle = getAngle(getRatio(yug, total));
			misu_angle = getAngle(getRatio(misu, total));
			iced_angle = getAngle(getRatio(iced, total));
		}

		// 파이차트 생성 메소드
		public void paint(Graphics g) {
			int startAngle = 0;

			g.setColor(new Color(0, 122, 255)); //아이스아메리카노
			g.fillArc(screenHeight/5, screenHeight/5, 200, 200,
					startAngle, (int) ia_angle);
			startAngle += ia_angle;

			g.setColor(new Color(0, 60, 133)); //핫아메리카노
			g.fillArc(screenHeight/5, screenHeight/5, 200, 200,
					startAngle, (int) ha_angle);
			startAngle += ha_angle;

			g.setColor(new Color(0, 31, 71)); //아이스라떼
			g.fillArc(screenHeight/5, screenHeight/5, 200, 200,
					startAngle, (int) il_angle);
			startAngle += il_angle;

			g.setColor(new Color(0, 15, 37)); //핫라떼
			g.fillArc(screenHeight/5, screenHeight/5, 200, 200,
					startAngle, (int) hl_angle);
			startAngle += hl_angle;

			g.setColor(new Color(79, 161, 200)); //녹차라떼
			g.fillArc(screenHeight/5, screenHeight/5, 200, 200,
					startAngle, (int) green_angle);
			startAngle += green_angle;

			g.setColor(new Color(48, 122, 159)); //요거트
			g.fillArc(screenHeight/5, screenHeight/5, 200, 200,
					startAngle, (int) yug_angle);
			startAngle += yug_angle;

			g.setColor(new Color(4, 77, 112)); //미숫가루
			g.fillArc(screenHeight/5, screenHeight/5, 200, 200,
					startAngle, (int) misu_angle);
			startAngle += (int) misu_angle;

			g.setColor(new Color(108, 140, 158)); //미숫가루
			g.fillArc(screenHeight/5, screenHeight/5, 200, 200,
					startAngle, (int) iced_angle);
		}
	}
}

