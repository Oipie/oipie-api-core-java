package com.oipie.core.users.domain;

import com.oipie.core.users.domain.errors.UserNotFoundError;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserFinderService {

    private final UserRepository userRepository;

    public UserFinderService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUserId(UserId userId) {
        Optional<User> user = this.userRepository.findById(userId);
        return user.orElseThrow(() -> new UserNotFoundError(userId));
    }
}
