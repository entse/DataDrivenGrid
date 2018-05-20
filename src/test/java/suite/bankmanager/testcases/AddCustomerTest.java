package suite.bankmanager.testcases;

import datadriven.base.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.Constans;
import utilities.DataProviders;
import utilities.DataUtil;
import utilities.ExcelReader;

import java.net.MalformedURLException;
import java.util.Hashtable;

public class AddCustomerTest extends TestBase{


    @Test(dataProviderClass = DataProviders.class, dataProvider = "bankManagerDP")
    public void AddCustomerTest(Hashtable<String, String> data) throws MalformedURLException {

        ExcelReader excel = new ExcelReader(Constans.SUITE1_XL_PATH);
        DataUtil.checkExecution("BankManagerSuite", "AddCustomerTest", data.get("Runmode"),excel);
        openBrowser(data.get("browser"));
        navigate("testsiteurl");

    }


    @AfterMethod
    public void tearDown(){
        getDriver().quit();
    }


}
