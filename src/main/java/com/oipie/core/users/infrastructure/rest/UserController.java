package com.oipie.core.users.infrastructure.rest;


import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.users.application.UserAuthenticator;
import com.oipie.core.users.application.UserCreator;
import com.oipie.core.users.domain.Email;
import com.oipie.core.users.infrastructure.rest.dtos.CreateUserRequestDTO;
import com.oipie.core.users.infrastructure.rest.dtos.LoginResponseDTO;
import com.oipie.core.users.infrastructure.rest.dtos.LoginUserRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserCreator userCreator;
    private final UserAuthenticator userAuthenticator;


    public UserController(UserCreator userCreator, UserAuthenticator userAuthenticator) {
        this.userCreator = userCreator;
        this.userAuthenticator = userAuthenticator;
    }


    @Transactional
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@Valid @RequestBody CreateUserRequestDTO requestDTO) throws DomainError {
        this.userCreator.createUser(
                Email.fromString(requestDTO.email()),
                requestDTO.nickname(),
                requestDTO.password()
        );
    }

    @Transactional
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDTO loginUser(@Valid @RequestBody LoginUserRequestDTO requestDTO) throws DomainError {
        return new LoginResponseDTO(this.userAuthenticator.login(
                Email.fromString(requestDTO.email()),
                requestDTO.password()
        ));
    }
}
