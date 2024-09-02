package Cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features/GoogleApis.feature",plugin = "json:target/jsonReports/Cucumber_Reports.json",
        glue = {"stepDefinitions"}
        )
//,tags = "@DeletePlace"
//tags ="@Addplace"
//plugin = "json:target/jsonReports/Cucumber_Reports.json"
public class TestRunner {
}
