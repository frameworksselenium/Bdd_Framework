package com.open.abddf.uiUtils;

import com.open.abddf.Logger.LoggerClass;
import com.open.abddf.html.HtmlLog;
import com.open.abddf.loadConfig.Config;
import com.open.abddf.threadLevelVariables.VariableManager;
import com.open.abddf.webDriverFactory.ManagerDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class UIUtils {

    org.apache.log4j.Logger log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(), VariableManager.getInstance().getVariablesManager().getObject("testCaseID"));

    public UIUtils(){

    }
    public void type(WebElement element, String value, String elementName, String page){
        try{
            boolean elementClickable = WaitUntilClickable(element, Integer.valueOf(Config.properties.getProperty("LONGWAIT")));
            highlightElement(element);
            MouseMoveToElement(element);
            //scrollToElement(element);
            element.sendKeys(value);
            log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Entered value '" + value + "' in '" + elementName + "' text box");
            HtmlLog.insertResult(VariableManager.getInstance().getVariablesManager().getObject("testCaseID"), "ThreadID: " + String.valueOf(Thread.currentThread().getId()), "Enter value '" + value + "' in '" + elementName + "' text box", "Entered value '" + value + "' in '" + elementName + "' text box","PASS");
        }catch(Exception e){
            HtmlLog.insertResult(VariableManager.getInstance().getVariablesManager().getObject("testCaseID"), "ThreadID: " + String.valueOf(Thread.currentThread().getId()), "Enter value '" + value + "' in '" + elementName + "' text box", "Not Entered value '" + value + "' in '" + elementName + "' text box","FAIL");
            log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void clickElement(WebElement element, String elementName, String page){
        try{
            boolean elementClickable = WaitUntilClickable(element, Integer.valueOf(Config.properties.getProperty("LONGWAIT")));
            highlightElement(element);
            MouseMoveToElement(element);
            //scrollToElement(element);
            element.click();
            log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Clicked on '" + elementName + "' button");
            HtmlLog.insertResult(VariableManager.getInstance().getVariablesManager().getObject("testCaseID"), "ThreadID: " + String.valueOf(Thread.currentThread().getId()), "Click on '" + elementName + "' button", "Clicked on '" + elementName + "' button", "PASS");
        }catch(Exception e){
            HtmlLog.insertResult(VariableManager.getInstance().getVariablesManager().getObject("testCaseID"), "ThreadID: " + String.valueOf(Thread.currentThread().getId()), "Click on '" + elementName + "' button", "not Clicked on '" + elementName + "' button", "FAIL");
            log.info("Thred ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
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
        String sConfigfilespath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\open\\abddf\\vbScript\\SendMail.vbs";
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

    public static void WaitUntilElementInvisible(WebElement element, int timeOut) {
        WebDriverWait wait = new WebDriverWait(ManagerDriver.getInstance().getWebDriver(), timeOut);
            wait.until(ExpectedConditions.invisibilityOf(element));
     }

    public static void MouseMoveToElement(WebElement element) throws InterruptedException {
        Actions action = new Actions(ManagerDriver.getInstance().getWebDriver());
        Thread.sleep(500);
        action.moveToElement(element).build().perform();
    }

    public static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor)ManagerDriver.getInstance().getWebDriver();
        js.executeScript("arguments[0].scrollIntoView();)", element);
    }

    public static void highlightElement(WebElement element) throws Exception {
            String attributevalue = "border:10px solid green;";
            JavascriptExecutor executor = (JavascriptExecutor) ManagerDriver.getInstance().getWebDriver();
            String getattrib = element.getAttribute("style");
            executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, attributevalue);
            Thread.sleep(100);
            executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, getattrib);
    }

}
