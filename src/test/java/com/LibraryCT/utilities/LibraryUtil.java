package com.LibraryCT.utilities;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class LibraryUtil  {
   private static Faker faker=new Faker();

    public static String getToken(String username, String password){
        return  given().baseUri("http://library1.cybertekschool.com")
                .basePath("/rest/v1")
                .contentType(ContentType.URLENC)
                .formParam("email",username)
                .formParam("password",password)
                .when()
                .post("/login")
                .path("token")
                ;

    }

    public static Map<String ,Object> getRandomBook(){
        Map<String,Object> myBookMap = new LinkedHashMap<>();

        myBookMap.put("name",faker.book().title());
        myBookMap.put("isbn",faker.number().digits(8));
        myBookMap.put("year",faker.number().numberBetween(1500,2021));
        myBookMap.put("author",faker.book().author());
        myBookMap.put("book_category_id",faker.number().randomDigitNotZero());
        myBookMap.put("description",faker.chuckNorris().fact());

        return myBookMap;
    }

    public static Map<String,Object>getRandomUser(int userGroupID){

        Map<String,Object> myBookMap = new LinkedHashMap<>();

        DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy-MM-dd");

        myBookMap.put("full_name",faker.name().fullName());
        myBookMap.put("email",faker.internet().emailAddress());
        myBookMap.put("password",faker.internet().password(8,15));
        myBookMap.put("user_group_id",userGroupID);
        myBookMap.put("status","ACTIVE");
        myBookMap.put("start_date",df.format(LocalDate.now()));
        myBookMap.put("end_date",df.format(LocalDate.now().plusMonths(2)));
        myBookMap.put("address",faker.address().fullAddress());

        return myBookMap;





    }



}
