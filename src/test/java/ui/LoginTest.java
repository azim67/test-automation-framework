package ui;

import base.BaseTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import org.openqa.selenium.By;



public class LoginTest extends BaseTest {

    @Test
    public void userCanLoginWithValidCredentials() {
        driver.get("https://www.saucedemo.com/");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    @Test
    public void userCannotLoginWithInvalidPassword() {
        driver.get("https://www.saucedemo.com/");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "wrong_password");

        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"));
    }

    @Test
    public void lockedOutUserCannotLogin() {
        driver.get("https://www.saucedemo.com/");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user", "secret_sauce");

        Assert.assertTrue(loginPage.getErrorMessage().contains("locked out"));
    }
	@Test
public void userCanAddItemToCart() {
    driver.get("https://www.saucedemo.com/");

    LoginPage loginPage = new LoginPage(driver);
    loginPage.login("standard_user", "secret_sauce");

    driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

    Assert.assertEquals(
            driver.findElement(By.className("shopping_cart_badge")).getText(),
            "1"
    );
	}
  
}