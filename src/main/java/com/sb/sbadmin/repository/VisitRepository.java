package com.sb.sbadmin.repository;

import com.sb.sbadmin.domain.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends JpaRepository<Visit,Long> {
    @Query(value = "select count(distinct(ipaddress)) from visit",nativeQuery = true)
    int getTotalVisit();
}
