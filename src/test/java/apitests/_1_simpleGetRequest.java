package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class _1_simpleGetRequest {

    String hrurl = "https://petstore.swagger.io/v2/store/order/5";

    @Test
    public void test1(){
        Response response = RestAssured.get(hrurl);
        //print the status code
        System.out.println("response.statusCode() = " + response.statusCode());

        //print the json body
        response.prettyPrint();
    }



    /*given accept type is json
    when user sends get request to regions endpoint
    Then response status code must be 200
    and body is json format
     */
    @Test
    public void test2(){
        //accept type is json
        Response response=RestAssured.given().accept(ContentType.JSON)
                                    .when().get(hrurl);
        //verify response status code is 200
        Assert.assertEquals(response.statusCode(),200);

        System.out.println("response.contentType() = " + response.contentType());

        //verify content type is json
        Assert.assertEquals(response.contentType(),"application/json");

    }
    @Test
    public void test3(){

        RestAssured.given().accept(ContentType.JSON)
                .when().get(hrurl)
                .then().assertThat().statusCode(200)
                .and().contentType("application/json"); //we don't need to write and it is useless
                                                        //we write it to understand commands easily

    }


    /*
    Given accept type is json
    When user sends get request to regions/2
    Then response status code must be 200
    and body is json format
    and response body contains placed
     */

    @Test
    public void test4(){
        Response response = given().accept(ContentType.JSON)
                            .when().get(hrurl);

        //verify status code
        Assert.assertEquals(response.getStatusCode(),200);

        //verify content type
        Assert.assertEquals(response.contentType(),"application/json");

        //verify body contains placed
        Assert.assertTrue(response.body().asString().contains("placed"));






    }



}
