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

        super.setUp();
        test = rep.startTest("AddCustomerTest  " + data.get("browser"));
        setExtentTest(test);
        ExcelReader excel = new ExcelReader(Constans.SUITE1_XL_PATH);
        DataUtil.checkExecution("BankManagerSuite", "AddCustomerTest", data.get("Runmode"),excel);
        openBrowser(data.get("browser"));
        navigate("testsiteurl");
        click("bmlBtn_XPATH");
        click("addCustBtn_XPATH");
        type("firstname_XPATH", data.get("firstname"));
        type("lastname_XPATH", data.get("lastname"));
        type("postcode_XPATH", data.get("postcode"));
        click("addbtn_XPATH");
        reportPass("Add customer test pass");

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
