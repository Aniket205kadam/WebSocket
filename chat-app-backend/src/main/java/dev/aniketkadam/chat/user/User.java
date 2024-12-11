package dev.aniketkadam.chat.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "users")
public class User {
    @Id
    private String nickName;
    private String fullName;
    private Status status;

    // constructor
    public User() {}

    public User(String nickName, String fullName, Status status) {
        this.nickName = nickName;
        this.fullName = fullName;
        this.status = status;
    }

    // setter and getter
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    //toString

    @Override
    public String toString() {
        return "User{" +
                "nickName='" + nickName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", status=" + status +
                '}';
    }
}
