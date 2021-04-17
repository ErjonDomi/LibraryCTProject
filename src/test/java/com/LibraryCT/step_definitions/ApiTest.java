package com.LibraryCT.step_definitions;

import com.LibraryCT.utilities.LibraryUtil;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class ApiTest {


    Map<String, Object> myBookMap;
    String token;
    int bookId;

    @Test
    public void new_book_is_added_using_api_endpoint() {
        String myToken = LibraryUtil.getToken("librarian69@library","KNPXrm3S");
        myBookMap = LibraryUtil.getRandomBook();
        bookId = given().baseUri("http://library1.cybertekschool.com")
                .basePath("/rest/v1")
                .log().all()
                .header("x-library-token", myToken)
                .formParams(LibraryUtil.getRandomBook())
                .when()
                .post("/add_book")
                .then()
                .log().all()
                .statusCode(200)
                .extract().jsonPath().getInt("book_id");
    }

    @Test
    public void new_book_is_added_using_api_endpoint11() {
         token = given().baseUri("http://library1.cybertekschool.com")
                .basePath("/rest/v1")
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("email", "librarian69@library")
                .formParam("password", "KNPXrm3S")
                .when()
                .post("/login")
                .then()
                .extract().path("token");
        System.out.println(token);


    }


}
