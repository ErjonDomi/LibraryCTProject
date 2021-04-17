package com.LibraryCT.utilities;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class LibraryBaseTest {
    @BeforeAll
    public static void init(){
        baseURI  = "http://library1.cybertekschool.com" ;
        basePath = "/rest/v1" ;
    }
    @AfterAll
    public static void tearDown(){
        reset();
    }

}
