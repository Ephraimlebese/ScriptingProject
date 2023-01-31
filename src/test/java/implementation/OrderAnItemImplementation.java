package implementation;

import Core.BaseClass;
import Core.TestFailedException;
import org.openqa.selenium.By;
import pageObjects.OrderAnItemPageObjects;

public class OrderAnItemImplementation extends BaseClass {
    public boolean addItemToCart(String name) throws TestFailedException {


        if (!seleniumDriverInstance.scrollToElementByXpath(OrderAnItemPageObjects.addProductToCartXpath(name))) {
            throw new TestFailedException("Failed to scroll ADD TO CART button for: " + name);
        }

        if (!seleniumDriverInstance.clickElementByXpath(OrderAnItemPageObjects.addProductToCartXpath(name))) {
            throw new TestFailedException("Failed to click ADD TO CART button for: " + name);
        }

        totalProduct++;
        double price = Double.parseDouble( Driver.findElement(By.xpath(OrderAnItemPageObjects.productPriceXpath(name))).getText().substring(1));
        totalProductPrice = totalProductPrice + price;
        if (!seleniumDriverInstance.scrollToElementByXpath(OrderAnItemPageObjects.totalProductAddedTOCartXpath(totalProduct))) {
            throw new TestFailedException("Failed to scroll to cart");
        }

        if (!seleniumDriverInstance.waitForElementByXpath(OrderAnItemPageObjects.totalProductAddedTOCartXpath(totalProduct))) {
            throw new TestFailedException("Failed to click add: " + name + " to cart");
        }

        takeAScreenShot("Added " + name + " to cart successfully ");

        return true;
    }
    public boolean validateAddedItemOnTheCart(String name) throws TestFailedException {

        if (!seleniumDriverInstance.scrollToElementByXpath(OrderAnItemPageObjects.totalProductAddedTOCartXpath(totalProduct))) {
            throw new TestFailedException("Failed to scroll to cart");
        }

        if (!seleniumDriverInstance.clickElementByXpath(OrderAnItemPageObjects.totalProductAddedTOCartXpath(totalProduct))) {
            throw new TestFailedException("Failed to click add: " + name + " to cart");
        }
        if (!seleniumDriverInstance.waitForElementByXpath(OrderAnItemPageObjects.yourCartPageXpath())) {
            throw new TestFailedException("Failed to navigate to the cart page");
        }

        if (!seleniumDriverInstance.waitForElementByXpath(OrderAnItemPageObjects.productOnTheCartXpath(name))) {
            throw new TestFailedException("Failed to add " + name + " on the cart ");
        }

        takeAScreenShot( name + " was added on the cart successfully");

        if (!seleniumDriverInstance.clickElementByXpath(OrderAnItemPageObjects.continueShoppingXpath())) {
            throw new TestFailedException("Failed to click continue shopping button ");
        }

        return true;
    }
    public boolean checkOut(String name, String surname, String code) throws TestFailedException {

        if (!seleniumDriverInstance.clickElementByXpath(OrderAnItemPageObjects.totalProductAddedTOCartXpath(totalProduct))) {
            throw new TestFailedException("Failed to click add item " + name + " to cart");
        }
        if (!seleniumDriverInstance.waitForElementByXpath(OrderAnItemPageObjects.yourCartPageXpath())) {
            throw new TestFailedException("Failed to navigate to the cart page");
        }

        if (!seleniumDriverInstance.clickElementByXpath(OrderAnItemPageObjects.checkOutButtonXpath())) {
            throw new TestFailedException("Failed to click checkout button");
        }

        if (!seleniumDriverInstance.waitForElementByXpath(OrderAnItemPageObjects.checkOutYourInfoPageXpath())) {
            throw new TestFailedException("Failed to navigate to Checkout: Your Information page");
        }

        if (!seleniumDriverInstance.enterTextByXpath(OrderAnItemPageObjects.nameInputXpath(), name)) {
            throw new TestFailedException("Failed to enter first name");
        }
        if (!seleniumDriverInstance.enterTextByXpath(OrderAnItemPageObjects.surnameInputXpath(), surname)) {
            throw new TestFailedException("Failed to enter last name");
        }
        if (!seleniumDriverInstance.enterTextByXpath(OrderAnItemPageObjects.postalCodeXpath(), code)) {
            throw new TestFailedException("Failed to enter a postal code");
        }

        takeAScreenShot("Successfully navigated to Checkout: Information page and capture details");

        if (!seleniumDriverInstance.clickElementByXpath(OrderAnItemPageObjects.continueButtonXpath())) {
            throw new TestFailedException("Failed to click the continue button");
        }

        return true;
    }
    public boolean finish() throws TestFailedException {

        if (!seleniumDriverInstance.waitForElementByXpath(OrderAnItemPageObjects.checkOutOverViewXpath())) {
            throw new TestFailedException("Failed to navigate to Checkout: Overview page");
        }
        if (!seleniumDriverInstance.scrollToElementByXpath(OrderAnItemPageObjects.totalXpath())) {
            throw new TestFailedException("Failed to scroll to total ");
        }

        String srtPrice = Driver.findElement(By.xpath(OrderAnItemPageObjects.totalXpath())).getText().split(":")[1];
        String srtTax = Driver.findElement(By.xpath(OrderAnItemPageObjects.taxXpath())).getText().split(":")[1];

        double total = Double.parseDouble(srtPrice.trim().substring(1));
        double tax = Double.parseDouble(srtTax.trim().substring(1));

        if(total != totalProductPrice){
            throw new TestFailedException("Failed to expected value is "+totalProductPrice+" actual value :" +total);
        }

        takeAScreenShot("Item total :"+totalProductPrice+"<br>" + "Total including tax: "+ (total + tax));

        if (!seleniumDriverInstance.clickElementByXpath(OrderAnItemPageObjects.finishButtonXpath())) {
            throw new TestFailedException("Failed to click finish button");
        }
        if (!seleniumDriverInstance.scrollToElementByXpath(OrderAnItemPageObjects.checkOutCompletePageXpath())) {
            throw new TestFailedException("Failed to scroll to checkout complete");
        }

        if (!seleniumDriverInstance.waitForElementByXpath(OrderAnItemPageObjects.checkOutCompletePageXpath())) {
            throw new TestFailedException("Failed to navigate to checkout complete page");
        }

        if (!seleniumDriverInstance.waitForElementByXpath(OrderAnItemPageObjects.THANKYOUFORYOURORDERXpath())) {
            throw new TestFailedException("Failed to complete the order");
        }

        takeAScreenShot("Successfully ordered the item");

        return true;
    }
}
