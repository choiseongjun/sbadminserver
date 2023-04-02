package com.sb.sbadmin.service;

import com.sb.sbadmin.domain.Visit;
import com.sb.sbadmin.dto.TotalTodayDTO;
import com.sb.sbadmin.dto.TotalVal;
import com.sb.sbadmin.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class VisitService {
    @Autowired
    private VisitRepository visitRepository;
    public Page<Visit> getVisit(Pageable pageable) {
        return visitRepository.findAll(pageable);
    }

    public int getTotalVisit() {
        return visitRepository.getTotalVisit();
    }

    public List<TotalTodayDTO> getVisitTodayTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        Date now = new Date();
        String now_dt = format.format(now);
        return visitRepository.getVisitTodayTime(now_dt);
    }

    public int getVisitTotalToday() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        Date now = new Date();
        String now_dt = format.format(now);
        return visitRepository.getTotalTodayVisit(now_dt);
    }
}
