package cheparsky.cucrunners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "target/test-classes/featureFolder",
        glue = {"pl.mbank.cucsteps"},
        plugin = {"pretty"},
        tags = {"@dev"}
)

public class DevTestRunner {
}
