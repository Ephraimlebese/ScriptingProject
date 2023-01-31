package stepsDefinition;
import Core.BaseClass;
import Core.TestFailedException;
import Core.TestRunner;
import implementation.LoginImplementation;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import org.junit.Assert;

public class LoginSteps extends BaseClass {

    private LoginImplementation loginImplementation;
    public LoginSteps() {
        loginImplementation = new LoginImplementation();
    }
    @Before
    public void beforeTest(Scenario scenario) {
        try {
            currentScenario = scenario;
        } catch (Exception ioe) {
            System.err.println("[ERROR] - Failed to read/write scenario count setup file - " + ioe.getMessage());
        }
    }
    @Given("I open chrome browser and navigate to SWAGLABS web application")
    public void lunchBrowserAndNavigateToLogin(){
        checkTestResult(() ->
        {
            loginImplementation.login();
        });
    }

    public void checkTestResult(TestRunner result) {
        try {
            result.run();
        } catch (TestFailedException e) {
            Assert.fail(e.getMessage());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
