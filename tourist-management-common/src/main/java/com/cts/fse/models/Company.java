package com.cts.fse.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company implements Serializable {

    @Id
    private Long id;

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "company_id")
    private List<Tarrifs> tarrifs = new ArrayList<>();
}
