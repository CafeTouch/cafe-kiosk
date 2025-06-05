// 2. data management
package Module;
import java.util.*;

public class Cart {
    List<CartItem> items = new ArrayList<CartItem>();

    public void addItem(CartItem item) {
        items.add(item);
    }

    public void deleteItem(CartItem item) {
        items.remove(item);
    }

    public void increaseQuantity(CartItem item) {
        item.setQuantity(item.getQuantity() + 1);
    }

    public void decreaseQuantity(CartItem item) {
        item.setQuantity(item.getQuantity() - 1);
    }

    public int getTotalPrice(CartItem item) { // 총 금액
        int totalPrice = 0;
        for (CartItem item1 : items) {
            int itemTotal = item.getPrice() * item.getQuantity();
            if (item.getOptionShot() == 1) { // 샷 추가
                itemTotal += 500 * item.getOptionShot();
            }
            totalPrice += itemTotal;
        }
        return totalPrice;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int getTotalCost() {
        int totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }

}