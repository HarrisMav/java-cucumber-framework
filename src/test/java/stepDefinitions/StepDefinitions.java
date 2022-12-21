package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class StepDefinitions {
    final private WebDriver driver = Hooks.driver;
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    private JavascriptExecutor ex=(JavascriptExecutor) driver;
    private List<List<String>> cryptoValues = new ArrayList<>();

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
        ex.executeScript("arguments[0].click()", dropdown);

        final WebElement buttonToPress = driver.findElement(By.xpath(String.format("//button[contains(text(),'%s')]",rowsToShow)));
        wait.until(ExpectedConditions.elementToBeClickable(buttonToPress));
        ex.executeScript("arguments[0].click()", buttonToPress);
    }

    /**
     * This click a button function uses JavascriptExecutor since there are a few non-interactable buttons that appear,
     * the problem comes when an ad comes through on different timings around the test lifecycle and at any moment can stop
     * the driver having access to buttons to click.
     *
     * @param linkText button text.
     */
    @And("the user clicks {string} button")
    public void theUserClicks(final String linkText) {
        ex.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath(String.format("//button[contains(text(),'%s')]",linkText))));
        final WebElement elementToClick = driver.findElement(By.xpath(String.format("//button[contains(text(),'%s')]",linkText)));
        ex.executeScript("arguments[0].click()", elementToClick);
    }

    /**
     * This method snapshots the Crypto table when it's read and then saves every result of a row into a List<String>
     * that List is saved into another List of those results through every iteration. Finally, all those are kept in this instance
     * of StepsDefinitions class as a class variable and can be accessible at any time during this test.
     *
     * There are a few instances where variables can be used, though it is an issue to do it like that since on Java the
     * variables are pass by value, therefore when we capture the element at the start of the method trying to make it a var
     * then that part might change for certain coins since the data is dynamic, therefore a Stale Element exception is thrown.
     * To resolve that I've used no variables and referenced everything whenever I needed it to make the tests robust.
     *
     * @param testCategories categories requested by the user. It can be any combination of categories as long as it retains
     *                       the same wording as on the page and this will work.This method can be re used for any configuration
     *                       of the requested categories we would like to see.
     */
    @And("the user captures Crypto data for {int} rows with headers")
    public void theUserCapturesCryptoDataForRowsWithHeaders(final int numberOfRows, final DataTable testCategories) {
        final List<String> categoryList = testCategories.transpose().asList(String.class);
        final List<Integer> categoryIndexList = new ArrayList<>();
        final List<WebElement> tableHeaders = driver.findElements(By.tagName("th"));

        for (int tableHeaderIndex = 0; tableHeaderIndex < tableHeaders.size(); tableHeaderIndex++) {
            if (categoryList.contains(tableHeaders.get(tableHeaderIndex).getText())) {
                categoryIndexList.add(tableHeaderIndex);
            }
        }

        for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
            List<String> requestedValues = new ArrayList<>();
            ex.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).get(rowIndex));
            for (final Integer categoryIndex: categoryIndexList) {
                final String requestedVal = driver.findElement(By.tagName("tbody"))
                        .findElements(By.tagName("tr")).get(rowIndex)
                        .findElements(By.tagName("td")).get(categoryIndex)
                        .getText();
                requestedValues.add(requestedVal);
            }
            cryptoValues.add(requestedValues);
        }
    }

    @And("the user selects {string} list item")
    public void theUserSelectsListItem(final String listItemText) {
        final WebElement listItemToPress = driver.findElement(By.xpath("//*[text()='"+listItemText+"']"));
        wait.until(ExpectedConditions.elementToBeClickable(listItemToPress));
        listItemToPress.click();
        System.out.println();
    }

    @And("the user clicks {string} menu button")
    public void theUserClicksMenuButton(final String menuButtonText) {
        driver.findElement(By.xpath(String.format("//button[contains(text(),'%s')]",menuButtonText))).click();
    }

    @And("the user clicks {string} slider")
    public void theUserClicksSlider(final String id) {
        driver.findElement(By.id(id)).click();
    }
}
