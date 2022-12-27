package com.cts.fse.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class CompanyTariffs {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "tourist_place_id", referencedColumnName = "id")
    private TouristPlaces place;

    @Min(value = 50000)
    @Max(value = 1500000)
    private Long cost;

    @Column(name = "company_id", insertable = false, updatable = false)
    private String companyId;

    @CreatedDate
    private Date lastUpdated;

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = new Date();
    }

}
