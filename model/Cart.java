// 장바구니 관리 기능
package model;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    // items의 각 원소 = 항목 1개 (cartItem item)
    private List<CartItem> items = new ArrayList<>();

    // 장바구니에 항목 추가
    public void addItem(CartItem item) { items.add(item); }

    // 장바구니에서 항목 삭제
    public void deleteItem(CartItem item) {
        items.remove(item);
    }

    // 장바구니에서 항목 get
    public List<CartItem> getItems() { return items; }

    // 항목 객체의 수량 증가
    public void increaseQuantity(CartItem item) { item.quantity = item.getQuantity() + 1; }

    // 항목 객체의 수량 감소
    public void decreaseQuantity(CartItem item) { item.quantity = item.getQuantity() - 1; }

    public boolean isEmpty() { return items.isEmpty(); } // 장바구니 비어있으면 결제하기 안 넘어가게 함

    public int getTotalCost() { // 총 금액
        int totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getItemPrice();
        }
        return totalPrice;
    }

    public int getTotalQuantity() { // 총 금액
        int totalQtt = 0;
        for (CartItem item : items) {
            totalQtt += item.getQuantity();
        }
        return totalQtt;
    }

    public void addOrUpdateItem(CartItem newItem) {
        for (CartItem item : items) {
            if (item.equals(newItem)) {
                item.setQuantity(item.getQuantity() + newItem.getQuantity());
                return;
            }
        }
        items.add(newItem);
    }


}
