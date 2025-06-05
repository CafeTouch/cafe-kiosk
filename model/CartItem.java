// 1. data structure
package model;

public class CartItem {
    private String name; // 메뉴 이름
    private int price; // 단일 메뉴 가격
    private String optionSize; // 사이즈
    private int optionShot; // 0 또는 1
    private int optionDisposable; // 0 또는 1
    private int quantity;

    public CartItem(String name, int price, String optionSize, int optionShot, int optionDisposable, int quantity) {
        this.name = name;
        this.price = price;
        this.optionSize = optionSize;
        this.optionShot = optionShot;
        this.optionDisposable = optionDisposable;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getOptionSize() {
        return optionSize;
    }

    public int getOptionDisposable() {
        return optionDisposable;
    }

    public void setOptionDisposable(int optionDisposable) {
        if (optionDisposable == 1) {
            this.optionDisposable = 1;
        }
        else {this.optionDisposable = 0;}
    }

    public int getOptionShot() {
        return optionShot;
    }

    public int getTotalPrice() {
        return (price + optionShot * 500) * quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int q) {
        this.quantity = q;
    }

}
