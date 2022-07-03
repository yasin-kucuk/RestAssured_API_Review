package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.*;

public class _3_spartanTestWithParameters {

    @BeforeClass
    public void beforeClass(){
        baseURI= "http://api.cybertektraining.com";
    }
    String cturl = "http://api.cybertektraining.com";
    String sturl = "http://3.30.189.73:8000";


    /*
    Given accept type is Json
    And Id parameter value is 26032
    When user sends GET request to /api/spartans/{id}
    Then response status code should be 200
    And response content-type: application/json;charset=UTF-8
    And "Blythe" should be in response payload
     */

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                            .and().pathParam("id",26032)
                            .when().get("/student/{id}");

        assertEquals(response.statusCode(),200);

        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        response.body().prettyPrint();
        assertTrue(response.body().asString().contains("chrissy121@gmail.com"));

    }

    /*
   Given accept type is Json
   And Id parameter value is 500
   When user sends GET request to /student/{id}
   Then response status code should be 404
   And response content-type: application/json;charset=UTF-8
   And "Student with id = 500 NOT FOUND!" should be in response payload
    */

    @Test
    public void negativePathParam() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",500)
                .when().get("/student/{id}");

        response.prettyPrint();

        assertEquals(response.statusCode(),404);

        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        assertTrue(response.body().asString().contains("Student with id = 500 NOT FOUND!"));

    }

   /*
   Given accept type is Json
   And query parameter values are:
   gender|Female
   nameContains|e
   And Id parameter value is 500
   When user sends GET request to /student/{id}
   Then response status code should be 200
   And response content-type: application/json;charset=UTF-8
   And "Female" should be in response payload
   And "Janette" should be in response payload
    */

    @Test
    public void positiveTestWithQueryParams() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender","Female")
                .and().queryParam("nameContains","e")
                .when().get("/student/{id}");

        response.prettyPrint();

        assertEquals(response.statusCode(),200);

        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));

    }

    public void positiveTestWithQueryParamWithMaps() {

        //create a map and add query parameters
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("gender","Female");
        queryMap.put("nameContains","e");

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when().get("/student/{id}");

        response.prettyPrint();

        assertEquals(response.statusCode(),200);

        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        assertTrue(response.body().asString().contains("Female"));

        assertTrue(response.body().asString().contains("Janette"));

    }

}
