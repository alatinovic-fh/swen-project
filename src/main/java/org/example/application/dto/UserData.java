package org.example.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserData {

    @JsonProperty("Name")
    private String fullName;
    @JsonProperty("Bio")
    private String bio;
    @JsonProperty("Image")
    private String image;

    public UserData() {

    }

    public UserData(String fullName, String bio, String image) {
        this.fullName = fullName;
        this.bio = bio;
        this.image = image;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
