package com.cts.fse.models;

import com.cts.fse.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date lastUpdated;

    @Email(message = "Not a valid email")
    @Column(nullable = false, length = 120, unique = true)
    private String email;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "company_id", nullable = false, updatable = true)
    private List<CompanyTariffs> tariffs;

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = new Date();
    }

}
