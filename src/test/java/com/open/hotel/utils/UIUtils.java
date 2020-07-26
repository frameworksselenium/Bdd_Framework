package com.open.hotel.utils;

import com.open.hotel.runner.TestNGRunner;
import com.open.hotel.utils.html.HtmlLog;
import com.open.hotel.utils.threadLevelVariables.VariableManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.open.hotel.utils.webDriverFactory.ManagerDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class UIUtils {

    public UIUtils(){

    }
    public void type(WebElement element, String value, String elementName, String page){
        try{
            boolean elementClickable = WaitUntilClickable(element, Integer.valueOf(TestNGRunner.properties.getProperty("LONGWAIT")));
            element.sendKeys(value);
            HtmlLog.insertResult(VariableManager.getInstance().getVariablesManager().getObject("testCaseID"), "ThreadID: " + String.valueOf(Thread.currentThread().getId()), "Enter value '" + value + "' in '" + elementName + "' text box", "Entered value '" + value + "' in '" + elementName + "' text box","PASS");
        }catch(Exception e){
            HtmlLog.insertResult(VariableManager.getInstance().getVariablesManager().getObject("testCaseID"), "ThreadID: " + String.valueOf(Thread.currentThread().getId()), "Enter value '" + value + "' in '" + elementName + "' text box", "Not Entered value '" + value + "' in '" + elementName + "' text box","FAIL");
            //Assert.fail(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void clickElement(WebElement element, String elementName, String page){
        try{
            boolean elementClickable = WaitUntilClickable(element, Integer.valueOf(TestNGRunner.properties.getProperty("LONGWAIT")));
            element.click();
            HtmlLog.insertResult(VariableManager.getInstance().getVariablesManager().getObject("testCaseID"), "ThreadID: " + String.valueOf(Thread.currentThread().getId()), "Click on '" + elementName + "' button", "Clicked on '" + elementName + "' button", "PASS");
        }catch(Exception e){
            HtmlLog.insertResult(VariableManager.getInstance().getVariablesManager().getObject("testCaseID"), "ThreadID: " + String.valueOf(Thread.currentThread().getId()), "Click on '" + elementName + "' button", "not Clicked on '" + elementName + "' button", "FAIL");
           //Assert..fail(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static boolean WaitUntilClickable(WebElement element, int iWaitTime) throws Exception {

        boolean bFlag = false;
        WebDriverWait wait = new WebDriverWait(ManagerDriver.getInstance().getWebDriver(), iWaitTime);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            e.printStackTrace();
            bFlag = false;
        }
        return bFlag;

    }

    public static void SendMail(String strMailTo, String strMailCC, String strSummaryFileName, String g_tSummaryEnd_Time, String g_tSummaryStart_Time, String strRelease, String strModuleName, String g_SummaryTotal_TC, String g_SummaryTotal_Pass, String g_SummaryTotal_Fail, String strEnvironment) {
        File directory = new File(".");
        String sConfigfilespath = System.getProperty("user.dir") + "\\src\\test\\resources\\VBScript\\SendMail.vbs";
        try {
            String[] parms = { "wscript", sConfigfilespath, strMailTo, strMailCC, strSummaryFileName, g_tSummaryEnd_Time, g_tSummaryStart_Time, strRelease, strModuleName, g_SummaryTotal_TC, g_SummaryTotal_Pass, g_SummaryTotal_Fail, strEnvironment };
            // Runtime.getRuntime().exec(parms);
            Process p = Runtime.getRuntime().exec(parms);
            if (!p.waitFor(2, TimeUnit.MINUTES)) {
                p.destroy();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
