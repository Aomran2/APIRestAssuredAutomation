import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.tngtech.java.junit.dataprovider.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@RunWith(DataProviderRunner.class)
public class restAssured {

    public static RequestSpecification requestSpecification;

    @BeforeClass
    public static void baseURL(){
        requestSpecification = new RequestSpecBuilder().
                setBaseUri("http://zippopotam.us").build();
    }

    @DataProvider
    public static Object[][] zipCodesAndPlaces() {
        return new Object[][]{
                {"us", "90210", "Beverly Hills"},
                {"us", "12345", "Schenectady"},
                {"ca", "B2R", "Waverley"}
        };
    }

    @Test
    @UseDataProvider("zipCodesAndPlaces")
    public void zipCodeAPI(String countryCode, String zipCode, String placeName) {

        given().
                spec(requestSpecification).
                pathParam("countryCode", countryCode).pathParam("zipCode", zipCode).
                when().
                get("/{countryCode}/{zipCode}").
                then().
                assertThat().
                body("places[0].'place name'", equalTo(placeName));
    }

    @Test
    public void zipCodeStatusCode(){
        given().
                spec(requestSpecification).
                when().
                get("/us/90210").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void zipCodeContentType(){
        given().
                spec(requestSpecification).
                when().
                get("/us/90210").
                then().
                assertThat().
                contentType("application/json");
//                contentType(ContentType.JSON);
    }

    @Test
    public void zipCodeLog(){
        given().
                spec(requestSpecification).
                log().all().
                get("/us/90210").
                then().
                log().body();
    }

    @Test
    public void zipCodeCaliBody(){
        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                body("places[0].'state'",equalTo("California"));
    }

    @Test
    public void zipCodePlaceNameSize(){
        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                body("places.'place name'", hasSize(1));
    }
}
