package com.cybertek.library.step_definitions;

import com.cybertek.library.pages.UsersPage;
import com.cybertek.library.utilities.BrowserUtils;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class AddUser_StepDefinitions {

    UsersPage usersPage = new UsersPage();

    @Then("Librarian add user")
    public void librarian_add_user() {
        usersPage.addUser();
    }

    @Then("User should be able to see new user name")
    public void userShouldBeAbleToSeeNewUserName() {
        BrowserUtils.sleep(3);
        Assert.assertTrue(usersPage.verifyNewUser());
    }

    @Then("User should be able to close the add user pop-up window")
    public void userShouldBeAbleToCloseTheAddUserPopUpWindow() {
        usersPage.close();
    }


    @Then("User should be able to edit user information")
    public void userShouldBeAbleToEditUserInformation() {
        BrowserUtils.sleep(3);
        usersPage.editUser();

    }

}
