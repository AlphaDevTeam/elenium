import com.opencsv.CSVReader;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.util.StringUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

//comment the above line and uncomment below line to use Chrome
//import org.openqa.selenium.chrome.ChromeDriver;
public class PG1 {


     final String COMMA_DELIMITER = ",";
    List<List<String>> records = new ArrayList<>();
    private static Map<String,String> credentials = new HashMap<>();

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
            credentials = objExcelFile.readExcel(filePath,"/TestSheet.xlsx","Test");

            runApp(args.length > 0 ? args[0] : "1");


        }catch (Exception e){
            System.out.println(e);
        }finally {
            System.out.println("App completed");
        }
    }

    private static void runApp(String arg){
        PG1 pg1 = new PG1();
        pg1.readCSVComplex();;
        pg1.doTest(NumberUtils.isNumber(arg) ? Integer.valueOf(arg) : 1);
    }

    private void readCSVComplex(){
        try (CSVReader csvReader = new CSVReader(new FileReader("test-data.csv"));) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
                System.out.println("added:" + values.toString());
            }
        } catch (IOException e) {
            System.out.println("Error " + e);
            e.printStackTrace();
        }finally {
            System.out.println("CSV Read Completed");
        }
    }

    public void doTest(int lineNum){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("ignore-certificate-errors");

//        System.setProperty("webdriver.chrome.driver","chromedriver");
//        WebDriver driver = new ChromeDriver(chromeOptions);

        System.setProperty("webdriver.gecko.driver","geckodriver");
        WebDriver driver = new FirefoxDriver();
        WebDriverWait _wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        String baseUrl = "https://noc.hutch.lk/infra/login.php";

        // launch Fire fox and direct it to the Base URL
        driver.get(baseUrl);

        driver.manage().window().maximize();

        WebElement username = driver.findElement(By.name("UserName"));
        username.sendKeys(credentials.get("userName"));
        WebElement password = driver.findElement(By.name("Password"));
        password.sendKeys(credentials.get("passWord"));
        password.submit();

        _wait.until(ExpectedConditions.urlContains("PHPSESSID"));

        String sessionID = driver.getCurrentUrl().split("\\?")[1].toString();
        System.out.println("Bypassing invalid URL [Workaround] with SessionID : " + sessionID);


        driver.get("https://noc.hutch.lk/tism/infra/infra_onsite.php?" + sessionID);

        int lineNumber = (lineNum <= 0) ? 1 : lineNum;

        //Fill Form Data
        smartFill(driver,lineNumber,"site_name_hutch","sitename","selectVisible");
        smartFill(driver,lineNumber,"11_base_station_type","type","selectVisible");
        smartFill(driver,lineNumber,"12a_cabin_type_if_indoor","type","freeText");
        smartFill(driver,lineNumber,"13a_name_board_available","nameb","freeText");
        smartFill(driver,lineNumber,"site_name_hutch","cabint","freeText");
        smartFill(driver,lineNumber,"longitude","lon","freeText");
        smartFill(driver,lineNumber,"latitude","lat","freeText");
        smartFill(driver,lineNumber,"site_address_hutch",
                "address","freeText");

        smartFill(driver,lineNumber,"12b1_shelter_available","sheltor","selectVisible");
        smartFill(driver,lineNumber,"13b_name_board_available_if_outdoor","namebo","selectVisible");



        smartFill(driver,lineNumber,"21_antenna_1_sector_id","seid1","freeText");
        smartFill(driver,lineNumber,"22_antenna_2_sector_id","seid2","freeText");
        smartFill(driver,lineNumber,"23_antenna_3_sector_id","seid3","freeText");
        smartFill(driver,lineNumber,"24_antenna_4_sector_id","seid4","freeText");
        smartFill(driver,lineNumber,"25_antenna_5_sector_id","seid5","freeText");
        smartFill(driver,lineNumber,"26_antenna_6_sector_id","seid6","freeText");
        smartFill(driver,lineNumber,"27_antenna_7_sector_id","seid7","freeText");
        smartFill(driver,lineNumber,"28_antenna_2_sector_id","seid8","freeText");
        smartFill(driver,lineNumber,"29_antenna_3_sector_id","seid9","freeText");
        smartFill(driver,lineNumber,"30_antenna_4_sector_id","seid10","freeText");
        smartFill(driver,lineNumber,"31_antenna_5_sector_id","seid11","freeText");
        smartFill(driver,lineNumber,"32_antenna_6_sector_id","seid12","freeText");

        smartFill(driver,lineNumber,"21_antenna_1_antenna_height_m_from_b_plate_to_the_antenna_top","h1","freeText");
        smartFill(driver,lineNumber,"22_antenna_2_antenna_height_m_from_b_plate_to_the_antenna_top","h2","freeText");
        smartFill(driver,lineNumber,"23_antenna_3_antenna_height_m_from_b_plate_to_the_antenna_top","h3","freeText");
        smartFill(driver,lineNumber,"24_antenna_4_antenna_height_m_from_b_plate_to_the_antenna_top","h4","freeText");
        smartFill(driver,lineNumber,"25_antenna_5_antenna_height_m_from_b_plate_to_the_antenna_top","h5","freeText");
        smartFill(driver,lineNumber,"26_antenna_6_antenna_height_m_from_b_plate_to_the_antenna_top","h6","freeText");
        smartFill(driver,lineNumber,"27_antenna_1_antenna_height_m_from_b_plate_to_the_antenna_top","h7","freeText");
        smartFill(driver,lineNumber,"28_antenna_2_antenna_height_m_from_b_plate_to_the_antenna_top","h8","freeText");
        smartFill(driver,lineNumber,"29_antenna_3_antenna_height_m_from_b_plate_to_the_antenna_top","h9","freeText");
        smartFill(driver,lineNumber,"30_antenna_4_antenna_height_m_from_b_plate_to_the_antenna_top","h10","freeText");
        smartFill(driver,lineNumber,"31_antenna_5_antenna_height_m_from_b_plate_to_the_antenna_top","h11","freeText");
        smartFill(driver,lineNumber,"32_antenna_6_antenna_height_m_from_b_plate_to_the_antenna_top","h12","freeText");

        smartFill(driver,lineNumber,"21_antenna_1_antenna_dimension_height_m","anh1","freeText");
        smartFill(driver,lineNumber,"22_antenna_1_antenna_dimension_height_m","anh2","freeText");
        smartFill(driver,lineNumber,"23_antenna_1_antenna_dimension_height_m","anh3","freeText");
        smartFill(driver,lineNumber,"24_antenna_1_antenna_dimension_height_m","anh4","freeText");
        smartFill(driver,lineNumber,"25_antenna_1_antenna_dimension_height_m","anh5","freeText");
        smartFill(driver,lineNumber,"26_antenna_1_antenna_dimension_height_m","anh6","freeText");
        smartFill(driver,lineNumber,"27_antenna_1_antenna_dimension_height_m","anh7","freeText");
        smartFill(driver,lineNumber,"28_antenna_1_antenna_dimension_height_m","anh8","freeText");
        smartFill(driver,lineNumber,"29_antenna_1_antenna_dimension_height_m","anh9","freeText");
        smartFill(driver,lineNumber,"30_antenna_1_antenna_dimension_height_m","anh10","freeText");
        smartFill(driver,lineNumber,"31_antenna_1_antenna_dimension_height_m","anh11","freeText");
        smartFill(driver,lineNumber,"32_antenna_1_antenna_dimension_height_m","anh12","freeText");

        smartFill(driver,lineNumber,"21_antenna_1_antenna_dimension_width_m","anw1","freeText");
        smartFill(driver,lineNumber,"22_antenna_1_antenna_dimension_width_m","anw2","freeText");
        smartFill(driver,lineNumber,"23_antenna_1_antenna_dimension_width_m","anw3","freeText");
        smartFill(driver,lineNumber,"24_antenna_1_antenna_dimension_width_m","anw4","freeText");
        smartFill(driver,lineNumber,"25_antenna_1_antenna_dimension_width_m","anw5","freeText");
        smartFill(driver,lineNumber,"26_antenna_1_antenna_dimension_width_m","anw6","freeText");
        smartFill(driver,lineNumber,"27_antenna_1_antenna_dimension_width_m","anw7","freeText");
        smartFill(driver,lineNumber,"28_antenna_1_antenna_dimension_width_m","anw8","freeText");
        smartFill(driver,lineNumber,"29_antenna_1_antenna_dimension_width_m","anw9","freeText");
        smartFill(driver,lineNumber,"30_antenna_1_antenna_dimension_width_m","anw10","freeText");
        smartFill(driver,lineNumber,"31_antenna_1_antenna_dimension_width_m","anw11","freeText");
        smartFill(driver,lineNumber,"32_antenna_1_antenna_dimension_width_m","anw12","freeText");

        smartFill(driver,lineNumber,"21_antenna_1_antenna_pole_length_m","pole1","freeText");
        smartFill(driver,lineNumber,"22_antenna_1_antenna_pole_length_m","pole2","freeText");
        smartFill(driver,lineNumber,"23_antenna_1_antenna_pole_length_m","pole3","freeText");
        smartFill(driver,lineNumber,"24_antenna_1_antenna_pole_length_m","pole4","freeText");
        smartFill(driver,lineNumber,"25_antenna_1_antenna_pole_length_m","pole5","freeText");
        smartFill(driver,lineNumber,"26_antenna_1_antenna_pole_length_m","pole6","freeText");
        smartFill(driver,lineNumber,"27_antenna_1_antenna_pole_length_m","pole7","freeText");
        smartFill(driver,lineNumber,"28_antenna_1_antenna_pole_length_m","pole8","freeText");
        smartFill(driver,lineNumber,"29_antenna_1_antenna_pole_length_m","pole9","freeText");
        smartFill(driver,lineNumber,"30_antenna_1_antenna_pole_length_m","pole10","freeText");
        smartFill(driver,lineNumber,"31_antenna_1_antenna_pole_length_m","pole11","freeText");
        smartFill(driver,lineNumber,"32_antenna_1_antenna_pole_length_m","pole12","freeText");


        smartFill(driver,lineNumber,"31_rru_1_sector_id","rruid1","freeText");
        smartFill(driver,lineNumber,"32_rru_2_sector_id","rruid2","freeText");
        smartFill(driver,lineNumber,"33_rru_3_sector_id","rruid3","freeText");
        smartFill(driver,lineNumber,"34_rru_4_sector_id","rruid4","freeText");
        smartFill(driver,lineNumber,"35_rru_5_sector_id","rruid5","freeText");
        smartFill(driver,lineNumber,"36_rru_6_sector_id","rruid6","freeText");
        smartFill(driver,lineNumber,"37_rru_7_sector_id","rruid7","freeText");
        smartFill(driver,lineNumber,"38_rru_8_sector_id","rruid8","freeText");
        smartFill(driver,lineNumber,"39_rru_9_sector_id","rruid9","freeText");
        smartFill(driver,lineNumber,"310_rru_10_sector_id","rruid10","freeText");
        smartFill(driver,lineNumber,"311_rru_11_sector_id","rruid11","freeText");
        smartFill(driver,lineNumber,"312_rru_12_sector_id","rruid12","freeText");
        smartFill(driver,lineNumber,"313_rru_13_sector_id","rruid13","freeText");
        smartFill(driver,lineNumber,"314_rru_14_sector_id","rruid14","freeText");
        smartFill(driver,lineNumber,"315_rru_15_sector_id","rruid15","freeText");
        smartFill(driver,lineNumber,"316_rru_16_sector_id","rruid16","freeText");
        smartFill(driver,lineNumber,"317_rru_17_sector_id","rruid17","freeText");
        smartFill(driver,lineNumber,"318_rru_18_sector_id","rruid18","freeText");
        smartFill(driver,lineNumber,"319_rru_19_sector_id","rruid19","freeText");
        smartFill(driver,lineNumber,"320_rru_20_sector_id","rruid20","freeText");
        smartFill(driver,lineNumber,"321_rru_21_sector_id","rruid21","freeText");
        smartFill(driver,lineNumber,"322_rru_22_sector_id","rruid22","freeText");
        smartFill(driver,lineNumber,"323_rru_23_sector_id","rruid23","freeText");
        smartFill(driver,lineNumber,"324_rru_24_sector_id","rruid24","freeText");
        smartFill(driver,lineNumber,"325_rru_25_sector_id","rruid25","freeText");
        smartFill(driver,lineNumber,"326_rru_26_sector_id","rruid26","freeText");
        smartFill(driver,lineNumber,"327_rru_27_sector_id","rruid27","freeText");
        smartFill(driver,lineNumber,"328_rru_28_sector_id","rruid28","freeText");
        smartFill(driver,lineNumber,"329_rru_29_sector_id","rruid29","freeText");
        smartFill(driver,lineNumber,"330_rru_30_sector_id","rruid30","freeText");


        smartFill(driver,lineNumber,"31_rru_1_height_of_rru_m_from_b_plate_to_rru_top","rruh1","freeText");
        smartFill(driver,lineNumber,"32_rru_2_height_of_rru_m_from_b_plate_to_rru_top","rruh2","freeText");
        smartFill(driver,lineNumber,"33_rru_3_height_of_rru_m_from_b_plate_to_rru_top","rruh3","freeText");
        smartFill(driver,lineNumber,"34_rru_4_height_of_rru_m_from_b_plate_to_rru_top","rruh4","freeText");
        smartFill(driver,lineNumber,"35_rru_5_height_of_rru_m_from_b_plate_to_rru_top","rruh5","freeText");
        smartFill(driver,lineNumber,"36_rru_6_height_of_rru_m_from_b_plate_to_rru_top","rruh6","freeText");
        smartFill(driver,lineNumber,"37_rru_7_height_of_rru_m_from_b_plate_to_rru_top","rruh7","freeText");
        smartFill(driver,lineNumber,"38_rru_8_height_of_rru_m_from_b_plate_to_rru_top","rruh8","freeText");
        smartFill(driver,lineNumber,"39_rru_9_height_of_rru_m_from_b_plate_to_rru_top","rruh9","freeText");
        smartFill(driver,lineNumber,"310_rru_10_height_of_rru_m_from_b_plate_to_rru_top","rruh10","freeText");
        smartFill(driver,lineNumber,"311_rru_11_height_of_rru_m_from_b_plate_to_rru_top","rruh11","freeText");
        smartFill(driver,lineNumber,"312_rru_12_height_of_rru_m_from_b_plate_to_rru_top","rruh12","freeText");
        smartFill(driver,lineNumber,"313_rru_13_height_of_rru_m_from_b_plate_to_rru_top","rruh13","freeText");
        smartFill(driver,lineNumber,"314_rru_14_height_of_rru_m_from_b_plate_to_rru_top","rruh14","freeText");
        smartFill(driver,lineNumber,"315_rru_15_height_of_rru_m_from_b_plate_to_rru_top","rruh15","freeText");
        smartFill(driver,lineNumber,"316_rru_16_height_of_rru_m_from_b_plate_to_rru_top","rruh16","freeText");
        smartFill(driver,lineNumber,"317_rru_17_height_of_rru_m_from_b_plate_to_rru_top","rruh17","freeText");
        smartFill(driver,lineNumber,"318_rru_18_height_of_rru_m_from_b_plate_to_rru_top","rruh18","freeText");
        smartFill(driver,lineNumber,"319_rru_19_height_of_rru_m_from_b_plate_to_rru_top","rruh19","freeText");
        smartFill(driver,lineNumber,"320_rru_20_height_of_rru_m_from_b_plate_to_rru_top","rruh20","freeText");
        smartFill(driver,lineNumber,"321_rru_21_height_of_rru_m_from_b_plate_to_rru_top","rruh21","freeText");
        smartFill(driver,lineNumber,"322_rru_22_height_of_rru_m_from_b_plate_to_rru_top","rruh22","freeText");
        smartFill(driver,lineNumber,"323_rru_23_height_of_rru_m_from_b_plate_to_rru_top","rruh23","freeText");
        smartFill(driver,lineNumber,"324_rru_24_height_of_rru_m_from_b_plate_to_rru_top","rruh24","freeText");
        smartFill(driver,lineNumber,"325_rru_25_height_of_rru_m_from_b_plate_to_rru_top","rruh25","freeText");
        smartFill(driver,lineNumber,"326_rru_26_height_of_rru_m_from_b_plate_to_rru_top","rruh26","freeText");
        smartFill(driver,lineNumber,"327_rru_27_height_of_rru_m_from_b_plate_to_rru_top","rruh27","freeText");
        smartFill(driver,lineNumber,"328_rru_28_height_of_rru_m_from_b_plate_to_rru_top","rruh28","freeText");
        smartFill(driver,lineNumber,"329_rru_29_height_of_rru_m_from_b_plate_to_rru_top","rruh29","freeText");
        smartFill(driver,lineNumber,"330_rru_30_height_of_rru_m_from_b_plate_to_rru_top","rruh30","freeText");


        smartFill(driver,lineNumber,"31_rru_1_dimension_height_m","rrudh1","freeText");
        smartFill(driver,lineNumber,"32_rru_2_dimension_height_m","rrudh2","freeText");
        smartFill(driver,lineNumber,"33_rru_3_dimension_height_m","rrudh3","freeText");
        smartFill(driver,lineNumber,"34_rru_4_dimension_height_m","rrudh4","freeText");
        smartFill(driver,lineNumber,"35_rru_5_dimension_height_m","rrudh5","freeText");
        smartFill(driver,lineNumber,"36_rru_6_dimension_height_m","rrudh6","freeText");
        smartFill(driver,lineNumber,"37_rru_7_dimension_height_m","rrudh7","freeText");
        smartFill(driver,lineNumber,"38_rru_8_dimension_height_m","rrudh8","freeText");
        smartFill(driver,lineNumber,"39_rru_9_dimension_height_m","rrudh9","freeText");
        smartFill(driver,lineNumber,"310_rru_10_dimension_height_m","rrudh10","freeText");
        smartFill(driver,lineNumber,"311_rru_11_dimension_height_m","rrudh11","freeText");
        smartFill(driver,lineNumber,"312_rru_12_dimension_height_m","rrudh12","freeText");
        smartFill(driver,lineNumber,"313_rru_13_dimension_height_m","rrudh13","freeText");
        smartFill(driver,lineNumber,"314_rru_14_dimension_height_m","rrudh14","freeText");
        smartFill(driver,lineNumber,"315_rru_15_dimension_height_m","rrudh15","freeText");
        smartFill(driver,lineNumber,"316_rru_16_dimension_height_m","rrudh16","freeText");
        smartFill(driver,lineNumber,"317_rru_17_dimension_height_m","rrudh17","freeText");
        smartFill(driver,lineNumber,"318_rru_18_dimension_height_m","rrudh18","freeText");
        smartFill(driver,lineNumber,"319_rru_19_dimension_height_m","rrudh19","freeText");
        smartFill(driver,lineNumber,"320_rru_20_dimension_height_m","rrudh20","freeText");
        smartFill(driver,lineNumber,"321_rru_21_dimension_height_m","rrudh21","freeText");
        smartFill(driver,lineNumber,"322_rru_22_dimension_height_m","rrudh22","freeText");
        smartFill(driver,lineNumber,"323_rru_23_dimension_height_m","rrudh23","freeText");
        smartFill(driver,lineNumber,"324_rru_24_dimension_height_m","rrudh24","freeText");
        smartFill(driver,lineNumber,"325_rru_25_dimension_height_m","rrudh25","freeText");
        smartFill(driver,lineNumber,"326_rru_26_dimension_height_m","rrudh26","freeText");
        smartFill(driver,lineNumber,"327_rru_27_dimension_height_m","rrudh27","freeText");
        smartFill(driver,lineNumber,"328_rru_28_dimension_height_m","rrudh28","freeText");
        smartFill(driver,lineNumber,"329_rru_29_dimension_height_m","rrudh29","freeText");
        smartFill(driver,lineNumber,"330_rru_30_dimension_height_m","rrudh30","freeText");


        smartFill(driver,lineNumber,"equipment_1_leg","combleg1","selectVisible");
        smartFill(driver,lineNumber,"equipment_2_leg","combleg2","selectVisible");
        smartFill(driver,lineNumber,"equipment_3_leg","combleg3","selectVisible");
        smartFill(driver,lineNumber,"equipment_4_leg","combleg4","selectVisible");
        smartFill(driver,lineNumber,"equipment_5_leg","combleg5","selectVisible");
        smartFill(driver,lineNumber,"equipment_6_leg","combleg6","selectVisible");
        smartFill(driver,lineNumber,"equipment_7_leg","combleg7","selectVisible");
        smartFill(driver,lineNumber,"equipment_8_leg","combleg8","selectVisible");
        smartFill(driver,lineNumber,"equipment_9_leg","combleg9","selectVisible");
        smartFill(driver,lineNumber,"equipment_10_leg","combleg10","selectVisible");
        smartFill(driver,lineNumber,"equipment_11_leg","combleg11","selectVisible");
        smartFill(driver,lineNumber,"equipment_12_leg","combleg12","selectVisible");
        smartFill(driver,lineNumber,"equipment_13_leg","combleg13","selectVisible");
        smartFill(driver,lineNumber,"equipment_14_leg","combleg14","selectVisible");
        smartFill(driver,lineNumber,"equipment_15_leg","combleg15","selectVisible");



        smartFill(driver,lineNumber,"equipment_1_type","combtype1","selectVisible");
        smartFill(driver,lineNumber,"equipment_2_type","combtype2","selectVisible");
        smartFill(driver,lineNumber,"equipment_3_type","combtype3","selectVisible");
        smartFill(driver,lineNumber,"equipment_4_type","combtype4","selectVisible");
        smartFill(driver,lineNumber,"equipment_5_type","combtype5","selectVisible");
        smartFill(driver,lineNumber,"equipment_6_type","combtype6","selectVisible");
        smartFill(driver,lineNumber,"equipment_7_type","combtype7","selectVisible");
        smartFill(driver,lineNumber,"equipment_8_type","combtype8","selectVisible");
        smartFill(driver,lineNumber,"equipment_9_type","combtype9","selectVisible");
        smartFill(driver,lineNumber,"equipment_10_type","combtype10","selectVisible");
        smartFill(driver,lineNumber,"equipment_11_type","combtype11","selectVisible");
        smartFill(driver,lineNumber,"equipment_12_type","combtype12","selectVisible");
        smartFill(driver,lineNumber,"equipment_13_type","combtype13","selectVisible");
        smartFill(driver,lineNumber,"equipment_14_type","combtype14","selectVisible");
        smartFill(driver,lineNumber,"equipment_15_type","combtype15","selectVisible");


        smartFill(driver,lineNumber,"equipment_1_height","combh1","freeText");
        smartFill(driver,lineNumber,"equipment_2_height","combh2","freeText");
        smartFill(driver,lineNumber,"equipment_3_height","combh3","freeText");
        smartFill(driver,lineNumber,"equipment_4_height","combh4","freeText");
        smartFill(driver,lineNumber,"equipment_5_height","combh5","freeText");
        smartFill(driver,lineNumber,"equipment_6_height","combh6","freeText");
        smartFill(driver,lineNumber,"equipment_7_height","combh7","freeText");
        smartFill(driver,lineNumber,"equipment_8_height","combh8","freeText");
        smartFill(driver,lineNumber,"equipment_9_height","combh9","freeText");
        smartFill(driver,lineNumber,"equipment_10_height","combh10","freeText");
        smartFill(driver,lineNumber,"equipment_11_height","combh11","freeText");
        smartFill(driver,lineNumber,"equipment_12_height","combh12","freeText");
        smartFill(driver,lineNumber,"equipment_13_height","combh13","freeText");
        smartFill(driver,lineNumber,"equipment_14_height","combh14","freeText");
        smartFill(driver,lineNumber,"equipment_15_height","combh15","freeText");



        smartFill(driver,lineNumber,"equipment_1_width","combw1","freeText");
        smartFill(driver,lineNumber,"equipment_2_width","combw2","freeText");
        smartFill(driver,lineNumber,"equipment_3_width","combw3","freeText");
        smartFill(driver,lineNumber,"equipment_4_width","combw4","freeText");
        smartFill(driver,lineNumber,"equipment_5_width","combw5","freeText");
        smartFill(driver,lineNumber,"equipment_6_width","combw6","freeText");
        smartFill(driver,lineNumber,"equipment_7_width","combw7","freeText");
        smartFill(driver,lineNumber,"equipment_8_width","combw8","freeText");
        smartFill(driver,lineNumber,"equipment_9_width","combw9","freeText");
        smartFill(driver,lineNumber,"equipment_10_width","combw10","freeText");
        smartFill(driver,lineNumber,"equipment_11_width","combw11","freeText");
        smartFill(driver,lineNumber,"equipment_12_width","combw12","freeText");
        smartFill(driver,lineNumber,"equipment_13_width","combw13","freeText");
        smartFill(driver,lineNumber,"equipment_14_width","combw14","freeText");
        smartFill(driver,lineNumber,"equipment_15_width","combw15","freeText");


        smartFill(driver,lineNumber,"equipment_1_height_from_base_plate_to_top","combmoh1","freeText");
        smartFill(driver,lineNumber,"equipment_2_height_from_base_plate_to_top","combmoh2","freeText");
        smartFill(driver,lineNumber,"equipment_3_height_from_base_plate_to_top","combmoh3","freeText");
        smartFill(driver,lineNumber,"equipment_4_height_from_base_plate_to_top","combmoh4","freeText");
        smartFill(driver,lineNumber,"equipment_5_height_from_base_plate_to_top","combmoh5","freeText");
        smartFill(driver,lineNumber,"equipment_6_height_from_base_plate_to_top","combmoh6","freeText");
        smartFill(driver,lineNumber,"equipment_7_height_from_base_plate_to_top","combmoh7","freeText");
        smartFill(driver,lineNumber,"equipment_8_height_from_base_plate_to_top","combmoh8","freeText");
        smartFill(driver,lineNumber,"equipment_9_height_from_base_plate_to_top","combmoh9","freeText");
        smartFill(driver,lineNumber,"equipment_10_height_from_base_plate_to_top","combmoh10","freeText");
        smartFill(driver,lineNumber,"equipment_11_height_from_base_plate_to_top","combmoh11","freeText");
        smartFill(driver,lineNumber,"equipment_12_height_from_base_plate_to_top","combmoh12","freeText");
        smartFill(driver,lineNumber,"equipment_13_height_from_base_plate_to_top","combmoh13","freeText");
        smartFill(driver,lineNumber,"equipment_14_height_from_base_plate_to_top","combmoh14","freeText");
        smartFill(driver,lineNumber,"equipment_15_height_from_base_plate_to_top","combmoh15","freeText");


        //MW Ant
        smartFill(driver,lineNumber,"512_mw_antenna_1_leg","mwleg1","selectVisible");
        smartFill(driver,lineNumber,"522_mw_antenna_2_leg","mwleg2","selectVisible");
        smartFill(driver,lineNumber,"532_mw_antenna_3_leg","mwleg3","selectVisible");
        smartFill(driver,lineNumber,"542_mw_antenna_4_leg","mwleg4","selectVisible");
        smartFill(driver,lineNumber,"552_mw_antenna_5_leg","mwleg5","selectVisible");
        smartFill(driver,lineNumber,"562_mw_antenna_6_leg","mwleg6","selectVisible");
        smartFill(driver,lineNumber,"572_mw_antenna_7_leg","mwleg7","selectVisible");
        smartFill(driver,lineNumber,"582_mw_antenna_8_leg","mwleg8","selectVisible");
        smartFill(driver,lineNumber,"592_mw_antenna_9_leg","mwleg9","selectVisible");
        smartFill(driver,lineNumber,"5102_mw_antenna_10_leg","mwleg10","selectVisible");
        smartFill(driver,lineNumber,"5112_mw_antenna_11_leg","mwleg11","selectVisible");
        smartFill(driver,lineNumber,"5122_mw_antenna_12_leg","mwleg12","selectVisible");
        smartFill(driver,lineNumber,"5132_mw_antenna_13_leg","mwleg13","selectVisible");
        smartFill(driver,lineNumber,"5142_mw_antenna_14_leg","mwleg14","selectVisible");
        smartFill(driver,lineNumber,"5152_mw_antenna_15_leg","mwleg15","selectVisible");


        smartFill(driver,lineNumber,"513_mw_antenna_1_height_from_base_plate_to_antenna","mwh1","freeText");
        smartFill(driver,lineNumber,"523_mw_antenna_2_height_from_base_plate_to_antenna","mwh2","freeText");
        smartFill(driver,lineNumber,"533_mw_antenna_3_height_from_base_plate_to_antenna","mwh3","freeText");
        smartFill(driver,lineNumber,"543_mw_antenna_4_height_from_base_plate_to_antenna","mwh4","freeText");
        smartFill(driver,lineNumber,"553_mw_antenna_5_height_from_base_plate_to_antenna","mwh5","freeText");
        smartFill(driver,lineNumber,"563_mw_antenna_6_height_from_base_plate_to_antenna","mwh6","freeText");
        smartFill(driver,lineNumber,"573_mw_antenna_7_height_from_base_plate_to_antenna","mwh7","freeText");
        smartFill(driver,lineNumber,"583_mw_antenna_8_height_from_base_plate_to_antenna","mwh8","freeText");
        smartFill(driver,lineNumber,"593_mw_antenna_9_height_from_base_plate_to_antenna","mwh9","freeText");
        smartFill(driver,lineNumber,"5103_mw_antenna_10_height_from_base_plate_to_antenna","mwh10","freeText");
        smartFill(driver,lineNumber,"5113_mw_antenna_11_height_from_base_plate_to_antenna","mwh11","freeText");
        smartFill(driver,lineNumber,"5123_mw_antenna_12_height_from_base_plate_to_antenna","mwh12","freeText");
        smartFill(driver,lineNumber,"5133_mw_antenna_13_height_from_base_plate_to_antenna","mwh13","freeText");
        smartFill(driver,lineNumber,"5143_mw_antenna_14_height_from_base_plate_to_antenna","mwh14","freeText");
        smartFill(driver,lineNumber,"5153_mw_antenna_15_height_from_base_plate_to_antenna","mwh15","freeText");


        smartFill(driver,lineNumber,"514_mw_antenna_1_diameter_in_m","mwd1","freeText");
        smartFill(driver,lineNumber,"524_mw_antenna_2_diameter_in_m","mwd2","freeText");
        smartFill(driver,lineNumber,"534_mw_antenna_3_diameter_in_m","mwd3","freeText");
        smartFill(driver,lineNumber,"544_mw_antenna_4_diameter_in_m","mwd4","freeText");
        smartFill(driver,lineNumber,"554_mw_antenna_5_diameter_in_m","mwd5","freeText");
        smartFill(driver,lineNumber,"564_mw_antenna_6_diameter_in_m","mwd6","freeText");
        smartFill(driver,lineNumber,"574_mw_antenna_7_diameter_in_m","mwd7","freeText");
        smartFill(driver,lineNumber,"584_mw_antenna_8_diameter_in_m","mwd8","freeText");
        smartFill(driver,lineNumber,"594_mw_antenna_9_diameter_in_m","mwd9","freeText");
        smartFill(driver,lineNumber,"5104_mw_antenna_10_diameter_in_m","mwd10","freeText");
        smartFill(driver,lineNumber,"5114_mw_antenna_11_diameter_in_m","mwd11","freeText");
        smartFill(driver,lineNumber,"5124_mw_antenna_12_diameter_in_m","mwd12","freeText");
        smartFill(driver,lineNumber,"5134_mw_antenna_13_diameter_in_m","mwd13","freeText");
        smartFill(driver,lineNumber,"5144_mw_antenna_14_diameter_in_m","mwd14","freeText");
        smartFill(driver,lineNumber,"5154_mw_antenna_15_diameter_in_m","mwd15","freeText");


        smartFill(driver,lineNumber,"91a3_plinth_actual_foundation_width_in_ft","outcabareapw1","freeText");
        smartFill(driver,lineNumber,"92a3_plinth_actual_foundation_width_in_ft","outcabareapw2","freeText");
        smartFill(driver,lineNumber,"93a3_plinth_actual_foundation_width_in_ft","outcabareapw3","freeText");
        smartFill(driver,lineNumber,"94a3_plinth_actual_foundation_width_in_ft","outcabareapw4","freeText");
        smartFill(driver,lineNumber,"95a3_plinth_actual_foundation_width_in_ft","outcabareapw5","freeText");

        smartFill(driver,lineNumber,"91a4_plinth_actual_foundation_length_in_ft","outcabareapl1","freeText");
        smartFill(driver,lineNumber,"92a4_plinth_actual_foundation_length_in_ft","outcabareapl2","freeText");
        smartFill(driver,lineNumber,"93a4_plinth_actual_foundation_length_in_ft","outcabareapl3","freeText");
        smartFill(driver,lineNumber,"94a4_plinth_actual_foundation_length_in_ft","outcabareapl4","freeText");
        smartFill(driver,lineNumber,"95a4_plinth_actual_foundation_length_in_ft","outcabareapl5","freeText");

        smartFill(driver,lineNumber,"91d2_outdoor_cabinet_1_projected_roof_area_width_in_ft","outcabarearw1","freeText");
        smartFill(driver,lineNumber,"92d2_outdoor_cabinet_2_projected_roof_area_width_in_ft","outcabarearw2","freeText");
        smartFill(driver,lineNumber,"93d2_outdoor_cabinet_3_projected_roof_area_width_in_ft","outcabarearw3","freeText");
        smartFill(driver,lineNumber,"94d2_outdoor_cabinet_4_projected_roof_area_width_in_ft","outcabarearw4","freeText");
        smartFill(driver,lineNumber,"95d2_outdoor_cabinet_5_projected_roof_area_width_in_ft","outcabarearw5","freeText");

        smartFill(driver,lineNumber,"91d2_outdoor_cabinet_1_projected_roof_area_length_in_ft","outcabarearl1","freeText");
        smartFill(driver,lineNumber,"92d2_outdoor_cabinet_2_projected_roof_area_length_in_ft","outcabarearl2","freeText");
        smartFill(driver,lineNumber,"93d2_outdoor_cabinet_3_projected_roof_area_length_in_ft","outcabarearl3","freeText");
        smartFill(driver,lineNumber,"94d2_outdoor_cabinet_4_projected_roof_area_length_in_ft","outcabarearl4","freeText");
        smartFill(driver,lineNumber,"95d2_outdoor_cabinet_5_projected_roof_area_length_in_ft","outcabarearl5","freeText");

        smartFill(driver,lineNumber,"91e_outdoor_cabin_area_1_projected_areas_with_roof_remarks","outcabinremark1","freeText");
        smartFill(driver,lineNumber,"92e_outdoor_cabin_area_2_projected_areas_with_roof_remarks","outcabinremark2","freeText");
        smartFill(driver,lineNumber,"93e_outdoor_cabin_area_3_projected_areas_with_roof_remarks","outcabinremark3","freeText");
        smartFill(driver,lineNumber,"94e_outdoor_cabin_area_4_projected_areas_with_roof_remarks","outcabinremark4","freeText");
        smartFill(driver,lineNumber,"95e_outdoor_cabin_area_5_projected_areas_with_roof_remarks","outcabinremark5","freeText");


        //Indoor Cabin Area
        smartFill(driver,lineNumber,"91inda3_plinth_actual_foundation_width_in_ft","incabareapw1","freeText");
        smartFill(driver,lineNumber,"92inda3_plinth_actual_foundation_width_in_ft","incabareapw2","freeText");
        smartFill(driver,lineNumber,"93inda3_plinth_actual_foundation_width_in_ft","incabareapw3","freeText");
        smartFill(driver,lineNumber,"94inda3_plinth_actual_foundation_width_in_ft","incabareapw4","freeText");
        smartFill(driver,lineNumber,"95inda3_plinth_actual_foundation_width_in_ft","incabareapw5","freeText");

        smartFill(driver,lineNumber,"91inda4_plinth_actual_foundation_width_in_ft","incabareapl1","freeText");
        smartFill(driver,lineNumber,"92inda4_plinth_actual_foundation_width_in_ft","incabareapl2","freeText");
        smartFill(driver,lineNumber,"93inda4_plinth_actual_foundation_width_in_ft","incabareapl3","freeText");
        smartFill(driver,lineNumber,"94inda4_plinth_actual_foundation_width_in_ft","incabareapl4","freeText");
        smartFill(driver,lineNumber,"95inda4_plinth_actual_foundation_width_in_ft","incabareapl5","freeText");

        smartFill(driver,lineNumber,"91indd2_indoor_cabin_1_projected_roof_area_width_in_ft","incabarearw1","freeText");
        smartFill(driver,lineNumber,"92indd2_indoor_cabin_2_projected_roof_area_width_in_ft","incabarearw2","freeText");
        smartFill(driver,lineNumber,"93indd2_indoor_cabin_3_projected_roof_area_width_in_ft","incabarearw3","freeText");
        smartFill(driver,lineNumber,"94indd2_indoor_cabin_4_projected_roof_area_width_in_ft","incabarearw4","freeText");
        smartFill(driver,lineNumber,"95indd2_indoor_cabin_5_projected_roof_area_width_in_ft","incabarearw5","freeText");

        smartFill(driver,lineNumber,"91indd3_indoor_cabin_1_projected_roof_area_width_in_ft","incabarearl1","freeText");
        smartFill(driver,lineNumber,"92indd3_indoor_cabin_2_projected_roof_area_width_in_ft","incabarearl2","freeText");
        smartFill(driver,lineNumber,"93indd3_indoor_cabin_3_projected_roof_area_width_in_ft","incabarearl3","freeText");
        smartFill(driver,lineNumber,"94indd3_indoor_cabin_4_projected_roof_area_width_in_ft","incabarearl4","freeText");
        smartFill(driver,lineNumber,"95indd3_indoor_cabin_5_projected_roof_area_width_in_ft","incabarearl5","freeText");

        smartFill(driver,lineNumber,"91indd3_indoor_cabin_1_projected_roof_area_with_roof_remarks","incabinremark1","freeText");
        smartFill(driver,lineNumber,"92indd3_indoor_cabin_2_projected_roof_area_with_roof_remarks","incabinremark2","freeText");
        smartFill(driver,lineNumber,"93indd3_indoor_cabin_3_projected_roof_area_with_roof_remarks","incabinremark3","freeText");
        smartFill(driver,lineNumber,"94indd3_indoor_cabin_4_projected_roof_area_with_roof_remarks","incabinremark4","freeText");
        smartFill(driver,lineNumber,"95indd3_indoor_cabin_5_projected_roof_area_with_roof_remarks","incabinremark5","freeText");


        //Generator Hut
        smartFill(driver,lineNumber,"91gena3_plinth_actual_foundation_width_in_ft","genhutpw1","freeText");
        smartFill(driver,lineNumber,"92gena3_plinth_actual_foundation_width_in_ft","genhutpw2","freeText");
        smartFill(driver,lineNumber,"93gena3_plinth_actual_foundation_width_in_ft","genhutpw3","freeText");

        smartFill(driver,lineNumber,"91gena4_plinth_actual_foundation_width_in_ft","genhutpl1","freeText");
        smartFill(driver,lineNumber,"92gena4_plinth_actual_foundation_width_in_ft","genhutpl2","freeText");
        smartFill(driver,lineNumber,"93gena4_plinth_actual_foundation_width_in_ft","genhutpl3","freeText");

        smartFill(driver,lineNumber,"91gend2_generator_hut_1_projected_roof_area_width_in_ft","genhutpa1","freeText");
        smartFill(driver,lineNumber,"92gend2_generator_hut_2_projected_roof_area_width_in_ft","genhutpa2","freeText");
        smartFill(driver,lineNumber,"93gend2_generator_hut_3_projected_roof_area_width_in_ft","genhutpa3","freeText");

        smartFill(driver,lineNumber,"91gend3_generator_hut_1_projected_roof_area_width_in_ft","genhutp2a1","freeText");
        smartFill(driver,lineNumber,"92gend3_generator_hut_2_projected_roof_area_width_in_ft","genhutp2a2","freeText");
        smartFill(driver,lineNumber,"93igend3_generator_hut_3_projected_roof_area_width_in_ft","genhutp2a3","freeText");

        smartFill(driver,lineNumber,"91gend3_generator_hut_area_1_projected_areas_with_roof_remarks","genhutremark1","freeText");
        smartFill(driver,lineNumber,"92gend3_generator_hut_area_2_projected_areas_with_roof_remarks","genhutremark2","freeText");
        smartFill(driver,lineNumber,"93gend3_generator_hut_area_3_projected_areas_with_roof_remarks","genhutremark3","freeText");

//        WebElement btsid = driver.findElement(By.name("sitename"));
//        btsid.sendKeys(records.get(0).get(0));


        //close Fire fox
        //driver.close();
    }

    public void smartFill(WebDriver driver , int lineNumber ,String headerName, String elementName,String elementType){

        int index = findIndex(headerName);
        if(index == -1 ){
            System.out.println("Data Skipped Due to the fact that field could not be found [" + headerName + "]");
        }else{
            switch(elementType) {
                case "selectVisible":
                    Select siteName = new Select(driver.findElement(By.name(elementName)));
                    System.out.println("[" + headerName + " - Setting [" + elementName + " ]: " + records.get(lineNumber).get(index));
                    if(records.get(lineNumber).get(index) == null || records.get(lineNumber).get(index).equalsIgnoreCase("")){
                        System.out.println("Skipped Due to the fact that value is Empty");
                        break;
                    }
                    siteName.selectByVisibleText(records.get(lineNumber).get(index).split(" ").length>0 ? records.get(lineNumber).get(index).split(" ")[0] : records.get(lineNumber).get(index));
                    break;
                case "selectValue":
                    Select infraOwner = new Select(driver.findElement(By.name(elementName)));
                    System.out.println("[" + headerName + " - Setting [" + elementName + " ]: " + records.get(lineNumber).get(index));
                    if(records.get(lineNumber).get(index) == null || records.get(lineNumber).get(index).equalsIgnoreCase("")){
                        System.out.println("Skipped Due to the fact that value is Empty");
                        break;
                    }
                    infraOwner.selectByValue(records.get(lineNumber).get(index));
                    break;
                case "freeText":
                    WebElement webelement = driver.findElement(By.name(elementName));
                    System.out.println("[" + headerName + " - Setting [" + elementName + " ]: " + records.get(lineNumber).get(index));
                    webelement.sendKeys(records.get(lineNumber).get(index));
                    break;
                default:
                    // code block
            }
        }
    }

    public int findIndex(String header){
        int a = -1;
        int index = -1;
        for (String value : records.get(0)) {
            a++;
            if(value!= null && value.equalsIgnoreCase(header)){
                index = a;
                break;
            }
        }
        return index;
    }
}