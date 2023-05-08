package stepdefinitions;


import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class SalesSteps extends BasePage {


    HomePage homePage;
    SalesPage salesPage;


    Page page;

    @Before

    public void initiateBrowserAndPages() {
        try {

            String browser = System.getProperty("browser") == null ? "Chrome" : System.getProperty("browser");
            String appurl = System.getProperty("appurl") == null ? "https://idp.bexio.com/login" : System.getProperty("appurl");

            //Chrome

            page = createPlaywrightPageInstance(browser);
            page.navigate(appurl);
            System.out.println("set browser ==== " + browser);
            System.out.println("created app url ==== " + appurl);
            homePage = new HomePage(page);
            salesPage = new SalesPage(page);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Given("the user logged in to the application with {string} and {string}")
    public void userLoginsToApp(String emailAddress, String password) {
//        homePage.navigateToUrl("https://idp.bexio.com/login");
        homePage.login(emailAddress, password);
    }

    @And("the user is on the Quotes page")
    public void userNavigatesToQuotes() {
        salesPage.navigateToQuotes();
    }

    @When("the user creates a new quote with {string} and {string} and {string}")
    public void theUserCreatesANewQuote(String desc, String quantity, String indPrice) {
        salesPage.createNewQuote(desc, quantity, indPrice);
    }

    @Then("the quote is successfully created with the correct values for {string} and {string} and {string}")
    public void theQuoteWasCreated(String desc, String quantity, String indPrice) {
        salesPage.newQuoteWasCreated(desc, quantity, indPrice);
    }

    @And("there is a quote in {string}")
    public void thereIsAQuoteIn(String state) {
        salesPage.checkThereIsAtLeastOneInState(state);
    }

    @When("the user marks the quote as {string}")
    public void theUserMarksTheQuoteAs(String newState) {
        salesPage.setNewState(newState);
    }

    @Then("the quote status should change to {string}")
    public void theQuoteStatusShouldChangeTo(String newState) {
        salesPage.checkNewState(newState);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (browser != null) {
            browser.close();
        }
        if (page != null) {
            page.close();
        }
    }



}