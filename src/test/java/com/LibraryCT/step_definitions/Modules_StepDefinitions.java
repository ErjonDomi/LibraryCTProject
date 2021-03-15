package com.LibraryCT.step_definitions;

import com.LibraryCT.pages.HomePage;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;

public class Modules_StepDefinitions {

    HomePage homePage = new HomePage();

    @Then("User should see following modules")
    public void user_should_see_following_modules(List<String> expectedModules) {
        Assert.assertEquals(homePage.modules(), expectedModules);
    }


}
