// 장바구니에 담을 항목 구성
package model;
import java.util.Objects;

public class CartItem {
    private String name; // 메뉴 이름
    private int price; // 단일 메뉴 가격
    private int hot; // 온도
    private String size; // 사이즈
    private int extraShot; // 1이면 추가 0이면 추가 X
    private int sweetener; // 1이면 추가 0이면 추가 X
    private int disposables; // 1이면 추가 0이면 추가 X
    public int quantity; // 수량
    public CartItem() {}

    public CartItem(String name, int price, int hot, String size, int extraShot, int sweetener, int disposables, int quantity) {
        this.name = name;
        this.price = price;
        this.hot = hot;
        this.size = size;
        this.extraShot = extraShot;
        this.sweetener = sweetener;
        this.disposables = disposables;
        this.quantity = quantity;
    }

    // getter 설정
    public String getName() { return name; } // 메뉴명

    public int getPrice() { return price; } // 단일 가격

    public int getHot() { return hot; } // 온도: Hot or Iced

    public String getSize() { return size; } // 사이즈: Small or Large

    public int getDisposables() { return disposables; } // 일회용품 추가: YES = 1 / NO = 0

    public int getExtraShot() { return extraShot; } // 샷 추가: YES = 1 / NO = 0

    public int getSweetener() { return sweetener; } // 시럽 추가: YES = 1 / NO = 0

    public int getQuantity() { return quantity; } // 수량

    public int getItemPrice() { return (price + extraShot * 500 + sweetener * 500) * quantity; } // 옵션 선택 후 변경 된 금액


    // setter 설정: factory에서 사용

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
    public String optionSummary() {
        StringBuilder sb = new StringBuilder();
        return "";
    }

}
