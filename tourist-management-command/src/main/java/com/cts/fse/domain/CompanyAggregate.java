package com.cts.fse.domain;

import com.cts.fse.api.commands.AddCompanyCommand;
import com.cts.fse.api.commands.UpdateCompanyCommand;
import com.cts.fse.events.CompanyAddedEvent;
import com.cts.fse.events.CompanyUpdatedEvent;
import com.cts.fse.models.CompanyTariffs;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class CompanyAggregate extends AggregateRoot {

    private String branchName;
    private String place;
    @NotBlank
    @URL
    private String website;
    @NotNull
    @Pattern(regexp = "^((\\\\+91-?)|0)?[0-9]{10}$")
    private String contact;
    @Email(message = "Not a valid email")
    private String email;
    private List<CompanyTariffs> tariffs;

    public CompanyAggregate(AddCompanyCommand addCompanyCommand) {
        raiseEvent(
                CompanyAddedEvent.builder().id(addCompanyCommand.getId())
                        .branchName(addCompanyCommand.getBranchName())
                        .place(addCompanyCommand.getPlace())
                        .website(addCompanyCommand.getWebsite())
                        .contact(addCompanyCommand.getContact())
                        .email(addCompanyCommand.getEmail())
                        .tariffs(addCompanyCommand.getTariffs()).build());
    }

    public void apply(CompanyAddedEvent companyAddedEvent) {
        this.id = companyAddedEvent.getId();
        this.branchName = companyAddedEvent.getBranchName();
        this.place = companyAddedEvent.getPlace();
        this.website = companyAddedEvent.getWebsite();
        this.contact = companyAddedEvent.getContact();
        this.email = companyAddedEvent.getEmail();
        this.tariffs = companyAddedEvent.getTariffs();
    }

    //  While updating the details, I am allowed to update only tariff details of the places. Update on personal information like branch name or email or anything else is not allowed
    public void updateCompany(UpdateCompanyCommand updateCompanyCommand) {
        raiseEvent(
                CompanyUpdatedEvent.builder().id(updateCompanyCommand.getId())
                        .branchName(updateCompanyCommand.getBranchName())
                        .website(updateCompanyCommand.getWebsite())
                        .place(updateCompanyCommand.getPlace())
                        .email(updateCompanyCommand.getEmail())
                        .contact(updateCompanyCommand.getContact())
                        .tariffs(updateCompanyCommand.getTariffs())
                        .build()
        );
    }

    public void apply(CompanyUpdatedEvent companyUpdatedEvent) {
        this.id = companyUpdatedEvent.getId();
        this.branchName = companyUpdatedEvent.getBranchName();
        this.place = companyUpdatedEvent.getPlace();
        this.website = companyUpdatedEvent.getWebsite();
        this.email = companyUpdatedEvent.getEmail();
        this.contact = companyUpdatedEvent.getContact();
        this.tariffs = companyUpdatedEvent.getTariffs();
    }
}
