package pageObjects;

public class LoginPageObject {
    public static String SwagLabsXPageXpath(){
        return "//div[@class='login_logo']";
    }
    public static String userNameXpath(){
        return "//input[@id='user-name']";
    }
    public static String userPassWordXpath(){
        return "//input[@id='password']";
    }
    public static String loginButtonXpath(){
        return "//input[@id='login-button']";
    }
    public static String productsPageXpath(){
        return "//span[text()='Products']";
    }
}
