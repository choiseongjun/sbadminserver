package com.sb.sbadmin.repository;

import com.sb.sbadmin.domain.Client;
import com.sb.sbadmin.domain.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
}
