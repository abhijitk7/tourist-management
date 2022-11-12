package com.cts.fse.domain;

import com.cts.fse.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, String> {
    Optional<Company> findByBranchName(String branchName);

    @Query("Select c from Company c where id in (Select distinct companyId from CompanyTariffs where tariffPlace=:place)")
    List<Company> findAllCompaniesByPlace(@Param("place") String place);
}
