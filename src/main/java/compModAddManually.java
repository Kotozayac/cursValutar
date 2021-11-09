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
            driver.findElement(By.xpath("(//h3[text()='Add KZS']/ancestor::div[@class='vs-popup']//span[@class='cell day' and text()='30'])[2]")).click();
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            driver.findElement(By.xpath("(//h3[text()='Add KZS']/ancestor::div[@class='vs-popup']//span[contains((@class),'cell day weekend') and text()='30'])[2]")).click();
        } catch (Exception e) {
            System.out.println(e);
        }
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@aria-autocomplete='list']")).sendKeys("Agen");
        Thread.sleep(8000);
        String medName = driver.findElement(By.xpath("//li[@id='vs1__option-1']")).getText().split("\\(")[0];
        driver.findElement(By.xpath("//li[@id='vs1__option-1']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//h3[text()='Add KZS']/ancestor::div[@class='vs-popup']//span[text()='Medication indicator']/..//input")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//li[@data-text='R'])[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.name("nvdPrice")).sendKeys("1.75");
        driver.findElement(By.name("referencePrice")).sendKeys("1.76");
        driver.findElement(By.xpath("//h3[text()='Add KZS']/ancestor::div[@class='vs-popup']//span[text()=' Submit ']")).click();
        Thread.sleep(5000);


        double val75 = 1.76*0.75;
        double val1 = roundVal(val75);
        String value1 = Double.toString(val1);
        System.out.println("75% compensation: " + value1);
        double val50 = 1.76*0.5;
        double val2 = roundVal(val50);
        String value2 = Double.toString(val2);
        System.out.println("50% compensation: " + value2);
        String comp75 = driver.findElement(By.xpath("//span[contains(text(),'" + medName + "')]/../following::td[2]//span[contains(text(),'75%')]/following::span[1]")).getText().replace("€","");
        if (value1.equals(comp75)) {
            System.out.println("Expected value: " + value1 + "\nActual value: " + comp75);
        } else {
            System.out.println("Values does not match!\nExpected value: " + value1 + "\nActual value: " + comp75);
            throw new Exception();
        }
        String comp50 = driver.findElement(By.xpath("//span[contains(text(),'" + medName + "')]/../following::td[2]//span[contains(text(),'50%')]/following::span[1]")).getText().replace("€","");
        if (value2.equals(comp50)) {
            System.out.println("Expected value: " + value2 + "\nActual value: " + comp50);
        } else {
            System.out.println("Values does not match!\nExpected value: " + value2 + "\nActual value: " + comp50);
            throw new Exception();
        }

        driver.close();
    }

    private static double roundVal(double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        double val = bd.doubleValue();
        return val;
    }
}
