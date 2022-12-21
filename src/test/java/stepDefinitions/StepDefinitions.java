package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StepDefinitions {
    final WebDriver driver = Hooks.driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    @When("the user navigates to {string}")
    public void theUserNavigatesTo(final String url) {
        driver.get(url);
    }

    /**
     * This method is broken down to 2 parts, firstly, we try to click on that dropdown using something that is always static
     * Show rows label looks like it is under the same parent element as the dropdown we aim to click, so getting the label first,
     * from it getting the parent and from the parent clicking the first div child element, the label is the paragraph
     * and the only div is the dropdown
     *
     * Then for the easier part we just find the button that contains number 20 then click it
     * @param rowsToShow
     */
    @And("the user shows rows by {string}")
    public void theUserShowsRowsBy(final String rowsToShow) {
        final WebElement dropdown = driver.findElement(By.xpath("//*[contains(text(),'Show rows')]"))
                .findElement(By.xpath("parent::*"))
                .findElement(By.tagName("div"));

        wait.until(ExpectedConditions.elementToBeClickable(dropdown));
        dropdown.click();

        final WebElement buttonToPress = driver.findElement(By.xpath(String.format("//button[contains(text(),'%s')]",rowsToShow)));

        wait.until(ExpectedConditions.elementToBeClickable(buttonToPress));
        buttonToPress.click();
    }

    @And("the user clicks {string} button")
    public void theUserClicks(final String linkText) {
        driver.findElement(By.xpath(String.format("//button[contains(text(),'%s')]",linkText))).click();
    }
}
