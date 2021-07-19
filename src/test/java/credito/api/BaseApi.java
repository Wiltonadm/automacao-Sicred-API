package credito.api;

import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.*;

public class BaseApi {

        @BeforeClass
        public static void preCondicao(){

            baseURI = "http://localhost";
            basePath = "/api";
            port = 8080;
        }
}
