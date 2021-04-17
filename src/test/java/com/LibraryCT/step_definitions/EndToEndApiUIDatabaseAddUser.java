package com.LibraryCT.step_definitions;

import com.LibraryCT.pages.LoginPage;
import com.LibraryCT.pages.UsersPage;
import com.LibraryCT.utilities.DB_Utility;
import com.LibraryCT.utilities.Driver;
import com.LibraryCT.utilities.LibraryUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import org.junit.Assert;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class EndToEndApiUIDatabaseAddUser {
    LoginPage loginPage = new LoginPage();
    UsersPage usersPage = new UsersPage();
    String email;
    String password;
    String fullName;
    Map<String, Object> body;


    @Given("user is on librarian page and user adds one user with api endpoint")
    public void user_is_on_librarian_page_and_user_adds_one_user_with_api_endpoint() {
        String username = "librarian69@library";
        String password1 = "KNPXrm3S";
        String token = LibraryUtil.getToken(username, password1);

        body = LibraryUtil.getRandomUser(3);
         given().baseUri("http://library1.cybertekschool.com")
                .basePath("/rest/v1")
                .contentType(ContentType.URLENC)
                .header("x-library-token", token)
                .formParams(body)
                .when().post("/add_user")
                .then()
                .statusCode(200)
                .extract().path("user_id");

        email = String.valueOf(body.get("email"));
        password = String.valueOf(body.get("password"));
        fullName = body.get("full_name").toString();
    }

    @When("user log in as with with his credentials")
    public void user_log_in_as_with_with_his_credentials() {
        Driver.getDriver().get("http://library1.cybertekschool.com/");
        loginPage.logInAsStudent(email, password);


    }

    @Then("user should verify his full name is displayed")
    public void user_should_verify_his_full_name_is_displayed() {
        Assert.assertTrue(usersPage.userIsDisplayed(fullName));

    }

    @And("user verifies that his\\/her name exist in the database")
    public void user_verifies_that_his_her_name_exist_in_the_database() {

        DB_Utility.createConnection();
        String query = "select full_name from users where full_name='"+fullName+"'";
        DB_Utility.runQuery(query);
        Assert.assertEquals(DB_Utility.getFirstData(), fullName);
        DB_Utility.destroy();

    }


}
