package com.LibraryCT.step_definitions;

import com.LibraryCT.pages.UsersPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class UserStatus_StepDefinitions {

    UsersPage usersPage = new UsersPage();

    @When("the user click Status dropdown")
    public void the_user_click_status_dropdown() {
       usersPage.userStatusDropDown.click();
    }


    @Then("the user should see the following options:")
    public void the_user_should_see_the_following_options(List<String> expectedUserStatus) {
        Assert.assertEquals(usersPage.userStatus(), expectedUserStatus);
    }

}
