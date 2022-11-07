package com.cts.fse.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String branchName;

    private String place;

    @NotBlank
    private String website;

    @NotNull
    @Pattern(regexp = "(^$|[0-9]{10})")
    private String contact;

    @Email(message = "Not a valid email")
    private String email;

    /*@OneToMany
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Set<CompanyTarrifs> tariffs;*/

    /*@OneToMany(
            mappedBy = "company",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )*/
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false, updatable = true)
    private Set<CompanyTarrifs> tariffs;

}
