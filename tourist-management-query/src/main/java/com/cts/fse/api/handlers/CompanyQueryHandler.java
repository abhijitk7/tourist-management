package com.cts.fse.api.handlers;

import com.cts.fse.api.queries.*;
import com.cts.fse.domain.BaseEntity;
import com.cts.fse.domain.CompanyRepository;
import com.cts.fse.domain.TouristPlacesRepository;
import com.cts.fse.models.Company;
import com.cts.fse.models.TouristPlaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyQueryHandler implements QueryHandler {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private TouristPlacesRepository touristPlacesRepository;

    @Override
    public List<BaseEntity> handle(FindAllCompaniesQuery query) {
        List<Company> companyList = this.companyRepository.findAll();
        List<BaseEntity> companies = new ArrayList<>();
        companyList.forEach(companies::add);
        return companies;
    }

    @Override
    public List<BaseEntity> handle(FindCompanyByIdQuery query) {
        var company = companyRepository.findById(query.getId());
        if (company.isEmpty()) {
            return null;
        }
        List<BaseEntity> requiredCompany = new ArrayList<>();
        requiredCompany.add(company.get());
        return requiredCompany;
    }

    @Override
    public List<BaseEntity> handle(FindCompanyByNameQuery query) {
        var company = companyRepository.findByBranchName(query.getCompanyName());
        if (company.isEmpty()) {
            return null;
        }
        List<BaseEntity> requiredCompany = new ArrayList<>();
        requiredCompany.add(company.get());
        return requiredCompany;
    }

    @Override
    public List<BaseEntity> handle(FindCompanyByPlacesQuery query) {
        List<Company> companyList = this.companyRepository.findAllCompaniesByPlace(query.getTouristPlace());
        if (companyList.isEmpty()) {
            return null;
        }
        List<BaseEntity> requiredCompanies = new ArrayList<>();
        companyList.forEach(requiredCompanies::add);
        return requiredCompanies;
    }

    @Override
    public List<BaseEntity> handle(FindAllTouristPlacesQuery query) {
        List<TouristPlaces> placesList = this.touristPlacesRepository.findAll();
        if (placesList.isEmpty()) {
            return null;
        }
        List<BaseEntity> requiredPlaces = new ArrayList<>();
        placesList.forEach(requiredPlaces::add);
        return requiredPlaces;
    }
}
