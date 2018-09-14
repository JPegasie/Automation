package com.pegasie.base;

import com.pegasie.datamodel.TestCase;
import com.pegasie.datamodel.TestStep;
import com.pegasie.util.CSVUtil;
import com.pegasie.util.ExcelUtil;
import com.pegasie.util.FileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestEngine {
	private static Properties properties = new Properties();
	private static Logger logger = LogManager.getLogger();
	private static TestRunner testRunner = new TestRunner(properties, logger);
	private static Boolean isAnyCaseFailed = false;

	public static void main(String[] args) {
		// <editor-fold desc="Step 1: Test Engine reads project config file and add it
		// to project property.">
		boolean isRemoteTest;
		try (FileInputStream configFile = new FileInputStream("src/main/java/com/pegasie/config/config.properties")) {
			properties.load(configFile);
			System.setProperty("IsRemoteTest", properties.getProperty("IsRemoteTest"));
			System.setProperty("SeleniumGridUrl", properties.getProperty("SeleniumGridUrl"));
			System.setProperty("DriverPath", properties.getProperty("DriverPath"));
			isRemoteTest = Boolean.parseBoolean(System.getProperty("IsRemoteTest"));
			System.setProperty("ScreenshotPath", isRemoteTest ? properties.getProperty("RemoteScreenshotPath")
					: properties.getProperty("LocalScreenshotPath"));
			System.setProperty("TestResultLocation", isRemoteTest ? properties.getProperty("RemoteTestResultLocation")
					: properties.getProperty("LocalTestResultLocation"));
			System.setProperty("ScreenshotFormat", properties.getProperty("ScreenshotFormat"));
			System.setProperty("DownloadFolder", isRemoteTest ? properties.getProperty("RemoteDownloadFolder")
					: properties.getProperty("LocalDownloadFolder"));
			System.setProperty("Upload", isRemoteTest ? properties.getProperty("RemoteUploadFolder")
					: properties.getProperty("LocalUploadFolder"));
			logger.info("Project config file is loaded.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.fatal("Project config file doesn't exist.");
			return;
		} catch (IOException e) {
			e.printStackTrace();
			logger.fatal("Test engine cannot read the project config file.");
			return;
		}
		// </editor-fold>

		try {
			DateFormat df = new SimpleDateFormat("yyyyMMddHHmm"); // add S if you need milliseconds
			String testExecutionDetailFile = null;
			String testResultFile = null;
			String testPlanFolder = null;
			if (isRemoteTest) {
				testPlanFolder = properties.getProperty("RemoteTestPlanFolder");
			} else {
				testPlanFolder = properties.getProperty("LocalTestPlanFolder");
			}
			File resultFolder = new File(System.getProperty("TestResultLocation"));
			if (!resultFolder.exists()) {
				resultFolder.mkdir();
			}
			testResultFile = System.getProperty("TestResultLocation") + "TestResult_" + df.format(new Date()) + ".csv";
			testExecutionDetailFile = System.getProperty("TestResultLocation") + "TestExecutionDetail_"
					+ df.format(new Date()) + ".csv";
			System.setProperty("TestExecutionDetailFile", testExecutionDetailFile);
			List<String> testPlans = FileUtil.getFileListByExtension(testPlanFolder, "xlsx");
			if (testPlans.isEmpty()) {
				logger.warn("Test plan folder is empty!");
				return;
			} else {
				CSVUtil.initTestResultFile(testResultFile);
				CSVUtil.initExecutionDetailFile(testExecutionDetailFile);
				logger.info("Test Engine create test result csv file.");
				for (String testPlanFile : testPlans) {
					System.setProperty("TestPlan", testPlanFile);
					logger.info("Test Engine starts to execute test cases in excel file: " + testPlanFile);
					String testPlanName = FileUtil.getFileNameFromAbsolutePath(testPlanFile);
					int executionTimes = 0;
					int argumentColumn = 4;
					try (Workbook testPlan = WorkbookFactory.create(new File(testPlanFile))) {
						// <editor-fold desc="Step 2: Test Engine starts reading config sheet and add it
						// to project property.">
						Sheet configSheet = testPlan.getSheet(properties.getProperty("ConfigSheetName"));
						HashMap configMap = ExcelUtil.getConfigValueFromExcel(configSheet);
						Set<Map.Entry<String, String>> entrySet = configMap.entrySet();
						for (Map.Entry entry : entrySet) {
							System.setProperty(entry.getKey().toString(), entry.getValue().toString());
						}
						logger.info(
								testPlanName + " --> Test Engine read config sheet and added it to project property.");
						// </editor-fold>

						// <editor-fold desc="Step 3: Test Engine starts reading TestCases sheet, then
						// add active cases to list.">
						Sheet caseSheet = testPlan.getSheet(properties.getProperty("TestCasesSheetName"));
						List<TestCase> testCaseList = ExcelUtil.getActiveTestCaseFromExcel(caseSheet);
						logger.info(testPlanName
								+ " --> Test Engine read TestCases sheet, then added those active cases to list.");
						// </editor-fold>

						// <editor-fold desc="Step 4: Test Engine starts reading TestCases sheet, then
						// add it to array list.">
						switch (System.getProperty("Language")) {
						case "French || English":
							executionTimes = 2;
							break;
						case "French":
							executionTimes = 1;
							break;
						case "English":
							executionTimes = 1;
							argumentColumn = 5;
							break;
						}
						logger.info("Test Engine read the language setting.");
						// </editor-fold>

						// <editor-fold desc="Step 5: Test Engine create test result csv file.">

						// </editor-fold>

						do {
							// <editor-fold desc="Step 6: Test Engine starts reading TestCases sheet, then
							// add it to array list.">
							Sheet stepSheet = testPlan.getSheet(properties.getProperty("TestStepsSheetName"));
							List<ArrayList<TestStep>> caseList = new ArrayList<ArrayList<TestStep>>();
							for (TestCase testCase : testCaseList) {
								ArrayList<TestStep> stepArray = ExcelUtil.getStepArrayFromExcel(stepSheet,
										testCase.testName, argumentColumn);
								caseList.add(stepArray);
							}
							logger.info(testPlanName
									+ " --> Test Engine starts reading TestCases sheet, then add it to array list.");
							// </editor-fold>

							// <editor-fold desc="Step 7: Test Engine send test case to test runner to
							// execute.">
							String language = argumentColumn == 4 ? "French" : "English";
							logger.info(testPlanName + " --> Test Engine send " + language
									+ " test case to test runner to execute.");
							for (ArrayList<TestStep> testSteps : caseList) {
								boolean result = testRunner.runTestCase(testSteps);
								if (result) {
									logger.info(
											testPlanName + " --> Test case: " + testSteps.get(0).testName + " passed.");
									CSVUtil.insertToResultFile(testResultFile, testPlanFile,
											String.valueOf(testSteps.get(0).testId), testSteps.get(0).testName,
											language, "Passed");
								} else {
									logger.error(
											testPlanName + " --> Test case: " + testSteps.get(0).testName + " failed.");
									CSVUtil.insertToResultFile(testResultFile, testPlanFile,
											String.valueOf(testSteps.get(0).testId), testSteps.get(0).testName,
											language, "Failed");
									if (!isAnyCaseFailed) {
										isAnyCaseFailed = true;
									}
								}
							}
							// </editor-fold>
							executionTimes--;
							argumentColumn = 5;
						} while (executionTimes > 0);

					} catch (IOException ioException) {
						ioException.printStackTrace();
						logger.error(ioException);
					} catch (InvalidFormatException invalidFormatException) {
						invalidFormatException.printStackTrace();
						logger.error(invalidFormatException);
					}
				}
//				if (isRemoteTest) {
//					String names = properties.getProperty("RemoteTesters");
//					String nodes = properties.getProperty("RemoteNodes");
//					String commandTemplate = "powershell.exe  Invoke-Command -ComputerName %s -ScriptBlock {Get-Process -Name \"iexplore\",\"chrome\",\"firefox\",\"chromedriver\",\"firefoxdriver\" -IncludeUserName | Where-Object UserName -match %s | Stop-Process -Force}";
//					String commandResult = "Test engine has terminated all the process of user %s on server %s";
//					if (names.contains(",")) {
//						String[] testers = names.split(",");
//						for (String tester : testers) {
//							String command = String.format(commandTemplate, nodes, tester);
//							try {
//								Runtime.getRuntime().exec(command);
//								logger.info(String.format(commandResult, tester, nodes));
//							} catch (IOException e) {
//								e.printStackTrace();
//							}
//						}
//					} else {
//						String command = String.format(commandTemplate, nodes, names);
//						try {
//							Runtime.getRuntime().exec(command);
//							logger.info(String.format(commandResult, names, nodes));
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//					}
//				}
			}
		} finally {
//			if (TestBase.driver != null)
//				TestBase.driver.quit();
		}
		if (isAnyCaseFailed) {
			logger.warn("There is failed case, please check the log file and test result files for more details.");
			System.exit(1);
		} else {
			logger.info("Congrats, everything is great!");
			System.exit(0);
		}
	}
}