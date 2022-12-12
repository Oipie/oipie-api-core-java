package com.oipie.core.users.application;


import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.shared.domain.IDService;
import com.oipie.core.shared.domain.AuthorizationService;
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
    private final IDService idService;

    private final UserRepository userRepository;

    public UserCreator(AuthorizationService authorizationService, IDService idService, UserRepository userRepository) {
        this.authorizationService = authorizationService;
        this.idService = idService;
        this.userRepository = userRepository;
    }


    public User createUser(Email email, String nickname, String password) throws DomainError {
        if (this.userRepository.findByEmail(email).isPresent()) throw new EmailAlreadyInUse(email);

        if (this.userRepository.isNicknameInUse(nickname)) throw new NicknameAlreadyInUse(nickname);


        User user = User.create(
                UserId.fromString(this.idService.generateID()),
                email,
                nickname,
                this.authorizationService.hashPassword(password)
        );
        this.userRepository.save(user);
        return user;
    }


}