package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.*;

public class _4_spartanTestWithPath {

    @BeforeClass
    public void beforeClass(){
        baseURI= "http://api.cybertektraining.com";
    }
    String cturl = "http://api.cybertektraining.com";
    String sturl = "http://3.30.189.73:8000";

    /*
    given accept type is json
    And path param is 10
    When user sends a get request to "api/spartans/{id}"
    Then status code is 200
    And content-type is "application/json;charset=UTF-8"
    And response payload values match the following:
        id is 1*
        name is "Lorenza"
        gender is "Female"
        phone is 3312820936
     */

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",10)
                .when().get("api/spartans/{id}");

        assertEquals(response.statusCode(),200);

        System.out.println(response.path("id").toString());
        System.out.println(response.path("name").toString());
        System.out.println(response.path("gender").toString());
        System.out.println(response.path("phone").toString());

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        assertEquals(id,10);
        assertEquals(name,"Lorenza");
        assertEquals(gender,"Female");
        assertEquals(phone,"3312820936l"); //put l at the end of the number because we declared phone as long


    }


}
