package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void BeforeScenarios() throws IOException {
        StepDefinitions sd = new StepDefinitions();
        if(StepDefinitions.place_id==null) {
            sd.add_payload_with("joe", "French", "USA");
            sd.user_calls_with_http_request("AddPlaceAPI", "POST");
            // String place_id = sd.getResponseParameterValue("place_id", sd.response);
        }


    }



}
