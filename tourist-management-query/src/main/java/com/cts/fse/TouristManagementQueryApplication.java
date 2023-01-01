package com.cts.fse;

import com.cts.fse.api.queries.*;
import com.cts.fse.domain.TouristPlacesRepository;
import com.cts.fse.infrastructure.QueryDispatcher;
import com.cts.fse.models.TouristPlaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@EnableJpaAuditing
public class TouristManagementQueryApplication implements CommandLineRunner {

    @Autowired
    private QueryDispatcher queryDispatcher;

    @Autowired
    private QueryHandler queryHandler;

    @Autowired
    private TouristPlacesRepository touristPlacesRepository;

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

    @Override
    public void run(String... args) throws Exception {
        long count=this.touristPlacesRepository.count();
        if(count==0){
            List<TouristPlaces> placesList= Arrays.asList(new TouristPlaces(1L,"Andaman"),
                                                            new TouristPlaces(2L,"Thailand"),
                                                                new TouristPlaces(3L,"Dubai"),
                                                                    new TouristPlaces(4L,"Singapore"),
                                                                        new TouristPlaces(5L,"Malaysia"));
            this.touristPlacesRepository.saveAll(placesList);
        }
    }
}
