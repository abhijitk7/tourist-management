package com.cts.fse.controller;

import com.cts.fse.api.commands.AddCompanyCommand;
import com.cts.fse.api.dto.CompanyDetailsResponse;
import com.cts.fse.dto.BaseResponse;
import com.cts.fse.infrastructure.CommandDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(path = "/tourism/api/v1/branch")
public class TouristCompanyController {

    @Autowired
    private CommandDispatcher commandDispatcher;


    @PostMapping("/add-places")
    public ResponseEntity<BaseResponse> addCompany(@RequestBody AddCompanyCommand addCompanyCommand) {
        var id = UUID.randomUUID().toString();
        addCompanyCommand.setId(id);
        try {
            this.commandDispatcher.send(addCompanyCommand);
            return new ResponseEntity<>(new CompanyDetailsResponse(id, "Company added successfully"), HttpStatus.CREATED);
        } catch (IllegalStateException exception) {
            log.error("Request body is incorrect " + exception);
            return new ResponseEntity<>(new BaseResponse(exception.toString()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Error occured while adding company -->" + e);
            return new ResponseEntity<>(new CompanyDetailsResponse(id, "Error occured while adding company"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
