package by.minsk.zimad.catdog.network;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PetResponse {
    @JsonProperty("url")
    private final String imageUrl;

    @JsonProperty("title")
    private final String title;

    public PetResponse(String imageUrl, String title) {
        this.imageUrl = imageUrl;
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }
}
