package methods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Autorization {
    public static void login (String dev, String email, String password) {
        WebDriver driver = new ChromeDriver();;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(dev);
        driver.findElement(By.xpath("//ul[@id=\"account_info_links_127\"]//a[1]")).click();
        WebElement login = driver.findElement(By.xpath("//input[@id=\"login_main_login\"]"));
        login.sendKeys(email);
        WebElement pass = driver.findElement(By.xpath("//input[@id=\"psw_main_login\"]"));
        pass.sendKeys(password);
        WebElement singIn = driver.findElement(By.xpath("//div[@class=\"ty-mainbox-container clearfix\"]//button"));
        singIn.click();

    }
}
