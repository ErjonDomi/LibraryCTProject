package com.LibraryCT.step_definitions;

import com.LibraryCT.utilities.ConfigurationReader;
import com.LibraryCT.utilities.DB_Utility;
import com.LibraryCT.utilities.LibraryUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import org.junit.Assert;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.basePath;

public class AddingBooksWithApiEndPoint {
    Map<String, Object> myBookMap;
    String myToken=LibraryUtil.getToken("librarian69@library", "KNPXrm3S");
    int bookId;

    @Given("new book is added using api endpoint")
    public void new_book_is_added_using_api_endpoint() {
        myBookMap = LibraryUtil.getRandomBook();
        baseURI = "http://library1.cybertekschool.com";
        basePath = "/rest/v1";

        bookId = given()
                .log().all()
                .header("x-library-token", myToken)
                .formParams(myBookMap)
                .when()
                .post("/add_book")
                .then()
                .log().all()
                .statusCode(200)
                .extract().jsonPath().getInt("book_id");
    }

    @Then("user should verify that the book is created in database")
    public void user_should_verify_that_the_book_is_created_in_database() {
        DB_Utility.createConnection();
        String query = "select name from books where id=" + bookId;
        DB_Utility.runQuery(query);
        Map<String, String> map = DB_Utility.getRowMap(1);
        Assert.assertEquals(map.get("name"), myBookMap.get("name"));
    }

}
