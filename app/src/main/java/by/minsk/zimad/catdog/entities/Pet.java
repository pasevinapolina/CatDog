package by.minsk.zimad.catdog.entities;

public class Pet {

    public enum Type {
        CAT,
        DOG
    }

    private final Type type;
    private final String imageUrl;
    private final String title;

    public Pet(Type type, String imageUrl, String title) {
        this.type = type;
        this.imageUrl = imageUrl;
        this.title = title;
    }

    public Type getType() {
        return type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }
}
