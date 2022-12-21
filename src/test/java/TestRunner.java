import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import stepDefinitions.Hooks;

/**
 * TestRunner example.
 * This class helps Cucumber run a features directory against a test implementation directory by using
 * CucumberOptions with parameters:
 * features = step implementation directory paths
 * glue = cucumber features file
 * plugin = generates report on defined path
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber" },
        features={ "src/test/resources/features" })
public class TestRunner{
    @BeforeClass
    public static void setUp(){
        Hooks.createChromeDriver();
    }

    @AfterClass
    public static void teardown(){
        Hooks.driver.quit();
    }
}