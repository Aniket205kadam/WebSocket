package dev.aniketkadam.like_button.post;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public Post savePost(Post post) {
        post.setId(String.valueOf(UUID.randomUUID()));
        post.setLikes(0);
        return repository.save(post);
    }

    public List<Post> getAllPost() {
        return repository.findAll();
    }

    public Integer getLikeCountAndInc(String postId) {
        Post post = repository.findById(postId)
                .orElseThrow(()-> new EntityNotFoundException("Post not found by ID: " + postId));
        Integer likeCounts = post.getLikes();
        if (likeCounts == null) {
            likeCounts = 0;
        }
        ++likeCounts;
        post.setLikes(likeCounts);
        repository.save(post);
        return likeCounts;

    }
}
