// [2] cart 관리 기능
package model;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<MenuItem> items = new ArrayList<>(); // 원소 = 장바구니 항목

    public void addItem(MenuItem item) { items.add(item); }

    public List<MenuItem> getItems() { return items; }

    public void deleteItem(MenuItem item) {
        items.remove(item);
    }

    public void increaseQuantity(MenuItem item) { item.quantity = item.getQuantity() + 1; }

    public void decreaseQuantity(MenuItem item) { item.quantity = item.getQuantity() - 1; }

    public boolean isEmpty() { return items.isEmpty(); } // 장바구니 비어있으면 결제하기 안 넘어가게 함

    public int getTotalCost() { // 총 금액
        int totalPrice = 0;
        for (MenuItem item : items) {
            totalPrice += item.getItemPrice();
        }
        return totalPrice;
    }

}
