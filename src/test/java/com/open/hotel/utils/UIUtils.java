package com.open.hotel.utils;

import com.open.hotel.runner.TestNGRunner;
import com.open.hotel.utils.threadLevelVariables.VariableManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.open.hotel.utils.webDriverFactory.ManagerDriver;
public class UIUtils {

    public void type(WebElement element, String value, String elementName, String page){
        try{
            boolean elementClickable = WaitUntilClickable(element, Integer.valueOf(10000));
            if (elementClickable == true) {

            }
            element.sendKeys(value);
            TestNGRunner.htmLog.insertResult(VariableManager.getInstance().getVariablesManager().getObject("testCaseID"), "methodName", "", "", "PASS");
        }catch(Exception e){
            TestNGRunner.htmLog.insertResult(VariableManager.getInstance().getVariablesManager().getObject("testCaseID"), "methodName", "", "", "FAIL");
            Assert.fail(e.getMessage());
        }
    }

    public void clickElement(WebElement element, String elementName, String page){
        try{
            boolean elementClickable = WaitUntilClickable(element, Integer.valueOf(10000));
            element.click();
            TestNGRunner.htmLog.insertResult(VariableManager.getInstance().getVariablesManager().getObject("testCaseID"), "methodName", "", "", "PASS");
        }catch(Exception e){
            TestNGRunner.htmLog.insertResult(VariableManager.getInstance().getVariablesManager().getObject("testCaseID"), "methodName", "", "", "FAIL");
            Assert.fail(e.getMessage());
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

}
