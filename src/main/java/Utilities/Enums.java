package Utilities;

public class Enums {
    public enum BrowserType {
        FireFox, Chrome;
    }
    public enum Environment {
        SAUCE("https://www.saucedemo.com/", "standard_user", "secret_sauce");

        public final String url, username, password;

        private Environment(String url, String username, String password) {
            this.url = url;
            this.username = username;
            this.password = password;

        }

    }
}
