package com.sb.sbadmin;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Locale;
import java.util.TimeZone;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
@EnableAspectJAutoProxy
//@EnableAsync
public class SbadminApplication {
    @PostConstruct
    public void started() {
        // timezone UTC 셋팅
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
        Locale.setDefault(Locale.KOREA);
    }
    public static void main(String[] args) {
        SpringApplication.run(SbadminApplication.class, args);
    }

}
