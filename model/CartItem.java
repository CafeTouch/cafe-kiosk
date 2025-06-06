// 단일 항목 관리
package model;

public class CartItem {
    private String name; // 메뉴 이름
    private int price; // 단일 메뉴 가격
    private String size; // 사이즈
    private int extraShot; // 0 또는 1
    private int disposables; // 0 또는 1
    public int quantity;

    public CartItem(String name, int price, String size, int extraShot, int disposables, int quantity) {
        this.name = name;
        this.price = price;
        this.size = size;
        this.extraShot = extraShot;
        this.disposables = disposables;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    } // 메뉴명

    public int getPrice() {
        return price;
    } // 단일 가격

    public String getSize() {
        return size;
    } // 사이즈: Small / Large

    public int getDisposables() { return disposables; } // YES = 1 / No = 0

    public int getExtraShot() { return extraShot; } // YES = 1 / No = 0

    public int getItemPrice() { return (price + extraShot * 500) * quantity; } // 단일 item 가격 계산

    public int getQuantity() { return quantity; }

}
