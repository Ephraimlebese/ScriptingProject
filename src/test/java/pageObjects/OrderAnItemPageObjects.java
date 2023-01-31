package pageObjects;

public class OrderAnItemPageObjects {
    public static String addProductToCartXpath(String name){ return "//div[text()='"+name+"']//..//../..//..//button[text()='Add to cart']"; }
    public static String productOnTheCartXpath(String name){
        return "//div[text()='"+name+"']";
    }
    public static String continueShoppingXpath(){
        return "//button[@id='continue-shopping']";
    }
    public static String totalProductAddedTOCartXpath(int num){ return "//a[@class='shopping_cart_link']//span[text()='"+num+"']"; }
    public static String yourCartPageXpath(){
        return "//span[text()='Your Cart']";
    }
    public static String totalXpath(){ return "//div[@class='summary_subtotal_label']"; }
    public static String taxXpath(){ return "//div[@class='summary_tax_label']"; }
    public static String checkOutOverViewXpath(){
        return "//span[text()='Checkout: Overview']";
    }
    public static String checkOutButtonXpath(){
        return "//button[@id='checkout']";
    }
    public static String checkOutYourInfoPageXpath(){
        return "//span[text()='Checkout: Your Information']";
    }
    public static String nameInputXpath(){
        return "//input[@id='first-name']";
    }
    public static String surnameInputXpath(){
        return "//input[@id='last-name']";
    }
    public static String postalCodeXpath(){
        return "//input[@id='postal-code']";
    }
    public static String continueButtonXpath(){
        return "//input[@id='continue']";
    }
    public static String productPriceXpath(String name){ return "//div[text()='"+name+"']//..//../..//..//div[@class='inventory_item_price']"; }
    public static String finishButtonXpath(){
        return "//button[@id='finish']";
    }
    public static String checkOutCompletePageXpath(){
        return "//span[text()='Checkout: Complete!']";
    }
    public static String THANKYOUFORYOURORDERXpath(){
        return "//h2[text()='THANK YOU FOR YOUR ORDER']";
    }
}
