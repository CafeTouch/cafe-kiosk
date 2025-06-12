package model;

public class CartItemFactory {
    public static CartItem create(
            MenuItem item,
            String size,
            int hot,
            int extraShot,
            int sweetener,
            int disposables,
            int quantity
    ) {
        CartItem cartItem = new CartItem();
        cartItem.setName(item.getName());
        cartItem.setPrice(item.getPrice());
        cartItem.setSize(size);
        cartItem.setHot(hot);
        cartItem.setExtraShot(extraShot);
        cartItem.setSweetener(sweetener);
        cartItem.setDisposables(disposables);
        cartItem.setQuantity(quantity);

        return cartItem;
    }
}
