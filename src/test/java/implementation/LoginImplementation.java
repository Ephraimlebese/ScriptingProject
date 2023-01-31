package implementation;

import Core.BaseClass;
import Core.TestFailedException;
import pageObjects.LoginPageObject;

public class LoginImplementation extends BaseClass {
    public boolean login() throws TestFailedException {

        if (!seleniumDriverInstance.navigateTo(testEnvironment.url)){
            throw new TestFailedException("Failed to navigate to "+testEnvironment.url);
        }

        if (!seleniumDriverInstance.waitForElementByXpath(LoginPageObject.SwagLabsXPageXpath())){
            throw new TestFailedException("Failed to navigate to "+testEnvironment.url);
        }

        takeAScreenShot("Navigated "+testEnvironment.url+" successfully ");

        if (!seleniumDriverInstance.enterTextByXpath(LoginPageObject.userNameXpath(),testEnvironment.username)){
            throw new TestFailedException("Failed to enter the user name text ");
        }

        if (!seleniumDriverInstance.enterTextByXpath(LoginPageObject.userPassWordXpath(),testEnvironment.password)){
            throw new TestFailedException("Failed to enter the password text ");
        }

        if (!seleniumDriverInstance.clickElementByXpath(LoginPageObject.loginButtonXpath())){
            throw new TestFailedException("Failed to click the login button ");
        }

        if (!seleniumDriverInstance.waitForElementByXpath(LoginPageObject.productsPageXpath())){
            throw new TestFailedException("Login failed");
        }
        takeAScreenShot("Login successfully ");

        return true;
    }
}
