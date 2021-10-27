import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class compensationModule {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver.manage().window().maximize();

        driver.get("http://localhost/");
        driver.findElement(By.name("email")).sendKeys("Anton");
        driver.findElement(By.name("password")).sendKeys("9JmVxzrQk5hi8V6");
        driver.findElement(By.name("button")).click();
    }
}
