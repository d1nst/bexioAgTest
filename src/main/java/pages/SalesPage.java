package pages;

import com.microsoft.playwright.Page;

import static utils.Utils.*;

public class SalesPage extends BasePage {

    Page page;

    public SalesPage(Page page) {
        this.page = page;
    }

    String salesDD = "//a[contains(text(),\"Sales\")]";
    String quotesLi = "//a[contains(text(),\"Quotes\")]";
    String newQuote = "//a[@data-track-element=\"sales.quotes.create_quote\"]";
    String contactName = "//input[@id=\"autocomplete_kb_item_contact_id\"]";
    String quoteNext = "//button[contains(text(),\"Next\")]";
    String defaultBexioContact = "//div[contains(@class, \"results\")]/ul/li[1]";
    String qQuote = "//input[@id=\"kb_position_custom_amount\"]";
    String ipQuote = "//input[@id=\"kb_position_custom_unit_price\"]";
    String quoteSave = "//button[contains(text(), \"Save\")]";
    String spanDesc = "//span[@id=\"kb_position_custom_text_parent\"]";
    String descQuote = "//body[@id=\"tinymce\"]";
    String itemsTab = "//a[@role=\"presentation\" and contains(text(),\"Items\")]";
    String iFrameContent = "//iframe[contains(@id,\"custom_text\")]";
    String newQuoteDesc = "//div[contains(@id, \"PositionCustom\")]/div[contains(@class, \"desc\")]";
    String newQuoteQuantity = "//div[contains(@id, \"PositionCustom\")]/div[contains(@class, \"amt\")]";
    String newQuoteIP = "//div[contains(@id, \"PositionCustom\")]/div[contains(@class, \"single_price\")]";
    String newQuotePriceTotal = "//div[contains(@id, \"PositionCustom\")]/div[contains(@class, \"total_price\")]";
    String newQuoteStatus = "//span[contains(@class,\"label label\")]";


    public void navigateToQuotes() {
        clickElement(page, salesDD);
        clickElement(page, quotesLi);
    }

    public void createNewQuote(String desc, String quantity, String price) {
        clickElement(page, newQuote);
        clickElement(page, contactName);
        fillElement(page, contactName, "bexio");
        clickElement(page, defaultBexioContact);
        clickElement(page, quoteNext);
        fillInIframeElement(page, iFrameContent, descQuote, desc);
        fillElement(page, qQuote, quantity);
        fillElement(page, ipQuote, price);
        clickElement(page, quoteSave);
    }

    public void newQuoteWasCreated(String desc, String quantity, String price) {
        assertContains(page, newQuoteDesc, desc);
        assertContains(page, newQuoteQuantity, quantity);
        assertContains(page, newQuoteIP, price);
        int totalPrice = Integer.parseInt(quantity) * Integer.parseInt(price);
        assertContains(page, newQuotePriceTotal, Integer.toString(totalPrice));
    }

    public void checkThereIsAtLeastOneInState(String state) throws Exception {
        clickFirstElementWithLocator(page, "//tbody/tr/td/span[contains(text(),\"" + state + "\")]");
    }

    public void setNewState(String state) {
        String newState = state.toLowerCase();
        clickElement(page, "//a[contains(@data-content,\"" + newState + "\")]");
    }

    public void checkNewState(String newState) {
        assertContains(page, newQuoteStatus, newState);
    }

}