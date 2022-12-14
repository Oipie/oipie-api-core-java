package com.oipie.core.users.application;


import com.oipie.core.shared.domain.AuthorizationService;
import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.shared.domain.IdentificationService;
import com.oipie.core.shared.domain.Password;
import com.oipie.core.shared.domain.errors.InvalidPassword;
import com.oipie.core.users.domain.Email;
import com.oipie.core.users.domain.User;
import com.oipie.core.users.domain.UserId;
import com.oipie.core.users.domain.UserRepository;
import com.oipie.core.users.domain.errors.EmailAlreadyInUse;
import com.oipie.core.users.domain.errors.NicknameAlreadyInUse;
import org.springframework.stereotype.Service;

@Service
public class UserCreator {


    private final AuthorizationService authorizationService;
    private final IdentificationService identificationService;

    private final UserRepository userRepository;

    public UserCreator(AuthorizationService authorizationService, IdentificationService identificationService, UserRepository userRepository) {
        this.authorizationService = authorizationService;
        this.identificationService = identificationService;
        this.userRepository = userRepository;
    }


    public User createUser(Email email, String nickname, String password) throws DomainError {
        if (this.userRepository.findByEmail(email).isPresent()) throw new EmailAlreadyInUse(email);

        if (this.userRepository.isNicknameInUse(nickname)) throw new NicknameAlreadyInUse(nickname);

        if (!Password.isValid(password)) throw new InvalidPassword();

        User user = User.create(
                UserId.fromString(this.identificationService.generateID()),
                email,
                nickname,
                this.authorizationService.hashPassword(password)
        );
        this.userRepository.save(user);
        return user;
    }


}
