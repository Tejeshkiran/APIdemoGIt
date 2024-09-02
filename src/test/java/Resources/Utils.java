package Resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class Utils {
    static RequestSpecification req;
    public RequestSpecification requestSpecification() throws IOException {

    if(req==null) {
        PrintStream stream = new PrintStream(new FileOutputStream("logging.txt"));
        req = new RequestSpecBuilder().setBaseUri(getBaseUrl())
                .addQueryParam("key", "qaclick123")
                .addFilter(RequestLoggingFilter.logRequestTo(stream))
                .addFilter(ResponseLoggingFilter.logResponseTo(stream))
                .setContentType(ContentType.JSON).build();
        return req;
    }
        return req;
    }
    public ResponseSpecification responseSpecification()
    {
        ResponseSpecification resspec =new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();
        return resspec;
    }
    public String getBaseUrl() throws IOException {
        Properties prop = new Properties();
        FileInputStream Fis = new FileInputStream("C:\\Users\\KiranTT\\IdeaProjects\\RestAPiProjects\\Practice-CuccumberBDD-Framework\\src\\test\\java\\Resources\\global.properties");
        prop.load(Fis);
        String baseUrl =  prop.getProperty("baseUrl");
        return baseUrl;
    }

    public String getResponseParameterValue(String KeyParameter, Response response)
    {
        JsonPath js = new JsonPath(response.asString());
        return js.getString(KeyParameter);

    }
}
