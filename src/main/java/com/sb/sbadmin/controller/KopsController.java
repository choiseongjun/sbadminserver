package com.sb.sbadmin.controller;

import com.sb.sbadmin.domain.StockInfo;
import com.sb.sbadmin.dto.BoardInfo;
import com.sb.sbadmin.repository.StockInfoRepository;
import com.sb.sbadmin.service.KopsService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/kops")
public class KopsController {
    private WebDriver driver;
    private WebElement element;
    private String url;

    // 1. 드라이버 설치 경로
    public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static String WEB_DRIVER_PATH = "chromedriver";

    @Autowired
    private StockInfoRepository stockInfoRepository;
    @Autowired
    private KopsService kopsService;

    @Scheduled(cron = "0 0/10 9-16 * * ?")
// @Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")  // 문자열 milliseconds 사용 시
    public void scheduleFixedRateTask() throws InterruptedException {
//        System.out.println("adsasd");
        kopsService.getIncreasStockInfogen();
    }


    @GetMapping("/increase/stock")
    public ResponseEntity<?> getIncreasestock() throws IOException, InterruptedException {
        return ResponseEntity.ok(kopsService.getStockInfoDb());
    }
    @GetMapping("/stock/info")
    public ResponseEntity<?> getStockInfo()  {
        return ResponseEntity.ok(kopsService.getStockInfoTopDb());
    }
}
