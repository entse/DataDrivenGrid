package utilities;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class DataProviders {

    @DataProvider(name = "bankManagerDP")
    public Object[][] getDataSuite1(Method m){

        System.out.println(m.getName());

        ExcelReader excel = new ExcelReader(Constans.SUITE1_XL_PATH);
        String testcase = m.getName();
        return DataUtil.getData(testcase, excel);

    }

    @DataProvider(name = "customerDP")
    public Object[][] getDataSuite2(Method m){

        System.out.println(m.getName());

        ExcelReader excel = new ExcelReader(Constans.SUITE2_XL_PATH);
        String testcase = m.getName();
        return DataUtil.getData(testcase, excel);

    }


}
