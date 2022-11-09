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
                .id(companyAddedEvent.getId())
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
       /* var company = Company.builder().place(companyUpdatedEvent.getPlace())
                .id(companyUpdatedEvent.getId())
                .email(companyUpdatedEvent.getEmail())
                .branchName(companyUpdatedEvent.getBranchName())
                .website(companyUpdatedEvent.getEmail())
                .contact(companyUpdatedEvent.getContact())
                .tariffs(companyUpdatedEvent.getTariffs())
                .build();*/
        var companyDetails = companyRepository.findById(companyUpdatedEvent.getId());
        if (companyDetails.isPresent()) {
            /*Map<String, Long> updatedTariffs = new HashMap<>();
            companyUpdatedEvent.getTariffs().stream().forEach((tariff -> updatedTariffs.put(tariff.getTariffPlace(), tariff.getCost())));

            companyDetails.get().getTariffs().stream().forEach(tariff -> tariff.setCost(updatedTariffs.get(tariff.getTariffPlace())));*/
            companyDetails.get().getTariffs().clear();
            companyDetails.get().setTariffs(companyUpdatedEvent.getTariffs());
        }
        companyRepository.saveAndFlush(companyDetails.get());
    }
}
