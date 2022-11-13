package com.cts.fse.models;

import com.cts.fse.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company extends BaseEntity implements Serializable {

    @Id
    @Column(name = "company_id")
    private String id;

    private String branchName;

    private String place;

    @NotBlank
    private String website;

    @NotNull
    private String contact;

    @Email(message = "Not a valid email")
    @Column(nullable = false, length = 120, unique = true)
    private String email;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "company_id", nullable = false, updatable = true)
    private Set<CompanyTariffs> tariffs;

}
