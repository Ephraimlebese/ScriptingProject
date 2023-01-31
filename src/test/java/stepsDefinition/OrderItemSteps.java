package stepsDefinition;

import Core.BaseClass;
import Core.TestFailedException;
import Core.TestRunner;
import implementation.OrderAnItemImplementation;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class OrderItemSteps extends BaseClass {
    private OrderAnItemImplementation orderAnItemImplementation;
    public OrderItemSteps() {
        orderAnItemImplementation = new OrderAnItemImplementation();
    }
    @Before
    public void beforeTest(Scenario scenario) {
        try {
            currentScenario = scenario;
        } catch (Exception ioe) {
            System.err.println("[ERROR] - Failed to read/write scenario count setup file - " + ioe.getMessage());
        }
    }
    @And("I add item {string} to the cart and validate the cart")
    public void iAddProductToTheCartAndValidateTheCart(String name) {
        checkTestResult(() ->
        {
            orderAnItemImplementation.addItemToCart(name);
            orderAnItemImplementation.validateAddedItemOnTheCart(name);
        });
    }

    @Then("I use {string} {string} postal code {string} to check out")
    public void iUsePostalCodeToCheckOut(String name, String surname, String code) {
        checkTestResult(() ->
        {
            orderAnItemImplementation.checkOut(name,surname,code);
            orderAnItemImplementation.finish();

        });
    }
    public void checkTestResult(TestRunner result){
        try {
            result.run();
        } catch (TestFailedException e) {
            Assert.fail(e.getMessage());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
