package cheparsky.cucrunners;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "target/test-classes/featureFolder",
        glue = {"pl.mbank.cucsteps"},
        plugin = {"pretty"},
        tags = {"@Negative"}
)

public class TestRunner {
}
