package com.cts.fse.api.commands;

import com.cts.fse.domain.CompanyAggregate;
import com.cts.fse.handlers.EventSourcingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyCommandHandler implements CommandHandler {

    @Autowired
    private EventSourcingHandler<CompanyAggregate> eventSourcingHandler;

    @Override
    public void handle(AddCompanyCommand addCompanyCommand) {
        var aggregate = new CompanyAggregate(addCompanyCommand);
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(UpdateCompanyCommand updateCompanyCommand) {
        var aggregate = eventSourcingHandler.getById(updateCompanyCommand.getId());
        aggregate.setTarrifs(updateCompanyCommand.getTarrifs());
        aggregate.setEmail(updateCompanyCommand.getEmail());
        aggregate.setBranchName(updateCompanyCommand.getBranchName());
        eventSourcingHandler.save(aggregate);
    }
}
