package com.cts.fse;

import com.cts.fse.api.commands.AddCompanyCommand;
import com.cts.fse.api.commands.CommandHandler;
import com.cts.fse.api.commands.UpdateCompanyCommand;
import com.cts.fse.infrastructure.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableEurekaClient
public class TouristManagementCommandApplication {

    @Autowired
    private CommandHandler commandHandler;

    @Autowired
    private CommandDispatcher commandDispatcher;

    public static void main(String[] args) {
        SpringApplication.run(TouristManagementCommandApplication.class, args);
    }

    @PostConstruct
    public void registerHandler() {
        commandDispatcher.registerHandler(AddCompanyCommand.class, commandHandler::handle);
        commandDispatcher.registerHandler(UpdateCompanyCommand.class, commandHandler::handle);
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
        config.setNonSecurePort(8083);
        config.setAppname("TOURIST-MANAGEMENT-COMMAND");
        return config;
    }

}
