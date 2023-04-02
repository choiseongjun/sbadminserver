package com.sb.sbadmin.repository;

import com.sb.sbadmin.domain.Visit;
import com.sb.sbadmin.dto.TotalTodayDTO;
import com.sb.sbadmin.dto.TotalVal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit,Long> {
    @Query(value = "select count(ipaddress) from visit",nativeQuery = true)
    int getTotalVisit();
    @Query(value = "select count(session_lastaccess) as countToday," +
            "substr(session_lastaccess,1,13) as sessionLastAccess " +
            "from visit where session_lastaccess " +
            "like concat(:nowDate,' %') group by substr(session_lastaccess,12,2) ",nativeQuery = true)
    List<TotalTodayDTO> getVisitTodayTime(@Param("nowDate") String nowDate);
    @Query(value = "select count(ipaddress) from visit where session_lastaccess like concat(:nowDate,' %') ",nativeQuery = true)
    int getTotalTodayVisit(@Param("nowDate") String nowDate);
}
