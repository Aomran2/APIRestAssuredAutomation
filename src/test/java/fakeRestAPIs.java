import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class fakeRestAPIs {

    public static RequestSpecification requestSpecification;
    public static ResponseSpecification responseSpecification;

    @BeforeClass
    public static void createReqSpec() {
        requestSpecification = new RequestSpecBuilder().
                setBaseUri("https://fakerestapi.azurewebsites.net/").build();
    }

    @BeforeClass
    public static void createResponseSpec(){
        responseSpecification = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).build();
    }


    @Test
    public void getActivities() {

        given().
                spec(requestSpecification).
                when().
                get("api/v1/Activities").
                then().
                spec(responseSpecification).
                assertThat().statusCode(200).
                log().body();
    }


    @Test
    public void firstActivity() {
        given().
                spec(requestSpecification).
                when().
                get("api/v1/Activities").
                then().
                spec(responseSpecification).
                assertThat().
                body("[0].'title'", equalTo("Activity 1"));
    }


//    @Test
//    public void numberOfElement() {
//        Response response = given().
//                spec(requestSpecification).
//                when().
//                get("api/v1/Activities").
//                then().
//                extract().
//                response();
//
//        String responseBody = response.getBody().asString();
//        int noOfElements = responseBody.split("\"element\":").length;
//        assertThat(noOfElements, equalTo(1));
//    }


    @Test
    public void sumArrayElements(){
        Response response = given().
                spec(requestSpecification).
                when().
                get("api/v1/Activities").
                then().
                spec(responseSpecification).
                extract().
                response();

        int sumOfElements = response.path("arrayName.size()");
        assertThat(sumOfElements, equalTo(30));
    }


    @Test
    public void checkContentType(){
        given().
                spec(requestSpecification).
                when().
                get("api/v1/Activities").
                then().
                spec(responseSpecification).
                assertThat().
                contentType(ContentType.JSON);
    }

    @Test
    public void checkTitle(){

        String title =
                given().
                spec(requestSpecification).
                when().
                get("api/v1/Activities").
                then().extract().
                path("[1]. 'title'");
        Assert.assertEquals(title, "Activity 2");
    }
}