import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

//comment the above line and uncomment below line to use Chrome
//import org.openqa.selenium.chrome.ChromeDriver;
public class PG1 {


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

            PG1 test = new PG1();
            test.doTest();

        }catch (Exception e){
            System.out.println(e);
        }


    }

    public void doTest(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("ignore-certificate-errors");

        System.setProperty("webdriver.chrome.driver","chromedriver");
        WebDriver driver = new ChromeDriver(chromeOptions);

        String baseUrl = "https://noc.hutch.lk/infra/login.php";
        String expectedTitle = "Welcome: Mercury Tours";
        String actualTitle = "";

        // launch Fire fox and direct it to the Base URL
        driver.get(baseUrl);

        // get the actual value of the title
        actualTitle = driver.getTitle();
        driver.manage().window().maximize();
//
        WebElement username = driver.findElement(By.name("UserName"));
        username.sendKeys("Dinesh_C");
        WebElement password = driver.findElement(By.name("Password"));
        password.sendKeys("daveedofF99");
        password.submit();
        String sessionID = driver.getCurrentUrl().split("\\?")[1].toString();
        System.out.println(sessionID);


        driver.get("https://noc.hutch.lk/tism/infra/infra_onsite.php?" + sessionID);

        //Fill Form Data
        WebElement btsid = driver.findElement(By.name("btsid"));
        btsid.sendKeys("TESTBTSID");

        /*
         * compare the actual title of the page with the expected one and print
         * the result as "Passed" or "Failed"
         */
        if (actualTitle.contentEquals(expectedTitle)){
            System.out.println("Title : " + actualTitle);
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
        }

        //close Fire fox
        //driver.close();
    }

}