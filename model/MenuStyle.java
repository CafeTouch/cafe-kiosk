package model;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
//커스텀 JPanel 클래스, 둥근 모서리랑 테두리, 배경색 그려주도록
public class MenuStyle extends JPanel {

    private Color backgroundColor;//배경 색상
    private Color borderColor;//테두리 색상
    //생성자: 레이아웃, 배경색, 테두리색 받아 저장
    public MenuStyle(LayoutManager layout, Color backgroundColor, Color borderColor) {
        super(layout);//부모 JPanel에 레이아웃 전달
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        setOpaque(false);//기본 배경 칠하지 않도록 설정
    }
    //이 패널 화면에 그려질 때 자동 호출됨
    @Override
    protected void paintComponent(Graphics g) {
        //기본 그리기 먼저 수행하고 2D 그래픽 객체로 변환해 더 정밀한 그리기 가능
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        //그래픽 부드럽게 렌더링
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //배경 그리기 - 둥근 배경
        g2.setColor(backgroundColor);//배경색 설정
        RoundRectangle2D roundedBox = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 30, 30);
        g2.fill(roundedBox);

        //테두리 그리기
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(2));
        g2.draw(roundedBox);//같은 모양의 사각형 테두리 그리기

        g2.dispose();//그래픽 리소스 해제
    }
    //공통 폰트 설정 메서드
    public static Font getCafeFont(int size) {
        return new Font("맑은 고딕", Font.PLAIN, size);  // 시스템 기본 한글 폰트 중 부드러운 느낌
    }
}
