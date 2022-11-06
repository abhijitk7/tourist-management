package com.cts.fse.api.commands;

public interface CommandHandler {
    void handle(AddCompanyCommand addCompanyCommand);

    void handle(UpdateCompanyCommand updateCompanyCommand);
}
