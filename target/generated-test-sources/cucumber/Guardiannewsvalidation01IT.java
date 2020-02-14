
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = {"/Users/naishta/Documents/workspace/seleniumassessment/src/test/resources/features/GuardianNewsValidation.feature:4"},
        plugin = {"json:/Users/naishta/Documents/workspace/seleniumassessment/target/cucumber-parallel/1.json"},
        monochrome = true,
                glue = {"stepdefs.MavenProject: com.selenium.test:selenium-assessment:1.0-SNAPSHOT @ /Users/naishta/Documents/workspace/seleniumassessment/pom.xml", "selenium"})

public class Guardiannewsvalidation01IT {
}