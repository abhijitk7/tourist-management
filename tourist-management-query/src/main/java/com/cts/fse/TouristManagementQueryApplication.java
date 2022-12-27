package com.cts.fse;

import com.cts.fse.api.queries.*;
import com.cts.fse.infrastructure.QueryDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableEurekaClient
@EnableJpaAuditing
public class TouristManagementQueryApplication {

    @Autowired
    private QueryDispatcher queryDispatcher;

    @Autowired
    private QueryHandler queryHandler;

    public static void main(String[] args) {
        SpringApplication.run(TouristManagementQueryApplication.class, args);
    }

    @PostConstruct
    public void registerHandlers() {
        queryDispatcher.registerHandler(FindAllCompaniesQuery.class, queryHandler::handle);
        queryDispatcher.registerHandler(FindCompanyByIdQuery.class, queryHandler::handle);
        queryDispatcher.registerHandler(FindCompanyByNameQuery.class, queryHandler::handle);
        queryDispatcher.registerHandler(FindCompanyByPlacesQuery.class, queryHandler::handle);
        queryDispatcher.registerHandler(FindAllTouristPlacesQuery.class, queryHandler::handle);
    }

}
