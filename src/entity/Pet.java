package entity;

public class Pet {
    private String name;
    private int age;
    private String breed;

    // Constructor
    public Pet(String name, int age, String breed) {
        this.name = name;
        this.age = age;
        this.breed = breed;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getBreed() {
        return breed;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    // toString Method
    @Override
    public String toString() {
        return "Pet [Name=" + name + ", Age=" + age + ", Breed=" + breed + "]";
    }
}
