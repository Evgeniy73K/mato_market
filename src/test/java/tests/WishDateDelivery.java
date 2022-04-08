package tests;

import Classes.WebDriverSettings;
import jdk.javadoc.doclet.Reporter;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.net.SocketOption;
import java.time.Month;
import java.util.concurrent.TimeUnit;

public class WishDateDelivery extends WebDriverSettings {
    public void login(String email, String password) {
        driver.findElement(By.xpath("//a[@class=\"ac-title\"]")).click();
        driver.findElement(By.xpath("//div[@id=\"dropdown_83\"]//div[@class=\"ty-account-info__buttons buttons-container\"]/a")).click();
        WebElement login = driver.findElement(By.xpath("//div[@class=\"ty-login-popup\"]//input[@name=\"user_login\"]"));
        login.sendKeys(email);
        WebElement pass = driver.findElement(By.xpath("//div[@class=\"ty-login-popup\"]//input[@name=\"password\"]"));
        pass.sendKeys(password);
        pass.submit();

    }

    @Test
    public void checkWishDate() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Actions actions = new Actions(driver);
        System.out.println(Month.of(4));

        driver.get("http://dev.cart-power.com/vnavolykin/mato.uz/?store_access_key=key_value");

        login("evg73ul@gmail.com", "123456");

        WebElement element = driver.findElement(By.xpath("//div[@class=\"ty-login-popup\"]"));
        wait.until(ExpectedConditions.invisibilityOf(element));

        WebElement search = driver.findElement(By.xpath("//body//form[@name=\"search_form\"]/input[@type=\"text\"]"));
        search.sendKeys("shipox");
        search.submit();

        driver.findElement(By.xpath("//div[@class=\"ut2-gl__body \"]")).click();
        driver.findElement(By.xpath("//button[@id=\"button_cart_551\"]")).click();

        WebElement checkoutPopup = driver.findElement(By.xpath("//div[@class=\"cm-notification-content cm-notification-content-extended notification-content-extended  cm-auto-hide\"]"));
        wait.until(ExpectedConditions.invisibilityOf(checkoutPopup));

        driver.findElement(By.xpath("//i[@class=\"ut2-icon-outline-cart filled\"]")).click();
        driver.findElement(By.xpath("//a[@class=\"ty-btn ty-btn__primary \"]")).click();

        WebElement ajax = driver.findElement(By.xpath("//div[@id=\"ajax_loading_box\"]"));
        wait.until(ExpectedConditions.invisibilityOf(ajax));

        WebElement calendar = driver.findElement(By.xpath("//div[@class=\"litecheckout__field litecheckout__field--small\"]"));
        wait.until(ExpectedConditions.visibilityOf(calendar));
        calendar.click();
        String dateValue = "04/08/2022";
        WebElement date = driver.findElement(By.xpath("//div[@aria-label=\"" + dateValue + "\"]"));
        wait.until(ExpectedConditions.visibilityOf(date));
        date.click();

        WebElement clock = driver.findElement(By.xpath("//div[@class=\"litecheckout__field litecheckout__field--small\"][2]"));
        wait.until(ExpectedConditions.invisibilityOf(ajax));
        clock.click();

        String timeValue = "17:00";
        WebElement time = driver.findElement(By.xpath("//li[@aria-label=\"" + timeValue + "\"]"));
        wait.until(ExpectedConditions.visibilityOf(time));
        time.click();
        wait.until(ExpectedConditions.invisibilityOf(ajax));

        driver.findElement(By.xpath("//label[@class=\"litecheckout__shipping-method__wrapper js-litecheckout-toggle\"]/p[text()=\"Call 555-0123\"]")).click();
        wait.until(ExpectedConditions.invisibilityOf(ajax));
        WebElement accept = driver.findElement(By.xpath("//div[@class=\"cm-field-container\"]//input"));
        accept.click();

        driver.findElement(By.xpath("//button[@class=\"litecheckout__submit-btn \"]")).click();

        wait.until(ExpectedConditions.invisibilityOf(ajax));

        driver.findElement(By.xpath("//div[@class=\"ty-checkout-complete__order-success\"]//a")).click();

        WebElement checkDate = driver.findElement(By.xpath("//table[@class=\"ty-orders-summary__table\"]//tr[@class=\"ty-orders-summary__row\"][2]"));

        actions.moveToElement(checkDate).build().perform();
        String wishDate = checkDate.getText();
        System.out.println(wishDate);

        String[] wishTime = wishDate.split(",");
        String[] wishDate_check = wishTime[0].split(" ");
        String[] dayOfMonth = dateValue.split("/");
        String[] getValueDayofMonth = dayOfMonth[1].split("");

        System.out.println("wish time " + wishTime[1]);
        System.out.println("time Value " + timeValue);
        System.out.println("Wishdatecheck "+wishDate_check[5]);
        System.out.println("getValueDayofMonth[1] "+getValueDayofMonth[1]);

        Assert.assertTrue("Test failed",wishTime[1].equals(" "+timeValue) && wishDate_check[5].equals(getValueDayofMonth[1]));

    }

}












