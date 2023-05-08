package pages;

import org.junit.Assert;

import com.microsoft.playwright.Page;
import static utils.Utils.*;

public class HomePage extends BasePage{

    Page page;

    public HomePage(Page page) {
        this.page = page;
    }

    //locators

    String emailField = "//input[@id=\"j_username\"]";
    String passwordField = "//input[@id=\"j_password\"]";
    String loginBtn = "//button[@type=\"submit\"]";


    public void login(String email, String password) {
        fillElement(page, emailField, email);
        fillElement(page, passwordField, password);
        clickElement(page, loginBtn);
    }
}