package daisy.pages;

import org.openqa.selenium.WebDriver;

public class BasePage {
protected static WebDriver driver;

/*
 * Constructor injecting the WebDriver interface
 *
 * @param webDriver
 */
public BasePage(WebDriver driver) {
        this.driver = driver;
        }

public String getTitle() {
        return driver.getTitle();
        }

        }
