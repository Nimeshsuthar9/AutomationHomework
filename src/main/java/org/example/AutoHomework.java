package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AutoHomework
{
    static String expectedResultMessage = "Your registration completed";
    // creating object of type Web driver
    private static WebDriver driver;

    // expected registration confirmation message
    static String expected = "Your registration completed";

    // Create new click on Element  method for Reusability
    public static void clickOnElement(By by)
    {
        driver.findElement(by).click();
    }

    // Create new Type Text  method for Reusability
    public static void typeText(By by, String text)
    {
        driver.findElement(by).sendKeys(text);
    }

    // Create new To Get Text From Element  method for Reusability
    public static String getTextFromElement(By by)
    {
        return driver.findElement(by).getText();
    }

    // Create new Get Date and Time method for Reusability
    public static String randomDate()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmm");
        Date date = new Date();
        return dateFormat.format(date);
    }

    @BeforeMethod // Create Annotation
    // create method to  Open Browser for Reusability
    public static void openBrowser()
    {
        // instantiating object of chromedriver class
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");
    }

    @AfterMethod// Create Annotation
    // create method to Close Browser for Reusability
    public static void closeBrowser()
    {
        driver.close();
    }

    @Test // create
    public static void userShouldAbleToRegisterSuccessfully()
    {

        // implicitly wait up to 20 seconds to find each element throughout the script
        // before throwing NoSuchElementFound exception
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        // click on register option
        clickOnElement(By.className("ico-register"));

        // choose male or female
        clickOnElement(By.id("gender-male"));

        // Type first name
        typeText(By.id("FirstName"), "Nimesh");

        // Type last name
        typeText(By.id("LastName"), "Suthar");

        // Type email
        typeText(By.id("Email"), "nimesh@gmail.com");

        // Type password
        typeText(By.id("Password"), "1234567");

        // Type confirm password
        typeText(By.id("ConfirmPassword"), "1234567");

        // click on Register button
        clickOnElement(By.id("register-button"));
        // Capture text of confirmation message
        String actual = getTextFromElement(By.className("result"));
        // check actual vs expected
        Assert.assertEquals(actual,expectedResultMessage,"You are not registered");
        // compare with requirement
        if (actual.equals(expected)) // comparing two Strings using inbuilt equal() String method
        {
            System.out.println("The test is PASS");
        }
        else
        {
            System.out.println("The test is FAIL");
        }
    }
    @Test
    public static void toVerifyUserCanReferProduct()
    {
        // implicitly wait up to 20 seconds to find each element throughout the script
        // before throwing NoSuchElementFound exception
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        // click on register option
        clickOnElement(By.className("ico-register"));

        // choose male or female
        clickOnElement(By.id("gender-male"));

        // Type first name
        typeText(By.id("FirstName"), "Nimesh");

        // Type last name
        typeText(By.id("LastName"), "Suthar");

        // Type email
        typeText(By.id("Email"), "nimesh"+randomDate()+"@gmail.com");

        // Type password
        typeText(By.id("Password"), "1234567");

        // Type confirm password
        typeText(By.id("ConfirmPassword"), "1234567");

        // click on Register button
        clickOnElement(By.id("register-button"));
        //Click On Login Button
        clickOnElement(By.className("ico-login"));
        // Type Email
        typeText(By.id("Email"),"nimesh"+randomDate()+"@gmail.com");
        // Type Password
        typeText(By.id("Password"),"1234567");
        //Click on Login Button
        clickOnElement(By.xpath("//button[@class='button-1 login-button']"));
        // click on product to refer
        clickOnElement(By.xpath("//img[@title='Show details for Apple MacBook Pro 13-inch']"));
        //Click on email friend
        clickOnElement(By.xpath("//button[normalize-space()='Email a friend']"));
        //Entre the friend email
        typeText(By.xpath("//input[@id='FriendEmail']"), "ddss@gmail.com");
        //Click on send email button button
        clickOnElement(By.xpath("//button[normalize-space()='Send email']"));
    }

    @Test
    public static void toVerifyUserCanSeeAddToCartButtonOnAllProduct()
    {

        //click on electronics
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Electronics']"));

        //Click on Camera & photo
        clickOnElement(By.xpath("//a[@title='Show products in category Camera & photo'][normalize-space()='Camera & photo']"));

        //Compare First Product Add to Cart Button
        String product1 = getTextFromElement(By.xpath("//div[@class='item-grid']//div[1]//div[1]//div[2]//div[3]//div[2]//button[2]"));

        //Compare Second Product Add to Cart Button
        String product2 = getTextFromElement(By.xpath("//div[@class='center-2']//div[3]//div[1]//div[2]//div[3]//div[2]//button[1]"));

        //Compare Third Product Add to Cart Button
        String product3 =getTextFromElement(By.xpath("//div[@class='center-2']//div[3]//div[1]//div[2]//div[3]//div[2]//button[1]"));
        if(product1 == product2)
        {
            if (product2 == product3)
            {
                System.out.println("There is A Cart Button");
            }
        }
        else
        {
            System.out.println("There is Cart Button Missing");
        }
    }
}
