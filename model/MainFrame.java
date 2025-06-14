package model;

import javax.swing.*;//GUI 위한 라이브러리
import java.awt.*;//Swing보다 더 기본적인 GUI(color, 폰트, 크기 등)
import controller.AppController;
import model.Cart;

//MainFrame 클래스는 메인 GUI 프레임 생성
public class MainFrame extends JFrame {
    //menuPanel: 메뉴 버튼들이 들어갈 중앙 패널, optionPanel: 옵션 선택 결과 보일 우측 패널.
    private JPanel menuPanel, optionPanel;
    private JLabel selectedLabel;//하단에 메뉴 선택 결과 출력
    private Cart cart;
    private CartPanel cartPanel;
    private AppController controller;

    public MainFrame(AppController controller) {//JFrame으로 창 만들기
        this.controller = controller;
        // 자꾸 빈 카트가 출력되길래 콘트롤러에서 getCart만들고 그것만 쓰는걸로 수정
        this.cart = controller.getCart();

        setTitle("카페 키오스크");
        setSize(600, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        //카테고리는 COFFEE, NON COFFEE 두개, 버튼 누르면 showMenus로 해당 카테고리 메뉴 표시
        JPanel categoryPanel = new JPanel();
        categoryPanel.setBackground(new Color(200, 220, 255));//173,216,230
        categoryPanel.setLayout(new GridLayout(2, 1, 10, 10));
        //카테고리 버튼 만들기
        JButton coffeeBtn = new JButton("COFFEE");
        JButton nonCoffeeBtn = new JButton("NON-COFFEE");

        coffeeBtn.setForeground(Color.WHITE);
        nonCoffeeBtn.setForeground(Color.WHITE);


        Font btnFont = new Font("맑은 고딕", Font.BOLD, 18);
        Color btnColor = new Color(0, 102, 204);//65,105,225
        Color borderColor = new Color(242, 242, 246);//70,130,180
        for (JButton btn : new JButton[]{coffeeBtn, nonCoffeeBtn}) {
            btn.setBackground(btnColor);
            btn.setFont(btnFont);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createLineBorder(borderColor, 2));
            btn.setPreferredSize(new Dimension(150, 60));//120, 40
        }
        //버튼 누르면 어떤 동작할지 지정
        coffeeBtn.addActionListener(e -> showMenus(Category.COFFEE));
        nonCoffeeBtn.addActionListener(e -> showMenus(Category.NON_COFFEE));

        categoryPanel.add(coffeeBtn);
        categoryPanel.add(nonCoffeeBtn);
        add(categoryPanel, BorderLayout.WEST);//North

        //메뉴 버튼 패널
        menuPanel = new MenuStyle(new GridLayout(2, 4, 10, 10),
                new Color(200, 220, 255), new Color(0, 102, 204));
        //창 중앙에 배치
        add(menuPanel, BorderLayout.CENTER);

        //메뉴 선택했을때 옵션 패널
        optionPanel = new MenuStyle(null,
                new Color(200, 220, 255), new Color(0, 102, 204));

        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));//세로 나열
        add(optionPanel, BorderLayout.EAST);//오른쪽
        //현재 선택된 메뉴 이르므 상태 표시//하단 상태 표시창, 주문 완료 후 메뉴명+옵션정보 표시됨
        selectedLabel = new JLabel("메뉴를 선택하세요");
        selectedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        selectedLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        //add(selectedLabel, BorderLayout.SOUTH);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        selectedLabel = new JLabel("메뉴를 선택하세요.");
        selectedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        selectedLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        bottomPanel.add(selectedLabel);

        // 장바구니
        //cart = new Cart();
        cartPanel = new CartPanel(controller, cart);
        cartPanel.setPreferredSize(new Dimension(600, 200));
        bottomPanel.add(cartPanel);

        add(bottomPanel, BorderLayout.SOUTH);


        setVisible(true);
        Color messageColor = new Color(0, 102, 204);
        //시작하면 나타나는 팝업 창
        StartDialog startDialog = new StartDialog(this, messageColor);
        setLocationRelativeTo(null);
        startDialog.setVisible(true);
        showMenus(Category.COFFEE);//커피 메뉴부터 보여주기
    }

    //카테고리 누르면 해당 카테고리에 맞는 메뉴 표시//메뉴 선택시 동작
    private void showMenus(Category category) {
        //기존 상태 모두 초기화 //각 메뉴는 MenuItemPanel로 생성되어 menuPanel에 추가됨
        menuPanel.removeAll();//기존 메뉴 버튼 제거
        optionPanel.removeAll();//옵션 영역도 초기화
        selectedLabel.setText("메뉴를 선택하세요");//상태 초기화
        //MenuBoard~에서 해당 카테고리의 메뉴 리스트 받음
        for (MenuItem item : MenuBoard.getMenuItems(category)) {
            MenuItemPanel panel = new MenuItemPanel(item, () -> showOptions(item));
            menuPanel.add(panel);


        }
        menuPanel.revalidate();
        menuPanel.repaint();
    }

    public void refresh() {
        removeAll();
        revalidate();
        repaint();
    }

    //각 메뉴 버튼에 showOptions(item)이 연결되어있음
    private void showOptions(MenuItem item) {
        //선택된 메뉴에 대한 옵션 설정할 수 있는 JDialog(팝업) 띄움
        //옵션 팝업 창 생성, 위치는 프레임 중앙
        JDialog optionDialog = new JDialog(this, "옵션 선택" + item.getName(), true);
        optionDialog.setSize(600, 800);//400, 650
        optionDialog.setLayout(new BoxLayout(optionDialog.getContentPane(), BoxLayout.Y_AXIS));
        optionDialog.setLocationRelativeTo(this);
        optionDialog.getContentPane().setBackground(new Color(200, 220, 255));
        //메뉴 이름 라벨 표시 , 현재 선택된 메뉴 이름 큰 글씨로 보여줌
        JLabel nameLabel = new JLabel("메뉴: " + item.getName());
        nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setBackground(new Color(200, 220, 255));
        nameLabel.setOpaque(true);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionDialog.add(nameLabel);
        optionDialog.add(Box.createVerticalStrut(10));//아래 여백 10픽셀 넣음
        //맛 선택 영역 (hot, ice)
        JPanel tastePanel = new JPanel();
        tastePanel.setBackground(Color.WHITE);
        tastePanel.setBorder(BorderFactory.createTitledBorder("맛 선택"));
        ImageIcon hotIcon = new ImageIcon(getClass().getResource("/model/Images/hot.jpg"));
        ImageIcon iceIcon = new ImageIcon(getClass().getResource("/model/Images/ice.jpg"));
        //이미지 크기 조절
        Image scaledHot = hotIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        Image scaledIce = iceIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedHotIcon = new ImageIcon(scaledHot);
        ImageIcon resizedIceIcon = new ImageIcon(scaledIce);
        //라디오 버튼 생성
        JRadioButton hotBtn = new JRadioButton("Hot", resizedHotIcon);
        JRadioButton iceBtn = new JRadioButton("Ice", resizedIceIcon);

        hotBtn.setHorizontalTextPosition(SwingConstants.RIGHT);//텍스트는 오른쪽에 표시
        iceBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
        hotBtn.setIconTextGap(10);//텍스트랑 아이콘 간격
        iceBtn.setIconTextGap(10);

// 기본 배경색 지정
        //선택 시 배경색 강조, 선택 여부 따라 배경색 바꾸기
        Color selectedColor = new Color(200, 220, 255);  // 선택됨
        Color defaultColor = new Color(0, 102, 204);   // 기본

        hotBtn.setBackground(defaultColor);
        iceBtn.setBackground(defaultColor);

        hotBtn.setForeground(Color.WHITE);
        iceBtn.setForeground(Color.WHITE);

// 선택 시 배경색 강조
        hotBtn.addActionListener(e -> {
            hotBtn.setBackground(selectedColor);
            iceBtn.setBackground(defaultColor);
        });
        iceBtn.addActionListener(e -> {
            iceBtn.setBackground(selectedColor);
            hotBtn.setBackground(defaultColor);
        });

// 그룹으로 묶기 //동시에 하나만 선택 가능하도록
        ButtonGroup tasteGroup = new ButtonGroup();
        tasteGroup.add(hotBtn);
        tasteGroup.add(iceBtn);

// 패널에 추가
        tastePanel.add(hotBtn);
        tastePanel.add(iceBtn);
        optionDialog.add(tastePanel);


        //사이즈 선택 영역(small, big)
        JPanel sizePanel = new JPanel();
        sizePanel.setBackground(Color.WHITE);
        sizePanel.setBorder(BorderFactory.createTitledBorder("사이즈 선택"));
        ImageIcon smallRawIcon = new ImageIcon(getClass().getResource("/model/Images/small.jpg"));
        Image smallImg = smallRawIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon smallIcon = new ImageIcon(smallImg);

        ImageIcon bigRawIcon = new ImageIcon(getClass().getResource("/model/Images/big.jpg"));
        Image bigImg = bigRawIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon bigIcon = new ImageIcon(bigImg);

// 버튼 생성
        JRadioButton smallBtn = new JRadioButton("Small", smallIcon);
        smallBtn.setBackground(defaultColor);

        JRadioButton bigBtn = new JRadioButton("Large", bigIcon);
        bigBtn.setBackground(defaultColor);

        smallBtn.setForeground(Color.WHITE);
        bigBtn.setForeground(Color.WHITE);


// 텍스트 위치 및 간격 설정
        smallBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
        bigBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
        smallBtn.setIconTextGap(10);
        bigBtn.setIconTextGap(10);

        ButtonGroup sizeGroup = new ButtonGroup();
        sizeGroup.add(smallBtn);
        sizeGroup.add(bigBtn);


        smallBtn.addActionListener(e -> {
            smallBtn.setBackground(selectedColor);
            bigBtn.setBackground(defaultColor);
        });
        bigBtn.addActionListener(e -> {
            bigBtn.setBackground(selectedColor);
            smallBtn.setBackground(defaultColor);
        });

        sizePanel.add(smallBtn);
        sizePanel.add(bigBtn);
        optionDialog.add(sizePanel);

        //일회용품 선택 영역
        JPanel dispoPanel = new JPanel();
        dispoPanel.setBackground(Color.WHITE);
        dispoPanel.setBorder(BorderFactory.createTitledBorder("일회용품 사용"));


        ImageIcon yesRawIcon = new ImageIcon(getClass().getResource("/model/Images/yes.jpg"));
        Image yesImg = yesRawIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon yesIcon = new ImageIcon(yesImg);

        ImageIcon noRawIcon = new ImageIcon(getClass().getResource("/model/Images/no.jpg"));
        Image noImg = noRawIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon noIcon = new ImageIcon(noImg);

        JRadioButton yesDispo = new JRadioButton("Yes", yesIcon);
        yesDispo.setBackground(new Color(0, 102, 204));
        yesDispo.setHorizontalTextPosition(SwingConstants.RIGHT);
        yesDispo.setIconTextGap(10);

        JRadioButton noDispo = new JRadioButton("No", noIcon);
        noDispo.setBackground(new Color(0, 102, 204));
        noDispo.setHorizontalTextPosition(SwingConstants.RIGHT);
        noDispo.setIconTextGap(10);

        yesDispo.setForeground(Color.WHITE);
        noDispo.setForeground(Color.WHITE);


        ButtonGroup dispoGroup = new ButtonGroup();
        dispoGroup.add(yesDispo);
        dispoGroup.add(noDispo);

        yesDispo.addActionListener(e -> {
            yesDispo.setBackground(selectedColor);
            noDispo.setBackground(defaultColor);
        });
        noDispo.addActionListener(e -> {
            noDispo.setBackground(selectedColor);
            yesDispo.setBackground(defaultColor);
        });

        dispoPanel.add(yesDispo);
        dispoPanel.add(noDispo);
        optionDialog.add(dispoPanel);
        //추가 옵션 영역(샷 추가, 시럽 추가)
        JPanel extraPanel = new JPanel();
        extraPanel.setBackground(Color.WHITE);
        extraPanel.setBorder(BorderFactory.createTitledBorder("옵션 추가(+500원)"));

        ImageIcon shotRawIcon = new ImageIcon(getClass().getResource("/model/Images/shot.jpg"));
        Image shotImg = shotRawIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon shotIcon = new ImageIcon(shotImg);

        ImageIcon syrupRawIcon = new ImageIcon(getClass().getResource("/model/Images/syrup.jpg"));
        Image syrupImg = syrupRawIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon syrupIcon = new ImageIcon(syrupImg);
        //중복 선택 가능하도록 ChoeckBox 사용
        JCheckBox shotBox = new JCheckBox("샷 추가", shotIcon);
        shotBox.setBackground(new Color(0, 102, 204));
        shotBox.setHorizontalTextPosition(SwingConstants.RIGHT);
        shotBox.setIconTextGap(10);

        JCheckBox syrupBox = new JCheckBox("시럽 추가", syrupIcon);
        syrupBox.setBackground(new Color(0, 102, 204));
        syrupBox.setHorizontalTextPosition(SwingConstants.RIGHT);
        syrupBox.setIconTextGap(10);

        shotBox.setForeground(Color.WHITE);
        syrupBox.setForeground(Color.WHITE);


        shotBox.addActionListener(e -> {
            shotBox.setBackground(shotBox.isSelected() ? selectedColor : defaultColor);
        });
        syrupBox.addActionListener(e -> {
            syrupBox.setBackground(syrupBox.isSelected() ? selectedColor : defaultColor);
        });

        extraPanel.add(shotBox);
        extraPanel.add(syrupBox);
        optionDialog.add(extraPanel);
        //옵션 선택 하라고 알려주는 부분
        JLabel optionLabel = new JLabel("옵션을 선택하세요");
        optionLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        optionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        optionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JSpinner quantitySpinner=new JSpinner(new SpinnerNumberModel(1,1, 99, 1));
        JPanel quantityPanel=new JPanel();
        quantityPanel.setBackground(Color.WHITE);
        quantityPanel.setBorder(BorderFactory.createTitledBorder("수량 선택"));
        quantityPanel.add(quantitySpinner);
        optionDialog.add(quantityPanel);

        //주문 확인 버튼
        JButton confirmBtn = new JButton("주문 확인");
        confirmBtn.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        confirmBtn.setBackground(Color.WHITE);
        confirmBtn.setForeground(Color.BLACK);
        confirmBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        //클릭 시 동작
        confirmBtn.addActionListener(e -> {
            //필수 항목 체크
            if (!hotBtn.isSelected() && !iceBtn.isSelected() ||
                    !smallBtn.isSelected() && !bigBtn.isSelected() ||
                    !yesDispo.isSelected() && !noDispo.isSelected()) {
                JOptionPane.showMessageDialog(optionDialog, "모든 필수 항목을 선택해주세요.", "알림", JOptionPane.WARNING_MESSAGE);
                return;
            }
            //JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 0, 99, 1));

            String size = smallBtn.isSelected() ? "Small" : "Large";
            int hot = hotBtn.isSelected() ? 1 : 0;
            int extraShot = shotBox.isSelected() ? 1 : 0;
            int sweetener = syrupBox.isSelected() ? 1 : 0;
            int disposables = yesDispo.isSelected() ? 1 : 0;
            int quantity = (int) quantitySpinner.getValue();

            CartItem cartItem = CartItemFactory.create(
                    item, size, hot, extraShot, sweetener, disposables, quantity
            );

            cart.addOrUpdateItem(cartItem);  // 장바구니에 넣기
            cartPanel.refresh();             // UI 갱신
            optionDialog.dispose();          // 팝업 닫기

            //선택된 옵션들 확인하기
           /* String taste=hotBtn.isSelected() ?"Hot": "Ice";
            String size=smallBtn.isSelected() ?"Small" :"Big";
            String dispo=yesDispo.isSelected() ?"일회용": "매장용";
            StringBuilder extra=new StringBuilder();
            if (shotBox.isSelected()) extra.append("샷 추가");
            if (syrupBox.isSelected()) extra.append("시럽 추가");
            if(extra.length()==0) extra.append("앖음");
            //상태창에 최종 요약 표시
            String summary=item.getName()+" /맛: "+taste+
                    " /사이즈: "+size+ " /컵: "+dispo+ " /옵션: "+extra.toString().trim();
            selectedLabel.setText(summary); */

            //옵션 창 닫기
            optionDialog.dispose();
        });


        //dialog에 모든 요소 추가 후 팝업 띄우기
        optionDialog.add(optionLabel);
        optionDialog.add(nameLabel);
        optionDialog.add(Box.createVerticalStrut(10));

        optionDialog.add(tastePanel);
        optionDialog.add(sizePanel);
        optionDialog.add(dispoPanel);
        optionDialog.add(extraPanel);
        optionDialog.add(Box.createVerticalStrut(10));
        optionDialog.add(confirmBtn);
        //옵션 선택하라고 강조하기 위해 옵션 선택 문구 한 번 깜빡이는 thread 기능
        new Thread(() -> {
            try {

                SwingUtilities.invokeLater(() -> optionLabel.setVisible(false));
                Thread.sleep(300);
                SwingUtilities.invokeLater(() -> optionLabel.setVisible(true));
                Thread.sleep(300);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        optionDialog.setVisible(true);
    }


    
}

