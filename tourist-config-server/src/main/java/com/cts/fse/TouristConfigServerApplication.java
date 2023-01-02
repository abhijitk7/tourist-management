package com.cts.fse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Bean;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class TouristConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TouristConfigServerApplication.class, args);
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
        config.setNonSecurePort(9011);
        config.setAppname("TOURIST-CONFIG-SERVER");
        return config;
    }

}
