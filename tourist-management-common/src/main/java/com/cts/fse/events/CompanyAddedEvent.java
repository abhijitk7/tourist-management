package com.cts.fse.events;

import com.cts.fse.models.CompanyTariffs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class CompanyAddedEvent extends BaseEvent {
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
    private List<CompanyTariffs> tariffs;
    private String firstName;
    private String lastName;
    private String password;
    private String encryptedPassword;
    private String roles;
    private Date lastUpdated;
}
