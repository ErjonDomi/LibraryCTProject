package com.LibraryCT.step_definitions;

import com.LibraryCT.pages.BooksPage;
import com.LibraryCT.pages.LoginPage;
import com.LibraryCT.utilities.DB_Utility;
import com.LibraryCT.utilities.Driver;
import com.LibraryCT.utilities.LibraryUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class BookE2EApiUIDb {
    LoginPage loginPage = new LoginPage();
    BooksPage booksPage = new BooksPage();
    Map<String, Object> myBookMap;
    String myToken = LibraryUtil.getToken("librarian69@library", "KNPXrm3S");
    int bookId;
    String name;

    @Given("user creates a book with api endpoint {string}")
    public void user_creates_a_book_with_api_endpoint(String string) {

        myBookMap = LibraryUtil.getRandomBook();
        myBookMap.put("name", string);

        bookId = given()
                .baseUri("http://library1.cybertekschool.com")
                .basePath("/rest/v1")
                .log().all()
                .header("x-library-token", myToken)
                .formParams(myBookMap)
                .when()
                .post("/add_book")
                .then()
                .log().all()
                .statusCode(200)
                .extract().jsonPath().getInt("book_id");

        name = myBookMap.get("name").toString();

    }

    @When("user login as librarian")
    public void user_login_as_librarian() {
        Driver.getDriver().get("http://library1.cybertekschool.com/");
        loginPage.logInAsLibrarian("librarian69@library","KNPXrm3S");
        booksPage.userModule.click();

    }

    @Then("user should verify that the book is created")
    public void user_should_verify_that_the_book_is_created() {
        booksPage.searchBox.sendKeys(name);
        Assert.assertEquals(booksPage.getName(name),name);
    }

    @Then("user should verify that the book is created in database as well")
    public void user_should_verify_that_the_book_is_created_in_database_as_well() {

        DB_Utility.createConnection();
        String query ="select name from books where name='"+name+"'";
        DB_Utility.runQuery(query);
        Assert.assertEquals(DB_Utility.getFirstData(),name);
    }

}
