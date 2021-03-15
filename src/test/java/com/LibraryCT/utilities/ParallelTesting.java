package com.LibraryCT.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParallelTesting {
    static WebDriver driver;

    @Test
    @Parameters("browsers")
    public static void getDr(String browser) {
        if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

            driver.manage().window().maximize();

            driver.get("http://www.facebook.com");

            driver.quit();

        } else if (browser.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();

            driver = new ChromeDriver();

            driver.manage().window().maximize();

            driver.get("http://www.facebook.com");

            driver.quit();
        }
    }

}

