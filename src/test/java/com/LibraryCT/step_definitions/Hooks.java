package com.LibraryCT.step_definitions;

import com.LibraryCT.utilities.BrowserUtils;
import com.LibraryCT.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {



    @Before
    public void setUp(Scenario scenario){
        if (scenario.getSourceTagNames().contains("@firefox")){
            Driver.setDriver("firefox");
        }else if(scenario.getSourceTagNames().contains("@chrome")){
            Driver.setDriver("chrome");
        }


    }

    @After()
    public void tearDownScenario(Scenario scenario){
        if (scenario.isFailed()) {
            byte[] screenShot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenShot, "image/png", scenario.getName());
        }

        BrowserUtils.wait(1);
        Driver.closeDriver();
    }

}
