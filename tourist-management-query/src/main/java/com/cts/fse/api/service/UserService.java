package com.cts.fse.api.service;

import com.cts.fse.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    private WebClient userWebClient;


    public Mono<UserModel> createUser(UserModel userModel) {
        return userWebClient.post()
                .uri("/tourism/user")
                .body(Mono.just(userModel), UserModel.class)
                .retrieve()
                .bodyToMono(UserModel.class);
    }
}
