// [2] cart 관리 기능
package model;
import java.util.*;

public class Cart {
    CartItem item;
    List<CartItem> items = new ArrayList<CartItem>(); // 원소 = 장바구니 항목

    public void addItem(CartItem item) { items.add(item); }

    public void deleteItem(CartItem item) {
        items.remove(item);
    }

    public void increaseQuantity(CartItem item) {
         item.quantity = item.getQuantity() + 1;
    }

    public void decreaseQuantity(CartItem item) {
        item.quantity = item.getQuantity() - 1;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    } // 장바구니 비어있으면 결제하기 안 넘어가게 함

    public int getTotalCost() { // 총 금액
        int totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getItemPrice();
        }
        return totalPrice;
    }

}
