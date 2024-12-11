package dev.aniketkadam.like_button.like;

public class LikeResponse {
    private int likes;
    private String postId;

    public LikeResponse() {}

    public LikeResponse(String postId, int likes) {
        this.postId = postId;
        this.likes = likes;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "LikeResponse{" +
                "likes=" + likes +
                ", postId='" + postId + '\'' +
                '}';
    }
}
