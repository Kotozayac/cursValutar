import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.*;

public class compensationModule {

    public static WebDriver driver = new ChromeDriver();

    public static void navigateToCompMod() throws Exception {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver.manage().window().maximize();

        driver.get("http://localhost/");
        String[] credent = credentials();
        driver.findElement(By.name("email")).sendKeys(credent[0]);
        driver.findElement(By.name("password")).sendKeys(credent[1]);
        driver.findElement(By.name("button")).click();
        Thread.sleep(5000);
        WebElement mainMenu = driver.findElement(By.xpath("//a[@href='/compensation']"));
        Actions action = new Actions(driver);
        action.moveToElement(mainMenu).perform();
        Thread.sleep(5000);
        WebElement compMod = driver.findElement(By.xpath("//span[contains(text(),'Compensation')]"));
        action.moveToElement(compMod).click().build().perform();
        Thread.sleep(5000);
    }

    public static String[] credentials() throws Exception {
        File file = new File("/home/anton/Desktop/hs-cm/credentials.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String[] cred = new String[2];
        cred[0] = br.readLine();
        cred[1] = br.readLine();
        return cred;
    }
}
