package runner;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"$featureFile"},
        plugin = {"pretty",
                "html:",
                "json:"},
        monochrome = false,
        dryRun = false,
        glue = "stepDefs")
public class ApiTestRunner {



}
