import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class fakeRestApisRevision {

    public static RequestSpecification requestSpecification;

    @Before
    public void BaseURL(){
        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net";
    }

    @BeforeClass
    public static void baseURL(){
        requestSpecification = new RequestSpecBuilder().
                setBaseUri("https://fakerestapi.azurewebsites.net").build();
    }


}
