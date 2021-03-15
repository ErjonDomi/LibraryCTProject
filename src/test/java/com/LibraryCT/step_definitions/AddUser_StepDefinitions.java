package com.LibraryCT.step_definitions;

import com.LibraryCT.pages.UsersPage;
import com.LibraryCT.utilities.BrowserUtils;
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
        BrowserUtils.wait(3);
        Assert.assertTrue(usersPage.verifyNewUser());
    }

    @Then("User should be able to close the add user pop-up window")
    public void userShouldBeAbleToCloseTheAddUserPopUpWindow() {
        usersPage.close();
    }


    @Then("User should be able to edit user information")
    public void userShouldBeAbleToEditUserInformation() {
        BrowserUtils.wait(3);
        usersPage.editUser();

    }

}
