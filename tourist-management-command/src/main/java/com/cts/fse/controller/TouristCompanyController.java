package com.cts.fse.controller;

import com.cts.fse.api.commands.AddCompanyCommand;
import com.cts.fse.api.commands.UpdateCompanyCommand;
import com.cts.fse.api.dto.CompanyDetailsResponse;
import com.cts.fse.dto.BaseResponse;
import com.cts.fse.exceptions.AggregateNotFoundException;
import com.cts.fse.infrastructure.CommandDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(path = "/tourism/api/v1")
public class TouristCompanyController {

    @Autowired
    private CommandDispatcher commandDispatcher;


    @PostMapping("/branch/add-places")
    public ResponseEntity<BaseResponse> addCompany(@RequestBody AddCompanyCommand addCompanyCommand) {
        var id = UUID.randomUUID().toString();
        addCompanyCommand.setId(id);
        try {
            this.commandDispatcher.send(addCompanyCommand);
            return new ResponseEntity<>(new CompanyDetailsResponse("Company added successfully", id), HttpStatus.CREATED);
        } catch (IllegalStateException exception) {
            log.error("Request body is incorrect " + exception);
            return new ResponseEntity<>(new BaseResponse(exception.toString()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Error occured while adding company -->" + e);
            return new ResponseEntity<>(new CompanyDetailsResponse("Error occurred while adding company", id), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-tariff/{companyId}")
    public ResponseEntity<BaseResponse> updateCompany(@PathVariable String companyId, @RequestBody UpdateCompanyCommand updateCompanyCommand) {
        try {
            updateCompanyCommand.setId(companyId);
            commandDispatcher.send(updateCompanyCommand);
            return new ResponseEntity<>(new CompanyDetailsResponse("Company details updated successfully", companyId), HttpStatus.OK);
        } catch (IllegalStateException | AggregateNotFoundException exception) {
            log.error("Request body is incorrect " + exception);
            return new ResponseEntity<>(new BaseResponse(exception.toString()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Error occurred while adding company -->" + e);
            return new ResponseEntity<>(new CompanyDetailsResponse("Error occurred while updating company", companyId), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
