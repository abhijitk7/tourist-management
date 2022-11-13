package com.cts.fse.api.controllers;

import com.cts.fse.api.dto.CompanyLookUpResponse;
import com.cts.fse.api.dto.PlacesLookUpResponse;
import com.cts.fse.api.queries.*;
import com.cts.fse.infrastructure.QueryDispatcher;
import com.cts.fse.models.Company;
import com.cts.fse.models.TouristPlaces;
import com.cts.fse.queries.BaseQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(path = "/tourism/api/v1")
public class CompanyController {

    @Autowired
    private QueryDispatcher queryDispatcher;

    @GetMapping(path = "/places")
    public ResponseEntity<PlacesLookUpResponse> getAllTouristPlaces() {
        try {
            List<TouristPlaces> placesList = queryDispatcher.send(new FindAllTouristPlacesQuery());
            if (placesList == null || placesList.size() == 0) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            var response = PlacesLookUpResponse.builder().touristPlaces(placesList).message("Successfully returned list of companies").
                    build();
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Error while retrieving list of companies" + e);
            return new ResponseEntity<>(new PlacesLookUpResponse("Error occurred while retrieving list of companies"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/company")
    public ResponseEntity<CompanyLookUpResponse> getAllCompanies() {
        try {
            List<Company> companies = queryDispatcher.send(new FindAllCompaniesQuery());
            if (companies == null || companies.size() == 0) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            var response = CompanyLookUpResponse.builder().
                    companies(companies).message("Successfully returned list of companies").
                    build();
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Error while retriving list of companies" + e);
            return new ResponseEntity<>(new CompanyLookUpResponse("Error occured while retriving list of companies"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{criteria}/{criteriaValue}")
    public ResponseEntity<CompanyLookUpResponse> getAllCompaniesByCriteria(@PathVariable String criteria, @PathVariable String criteriaValue) {
        Map<String, BaseQuery> queryDispatchMap = new HashMap<>();
        queryDispatchMap.put("id", new FindCompanyByIdQuery(criteriaValue));
        queryDispatchMap.put("companyName", new FindCompanyByNameQuery(criteriaValue));
        queryDispatchMap.put("touristPlace", new FindCompanyByPlacesQuery(criteriaValue));
        try {
            if (queryDispatchMap.containsKey(criteria)) {
                List<Company> companies = null;
                if ("id".equalsIgnoreCase(criteria)) {
                    companies = queryDispatcher.send(new FindCompanyByIdQuery(criteriaValue));
                } else if ("companyName".equalsIgnoreCase(criteria)) {
                    companies = queryDispatcher.send(new FindCompanyByNameQuery(criteriaValue));
                } else if ("touristPlace".equalsIgnoreCase(criteria)) {
                    companies = queryDispatcher.send(new FindCompanyByPlacesQuery(criteriaValue));
                }

                if (companies == null || companies.size() == 0) {
                    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
                }
                var response = CompanyLookUpResponse.builder().
                        companies(companies).message("Successfully returned list of companies").
                        build();
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                log.warn("Invalid search criteria provided");
                return new ResponseEntity<>(new CompanyLookUpResponse("Invalid search criteria provided"), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error("Error while retriving list of companies" + e);
            return new ResponseEntity<>(new CompanyLookUpResponse("Error occured while retriving list of companies"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/company/{companyId}")
    public ResponseEntity<CompanyLookUpResponse> geCompanyById(@PathVariable String companyId) {
        try {
            List<Company> companies = queryDispatcher.send(new FindCompanyByIdQuery(companyId));
            if (companies == null || companies.size() == 0) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            var response = CompanyLookUpResponse.builder().
                    companies(companies).message("Successfully returned list of companies").
                    build();
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Error while retriving list of companies" + e);
            return new ResponseEntity<>(new CompanyLookUpResponse("Error occured while retriving list of companies"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
