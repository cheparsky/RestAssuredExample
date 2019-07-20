package cheparsky.runners;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "target/test-classes/features",
        glue = {"cheparsky.cucumberSteps"},
        plugin = {"pretty"},
        tags = {"@Test"}
)

public class WorkingTests {
}
