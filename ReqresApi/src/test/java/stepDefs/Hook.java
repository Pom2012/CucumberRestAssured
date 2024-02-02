package stepDefs;

import baseApi.BaseApi;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hook {



    @Before
    public void setupBefore(Scenario scenario) throws Exception {
        System.out.println("*************************************************************************************");
        System.out.println("        Scenario: "+scenario.getName()+" has started");
        System.out.println("*************************************************************************************");
    }

    @After
    public void tearDownAfter(Scenario scenario) throws Exception {
        if (BaseApi.response == null) {
            scenario.write("Response: no response");
        }else {
            System.out.println("*************************************************************************************");
            System.out.println("        Scenario: "+scenario.getName()+" is completed");
            System.out.println("        Response: "+BaseApi.response.asString());
            System.out.println("        Status code: "+BaseApi.response.getStatusCode());
            System.out.println("*************************************************************************************");
        }

    }
}
