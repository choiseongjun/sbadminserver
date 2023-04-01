package com.sb.sbadmin.service;

import com.sb.sbadmin.domain.Visit;
import com.sb.sbadmin.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
}
