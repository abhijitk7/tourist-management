package com.cts.fse.api.commands;

import com.cts.fse.command.BaseCommand;
import com.cts.fse.models.CompanyTariffs;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class AddCompanyCommand extends BaseCommand {
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
    private String firstName;
    private String lastName;
    private String password;
    private String encryptedPassword;
    private String roles;

}
