/*미리 메뉴 항목들 등록해 놔서 - 사용자가 "커피" 카테고리 클릭하면-
    getMenuItems(Category.COFFEE) 호출- 커피 항목만 filtering 되어 출력*/
package model;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
//전체 메뉴판 역할 - MenuItem(메뉴 항목) 목록 관리, 특정 카테고리에 맞는 메뉴만 골라줌
public class MenuBoard {
    //items: 메뉴 항목들 담는 리스트
    private static final List<MenuItem> items=new ArrayList<>();
    //이 블록은 클래스가 메모리 올라갈 때 한 번 실행됨, 메뉴 항목들 리스트에 미리 등록
    static {
        items.add(new MenuItem("아메리카노", 3000, Category.COFFEE, "ice_coffee.jpg"));
        items.add(new MenuItem("카페 모카", 3500, Category.COFFEE, "coffee_moka.jpg"));
        items.add(new MenuItem("카푸치노", 4500, Category.COFFEE, "capucino.jpg"));
        items.add(new MenuItem("카페라떼", 4000, Category.COFFEE, "iced_latte.jpg"));
        items.add(new MenuItem("아이스티", 3500, Category.NON_COFFEE, "icedtea.jpg"));
        items.add(new MenuItem("녹차라떼", 4000, Category.NON_COFFEE, "greentea_latte.jpg"));
        items.add(new MenuItem("요거트 스무디", 5000, Category.NON_COFFEE, "yog_smoothie.jpg"));
        items.add(new MenuItem("미숫가루", 4000, Category.NON_COFFEE, "misu_garu.jpg"));

    }
    //메뉴 항목 조회 메서드 - 특정 카테고리에 해당하는 메뉴만 골라서 반환
    public static List<MenuItem> getMenuItems(Category category) {
        return items.stream()
                .filter(item-> item.getCategory()==category)//요청된 카테고리랑 각 메뉴 항목 카테고리 같은지 검사
                .collect(Collectors.toList());//다시 리스트 형태로 만들어 리턴
        //예를 들어 getMenuItem(Catgory.COFFEE)로출하면 커피 메뉴들만 리턴됨
    }
}
