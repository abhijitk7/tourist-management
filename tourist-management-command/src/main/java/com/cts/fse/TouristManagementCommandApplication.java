package com.cts.fse;

import com.cts.fse.api.commands.AddCompanyCommand;
import com.cts.fse.api.commands.CommandHandler;
import com.cts.fse.api.commands.UpdateCompanyCommand;
import com.cts.fse.infrastructure.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
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

}
