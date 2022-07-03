package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class _2_spartanGetRequest {

    String cturl = "http://api.cybertektraining.com";
    String sturl = "http://3.30.189.73:8000";

    @Test
    public void test1() {

        Response response = when().get(cturl+"/student/26032");

        System.out.println("response.statusCode() = " + response.statusCode());

        response.prettyPrint();

    }

    /*
    When user sends a get request to /student/all
    then status code should be 200
    And content type should be application/json;charset=UFT-8
    and json body should contain Clark
     */

    @Test
    public void test2(){
        //my method
        when().get(cturl + "/student/all")
                .then().assertThat().statusCode(200)
                .and().contentType("application/json;charset=UTF-8");

        //Jamal's method
        Response response = when().get(cturl + "/student/all");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json;charset=UTF-8");
        Assert.assertTrue(response.body().asString().contains("Clark"));

    }

    /*
    Given no headers provided
    When users sends GET request to /api/hello
    Then response status code should be 200
    And Content type header should be "text/plain;charset=UTF-8"
    And header should contain date
    And Content-Length should be 17
    And body should be "Hello from Sparta"
     */
    @Test
    public void test3(){
    Response response = when().get(sturl+"/api/hello");

    Assert.assertEquals(response.statusCode(),200);
    Assert.assertEquals(response.contentType(),"text/plain;charset=UTF-8");

    //verify we have headers named date
    // header method bring us inside of that header
    System.out.println("response.header(\"Date\") = " + response.header("Date"));

    //headers methods are useful to compare
    response.headers().hasHeaderWithName("Date");

    Assert.assertEquals(response.header("Content-Length"),17);

    Assert.assertTrue(response.body().asString().contains("Hello from Spartan"));


    }






}
