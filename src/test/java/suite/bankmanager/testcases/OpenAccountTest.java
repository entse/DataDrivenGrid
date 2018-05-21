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

public class OpenAccountTest extends TestBase{


    @Test(dataProviderClass = DataProviders.class, dataProvider = "bankManagerDP")
    public void openAccountTest(Hashtable<String, String> data) throws MalformedURLException {

        super.setUp();
        test = rep.startTest("OpenAccountTest  " + data.get("browser"));
        setExtentTest(test);
        ExcelReader excel = new ExcelReader(Constans.SUITE1_XL_PATH);
        DataUtil.checkExecution("BankManagerSuite", "OpenAccountTest", data.get("Runmode"),excel);
        openBrowser(data.get("browser"));
        navigate("testsiteurl");
        click("bmlBtn_XPATH");
        click("openaccountBtn_XPATH");
        select("customer_XPATH", data.get("customer"));
        select("currency_XPATH", data.get("currency"));
        click("processBtn_XPATH");
        reportPass("Open account test pass");

    }


    @AfterMethod
    public void tearDown(){

        if(rep != null){

            rep.endTest(test);
            rep.flush();

        }

        getDriver().quit();
    }


}
