package entity;

public class Cat extends Pet {
    private String catColor;

    // Constructor
    public Cat(String name, int age, String breed, String catColor) {
        super(name, age, breed);
        this.catColor = catColor;
    }

    // Getter
    public String getCatColor() {
        return catColor;
    }

    // Setter
    public void setCatColor(String catColor) {
        this.catColor = catColor;
    }

    // toString Method
    @Override
    public String toString() {
        return super.toString() + ", CatColor=" + catColor;
    }
}
