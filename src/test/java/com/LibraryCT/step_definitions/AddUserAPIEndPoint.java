package com.LibraryCT.step_definitions;

import com.LibraryCT.pages.LoginPage;
import com.LibraryCT.pages.UsersPage;
import com.LibraryCT.utilities.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import org.junit.Assert;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class AddUserAPIEndPoint {
    LoginPage loginPage = new LoginPage();
    UsersPage usersPage = new UsersPage();
    String email;
    String password;
    String fullName;
    Map<String, Object> body;

    @Given("new student is added using the api endpoint")
    public void new_student_is_added_using_the_api_endpoint() {
        baseURI = "http://library1.cybertekschool.com";
        basePath = "/rest/v1";
        String username = "librarian69@library";
        String password1 = "KNPXrm3S";
        String token = LibraryUtil.getToken(username, password1);
        System.out.println(token);
        body= LibraryUtil.getRandomUser(3);
        String myId = given().log().all()
                .contentType(ContentType.URLENC)
                .header("x-library-token", token)
                .formParams(body)
                .when().post("/add_user")
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract().path("user_id");
        given().log().all()
                .header("x-library-token", token)
                .pathParam("id", myId)
                .when()
                .get("/get_user_by_id/{id}")
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("id", is(myId));

        email = String.valueOf(body.get("email"));
        password = String.valueOf(body.get("password"));
        fullName = body.get("full_name").toString();
        BrowserUtils.wait(3);
    }

    @Then("I should verify the user is created in database")
    public void i_should_verify_the_user_is_created_in_database() {

        DB_Utility.createConnection();
        String query = "select * from users where full_name = '" + fullName + "'";
        DB_Utility.runQuery(query);
        Map<String, String> actual = DB_Utility.getRowMap(1);
        String actualFullName = actual.get("full_name");
        Assert.assertEquals(fullName, actualFullName);

    }
}
