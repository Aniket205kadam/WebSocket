package dev.aniketkadam.like_button.post;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @MessageMapping("/save")
    @SendTo("/like/posts")
    public Post savePosy(@Payload Post post) {
        return postService.savePost(post);
    }

    @GetMapping
    public List<Post> getAllPost() {
        return postService.getAllPost();
    }

    @PostMapping
    public Post saveTest(@RequestBody Post post) {
        return postService.savePost(post);
    }
}
