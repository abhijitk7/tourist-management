package com.cts.fse.domain;

import com.cts.fse.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, String> {
    Optional<Company> findByBranchName(String branchName);
}
