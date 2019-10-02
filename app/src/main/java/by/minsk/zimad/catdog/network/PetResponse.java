package by.minsk.zimad.catdog.network;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.reactivex.annotations.NonNull;

public class PetResponse {
    @JsonProperty("url")
    private String imageUrl;

    @JsonProperty("title")
    private String title;

    public PetResponse() { }

    public PetResponse(String imageUrl, String title) {
        this.imageUrl = imageUrl;
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NonNull
    @Override
    public String toString() {
        return "PetResponse{" +
                "imageUrl='" + imageUrl + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
