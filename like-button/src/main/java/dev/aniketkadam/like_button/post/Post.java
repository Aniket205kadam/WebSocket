package dev.aniketkadam.like_button.post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "posts")
public class Post {
    @Id
    private String id;
    private String caption;
    private String username;

    @Column(length = 1000)
    private String userProfileUrl;

    @Column(length = 1000)
    private String imageUrl;
    private Integer likes = 0;

    public Post() {}

    public Post(String id, String caption, String username, String userProfileUrl, String imageUrl, Integer likes) {
        this.id = id;
        this.caption = caption;
        this.username = username;
        this.userProfileUrl = userProfileUrl;
        this.imageUrl = imageUrl;
        this.likes = likes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserProfileUrl() {
        return userProfileUrl;
    }

    public void setUserProfileUrl(String userProfileUrl) {
        this.userProfileUrl = userProfileUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }
}
