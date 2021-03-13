package com.cybertek.library.step_definitions;

import com.cybertek.library.pages.HomePage;
import com.cybertek.library.pages.LoginPage;
import com.cybertek.library.utilities.BrowserUtils;
import com.cybertek.library.utilities.ConfigurationReader;
import com.cybertek.library.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class Login_StepDefinitions {

    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();

    @Given("User is on the login page")
    public void user_is_on_the_login_page() {
        String url = ConfigurationReader.getProperty("url");
        Driver.getDriver().get(url);
    }

    @When("User login as a Student")
    public void user_login_as_a_student() {
        loginPage.logInAsStudent();
    }

    @When("User login as a librarian")
    public void user_login_as_a_librarian() {
        loginPage.logInAsLibrarian();
    }

    @When("User enter username {string}")
    public void i_enter_username(String username) {
        loginPage.inputEmail.sendKeys(username);
    }

    @When("User enter password {string}")
    public void i_enter_password(String password) {
        loginPage.inputPassword.sendKeys(password);
    }

    @When("I login using {string} and {string}")
    public void i_login_using_and(String username, String password) {
        i_enter_username(username);
        i_enter_password(password);
        click_the_sign_in_button();
        BrowserUtils.sleep(3);
    }

    @When("User click the sign in button")
    public void click_the_sign_in_button() {
        loginPage.submitButton.click();
    }


    @Then("Dashboard should be displayed")
    public void dashboard_should_be_displayed() {
        String expectedTitle = "Library";
        BrowserUtils.sleep(3);
        String actualTitle = Driver.getDriver().getTitle();

        Assert.assertEquals(actualTitle,expectedTitle);
    }

    @Then("There should be {int} users")
    public void there_should_be_users(int number) {
        Assert.assertEquals(number, homePage.howManyUsers());
    }

    @Then("account holder name should be {string}")
    public void account_holder_name_should_be(String expectedAccountHolderName) {
        BrowserUtils.waitForVisibility(homePage.accountHolderName, 5);
        String actualAccountHolderName = homePage.accountHolderName.getText();
        Assert.assertEquals(actualAccountHolderName, expectedAccountHolderName);

    }



}
