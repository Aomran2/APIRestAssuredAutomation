import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class postAPIs {

    @Before
    public void BaseUrl(){
        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net";
    }

    @Test
    public void Activities(){

        String requestBody = "{\n" +
                "  \"id\": 0,\n" +
                "  \"title\": \"string\",\n" +
                "  \"dueDate\": \"2023-11-05T09:19:27.269Z\",\n" +
                "  \"completed\": true\n" +
                "}";

        given().
                contentType(ContentType.JSON).
                body(requestBody).
                when().
                post("/api/v1/Activities").
                then().log().body().
                assertThat().
                statusCode(200).
                body("completed",equalTo(true));
    }



    @Test
    public void authors (){

        String requestBody = "{\n" +
                "\"id\":1,\n" +
                "\"title\":\"string\",\n" +
                "\"dueDate\":\"2023-11-05T09:19:27.269Z\",\n" +
                "\"completed\":true\n" +
                "}";

        given().
                contentType(ContentType.JSON).
                body(requestBody).
                when().
                post("/api/v1/Activities").
                then().log().body().
                assertThat().
                statusCode(200).
                body("id", equalTo(1));
    }


    @Test
    public void Books(){

        String requestBody = "{\n" +
                "  \"id\": 0,\n" +
                "  \"title\": \"string\",\n" +
                "  \"description\": \"string\",\n" +
                "  \"pageCount\": 0,\n" +
                "  \"excerpt\": \"string\",\n" +
                "  \"publishDate\": \"2023-11-05T09:46:35.585Z\"\n" +
                "}";

        given().
                contentType(ContentType.JSON).
                body(requestBody).
                when().
                post("/api/v1/Books").
                then().log().body().
                assertThat().
                statusCode(200).
                body("description", equalTo("string"));
    }

    @Test
    public void coverPhotos(){

        String requestBody = "{\n" +
                "  \"id\": 0,\n" +
                "  \"idBook\": 0,\n" +
                "  \"url\": \"string\"\n" +
                "}";

        given().
                contentType(ContentType.JSON).
                body(requestBody).
                when().
                post("/api/v1/CoverPhotos").
                then().log().body().
                assertThat().
                statusCode(200).
                body("idBook",equalTo(0));
    }


    @Test
    public void users(){

        String requestBody = "{\n" +
                "  \"id\": 5,\n" +
                "  \"userName\": \"string\",\n" +
                "  \"password\": \"string\"\n" +
                "}";

        given().
                contentType(ContentType.JSON).
                body(requestBody).
                when().
                post("/api/v1/Users").
                then().log().body().
                assertThat().
                statusCode(200).
                body("id", equalTo(5)).
                header("Content-Type", containsString("application/json;"));
    }
}
