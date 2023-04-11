package com.sb.sbadmin.controller;

import com.sb.sbadmin.dto.BoardInfo;
import com.sb.sbadmin.dto.StockInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/v1/kops")
public class KopsController {
    private WebDriver driver;
    private WebElement element;
    private String url;

    // 1. 드라이버 설치 경로
    public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static String WEB_DRIVER_PATH = "chromedriver";

    @GetMapping("/increase/stock")
    public ResponseEntity<?> getIncreasestock() throws IOException, InterruptedException {
//        String URL = "https://www.kokstock.com/main.asp";

        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        // 2. WebDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("headless");

        driver = new ChromeDriver(options);

        url = "https://www.kokstock.com/main.asp";
        driver.get(url);
//        WebElement elm = driver.findElement(By.className("News"));
        List<WebElement> elm = driver.findElements(By.cssSelector(".news1 li"));

        List<List> allLi = new ArrayList<>();
        List<StockInfo> li = new ArrayList<>();
//        System.out.println(elm.getText());

        for(int i = 0; i < elm.size(); i++){
            WebElement stockItem = elm.get(i).findElement(By.cssSelector(".StockItem"));

            WebElement percent = elm.get(i).findElement(By.cssSelector(".fRed"));
            StockInfo stockInfo =new StockInfo();
            stockInfo.setName(stockItem.getText());
            stockInfo.setName2(percent.getText());
            li.add(stockInfo);
        }

        List<WebElement> news2 = driver.findElements(By.cssSelector(".news3 li"));

        System.out.println("size="+news2.size());
        List<BoardInfo> li2 = new ArrayList<>();

        for(int i = 0; i < news2.size(); i++){
            WebElement marquee = news2.get(i).findElement(By.cssSelector(".marquee"));

            System.out.println(marquee.getText());
            BoardInfo board =new BoardInfo();
            board.setName(marquee.getText());
//            board.setName2(time.getText());
            li2.add(board);
        }
        allLi.add(li);
        allLi.add(li2);
        return ResponseEntity.ok(allLi);
    }
    @GetMapping("/stock/info")
    public ResponseEntity<?> getStockInfo()  {
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        // 2. WebDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("headless");

        driver = new ChromeDriver(options);

        url = "https://www.kokstock.com/main.asp";
        driver.get(url);
        List<List> allLi = new ArrayList<>();

        List<WebElement> table = driver.findElements(By.xpath("//*[@id=\"divTheme\"]/div[1]/table/tbody/tr"));
        List<Map> li = new ArrayList<>();
        for(int i = 0; i < table.size(); i++){
            WebElement firstTxt = table.get(i).findElement(By.cssSelector("td:nth-child(1)"));
            WebElement secondTxt = table.get(i).findElement(By.cssSelector("td:nth-child(2)"));
            WebElement thridTxt = table.get(i).findElement(By.cssSelector("td:nth-child(3)"));
            Map<String,String> map = new HashMap<>();
            map.put("firstTxt", firstTxt.getText());
            map.put("secondTxt", secondTxt.getText());
            map.put("thridTxt", thridTxt.getText());
            li.add(map);
        }
        List<WebElement> table2 = driver.findElements(By.xpath("//*[@id=\"tab-2\"]/div/div[2]/div/table/tbody/tr"));
        List<Map> li2 = new ArrayList<>();
        for(int i = 0; i < table2.size(); i++){
            WebElement firstTxt = table2.get(i).findElement(By.cssSelector("td:nth-child(1)"));
            WebElement secondTxt = table2.get(i).findElement(By.cssSelector("td:nth-child(2)"));
            WebElement thridTxt = table2.get(i).findElement(By.cssSelector("td:nth-child(3)"));
            Map<String,String> map = new HashMap<>();
            map.put("firstTxt", firstTxt.getText());
            map.put("secondTxt", secondTxt.getText());
            map.put("thridTxt", thridTxt.getText());
            li2.add(map);
        }
        allLi.add(li);
        allLi.add(li2);
        return ResponseEntity.ok(allLi);
    }
}
