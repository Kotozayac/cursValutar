import org.openqa.selenium.By;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class compModAddManually extends compensationModule {
    public static void main(String[] args) throws Exception{
        addManually();
    }

    private static void addManually() throws Exception{
        navigateToCompMod();
        driver.findElement(By.xpath("//button/span[contains(text(),'Add')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//h3[text()='Add KZS']/ancestor::div[@class='vs-popup']//span[text()='Begin date']/..//input[@placeholder='Select Date']")).click();
        Thread.sleep(2000);
        try {
            driver.findElement(By.xpath("//h3[text()='Add KZS']/ancestor::div[@class='vs-popup']//span[@class='cell day' and text()='1']")).click();
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            driver.findElement(By.xpath("//h3[text()='Add KZS']/ancestor::div[@class='vs-popup']//span[contains((@class),'cell day weekend') and text()='1']")).click();
        } catch (Exception e) {
            System.out.println(e);
        }
        Thread.sleep(2000);
        driver.findElement(By.xpath("//h3[text()='Add KZS']/ancestor::div[@class='vs-popup']//span[text()='End date']/..//input[@placeholder='Select Date']")).click();
        Thread.sleep(2000);
        try {
            driver.findElement(By.xpath("//h3[text()='Add KZS']/ancestor::div[@class='vs-popup']//span[@class='cell day' and text()='31']")).click();
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            driver.findElement(By.xpath("//h3[text()='Add KZS']/ancestor::div[@class='vs-popup']//span[contains((@class),'cell day weekend') and text()='31']")).click();
        } catch (Exception e) {
            System.out.println(e);
        }
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@aria-autocomplete='list']")).sendKeys("Agen");
        Thread.sleep(5000);
        String medName = driver.findElement(By.id("vs3__option-0")).getText();
        driver.findElement(By.id("vs3__option-0")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//h3[text()='Add KZS']/ancestor::div[@class='vs-popup']//span[text()='Medication indicator']/..//input")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//li[@data-text='R'])[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.name("nvdPrice")).sendKeys("1.75");
        driver.findElement(By.name("referencePrice")).sendKeys("1.76");
        driver.findElement(By.xpath("//h3[text()='Add KZS']/ancestor::div[@class='vs-popup']//span[text()=' Submit ']")).click();
        Thread.sleep(5000);


        double val75 = 1.75*0.75;
        double val1 = roundVal(val75);
        String value1 = Double.toString(val1);
        double val50 = 1.75*0.5;
    }

    private static double roundVal(double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        double val = bd.doubleValue();
        System.out.println("75% of ref price: " + val);
        return val;
    }
}
