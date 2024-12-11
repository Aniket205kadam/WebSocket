package dev.aniketkadam.chat.user;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        user.setStatus(Status.ONLINE);
        return userRepository.save(user);
    }

    public User disconnect(User user) {
        var storedUser = userRepository.findById(user.getNickName())
                .orElse(null);
        if (storedUser != null) {
             storedUser.setStatus(Status.OFFLINE);
             userRepository.save(user);
        }
        return storedUser;
    }

    public List<User> findConnectedUser() {
        return userRepository.findAllByStatus(Status.ONLINE);
    }
}
