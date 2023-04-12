package com.sb.sbadmin.repository;

import com.sb.sbadmin.domain.StockTop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockTopRepository extends JpaRepository<StockTop,Long> {

}
