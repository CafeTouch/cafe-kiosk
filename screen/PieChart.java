package screen;

import javax.swing.*;
import java.awt.*;

public class PieChart extends JFrame {

    // 프레임 설정 및 패널 추가
    public PieChart() {
        setTitle("판매 차트"); // 윈도우 제목 설정
        setSize(400, 300);     // 윈도우 크기 설정
        setLocationRelativeTo(null); // 화면 중앙에 위치
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 닫기 버튼 클릭 시 창만 종료

        // 차트를 프레임에 추가
        ChartPanel panel = new ChartPanel();
        setContentPane(panel);

        setVisible(true);
    }

    // 음료 정보 저장 클래스
    class Drink {
        String name;   // 음료 이름
        int count;     // 판매 수량
        Color color;   // 파이차트에서 사용되는 색상

        // 음료 이름, 수량, 색상 초기화
        public Drink(String name, int count, Color color) {
            this.name = name;
            this.count = count;
            this.color = color;
        }
    }

    // 음료 데이터 배열
    private Drink[] drinks = {
            new Drink("아메리카노", 30, new Color(99, 177, 255)),
            new Drink("카푸치노", 10, new Color(184, 79, 200)),
            new Drink("카페라떼", 15, new Color(0, 60, 133)),
            new Drink("카페모카", 5, new Color(59, 23, 103)),
            new Drink("녹차 라떼", 7, new Color(106, 152, 57)),
            new Drink("요거트 스무디", 3, new Color(250, 244, 173)),
            new Drink("미숫가루", 2, new Color(140, 107, 66)),
            new Drink("아이스티", 8, new Color(255, 255, 255)),
    };

    private class ChartPanel extends JPanel {

        // 음료 각도를 저장하는 배열
        double[] angles = new double[drinks.length];

        // 차트 각도 계산
        public ChartPanel() {
            newAngle(); // 각도 계산 함수 호출
        }

        // 음료 개수 이용해서 각 음료에 대한 비율을 구함
        public void newAngle() {
            int total = 0;

            // total 구하는 for문(total은 판매된 모든 음료의 개수)
            for (Drink d : drinks) {
                total += d.count;
            }

            // 각 음료의 비율을 전체 비율과 대비해서 각도 설정
            for (int i = 0; i < drinks.length; i++) {
                double ratio = (double) drinks[i].count / total; // 각도 구함
                angles[i] = ratio * 360; // 각도*360으로 비율 구함
            }
        }

        // 판넬에 차트 그리기
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // 배경 지우기

            int startAngle = 0; // 차트 시작 각도(0으로 시작해서 점점 더함)
            int pieX = 30;      // 파이차트 X 위치
            int pieY = 30;      // 파이차트 Y 위치
            int pieSize = 200;  // 파이차트 지름

            //for문으로 배열의 음료 정보로 파이차트 그림
            for (int i = 0; i < drinks.length; i++) {
                g.setColor(drinks[i].color);
                g.fillArc(pieX, pieY, pieSize, pieSize, startAngle, (int) angles[i]);
                startAngle += angles[i]; // 다음 부채꼴 시작 각도
                // -> (startAngle+angles[0]+...+angles[i])가 배열[i+1]의 startAngle이 됨
            }

            // 차트 옆에 음료 이름과 색상(이하 범례) 표시
            int informX = pieX + pieSize + 20; // 범례의 x 위치(파이차트 +20에 작성)
            int informY = pieY;                // 범례의 y 시작 위치(범례가 수직으로 제공되므로 시작위치)
            int boxSize = 12;                 // 색상 박스 크기
            int space = 18;                  // 각 범례 사이 간격

            g.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

            // for문으로 음료 배열 안의 이름을 범례로 출력
            for (Drink drink : drinks) {
                // 음료 색상을 범례로 출력
                g.setColor(drink.color);
                g.fillRect(informX, informY, boxSize, boxSize);

                // 음료 이름을 범례로 출력
                g.setColor(Color.BLACK);
                g.drawString(drink.name, informX + boxSize + 5, informY + boxSize - 2);

                // 다음 줄로 이동해서 다음 배열 음료의 범례 출력
                informY += space;
            }
        }
    }
}
