import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class putAPIs {

    @Before
    public void baseURL(){
        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net";
    }


    @Test
    public void putActivities(){

        String requestBody = "{\n" +
                "  \"id\": 0,\n" +
                "  \"title\": \"string\",\n" +
                "  \"dueDate\": \"2023-11-05T10:32:09.880Z\",\n" +
                "  \"completed\": true\n" +
                "}";

        given().
                contentType(ContentType.JSON).
                body(requestBody).
                when().
                put("/api/v1/Activities/1").
                then().log().body().
                assertThat().
                statusCode(200).
                body("completed", equalTo(true));
    }

    @Test
    public void putAuthors(){

        String requestBody = "{\n" +
                "  \"id\": 0,\n" +
                "  \"idBook\": 0,\n" +
                "  \"firstName\": \"string\",\n" +
                "  \"lastName\": \"string\"\n" +
                "}";

        given().
                contentType(ContentType.JSON).
                body(requestBody).
                when().
                put("/api/v1/Authors/1").
                then().log().body().
                assertThat().
                statusCode(200).
                body("firstName",equalTo("string"));
    }

    @Test
    public void putBooks(){

        String requestBody = "{\n" +
                "  \"id\": 0,\n" +
                "  \"title\": \"string\",\n" +
                "  \"description\": \"string\",\n" +
                "  \"pageCount\": 0,\n" +
                "  \"excerpt\": \"string\",\n" +
                "  \"publishDate\": \"2023-11-06T08:44:31.828Z\"\n" +
                "}";

        given().
                contentType(ContentType.JSON).
                body(requestBody).
                when().
                put("/api/v1/Books/19").
                then().log().body().
                assertThat().
                statusCode(200).
                body("description",equalTo("string")).
                contentType(ContentType.JSON).
                header("Server", equalTo("Kestrel"));
    }


    @Test
    public void putCoverPhotos(){

        String requestBody = "{\n" +
                "  \"id\": 0,\n" +
                "  \"idBook\": 0,\n" +
                "  \"url\": \"string\"\n" +
                "}";

        given().
                contentType(ContentType.JSON).
                body(requestBody).
                when().
                put("/api/v1/CoverPhotos/25").
                then().log().body().
                assertThat().
                statusCode(200).
                header("Content-Type", containsString("application/json;"));
    }


    @Test
    public void putUsers(){

        String requestBody = "{\n" +
                "  \"id\": 0,\n" +
                "  \"userName\": \"string\",\n" +
                "  \"password\": \"string\"\n" +
                "}";

        given().
                contentType(ContentType.JSON).
                body(requestBody).
                when().
                put("/api/v1/Users/45").
                then().log().body().
                assertThat().
                statusCode(200).
                body("userName", equalTo("string")).
                header("Content-Type", containsString("application/json;"));
    }
}
