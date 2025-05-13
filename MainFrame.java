import javax.swing.*;//GUI 위한 라이브러리
import java.awt.*;//Swing보다 더 기본적인 GUI(color, 폰트, 크기 등)
import java.awt.event.*;//사용자 입력을 처리할 때 사용됨 //이벤트 리스너는 사용자 동작 감지해 반응

public class MainFrame extends JFrame {
    private JPanel menuPanel, optionPanel;
    private JLabel selectedLabel;

    public MainFrame() {
        setTitle("카페 키오스크");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel categoryPanel=new JPanel();
        JButton coffeeBtn=new JButton("커피");
        JButton nonCoffeeBtn=new JButton("논커피");

        coffeeBtn.addActionListener(e->showMenus(Category.COFFEE));
        nonCoffeeBtn.addActionListener(e->showMenus(Category.NON_COFFEE));

        categoryPanel.add(coffeeBtn);
        categoryPanel.add(nonCoffeeBtn);
        add(categoryPanel, BorderLayout.NORTH);

        menuPanel=new JPanel();
        menuPanel.setLayout(new GridLayout(2, 4, 10, 10));
        add(menuPanel, BorderLayout.CENTER);

        optionPanel=new JPanel();
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
        add(optionPanel, BorderLayout.EAST);

        selectedLabel=new JLabel("메뉴를 선택하세요");
        add(selectedLabel, BorderLayout.SOUTH);

        setVisible(true);
        showMenus(Category.COFFEE);
    }

    private void showMenus(Category category) {
        menuPanel.removeAll();
        optionPanel.removeAll();
        selectedLabel.setText("메뉴를 선택하세요");

        for (MenuItem item: MenuBoard.getMenuItems(category)) {
            JButton btn=new JButton("<html>"+item.getName()+"<br>"+item.getPrice()+"원</html>");
            btn.setBackground(new Color(230, 230, 250));
            //btn.setIcon(new ImageIcon("../images/"+item.getImagePath()));
            ImageIcon originalIcon=new ImageIcon("../images/"+item.getImagePath());
            Image scaledImage=originalIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            //btn.setIcon(new ImageIcon(scaledImage));
            ImageIcon resizedIcon=new ImageIcon(scaledImage);
            btn.setIcon(resizedIcon);
            btn.setVerticalTextPosition(SwingConstants.BOTTOM);
            btn.setHorizontalTextPosition(SwingConstants.CENTER);

            btn.addActionListener(e->showOptions(item));
            menuPanel.add(btn);
        }
        menuPanel.revalidate();
        menuPanel.repaint();
    }
    private void showOptions(MenuItem item) {
       /* optionPanel.removeAll();

        JLabel nameLabel=new JLabel("메뉴: "+item.getName());
        JCheckBox smallBox=new JCheckBox("Small");
        JCheckBox bigBox=new JCheckBox("Big");*/
        JDialog optionDialog=new JDialog(this, "옵션 선택- " +item.getName(), true);
        optionDialog.setSize(300, 250);
        optionDialog.setLayout(new BoxLayout(optionDialog.getContentPane(), BoxLayout.Y_AXIS));
        optionDialog.setLocationRelativeTo(this);

        JLabel nameLabel=new JLabel("메뉴: "+item.getName());
        JCheckBox smallBox=new JCheckBox("Small");
        JCheckBox bigBox=new JCheckBox("Big");

        smallBox.addActionListener(e-> {
            if(smallBox.isSelected()) bigBox.setSelected(false);
        });
        bigBox.addActionListener(e-> {
            if(bigBox.isSelected()) smallBox.setSelected(false);
        });

        JCheckBox shotBox=new JCheckBox("샷 추가");
        JCheckBox dispoBox=new JCheckBox("일회용컵");

        JButton confirmBtn=new JButton("주문 확인");
        confirmBtn.addActionListener(e-> {
            //String size=(String)sizeBox.getSelectedItem();
            String size=smallBox.isSelected() ? "Small" : bigBox.isSelected() ? "Big" : "선택 안 함.";
            boolean shot=shotBox.isSelected();
            boolean dispo=dispoBox.isSelected();
            String summary=item.getName()+"/ 사이즈: "+ size +
                    " / 샷 추가: "+ (shot ?"0" : "X") +
                    " / 컵 종류: "+(dispo ? "일회용" : "매장용");
            selectedLabel.setText(summary);
            optionDialog.dispose();
        });


        optionDialog.add(nameLabel);
        optionDialog.add(Box.createVerticalStrut(10));
        optionDialog.add(smallBox);
        optionDialog.add(bigBox);
        optionDialog.add(shotBox);
        optionDialog.add(dispoBox);
        optionDialog.add(Box.createVerticalStrut(10));
        optionDialog.add(confirmBtn);

        optionDialog.setVisible(true);
    }
    public static void main(String[] args) {
        new MainFrame();
    }
}
