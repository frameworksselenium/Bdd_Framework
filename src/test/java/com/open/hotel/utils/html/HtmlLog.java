package com.open.hotel.utils.html;

import com.open.hotel.utils.UIUtils;
import com.open.hotel.utils.threadLevelVariables.VariableManager;
import com.open.hotel.utils.webDriverFactory.ManagerDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.open.hotel.runner.TestNGRunner;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class HtmlLog extends UIUtils {
    public static String g_sFileName = "";
    public static String g_iCapture_Count = "";// 'Number of Image capture
    public static int g_iImage_Capture;//=""; //'Flag for Image Capture in Result File
    public static int g_iPass_Count = 0; //'Pass Count
    public static int g_iFail_Count;//=0; //'Fail Count
    public static int g_Total_TC;//=0;
    public static int g_Total_Pass;//=0;
    public static int g_Total_Fail;//=0;
    public static int g_Flag;//=0;
    public static int g_Flag1;//=0;
    public static Date g_tSummaryStart_Time;// 'Start Time
    public static Date g_tSummaryEnd_Time; //'End Time

    //public static Date g_tStart_Time; //'Start Time
    public static Date g_tSummaryTCStart_Time; //'Start time for each test case in Summary Report
    public static Date g_tSummaryTCEnd_Time; //'Start time for each test case in Summary Report

    public static int g_SummaryTotal_TC;//=0;
    public static int g_SummaryTotal_Pass;//=0;
    public static int g_SummaryTotal_Fail;//=0;
    public static int g_SummaryFlag = 0;
    public static String g_ScriptName = "";
    public static String g_sSection = "";

    public HtmlLog(){

    }

    public static void summaryInitialization(String strSummaryReportName) {
        Date d = new Date();
        TestNGRunner.properties.setProperty("ResultsFolderPath", TestNGRunner.properties.getProperty("resultFolderName"));
        String summaryFileName = TestNGRunner.properties.getProperty("ResultsFolderPath")+"\\" + strSummaryReportName  +d.getYear() + d.getMonth() + d.getDay() + "_" + d.getHours() + d.getMinutes() + d.getSeconds()+ ".htm";
        TestNGRunner.properties.setProperty("SummaryFileName", summaryFileName);
        summaryOpenHtmlFile(strSummaryReportName);// 'logo, heading
        summaryInsertSection(); //'TestCaseID,Scenario Name and Result
    }

    public static void summaryOpenHtmlFile(String sSection) {
        g_iPass_Count=0;
        g_iFail_Count=0;
        g_sFileName = "sScriptName";
        g_SummaryTotal_TC = 0;
        g_SummaryTotal_Pass = 0;
        g_SummaryTotal_Fail = 0;
        g_SummaryFlag = 0;
        g_ScriptName="sScriptName";
        Date d = new Date();
        String gsTempFile = TestNGRunner.properties.getProperty("SummaryFileName");
        if(gsTempFile ==null) {
            //gsTempFile = TestNGRunner.properties.getProperty("SummaryFolderName1") +d.getYear() + d.getMonth() + d.getDay() + "_" + d.getHours() + d.getMinutes() + d.getSeconds()+ ".htm";
            //TestNGRunner.properties.setProperty("SummaryFileName",gsTempFile);
        }
        Path objPath = Paths.get(gsTempFile);
        FileWriter objFile= null;
        try {
            objFile = new FileWriter(gsTempFile,true);

            objFile.write("<HTML><BODY><TABLE BORDER=0 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
            objFile.write("<TR COLS=2><TD BGCOLOR=WHITE WIDTH=6%><IMG SRC='https://www.capgemini.com/sites/all/themes/capgemini/logo.png'></TD><TD WIDTH=94% BGCOLOR=WHITE><FONT FACE=VERDANA COLOR=NAVY SIZE=3><B>&nbsp;Automation Test Results<BR/><FONT FACE=VERDANA COLOR=SILVER SIZE=2>&nbsp; Date: " + d +"</BR>&nbsp;On Machine :" + TestNGRunner.properties.getProperty("LocalHostName") + "</B></FONT></TD><TD BGCOLOR=WHITE WIDTH=6%><IMG SRC='https://www.capgemini.com/sites/all/themes/capgemini/logo.png'></TD></TR></TABLE>");
            objFile.write("<TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
            objFile.write("<TR><TD BGCOLOR=#66699 WIDTH=15%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Module Name:</B></FONT></TD><TD COLSPAN=6 BGCOLOR=#66699 ><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>" + sSection + "</B></FONT></TD></TR>");
            objFile.write("</TABLE></BODY></HTML>");
            objFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        g_tSummaryStart_Time = d;
        //g_tSummaryTCStart_Time = d;
        g_sSection=sSection;
    }

    public static void summaryInsertSection() {

        String gsTempFile = TestNGRunner.properties.getProperty("SummaryFileName");

        Path objPath=Paths.get(gsTempFile);
        FileWriter objFile= null;
        try {
            objFile = new FileWriter(gsTempFile,true);
            objFile.write("<HTML><BODY><TABLE BORDER=1 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
            objFile.write("<TR COLS=6><TD BGCOLOR=#FFCC99 WIDTH=15><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Test Case ID</B></FONT></TD><TD BGCOLOR=#FFCC99 WIDTH=45%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Test Case Name</B></FONT></TD><TD BGCOLOR=#FFCC99 WIDTH=20%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Scenario Name</B></FONT></TD><TD BGCOLOR=#FFCC99 SIZE=2 WIDTH=10%><B>Time</B></FONT></TD><TD BGCOLOR=#FFCC99 SIZE=2 WIDTH=10%><B>Result</B></FONT></TD></TR>");
            objFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initilization(String BprocessName) {
        Date d = new Date();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        g_tSummaryTCStart_Time = d;
        VariableManager.getInstance().getVariablesManager().setObject("g_tSummaryTCStart_Time",dtf.format(now).toString());

        String fileName = TestNGRunner.properties.getProperty("ResultsFolderPath") + "\\" + BprocessName + d.getYear() + d.getMonth() + d.getDay() + "_" + d.getHours() + d.getMinutes() + d.getSeconds()+ ".htm";
        //TestNGRunner.properties.setProperty("FileName", fileName);
        VariableManager.getInstance().getVariablesManager().setObject("FileName",fileName);

        openHtmlFile(BprocessName);
        insertSection();
        insertTestCaseName("");
    }

    public static void openHtmlFile(String sSection) {
        g_iPass_Count=0;
        g_iFail_Count=0;
        g_sFileName = "sScriptName";
        g_iImage_Capture = 1;
        g_Total_TC = 0;
        g_Total_Pass = 0;
        g_Total_Fail = 0;
        g_Flag = 0;
        g_Flag1 = 0;
        g_ScriptName="sScriptName";
        String machinename = null;

        Date d = new Date();
        String gsTempFile = VariableManager.getInstance().getVariablesManager().getObject("FileName");
        Path objPath=Paths.get(gsTempFile);
            FileWriter objFile= null;
            try {
                objFile = new FileWriter(gsTempFile,true);
                objFile.write("<HTML><BODY><TABLE BORDER=0 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
                objFile.write("<TR COLS=2><TD BGCOLOR=WHITE WIDTH=6%><IMG SRC='https://www.capgemini.com/sites/all/themes/capgemini/logo.png'></TD><TD WIDTH=94% BGCOLOR=WHITE><FONT FACE=VERDANA COLOR=NAVY SIZE=3><B>GW PC Automation Test Results<BR/><FONT FACE=VERDANA COLOR=SILVER SIZE=2>Date: " + d +"</BR>on Machine :" + machinename + "</B></FONT></TD><TD BGCOLOR=WHITE WIDTH=6%><IMG  SRC='https://www.capgemini.com/sites/all/themes/capgemini/logo.png'></TD></TR></TABLE>");
                objFile.write("<TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
                objFile.write("<TR><TD BGCOLOR=#66699 WIDTH=15%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Module Name:</B></FONT></TD><TD COLSPAN=6 BGCOLOR=#66699 ><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>" + sSection + "</B></FONT></TD></TR>");
                objFile.write("</TABLE></BODY></HTML>");
                objFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        g_sSection=sSection;
    }

    public static void insertSection() {
        Date d = new Date();
        String gsTempFile = VariableManager.getInstance().getVariablesManager().getObject("FileName");
        Path objPath=Paths.get(gsTempFile);

        if(Files.exists(objPath)) {
            FileWriter objFile= null;
            try {
                objFile = new FileWriter(gsTempFile,true);
                objFile.write("<HTML><BODY><TABLE BORDER=1 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
                objFile.write("<TR COLS=6><TD BGCOLOR=#FFCC99 WIDTH=15%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Test Case Name</B></FONT></TD><TD BGCOLOR=#FFCC99 WIDTH=15%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Component</B></FONT></TD><TD BGCOLOR=#FFCC99 WIDTH=15%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Expected Value</B></FONT></TD><TD BGCOLOR=#FFCC99 WIDTH=25%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Actual Value</B></FONT></TD><TD BGCOLOR=#FFCC99 WIDTH=25%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Result</B></FONT></TD></TR>");
                objFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void insertTestCaseName(String sDesc) {
        Date d = new Date();
        String gsTempFile = VariableManager.getInstance().getVariablesManager().getObject("FileName");
        Path objPath=Paths.get(gsTempFile);

        g_Total_TC = g_Total_TC+1;
        if(g_Flag1!=0) {
            if(g_Flag == 0) {
                g_Total_Pass = g_Total_Pass+1;
            }
            else {
                g_Total_Fail = g_Total_Fail+1;
            }
        }
        g_Flag = 0;
    }

    public static void insertResult(String sTestCaseName, String sDesc, String sExpected, String sActual, String sResult){
        WebDriver driver = ManagerDriver.getInstance().getWebDriver();
        g_Flag1=1;
        Date d = new Date();
        String gsTempFile = VariableManager.getInstance().getVariablesManager().getObject("FileName");
        Path objPath=Paths.get(gsTempFile);
        try{
            if(Files.exists(objPath)) {
                FileWriter objFile=new FileWriter(gsTempFile,true);
                if(sResult.toUpperCase()=="PASS") {
                    g_iPass_Count = g_iPass_Count + 1;
                    if (TestNGRunner.properties.getProperty("CaptureScreenShotforPass").equalsIgnoreCase("YES")) {
                        String I_sFile="";
                        g_iCapture_Count="Screen" + d.getHours() +d.getMinutes() + d.getSeconds();
                        I_sFile = TestNGRunner.properties.getProperty("resultFolderName") + "\\" + VariableManager.getInstance().getVariablesManager().getObject("testCaseName") + "\\Screen" + g_iCapture_Count + ".png";
                        if(driver != null) {
                            File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                            FileUtils.copyFile(scrFile, new File(I_sFile));
                        }
                        objFile.write("<TR COLS=5><TD BGCOLOR=#EEEEEE WIDTH=15%><FONT FACE=VERDANA SIZE=2>" + sTestCaseName +"</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=15%><FONT FACE=VERDANA SIZE=2>" + sDesc +"</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2>" + sExpected +"</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=WINGDINGS SIZE=4>2></FONT><FONT FACE=VERDANA SIZE=2><A HREF='" + I_sFile +"'>" + sActual + "</A></FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE='WINGDINGS 2' SIZE=5 COLOR=GREEN>P</FONT><FONT FACE=VERDANA SIZE=2 COLOR=GREEN><B>" + sResult + "</B></FONT></TD></TR>");
                    }
                    else {
                        objFile.write("<TR COLS=5><TD BGCOLOR=#EEEEEE WIDTH=15%><FONT FACE=VERDANA SIZE=2>" + sTestCaseName + "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=15%><FONT FACE=VERDANA SIZE=2>" + sDesc + "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2>" + sExpected +"</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2>" + sActual + "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE='WINGDINGS 2' SIZE=5 COLOR=GREEN>P</FONT><FONT FACE=VERDANA SIZE=2 COLOR=GREEN><B>" + sResult + "</B></FONT></TD></TR>");
                    }
                }
                else if(sResult.toUpperCase()=="FAIL") {
                    g_Flag = 1;
                    g_SummaryFlag = 1;
                    g_iFail_Count = g_iFail_Count + 1;
                    if (TestNGRunner.properties.getProperty("CaptureScreenShotforFail").equalsIgnoreCase("YES")) {
                        String I_sFile="";
                        g_iCapture_Count="Screen" + d.getHours() +d.getMinutes() + d.getSeconds();
                        I_sFile = TestNGRunner.properties.getProperty("resultFolderName") + "\\" + VariableManager.getInstance().getVariablesManager().getObject("testCaseName") + "_Screen" + g_iCapture_Count + ".png";
                        if(driver != null) {
                            File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                            FileUtils.copyFile(scrFile, new File(I_sFile));
                        }
                        objFile.write("<TR COLS=5><TD BGCOLOR=#EEEEEE WIDTH=15%><FONT FACE=VERDANA SIZE=2>" + sTestCaseName +"</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=15%><FONT FACE=VERDANA SIZE=2>" + sDesc +"</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2>" + sExpected +"</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=WINGDINGS SIZE=4>2></FONT><FONT FACE=VERDANA SIZE=2><A HREF='" + I_sFile +"'>" + sActual + "</A></FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE='WINGDINGS 2' SIZE=5 COLOR=GREEN>P</FONT><FONT FACE=VERDANA SIZE=2 COLOR=RED><B>" + sResult + "</B></FONT></TD></TR>");
                    }
                    else {
                        objFile.write("<TR COLS=5><TD BGCOLOR=#EEEEEE WIDTH=15%><FONT FACE=VERDANA SIZE=2>" + sTestCaseName + "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=15%><FONT FACE=VERDANA SIZE=2>" + sDesc + "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2>" + sExpected +"</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2>" + sActual + "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE='WINGDINGS 2' SIZE=5 COLOR=GREEN>P</FONT><FONT FACE=VERDANA SIZE=2 COLOR=RED><B>" + sResult + "</B></FONT></TD></TR>");
                    }
                }
                else if(sResult.toUpperCase()=="WARNING") {
                    if (TestNGRunner.properties.getProperty("CaptureScreenShotforWarning").equalsIgnoreCase("YES")) {
                        String I_sFile="";
                        g_iCapture_Count="Screen" + d.getHours() +d.getMinutes() + d.getSeconds();
                        I_sFile = TestNGRunner.properties.getProperty("resultFolderName") + "\\" + VariableManager.getInstance().getVariablesManager().getObject("testCaseName") + "\\Screen" + g_iCapture_Count + ".jpeg";
                        if(driver != null){
                            File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                            FileUtils.copyFile(scrFile, new File(I_sFile));
                        }
                        objFile.write("<TR COLS=5><TD BGCOLOR=#EEEEEE WIDTH=15%><FONT FACE=VERDANA SIZE=2>" + sTestCaseName +"</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=15%><FONT FACE=VERDANA SIZE=2>" + sDesc +"</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2>" + sExpected +"</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=WINGDINGS SIZE=4>2></FONT><FONT FACE=VERDANA SIZE=2><A HREF='" + I_sFile +"'>" + sActual + "</A></FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE='WINGDINGS 2' SIZE=5 COLOR=GREEN>P</FONT><FONT FACE=VERDANA SIZE=2 COLOR=GREEN><B>" + sResult + "</B></FONT></TD></TR>");
                    }
                    else {
                        objFile.write("<TR COLS=5><TD BGCOLOR=#EEEEEE WIDTH=15%><FONT FACE=VERDANA SIZE=2>" + sTestCaseName + "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=15%><FONT FACE=VERDANA SIZE=2>" + sDesc + "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2>" + sExpected +"</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2>" + sActual + "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE='WINGDINGS 2' SIZE=5 COLOR=GREEN>P</FONT><FONT FACE=VERDANA SIZE=2 COLOR=GREEN><B>" + sResult + "</B></FONT></TD></TR>");
                    }
                }
                objFile.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void  summaryInsertTestCase() throws ParseException {
        //Date g_tSummaryTCEnd_Time = null;
        Date d = new Date();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        g_tSummaryTCEnd_Time = d;
        //VariableManager.getInstance().getVariablesManager().setObject("g_tSummaryTCEnd_Time", dtf.format(now).toString());

        String intDateDiff = "";
        //long diff = g_tSummaryTCEnd_Time.getTime() - g_tSummaryTCStart_Time.getTime();
        //String end = VariableManager.getInstance().getVariablesManager().getObject("g_tSummaryTCEnd_Time");
        String start = VariableManager.getInstance().getVariablesManager().getObject("g_tSummaryTCStart_Time");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        //Date end1 = formatter.parse(end);
        Date start1 = formatter.parse(start);

        long diff = g_tSummaryTCEnd_Time.getTime() - start1.getTime();

        String gsTempFile = TestNGRunner.properties.getProperty("SummaryFileName");
        Path objPath=Paths.get(gsTempFile);
        g_SummaryTotal_TC = g_SummaryTotal_TC+1;
        if (g_SummaryFlag==0) {
            g_SummaryTotal_Pass = g_SummaryTotal_Pass+1;
        }
        else {
            g_SummaryTotal_Fail = g_SummaryTotal_Fail+1;
        }


        String strStatus="";
        switch (g_SummaryFlag) {
            case 0:
                strStatus = "PASSED";
                break;
            case 1:
                strStatus = "FAILED";
                break;
            default:
                strStatus = "FAILED";
                break;
        }

        long intDateDiff1 = diff / (60 * 1000) % 60;
        if (intDateDiff1 == 0){
            intDateDiff1 = diff / 1000 % 60;
            intDateDiff = Long.toString(intDateDiff1) + " Seconds";
        }
        else{
            intDateDiff1 = diff / (60 * 1000) % 60;
            intDateDiff = Long.toString(intDateDiff1) + " Minutes";
        }

        if(Files.exists(objPath)){
            FileWriter objFile= null;
            try {
                objFile = new FileWriter(gsTempFile,true);

                if(strStatus.toUpperCase()== "PASSED") {
                    objFile.write("<TR COLS=6><TD BGCOLOR=#EEEEEE WIDTH=15%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>" + VariableManager.getInstance().getVariablesManager().getObject("testCaseID") + "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=45%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><A HREF='" + VariableManager.getInstance().getVariablesManager().getObject("FileName") + "'>" + VariableManager.getInstance().getVariablesManager().getObject("testCaseName") + "</A></FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=20%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>" + VariableManager.getInstance().getVariablesManager().getObject("testCaseName") + "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>" + intDateDiff + "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=WINGDINGS 2' SIZE=5 COLOR=GREEN>P</FONT><FONT FACE=VERDANA SIZE=2 COLOR=GREEN><B>" + strStatus + "</B></FONT></TD></TR>");
                }
                else if (strStatus.toUpperCase()== "FAILED") {
                    objFile.write("<TR COLS=6><TD BGCOLOR=#EEEEEE WIDTH=15%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>" + VariableManager.getInstance().getVariablesManager().getObject("testCaseID") + "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=45%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><A HREF='" + VariableManager.getInstance().getVariablesManager().getObject("FileName") + "'>" + VariableManager.getInstance().getVariablesManager().getObject("testCaseName") + "</A></FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=20%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>" + VariableManager.getInstance().getVariablesManager().getObject("testCaseName") + "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>" + intDateDiff + "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=WINGDINGS 2' SIZE=5 COLOR=RED>O</FONT><FONT FACE=VERDANA SIZE=2 COLOR=RED><B>" + strStatus + "</B></FONT></TD></TR>");
                }
                g_tSummaryTCStart_Time = d;
                objFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            g_SummaryFlag = 0;
        }
    }

    public static void summaryCloseHtml(String strRelease) {
        Date d = new Date();
        String gsTempFile = TestNGRunner.properties.getProperty("SummaryFileName");
        Path objPath=Paths.get(gsTempFile);

        g_tSummaryEnd_Time = d;
        Date ts = d;
           String sTime = "";
        long diff = g_tSummaryEnd_Time.getTime() - g_tSummaryStart_Time.getTime();
        long intDateDiff1 = diff / (60 * 1000) % 60;
        if (intDateDiff1 == 0) {
            intDateDiff1 = diff / 1000 % 60;
            sTime = Long.toString(intDateDiff1) + " Seconds";
        }
        else {
            intDateDiff1 = diff / (60 * 1000) % 60;
            sTime = Long.toString(intDateDiff1) + " Minutes";
        }
        if(Files.exists(objPath))
        {
            FileWriter objFile= null;
            try {
                objFile = new FileWriter(gsTempFile,true);
                objFile.write("<HTML><BODY><TABLE BORDER=1 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
                objFile.write("<TR><TD BGCOLOR=#66699 WIDTH=15%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Release :</B></FONT></TD><TD COLSPAN=6 BGCOLOR=#66699><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>" + strRelease + "</B></FONT></TD></TR>");
                objFile.write("<TR COLS=5><TD BGCOLOR=#66699 WIDTH=25%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Total Test Case Executed : " + g_SummaryTotal_TC + "</B></FONT></TD><TD BGCOLOR=#66699 WIDTH=15%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Total Test Cases Passed : " + g_SummaryTotal_Pass + "</B></FONT></TD><TD BGCOLOR=#66699 WIDTH=25%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B> Total Test Cases Failed : " + g_SummaryTotal_Fail + "</B></FONT></TD><TD BGCOLOR=#66699 WIDTH=25%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Total Execution Time : " + sTime + " </B></FONT></TD></TR>");
                objFile.write("</TABLE></BODY></HTML>");
                objFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String sendMail = TestNGRunner.properties.getProperty("SendMail");
        if (sendMail.contains("YES")) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SendMail(TestNGRunner.properties.getProperty("MailTo"), TestNGRunner.properties.getProperty("MailCC"), gsTempFile, formatter.format(g_tSummaryEnd_Time), formatter.format(g_tSummaryStart_Time), strRelease, TestNGRunner.properties.getProperty("ModuleName"), String.valueOf(g_SummaryTotal_TC), String.valueOf(g_SummaryTotal_Pass), String.valueOf(g_SummaryTotal_Fail), TestNGRunner.properties.getProperty("Region"));
        }
    }

}
