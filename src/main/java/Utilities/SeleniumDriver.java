package Utilities;

import Core.BaseClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SeleniumDriver extends BaseClass{
    public SeleniumDriver(Enums.BrowserType selectedBrowser) {
        browserType = selectedBrowser;
        startDriver();
    }

    public void startDriver() {
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");

        switch (browserType) {
            case FireFox:
                WebDriverManager.firefoxdriver().setup();
                Driver = new FirefoxDriver();
                Driver.manage().window().maximize();
                // <editor-fold defaultstate="collapsed" desc="Firefox Help">
/*
    NOTE: Firefox doesn't allow implicit waits anymore.
    Current gecko Driver: v0.14.0
    Suggested Firefox: v52.9
    Download from: https://ftp.mozilla.org/pub/firefox/releases/ or https://ftp.mozilla.org/pub/firefox/releases/52.9.0esr/win32/en-ZA/
                 */
// </editor-fold>
                break;
            case Chrome:
                ChromeOptions options = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                Driver = new ChromeDriver(options);
                break;

            default:
                break;
        }
        Driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        Driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        Driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
        Driver.manage().window().maximize();

    }

    public boolean navigateTo(String pageUrl) {
        try {
            Driver.get(pageUrl);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clickElementByXpath(String elementXpath) {

        try {
            waitForElementByXpath(elementXpath);
            WebElement elementToClick = Driver.findElement(By.xpath(elementXpath));
            elementToClick.click();
            System.out.println("[INFO] Successfully clicked on " + "'" + elementXpath + "'");
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean waitForElementByXpath(String elementXpath) {
        boolean elementFound = false;
        try {
            int waitCount = 0;
            System.out.println("[INFO] Waiting for " + "'" + elementXpath + "' " + waitCount);
            while (!elementFound && waitCount < 60) {
                try {
                    WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(1));
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath)));
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
                    if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath))) != null) {
                        elementFound = true;
                        System.out.println("[INFO] Element Found " + "'" + elementXpath + "' " + waitCount);
                        break;
                    }
                } catch (Exception e) {
                    elementFound = false;

                }
                waitCount++;
            }
            if (!elementFound) {
                System.err.println("[INFO] Element not Found" + "'" + elementXpath + "' " + waitCount);
            }
            if (waitCount == 10) {
                return elementFound;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return elementFound;
    }
    public boolean waitForElementByXpath(String elementXpath, int timeout) {
        boolean elementFound = false;
        try {
            int waitCount = 0;
            System.out.println("[INFO] Waiting for " + elementXpath + " timeout set to " + timeout);
            while (!elementFound && waitCount < timeout) {
                try {
                    WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(1));
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath)));
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
                    if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath))) != null) {
                        elementFound = true;
                        System.out.println("[INFO] Element Found " + "'" + elementXpath + "' " + waitCount);
                        break;
                    }
                } catch (Exception e) {
                    elementFound = false;
                }
                waitCount++;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return elementFound;
    }

    public boolean enterTextByXpath(String elementXpath, String textToEnter) {
        try {
            this.waitForElementByXpath(elementXpath);
            WebElement elementToTypeIn = Driver.findElement(By.xpath(elementXpath));
            elementToTypeIn.sendKeys(textToEnter);
            System.out.println("[INFO] Successfully entered " + "'" + textToEnter + "'");
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    public boolean scrollToElementByXpath(String elementXpath) {

        try {
            WebElement element = Driver.findElement(By.xpath(elementXpath));
            ((JavascriptExecutor) Driver).executeScript("arguments[0].scrollIntoView(true);", element);
            System.out.println("[INFO] Scrolled to element - " + elementXpath);
            return true;
        } catch (Exception e) {
            System.out.println("[INFO] Error scrolling to element - " + elementXpath + " - " + e.getStackTrace());
            return false;
        }

    }
}
