package dev.aniketkadam.chat.repositories;

import dev.aniketkadam.chat.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {
    //get room using room id
    Room findByRoomId(String roomId);
}
