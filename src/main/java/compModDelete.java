import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class compModDelete extends compensationModule {
    public static void main(String[] args) throws Exception{
        delete();
    }

    private static void delete() throws Exception {
        navigateToCompMod();
        Thread.sleep(5000);
        List<WebElement> el = driver.findElements(By.xpath("//div[@class='con-vs-tooltip ml-2']/button"));
        el.get(0).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@class='vs-component vs-button vs-dialog-accept-button vs-button-danger vs-button-filled']")).click();
        Thread.sleep(5000);
        el.get(2).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@class='vs-component vs-button vs-dialog-accept-button vs-button-danger vs-button-filled']")).click();
        Thread.sleep(5000);
        el.get(4).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@class='vs-component vs-button vs-dialog-accept-button vs-button-danger vs-button-filled']")).click();
        Thread.sleep(5000);
        el.get(5).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@class='vs-component vs-button vs-dialog-accept-button vs-button-danger vs-button-filled']")).click();
        Thread.sleep(5000);

        driver.close();
    }
}
