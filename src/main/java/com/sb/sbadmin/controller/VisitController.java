package com.sb.sbadmin.controller;

import com.sb.sbadmin.domain.Visit;
import com.sb.sbadmin.repository.VisitRepository;
import com.sb.sbadmin.service.VisitService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UrlPathHelper;

import java.text.SimpleDateFormat;
import java.util.Locale;

@RestController
@RequestMapping("/visit")
public class VisitController {

    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private VisitService visitService;

    @PostMapping
    public ResponseEntity<?> visitCount(){
        HttpServletRequest request = // 5
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Visit visit = new Visit();
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd HH:mm:ss", Locale.KOREA );

        String lastTime = formatter.format ( request.getSession().getLastAccessedTime() );//세션마지막요청시간
        String initTime = formatter.format ( request.getSession().getCreationTime());//세션초기시간
        String sessionId = request.getSession().getId();
        String browserInfo = request.getHeader("User-Agent");
        String referer = (String)request.getHeader("REFERER");

        String accessPath = urlPathHelper.getOriginatingRequestUri(request);
        visit.setIpaddress(request.getRemoteAddr());
        visit.setSessionId(sessionId);
        visit.setBrowser(browserInfo);
        visit.setAccesspath(accessPath);
        visit.setSessionlastaccess(lastTime);
        visit.setOstype(System.getProperty("os.name"));
        visit.setReferer(referer);
        visitRepository.save(visit);
        return null;
    }
    @GetMapping
    public ResponseEntity<?> getVisit(@RequestParam int pageNum,
                                      @RequestParam int page,
                                      Pageable pageable){
        pageable = PageRequest.of(page, pageNum, Sort.Direction.DESC, "id");// 내림차순으로 정렬한다

        return ResponseEntity.ok(visitService.getVisit(pageable));
    }
    @GetMapping("/total")
    public ResponseEntity<?> getVisit(){
        return ResponseEntity.ok(visitService.getTotalVisit());
    }
}
