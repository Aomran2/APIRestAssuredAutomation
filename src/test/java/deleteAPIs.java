import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class deleteAPIs {

    @Before
    public void URL(){
        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net";
    }

    @Test
    public void deleteActivities(){

        given().
                contentType(ContentType.JSON);
        when().
                delete("/api/v1/Activities/0").
                then().
                assertThat().
                statusCode(200);
    }


    @Test
    public void deleteAuthors(){

        given().
                contentType(ContentType.JSON).
                when().
                delete("/api/v1/Authors/1").
                then().
                assertThat().
                statusCode(200);
    }


    @Test
    public void deleteBooks(){

        given().
                contentType(ContentType.JSON).
                when().
                delete("/api/v1/Books/2").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void deleteCoverPhotos(){

        given().
                contentType(ContentType.JSON).
                when().
                delete("/api/v1/CoverPhotos/5").
                then().
                assertThat().
                statusCode(200);
    }


    @Test
    public void deleteUsers(){

        given().
                contentType(ContentType.JSON).
                when().
                delete("/api/v1/Users/10").
                then().
                assertThat().
                statusCode(200);
    }
}
