import com.opencsv.CSVReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//comment the above line and uncomment below line to use Chrome
//import org.openqa.selenium.chrome.ChromeDriver;
public class PG1 {


    private static final String COMMA_DELIMITER = ",";
    List<List<String>> records = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Starting.....");

//        System.setProperty("webdriver.gecko.driver","C:\\geckodriver.exe");
//        WebDriver driver = new FirefoxDriver();
        //comment the above 2 lines and uncomment below 2 lines to use Chrome

        //Create an object of ReadGuru99ExcelFile class

        ReadExcel objExcelFile = new ReadExcel();

        //Prepare the path of excel file

        String filePath = System.getProperty("user.dir");

        //Call read file method of the class to read data
        try{
            objExcelFile.readExcel(filePath,"/TestSheet.xlsx","Test");

            runApp();


        }catch (Exception e){
            System.out.println(e);
        }finally {
            System.out.println("App completed");
        }
    }

    private static void runApp(){
        PG1 pg1 = new PG1();
        pg1.readCSVComplex();;
        pg1.doTest();
    }

    private void readCSVComplex(){
        try (CSVReader csvReader = new CSVReader(new FileReader("data-test.csv"));) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
                System.out.println("added:" + values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("CSV Read Completed");
        }
    }

    public void doTest(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("ignore-certificate-errors");

        System.setProperty("webdriver.chrome.driver","chromedriver");
        WebDriver driver = new ChromeDriver(chromeOptions);

        String baseUrl = "https://noc.hutch.lk/infra/login.php";

        // launch Fire fox and direct it to the Base URL
        driver.get(baseUrl);

        driver.manage().window().maximize();

        WebElement username = driver.findElement(By.name("UserName"));
        username.sendKeys("Alwis_A");
        WebElement password = driver.findElement(By.name("Password"));
        password.sendKeys("alwiS99");
        password.submit();
        String sessionID = driver.getCurrentUrl().split("\\?")[1].toString();
        System.out.println("Bypassing invalid URL [Workaround] with SessionID : " + sessionID);


        driver.get("https://noc.hutch.lk/tism/infra/infra_onsite.php?" + sessionID);

        //Fill Form Data
        smartFill(driver,"1.1.1_site_name_hutch_format","sitename","selectVisible");
        smartFill(driver,"1.6_tower_ownership","infra_owner","selectValue");
        smartFill(driver,"1.4.2_gps_location_longitude","lon","freeText");
        smartFill(driver,"1.4.1_gps_location_latitude","lat","freeText");
        smartFill(driver,"1.5.2_site_address","address","freeText");


//        WebElement btsid = driver.findElement(By.name("sitename"));
//        btsid.sendKeys(records.get(0).get(0));


        //close Fire fox
        //driver.close();
    }

    public void smartFill(WebDriver driver ,String headerName, String elementName,String elementType){

        int index = findIndex(headerName);

        switch(elementType) {
            case "selectVisible":
                Select siteName = new Select(driver.findElement(By.name(elementName)));
                System.out.println("Setting [" + elementName + " ]: " + records.get(1).get(index));
                siteName.selectByVisibleText(records.get(1).get(index));
                break;
            case "selectValue":
                Select infraOwner = new Select(driver.findElement(By.name(elementName)));
                System.out.println("Setting [" + elementName + " ]: " + records.get(1).get(index));
                infraOwner.selectByValue(records.get(1).get(index));
                break;
            case "freeText":
                WebElement webelement = driver.findElement(By.name(elementName));
                System.out.println("Setting [" + elementName + " ]: " + records.get(1).get(index));
                webelement.sendKeys(records.get(1).get(index));
                break;
            default:
                // code block
        }





    }

    public int findIndex(String header){
        int a = -1;
        for (String value : records.get(0)) {
            a++;
            if(value!= null && value.equalsIgnoreCase(header)){
                break;
            }
        }
        return a;
    }
}