package Core;

import Utilities.Enums;
import Utilities.SeleniumDriver;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class BaseClass {
    public static Enums.BrowserType browserType;
    public static SeleniumDriver seleniumDriverInstance;
    public static Enums.Environment testEnvironment;
    public static WebDriver Driver;
    public static Scenario currentScenario;
    public static int totalProduct = 0;
    public static double totalProductPrice = 0;
    public static void takeAScreenShot(String massege) {
        final byte[] image = ((TakesScreenshot) Driver).getScreenshotAs(OutputType.BYTES);
        currentScenario.attach(image, "image/png", massege);
    }
}
