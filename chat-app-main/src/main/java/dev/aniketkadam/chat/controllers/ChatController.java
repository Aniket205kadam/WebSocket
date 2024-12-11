package dev.aniketkadam.chat.controllers;

import dev.aniketkadam.chat.models.Message;
import dev.aniketkadam.chat.models.Room;
import dev.aniketkadam.chat.payload.MessageRequest;
import dev.aniketkadam.chat.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@CrossOrigin("http://localhost:5173")
//@RequiredArgsConstructor
public class ChatController {
    private final RoomRepository roomRepository;

    public ChatController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    //for sending and revising messages
    @MessageMapping("/sendMessage/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public Message sendMessage(
            @DestinationVariable String roomId,
            @RequestBody MessageRequest request
    ) {
        Room room = roomRepository.findByRoomId(roomId);
//        Message message = Message.builder()
//                .content(request.getContent())
//                .sender(request.getSender())
//                .timeStamp(LocalDateTime.now())
//                .build();

        Message message = new Message();
        message.setContent(request.getContent());
        message.setSender(request.getSender());
        message.setTimeStamp(LocalDateTime.now());

        if (room == null) {
//            return new ResponseEntity<>(
//                    "Room Id not Found!",
//                    HttpStatus.NOT_FOUND
//            );
            return null;
        }
        room.getMessages().add(message);
        roomRepository.save(room);
//        return new ResponseEntity<>(
//                message,
//                HttpStatus.OK
//        );
        return message;
    }
}
