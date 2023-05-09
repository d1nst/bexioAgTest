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

    public static void clickFirstElementWithLocator(final Page page, String element) throws Exception {
        page.waitForSelector(element);
        List<String> elements = page.locator(element).allTextContents();
        System.out.println("A total of " + elements.size() + " elements were found");
        try {
            page.click(element + "[1]");
        } catch (Exception e) {
            throw new Exception("There are no elements found");
        }
    }
}
