package utilities;

import org.testng.SkipException;

import java.util.Hashtable;

public class DataUtil {

    public static void checkExecution (String testSuiteName, String testCaseName, String dataRunMode, ExcelReader excel) throws SkipException {

        if(!isSuiteRunnable(testSuiteName)){
            throw new SkipException("Skipping the test :" + testCaseName + "cause the Runmode of Test Suite :" + testSuiteName + "is NO");
        }

        if(!isTestRunnable(testCaseName, excel)){
            throw new SkipException("Skipping the test :" + testCaseName + "cause the Runmode of Test Case :" + testCaseName + "is NO");
        }

        if(dataRunMode.equalsIgnoreCase(Constans.RUNMODE_NO)){
            throw new SkipException("Skipping the test :" + testCaseName + "cause the Runmode for DataSet is NO");
        }
    }


    public static boolean isSuiteRunnable(String suiteName){

        ExcelReader excel = new ExcelReader(Constans.SUITE_XL_PATH);
        int rows = excel.getRowCount(Constans.SUITE_SHEET);

        for (int rowNUm = 2; rowNUm <= rows; rowNUm++){
            String data = excel.getCellData(Constans.SUITE_SHEET, Constans.SUITENAME_COL, rowNUm);
            if (data.equals(suiteName)){
                String runmode = excel.getCellData(Constans.SUITE_SHEET, Constans.RUNMODE_COL, rowNUm);
                if(runmode.equals(Constans.RUNMODE_YES))
                    return true;
                else
                    return false;
            }
        }
        return false;
    }

    public static boolean isTestRunnable(String testCaseName, ExcelReader excel){

        int rows = excel.getRowCount(Constans.TESTCASE_SHEET);

        for (int rowNUm = 2; rowNUm <= rows; rowNUm++){
            String data = excel.getCellData(Constans.TESTCASE_SHEET, Constans.TESTCASE_COL, rowNUm);
            if (data.equals(testCaseName)){
                String runmode = excel.getCellData(Constans.TESTCASE_SHEET, Constans.RUNMODE_COL, rowNUm);
                if(runmode.equals(Constans.RUNMODE_YES))
                    return true;
                else
                    return false;
            }
        }
        return false;

    }

    public static Object[][] getData(String testcase, ExcelReader excel) {


        int rows = excel.getRowCount(Constans.DATA_SHEET);
        System.out.println("Total rows are: " + rows);

        String testName = testcase;
        //Find the test case start row
        int testCaseRowNum = 1;

        for (testCaseRowNum = 1; testCaseRowNum <= rows; testCaseRowNum++) {

            String testCaseName = excel.getCellData(Constans.DATA_SHEET, 0, testCaseRowNum);

            if (testCaseName.equalsIgnoreCase(testName)) {
                break;
            }

        }
        System.out.println("Test case starts from row num: " + testCaseRowNum);

        //Checking total rows in test case
        int dataStartRowNum = testCaseRowNum + 2;
        int testRows = 0;
        while (!excel.getCellData(Constans.DATA_SHEET, 0, dataStartRowNum + testRows).equals("")) {
            testRows++;
        }

        System.out.println("Total rows of data are: " + testRows);

        //Checking total cols in test case

        int colStartColNum = testCaseRowNum + 1;
        int testCols = 0;

        while (!excel.getCellData(Constans.DATA_SHEET, testCols, colStartColNum).equals("")) {
            testCols++;
        }

        System.out.println("Total cols are: " + testCols);

        //Printing data

        Object[][] data = new Object[testRows][1];

        int i = 0;
        for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + testRows); rNum++) {

            Hashtable<String, String> table = new Hashtable<String, String>();

            for (int cNum = 0; cNum < testCols; cNum++) {
                //System.out.println(excel.getCellData(Constans.DATA_SHEET, cNum, rNum));
                String testData = excel.getCellData(Constans.DATA_SHEET, cNum, rNum);
                String colName = excel.getCellData(Constans.DATA_SHEET, cNum, colStartColNum);

                table.put(colName, testData);
            }

            data[i][0] = table;
            i++;
        }

        return data;
    }



}
