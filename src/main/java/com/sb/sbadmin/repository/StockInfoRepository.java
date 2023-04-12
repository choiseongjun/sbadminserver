package com.sb.sbadmin.repository;

import com.sb.sbadmin.domain.StockInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockInfoRepository extends JpaRepository<StockInfo,Long> {
}
