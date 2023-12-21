import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class chapterFour {

    public static RequestSpecification requestSpecification;
    public static ResponseSpecification responseSpecification;

    @BeforeClass
    public static void createRequestSpec (){
        requestSpecification = new RequestSpecBuilder().
                setBaseUri("http://zippopotam.us").build();
    }

    @BeforeClass
    public static void createResponseSpec(){
        responseSpecification = new ResponseSpecBuilder().
               expectStatusCode(200).
                expectContentType(ContentType.JSON).build();
    }


    @Test
    public void zipRequestWithRequestSpec (){
        given().
                spec(requestSpecification).
                when().
                get("/us/90210").
                then().
                spec(responseSpecification).
                assertThat().statusCode(200).log().body();
    }

    @Test
    public void checkPlaceName(){
        String placeName =
            given().
            spec(requestSpecification).
            when().
            get("/us/90210").
            then().extract().
                    path("places[0]. 'place name'");
        Assert.assertEquals(placeName, "Beverly Hills");

    }
}
