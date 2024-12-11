package dev.aniketkadam.like_button.like;

import dev.aniketkadam.like_button.post.PostService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class LikeController {
    private final SimpMessagingTemplate messagingTemplate;
    private final PostService postService;

    public LikeController(SimpMessagingTemplate messagingTemplate, PostService postService) {
        this.messagingTemplate = messagingTemplate;
        this.postService = postService;
    }

    @MessageMapping("/{postId}/like")
    public void likePost(@DestinationVariable("postId") String postId) {
        System.out.println("called.>!");
        Integer updateLikeCount = postService.getLikeCountAndInc(postId);
        messagingTemplate.convertAndSendToUser(
                postId,
                "/likes",
                new LikeResponse(postId, updateLikeCount)
        );
    }
}

// Bug notes : when you return the object to the client using the SimpMessagingTemplate then in your returning
// object class have the no-args constructor, getter and setter compulsory to have this, otherwise get the conversation failed error
