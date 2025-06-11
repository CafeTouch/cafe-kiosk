package model;

//하나의 매뉴 항목 나타냄(각 메뉴의 정보 담는 역할)
public class NewMenuItem {
    private String name;//메뉴 이름
    private int price;//메뉴 가격
    private Category category;//메뉴 카테고리, enum 사용
    private String imagePath;//메뉴 이미지 파일 경로
    private String size;
    private int extraShot; // 0 또는 1
    private int disposables; // 0 또는 1
    public int quantity;

    //객체 생성 시 이름, 가격 등 받아서 멤버 변수에 저장
    public NewMenuItem(String name, int price, Category category, String imagePath, String size, int extraShot, int disposables, int quantity) {
        this.name=name;
        this.price=price;
        this.category=category;
        this.imagePath=imagePath;
        this.size = size;
        this.extraShot = extraShot;
        this.disposables = disposables;
        this.quantity = quantity;
    }
    //메서드들 - 외부에서 메뉴 정보 읽을 때 사용함
    //이름 표시
    public String getName() { return name; }
    //가격 표시
    public int getPrice() { return price; }
    //커피, 논커피 구분
    public Category getCategory() { return category; }
    //메뉴 이미지 불러올 때
    public String getImagePath() { return imagePath; }

    public String getSize() {
        return size;
    } // 사이즈: Small / Large

    public int getDisposables() { return disposables; } // YES = 1 / No = 0

    public int getExtraShot() { return extraShot; } // YES = 1 / No = 0

    public int getItemPrice() { return (price + extraShot * 500) * quantity; } // 단일 item 가격 계산

    public int getQuantity() { return quantity; }

}