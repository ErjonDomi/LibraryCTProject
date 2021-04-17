package com.LibraryCT.step_definitions;

import com.LibraryCT.utilities.ConfigurationReader;
import com.LibraryCT.utilities.DB_Utility;
import com.LibraryCT.utilities.LibraryUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.*;

public class DashboardStatsApi {

    String bookCount;
    String borrowedBooks;
    String users;

    @Given("when user access api and check dashboard stats")
    public void when_user_access_api_and_check_dashboard_stats() {
        String token = LibraryUtil.getToken(ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password"));
        Response response = given().baseUri("http://library1.cybertekschool.com")
                .basePath("/rest/v1")
                .header("x-library-token", token)
                .when()
                .get("/dashboard_stats")
                .then()
                .statusCode(200)
                .extract().response();
        bookCount = response.path("book_count");
        borrowedBooks = response.path("borrowed_books");
        users = response.path("users");
        System.out.println(bookCount);
        System.out.println("borrowedBooks = " + borrowedBooks);
        System.out.println("users = " + users);


    }

    @Then("User verify that the book count is same in database")
    public void user_verify_that_the_book_count_is_same_in_database() {
        /**
         * select count(id) from books;
         */
        DB_Utility.createConnection();

        String query = "select count(id) from books";
        DB_Utility.runQuery(query);
        DB_Utility.getColumnDataAsList(1).get(0);
        Assert.assertEquals(DB_Utility.getColumnDataAsList(1).get(0), bookCount);
        DB_Utility.destroy();


    }

    @Then("User verify that the borrowed books count  is same in database")
    public void user_verify_that_the_borrowed_books_count_is_same_in_database() {
        /**
         * select count(id)from book_borrow;
         */
        DB_Utility.createConnection();
        String query = "select count(id)from book_borrow  where returned_date is null ;";
        DB_Utility.runQuery(query);
        DB_Utility.getColumnDataAsList(1).get(0);
        Assert.assertEquals(DB_Utility.getColumnDataAsList(1).get(0), borrowedBooks);
        DB_Utility.destroy();
    }

    @Then("User verify that the users  count  is same in database")
    public void user_verify_that_the_users_count_is_same_in_database() {
        /**
         * select  count(id) from users;
         */
        DB_Utility.createConnection();
        String query = "select  count(id) from users";
        DB_Utility.runQuery(query);
        DB_Utility.getColumnDataAsList(1).get(0);
        Assert.assertEquals(DB_Utility.getColumnDataAsList(1).get(0), users);
        DB_Utility.destroy();
    }

}
