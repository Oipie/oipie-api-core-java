package com.oipie.core.users.application;

import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.shared.domain.AuthorizationService;
import com.oipie.core.users.domain.Email;
import com.oipie.core.users.domain.User;
import com.oipie.core.users.domain.UserRepository;
import com.oipie.core.users.domain.errors.LoginAttemptFailed;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthenticator {

    private final AuthorizationService authorizationService;

    private final UserRepository userRepository;

    public UserAuthenticator(AuthorizationService authorizationService, UserRepository userRepository) {
        this.authorizationService = authorizationService;
        this.userRepository = userRepository;
    }

    public String login(Email email, String password) throws DomainError {
        Optional<User> user = this.userRepository.findByEmail(email);
        if (user.isEmpty()) throw new LoginAttemptFailed();

        boolean isValidPassword = this.authorizationService.verifyPassword(user.get().getPassword(), password);
        if (!isValidPassword) throw new LoginAttemptFailed();

        return this.authorizationService.createJWT(user.get().getUserId());


    }
}
