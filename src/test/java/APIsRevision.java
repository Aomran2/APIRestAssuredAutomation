import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(DataProviderRunner.class)
public class APIsRevision {

    public static RequestSpecification requestSpecification;

//    @BeforeClass
//    public static void baseURL (){
//        requestSpecification = new RequestSpecBuilder()
//                .setBaseUri("http://zippopotam.us").build();
//    }

    @Before
    public void BaseURL(){
        RestAssured.baseURI = "http://zippopotam.us";
    }

    @DataProvider
    public static Object[][] multipleParam(){
        return new Object[][]{
                {"us", "90210", "Beverly Hills"},
                {"us", "12345", "Schenectady"},
                {"ca", "B2R", "Waverley"}
        };
    }

    @Test
    @UseDataProvider("multipleParam")
    public void zipCodeAPIs (String countryCode, String zipCode, String placeName){

        given().
//                spec(requestSpecification).
                pathParam("countryCode", countryCode).pathParam("zipCode", zipCode).
                when().
                get("/{countryCode}/{zipCode}").
                then().log().body().
                assertThat().
                body("places[0].'place name'", equalTo(placeName));
    }

    @Test
    public void zipCodeStatusCode(){
        given().
//                spec(requestSpecification).
                when().
                get("/us/90210").
                then().log().body().
                assertThat().
                statusCode(200);
    }

    @Test
    public void zipCodeContentType(){
        given().
//                spec(requestSpecification).
                when().
                get("/us/90210").
                then().
                assertThat().
                contentType(ContentType.JSON);
    }

    @Test
    public void zipCodeCaliBody(){
        given().
//                spec(requestSpecification).
                when().
                get("/us/90210").
                then().
                assertThat().log().body().
                body("places[0].'state'",equalTo("California"));
    }

    @Test
    public void zipCodeAbbrBody(){
        given().
//                spec(requestSpecification).
                when().
                get("/us/90210").
                then().
                assertThat().log().body().
                body("places[0].'state abbreviation'",equalTo("CA"));
    }

    @Test
    public void zipCodeLatBody(){
        given().
//                spec(requestSpecification).
                when().
                get("/us/90210").
                then().
                assertThat().log().body().
                body("places[0].'latitude'",equalTo("34.0901"));
    }

    @Test
    public void zipCodeCTBody(){
        given().
//                spec(requestSpecification).
                when().
                get("/us/90210").
                then().
                assertThat().log().
                body().contentType(ContentType.JSON);
    }
}
