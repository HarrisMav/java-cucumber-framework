package stepDefinitions;

import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class StepDefinitions {
    final WebDriver driver = Hooks.driver;
    @When("the user navigates to {string}")
    public void theUserNavigatesTo(final String url) {
        driver.get(url);
    }
}
