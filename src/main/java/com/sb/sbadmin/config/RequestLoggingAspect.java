package com.sb.sbadmin.config;

import com.google.common.base.Joiner;
import com.sb.sbadmin.domain.Visit;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UrlPathHelper;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Component // 1
@Aspect // 2
public class RequestLoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingAspect.class);


    private String paramMapToString(Map<String, String[]> paramMap) {
        return paramMap.entrySet().stream()
                .map(entry -> String.format("%s -> (%s)", entry.getKey(), Joiner.on(",").join(entry.getValue())))
                .collect(Collectors.joining(", "));
    }

    @Pointcut("within(com.sb.sbadmin.controller..*)") // 3
    public void onRequest() {
    }

    @Around("com.sb.sbadmin.config.RequestLoggingAspect.onRequest()") // 4
    public Object doLogging(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = // 5
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        Map<String, String[]> paramMap = request.getParameterMap();
        String params = "";
        if (paramMap.isEmpty() == false) {
            params = " [" + paramMapToString(paramMap) + "]";
        }

        long start = System.currentTimeMillis();
        try {

//		     People people = peopleRepository.findByName(username);
//		     long userid = people.getId();
//
//			System.out.println("프린서팔"+userid);
            String anony = "";
            long userid = 0;
//            People people = peopleRepository.findByName(username);
//
//            SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd HH:mm:ss", Locale.KOREA );
//            String lastTime = formatter.format ( request.getSession().getLastAccessedTime() );//세션마지막요청시간
//            String initTime = formatter.format ( request.getSession().getCreationTime());//세션초기시간
//
//            UrlPathHelper urlPathHelper = new UrlPathHelper();
//            String accessPath = urlPathHelper.getOriginatingRequestUri(request);
//            System.out.println("originalURL-->" + accessPath);
//
//            UserHistory userhistory = new UserHistory();
            Visit visit = new Visit();
            UrlPathHelper urlPathHelper = new UrlPathHelper();
            SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd HH:mm:ss", Locale.KOREA );

            String lastTime = formatter.format ( request.getSession().getLastAccessedTime() );//세션마지막요청시간
            String initTime = formatter.format ( request.getSession().getCreationTime());//세션초기시간
            String sessionId = request.getSession().getId();
            String browserInfo = request.getHeader("User-Agent");


            String accessPath = urlPathHelper.getOriginatingRequestUri(request);
            visit.setIpaddress(request.getRemoteAddr());
            visit.setSessionId(sessionId);
            visit.setBrowser(browserInfo);

            return pjp.proceed(pjp.getArgs()); // 6
        } finally {
            long end = System.currentTimeMillis();
            logger.debug("Request: {} {}{} < {} ({}ms)", request.getMethod(), request.getRequestURI(), params,
                    request.getRemoteHost(), end - start);
        }
    }
}