import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MenuBoard {
    private static final List<MenuItem> items=new ArrayList<>();

    static {
        items.add(new MenuItem("아이스 아메리카노", 3000, Category.COFFEE, "ice_coffee.jpg"));
        items.add(new MenuItem("핫 아메리카노", 3000, Category.COFFEE, "black_coffee.jpg"));
        items.add(new MenuItem("아이스 카페라뗴", 4000, Category.COFFEE, "iced_latte.jpg"));
        items.add(new MenuItem("핫 카페라떼", 4000, Category.COFFEE, "hott_latte.jpg"));
        items.add(new MenuItem("아이스티", 3500, Category.NON_COFFEE, "icedtea.jpg"));
        items.add(new MenuItem("녹차라떼", 4000, Category.NON_COFFEE, "greentea_latte.jpg"));
        items.add(new MenuItem("요거트 스무디", 5000, Category.NON_COFFEE, "yog_smoothie.jpg"));
        items.add(new MenuItem("미숫가루", 3500, Category.NON_COFFEE, "misu_garu.jpg"));

    }
    /*public static List<MenuItem> getMenuItems(Category category) {
        List<MenuItem> filtered=new ArrayList<>();
        for (MenuItem item: items) {
            if(item.getCategory()==category) filtered.add(item);
        }
        return filtered;
    }*/

    public static List<MenuItem> getMenuItems(Category category) {
        return items.stream()
                .filter(item-> item.getCategory()==category)
                .collect(Collectors.toList());
    }
}
