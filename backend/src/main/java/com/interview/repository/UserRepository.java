package com.interview.repository;

import com.interview.repository.model.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT DISTINCT ue FROM app_user ue " +
            "LEFT JOIN document doc ON doc.user=ue " +
            "WHERE (:searchByQuery IS NULL " +
            "OR (ue.firstname LIKE %:searchByQuery% " +
            "OR ue.lastname LIKE %:searchByQuery% " +
            "OR doc.name LIKE %:searchByQuery%))"
    )
    List<UserEntity> findAllBy(String searchByQuery, Pageable pageable);
}
