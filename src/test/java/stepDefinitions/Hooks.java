package stepDefinitions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * stepDefinitions.Hooks.java is a class that helps the runner and our tests get started with a clean instance of the driver for every
 * test to be executed as well as the teardown process of our driver once the test is done
 */
public class Hooks {
    public static WebDriver driver;
    public static ChromeDriverService driverService;

    public static void createChromeDriver(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(setupChromeOptions());
        driverService = ChromeDriverService.createDefaultService();
        driver = new ChromeDriver(driverService, chromeOptions);
    }

    private static List<String> setupChromeOptions(){
        final List<String> chromeOptionsList = new ArrayList<>();
//        chromeOptionsList.add("--headless");
        chromeOptionsList.add("--incognito");

        return chromeOptionsList;
    }
}
