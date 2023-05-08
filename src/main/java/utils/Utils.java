package utils;

import com.microsoft.playwright.Page;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Utils {


    public static void fillInIframeElement(final Page page, String iFrameLocator, String textAreaInside, String value) {

        page.frameLocator(iFrameLocator).locator(textAreaInside).fill(value);

    }

    public static void clickElement(final Page page, String element) {
        page.waitForSelector(element);
        page.click(element);
    }

    public static void fillElement(final Page page, String element, String value) {
        page.waitForSelector(element);
        page.fill(element, value);
    }

    public static void assertContains(final Page page, String element, String value) {
        page.waitForSelector(element);
        assertThat(page.locator(element)).containsText(value);
    }

    public static void clickFirstElementWithLocator(final Page page, String element) {
        page.waitForSelector(element);
        List<String> elements = page.locator(element).allTextContents();
        if (elements.size() > 1) {
            System.out.println("There is at least one element");
            page.click(element + "[1]");
        } else {
            System.out.println("Please create at least one element with necesary details");
        }
    }

}
