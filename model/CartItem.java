// [1] 단일 item 관리
package model;
import java.util.Objects;

// 객체 hot, sweetener 추가했음
public class CartItem {
    private String name; // 메뉴 이름
    private int price; // 단일 메뉴 가격
    private int hot;
    private String size; // 사이즈
    private int extraShot; // 0 또는 1
    private int sweetener;
    private int disposables; // 0 또는 1
    public int quantity;
    public CartItem() {}

    public CartItem(String name, int price, int hot, String size, int extraShot, int sweetener, int disposables, int quantity) {
        this.name = name;
        this.price = price;
        this.hot = hot; // hot이면 1, ice면 0
        this.size = size; // small or large
        this.extraShot = extraShot; // 1이면 추가, 0이면 안 함
        this.sweetener = sweetener; // ==
        this.disposables = disposables;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    } // 메뉴명

    public int getPrice() {
        return price;
    } // 단일 가격

    public int getHot() { return hot; }

    public String getSize() {
        return size;
    } // 사이즈: Small / Large

    public int getDisposables() { return disposables; } // YES = 1 / No = 0

    public int getExtraShot() { return extraShot; } // YES = 1 / No = 0

    public int getSweetener() { return sweetener; }

    public int getItemPrice() { return (price + extraShot * 500 + sweetener * 500) * quantity; } // 단일 item 가격 계산

    public int getQuantity() { return quantity; }

    // factory에서 사용

    public void setName(String name) { this.name = name; }

    public void setPrice(int price) { this.price = price; }

    public void setHot(int hot) { this.hot = hot; }

    public void setSize(String size) { this.size = size; }

    public void setExtraShot(int extraShot) { this.extraShot = extraShot; }

    public void setSweetener(int sweetener) { this.sweetener = sweetener; }

    public void setDisposables(int disposables) { this.disposables = disposables; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartItem other = (CartItem) o;

        return Objects.equals(name, other.name) &&
                Objects.equals(size, other.size) &&
                hot == other.hot &&
                extraShot == other.extraShot &&
                sweetener == other.sweetener &&
                disposables == other.disposables;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size, hot, extraShot, sweetener, disposables);
    }

}
