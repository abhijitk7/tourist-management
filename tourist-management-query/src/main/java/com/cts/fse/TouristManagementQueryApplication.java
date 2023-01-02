package com.cts.fse;

import com.cts.fse.api.queries.*;
import com.cts.fse.domain.TouristPlacesRepository;
import com.cts.fse.infrastructure.QueryDispatcher;
import com.cts.fse.models.TouristPlaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
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

    @Bean
    public EurekaInstanceConfigBean eurekaInstanceConfig(InetUtils inetUtils) {
        EurekaInstanceConfigBean config = new EurekaInstanceConfigBean(inetUtils);
        String ip = null;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
            System.out.println("Ip Address : "+ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        config.setIpAddress(ip);
        config.setPreferIpAddress(true);
        config.setNonSecurePort(8084);
        config.setAppname("TOURIST-MANAGEMENT-QUERY");
        return config;
    }
}
