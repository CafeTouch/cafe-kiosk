import javax.swing.*;
import java.awt.*;

public class FirstScreen {
	// ����ũž ũ�� ���
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	int screenWidth = screenSize.width;
	int screenHeight = screenSize.height;

	FirstScreen(String msg) {
		// ������ ����
		JFrame f = new JFrame(msg);
		
		// ������ ����� ����ũž ũ��� ����, ȭ�� ����� ��ġ
		f.setSize(screenWidth, screenHeight);
		f.setLocationRelativeTo(null);
		f.setLayout(null); // ��ġ ���� ���� �� �ʿ�
		
		//����
		Color backGroundColor = new Color(235,244,253);
		f.getContentPane().setBackground(backGroundColor);
		
		
		// ��ư----------
		
		// ��ư ������
		int BW = screenWidth/2;
		int BH = screenHeight/6;
		
		// ��ư�� ������ �̹���
		ImageIcon hereIcon = new ImageIcon("C:/Users/riwon/Downloads/noun-store-5847545.png");
		ImageIcon togoIcon = new ImageIcon("C:/Users/riwon/Downloads/noun-coffee-6200565.png");
		Image newHere = hereIcon.getImage().getScaledInstance(BH,BH, Image.SCALE_FAST);
		Image newTogo = togoIcon.getImage().getScaledInstance(BH,BH, Image.SCALE_FAST);
		ImageIcon resizedHere = new ImageIcon(newHere);
		ImageIcon resizedTogo = new ImageIcon(newTogo);
		
		JButton here = new JButton("For here", resizedHere);
		JButton togo = new JButton("To go", resizedTogo);
		
		//��ư Ŀ���͸���¡
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
		
		// ������ �̹��� ����
		ImageIcon NSIcon = new ImageIcon("C:/Users/riwon/Downloads/sookmyung_noonsong.png");
		Image newNS = NSIcon.getImage().getScaledInstance(BH,BH, Image.SCALE_FAST);
		ImageIcon resizedNS = new ImageIcon(newNS);
		JLabel lb = new JLabel(resizedNS);
		lb.setBounds(screenWidth/4,screenHeight/6, BH, BH);
		f.add(lb);
		
		// ����ī�� �۾� ����
		JLabel cafe = new JLabel("Noonsong CAFE");
		cafe.setBounds(screenWidth/2 - 100, screenHeight/6, 500, 100);
		cafe.setFont(new Font("Arial", Font.BOLD, 24));
		f.add(cafe);
		
		// ������Ʈ
		PieChart chart = new PieChart();
		chart.setBounds(screenWidth - 250, 100, 200, 200);
		f.add(chart);
		
		
		f.setVisible(true);
	}
}
