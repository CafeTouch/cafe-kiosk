package model;
public class MenuItem {
    private String name;
    private int price;
    private Category category;
    private String imagePath;

    public MenuItem(String name, int price, Category category, String imagePath) {
        this.name=name;
        this.price=price;
        this.category=category;
        this.imagePath=imagePath;
    }
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public String getImagePath() {
        return imagePath;
    }
}
