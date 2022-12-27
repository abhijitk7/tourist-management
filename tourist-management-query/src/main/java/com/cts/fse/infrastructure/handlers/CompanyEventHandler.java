package com.cts.fse.infrastructure.handlers;

import com.cts.fse.api.service.UserService;
import com.cts.fse.domain.CompanyRepository;
import com.cts.fse.events.CompanyAddedEvent;
import com.cts.fse.events.CompanyUpdatedEvent;
import com.cts.fse.models.Company;
import com.cts.fse.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyEventHandler implements EventHandler {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserService userService;


    @Override
    public void on(CompanyAddedEvent companyAddedEvent) {
        var company = Company.builder().place(companyAddedEvent.getPlace())
                .id(companyAddedEvent.getId())
                .email(companyAddedEvent.getEmail())
                .branchName(companyAddedEvent.getBranchName())
                .website(companyAddedEvent.getWebsite())
                .contact(companyAddedEvent.getContact())
                .tariffs(companyAddedEvent.getTariffs())
                .build();
        Company savedCompany = companyRepository.save(company);

        UserModel userModel = UserModel.builder().email(savedCompany.getEmail())
                .firstName(companyAddedEvent.getFirstName())
                .lastName(companyAddedEvent.getLastName())
                .email(savedCompany.getEmail())
                .roles("USER")
                .password(companyAddedEvent.getPassword())
                .companyId(savedCompany.getId())
                .build();

        UserModel user = this.userService.createUser(userModel).block();

    }

    @Override
    public void on(CompanyUpdatedEvent companyUpdatedEvent) {
        var companyDetails = companyRepository.findById(companyUpdatedEvent.getId());
        if (companyDetails.isPresent()) {
            companyDetails.get().getTariffs().clear();
            companyDetails.get().setTariffs(companyUpdatedEvent.getTariffs());
        }
        companyRepository.saveAndFlush(companyDetails.get());
    }
}
