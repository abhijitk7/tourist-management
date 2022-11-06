package com.cts.fse.domain;

import com.cts.fse.api.commands.AddCompanyCommand;
import com.cts.fse.api.commands.UpdateCompanyCommand;
import com.cts.fse.events.CompanyAddedEvent;
import com.cts.fse.events.CompanyUpdatedEvent;
import com.cts.fse.models.Tarrifs;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
public class CompanyAggregate extends AggregateRoot {

    private String branchName;
    private String place;
    @NotBlank
    @URL
    private String website;
    @NotNull
    @Pattern(regexp = "(^$|[0-9]{10})")
    private String contact;
    @Email(message = "Not a valid email")
    private String email;
    private List<Tarrifs> tarrifs;

    public CompanyAggregate(AddCompanyCommand addCompanyCommand) {
        raiseEvent(
                CompanyAddedEvent.builder().id(addCompanyCommand.getId())
                        .branchName(addCompanyCommand.getBranchName())
                        .place(addCompanyCommand.getPlace())
                        .website(addCompanyCommand.getWebsite())
                        .contact(addCompanyCommand.getContact())
                        .email(addCompanyCommand.getEmail())
                        .tarrifs(addCompanyCommand.getTarrifs()).build());
    }

    public void apply(CompanyAddedEvent companyAddedEvent) {
        this.id = companyAddedEvent.getId();
        this.branchName = companyAddedEvent.getBranchName();
        this.website = companyAddedEvent.getWebsite();
        this.contact = companyAddedEvent.getContact();
        this.email = companyAddedEvent.getEmail();
        this.tarrifs = companyAddedEvent.getTarrifs();
    }

    //  While updating the details, I am allowed to update only tariff details of the places. Update on personal information like branch name or email or anything else is not allowed
    public void updateCompany(UpdateCompanyCommand updateCompanyCommand) {
        raiseEvent(
                CompanyUpdatedEvent.builder().id(updateCompanyCommand.getId())
                        .tarrifs(updateCompanyCommand.getTarrifs())
                        .email(updateCompanyCommand.getEmail())
                        .branchName(updateCompanyCommand.getBranchName())
                        .build()
        );
    }

    public void apply(CompanyUpdatedEvent companyUpdatedEvent) {
        this.id = companyUpdatedEvent.getId();
        this.branchName = companyUpdatedEvent.getBranchName();
        this.email = companyUpdatedEvent.getEmail();
        this.tarrifs = companyUpdatedEvent.getTarrifs();
    }
}
