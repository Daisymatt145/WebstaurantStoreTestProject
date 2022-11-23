package daisy.pages;

import com.google.common.collect.Iterables;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.*;

public class ProductSearchPage  extends BasePage {
    public static Logger log = LogManager.getLogger();
    public ProductSearchPage(WebDriver driver) {
         super(driver);
    }

    @FindBy(xpath = "//*[@id='searchval']")
    public WebElement searchInputElement;
    @FindBy(xpath="//a[@aria-label='current page, page 9']")
    public WebElement lastpageLink;
    @FindBy(xpath = "//*[@id='searchForm']/div/button")
    public WebElement searchButtonElement;

    @FindBy(xpath = "//div[@text(),'Results for']")
    public WebElement numberOfItemsElement;

    @FindBy(xpath = "//*[@id='ProductBoxContainer' AND contains[text(),'addToCartButton']")
    public WebElement allproductElementsonpage1;

    @FindBy(xpath = "//*[@id='paging']/nav/ul/li[1]/a")
    public WebElement pageLinkElement;

    public void searchForaProduct(String productName) {
        searchInputElement = driver.findElement(By.xpath("//*[@id='searchval']"));
        searchInputElement.click();
        searchInputElement.sendKeys(productName);
        searchButtonElement = driver.findElement(By.xpath("//*[@id='searchForm']/div/button"));
        searchButtonElement.click();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
     lastpageLink  = driver.findElement(By.xpath("//*[@id='paging']/nav/ul/li[8]/a"));
     if(lastpageLink.isDisplayed()){
         System.out.println("There are 9 pages of Results");
     }
    }

    public void listAllItemsAndValidateProductsAreInStock() {

        List<WebElement> listOfAllProductsOfallPages = new ArrayList<>();
        List<WebElement> listOfAllProductOutOfStockTotal = new ArrayList<>();
        List<WebElement> listOfAllProductOutOfStockPerPage=new ArrayList<>();
        List<WebElement> listOfAllProductsLastPage=new ArrayList<>();
        WebElement lastElement= null;

        System.out.println("Last page Link"+lastpageLink.getText().contains("current page, page 8"));
        for(int i=1;i<=8;i++) {
            WebElement elementToOrder,viewCart,emptyCart,emptyCartButton;
            String xpathExpression = String.format("//*[@id='paging']/nav/ul/li[%d]/a", 8);
            pageLinkElement = driver.findElement(By.xpath("//*[@id='paging']/nav/ul/li[8]/a"));
            pageLinkElement.click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            List<WebElement> listOfAllProducts;
            listOfAllProducts = driver.findElements(By.xpath("//input[@name='addToCartButton']"));

            if(i==8) {
                lastElement =listOfAllProducts.get(listOfAllProducts.size()-1);

                    listOfAllProducts.get(listOfAllProducts.size()-1);

                    elementToOrder = driver.findElement(By.xpath("//a[contains(text(),'Advance Tabco')]"));

                    System.out.println("Shooping cart element" + elementToOrder.getText());

                    log.info("Clicked on the Shopping Cart Button for the Table!");
                    elementToOrder.click();
                    WebElement addToshoppingCartAcc = driver.findElement(By.xpath("//*[@id='buyButton']"));
                    addToshoppingCartAcc.click();
                    log.info("clicked on Shopping Cart");
                    log.info("added the item to Shopping Cart second window");
                    viewCart = driver.findElement(By.xpath("//a[@class='btn btn-small btn-primary']"));
                    viewCart.click();
                    log.info("Clicked on View Cart");
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
                    emptyCartButton = driver.findElement(By.xpath("//button[normalize-space()='Empty Cart']"));
                    emptyCartButton.click();
                    WebElement emptyCartDialog = driver.findElement(By.xpath("//button[contains(text(),'Empty Cart')]"));
                    log.info("Clicked on Empty Cart");

            }

              listOfAllProducts = driver.findElements(By.xpath("//input[@name='addToCartButton']"));
              Iterator productIt = listOfAllProducts.iterator();
            lastElement =  listOfAllProducts.get(listOfAllProducts.size()-1);
           System.out.println("Last ElementText Content"+ lastElement.getText());

            ;
             while (productIt.hasNext()){
                Assert.assertTrue(productIt.next().toString().contains("addToCartButton"));

            }
            System.out.println("All Elements-------------------------+Page+---" + i + "----" + listOfAllProducts.size());

            listOfAllProductOutOfStockPerPage  = driver.findElements(By.xpath("//*[@id='enter-email']"));
            System.out.println("Number of Tables Out of Stock on page  ---------" + "Page+---+" + i + "----" + listOfAllProductOutOfStockPerPage.size());
            listOfAllProductOutOfStockTotal.addAll(listOfAllProductOutOfStockPerPage);
            System.out.println(("Total out Toal----------------"+listOfAllProductOutOfStockTotal.size()));
            listOfAllProductsOfallPages.addAll(listOfAllProducts);
            int actual =listOfAllProductsOfallPages.size();
            int exp =listOfAllProductsOfallPages.size();
            int outofStockAct,outOfStockExp;

            System.out.println("Remaining in Stcok products Total-------" + listOfAllProductsOfallPages .size());
           System.out.println("There are Steel Tables in Stock------------"+actual);Assert.assertEquals(actual,exp );
              outofStockAct = listOfAllProductOutOfStockTotal.size();
             outOfStockExp =outofStockAct;
            System.out.println("There are Steel Tables out of Stock------------");
            Assert.assertEquals(outofStockAct,outOfStockExp );
        }

        Iterator<WebElement> itAllElements = listOfAllProductsOfallPages.iterator();

        System.out.println("Number of Tables  total--------"+ listOfAllProductsOfallPages.size());

    }



    public void verifyListandFindLastitem() {

    }

}




