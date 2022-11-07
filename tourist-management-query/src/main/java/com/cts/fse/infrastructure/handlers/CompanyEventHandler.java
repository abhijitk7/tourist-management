package com.cts.fse.infrastructure.handlers;

import com.cts.fse.domain.CompanyRepository;
import com.cts.fse.events.CompanyAddedEvent;
import com.cts.fse.events.CompanyUpdatedEvent;
import com.cts.fse.models.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyEventHandler implements EventHandler {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public void on(CompanyAddedEvent companyAddedEvent) {
        var company = Company.builder().place(companyAddedEvent.getPlace())
                .email(companyAddedEvent.getEmail())
                .branchName(companyAddedEvent.getBranchName())
                .website(companyAddedEvent.getWebsite())
                .contact(companyAddedEvent.getContact())
                .tariffs(companyAddedEvent.getTariffs())
                .build();
        companyRepository.save(company);
    }

    @Override
    public void on(CompanyUpdatedEvent companyUpdatedEvent) {
        var company = Company.builder().place(companyUpdatedEvent.getPlace())
                .email(companyUpdatedEvent.getEmail())
                .branchName(companyUpdatedEvent.getBranchName())
                .website(companyUpdatedEvent.getEmail())
                .contact(companyUpdatedEvent.getContact())
                .tariffs(companyUpdatedEvent.getTariffs())
                .build();
        companyRepository.save(company);
    }
}
