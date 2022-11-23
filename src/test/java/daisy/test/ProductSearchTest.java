package daisy.test;
import daisy.pages.ProductSearchPage;
import org.testng.annotations.Test;
import daisy.test.BaseTest;

public class ProductSearchTest  extends BaseTest {

    @Test
    public void searchForAProductTestCase1() {
        log.info("Test Just Started");
        ProductSearchPage searchPage = new ProductSearchPage(driver);
        String productName = "stainless work table";
        searchPage.searchForaProduct(productName);
        //searchPage.numberofProductsfound();
        searchPage.listAllItemsAndValidateProductsAreInStock();

    }


}

