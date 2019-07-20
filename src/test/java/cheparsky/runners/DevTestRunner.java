package cheparsky.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "target/test-classes/features",
        glue = {"cheparsky.cucumberSteps"},
        plugin = {"pretty"},
        tags = {"@dev"}
)

public class DevTestRunner {
}
