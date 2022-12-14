package com.cts.fse.repository;

import com.cts.fse.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByEmail(String email);

    @Query("Select companyId From UserModel Where email=:email")
    String findUsersCompany(@Param("email") String email);
}
