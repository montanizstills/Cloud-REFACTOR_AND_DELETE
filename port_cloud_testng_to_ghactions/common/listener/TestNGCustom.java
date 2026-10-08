/*###################################################################################################
Name: TestNGCustom.java 
Description: TestNG custom test reporting 
Author: Sudheer Bandi
Date Created:01/17/2014
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.listener;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestNGCustom extends TestListenerAdapter {

	// Take screen shot only for failed test case

	@Override
	public void onTestFailure(ITestResult tr) {
		try {
			common.utilities.UtilityScript.xScreenShot();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			// common.utilities.UtilityScript.xUpdateTestDetails("FAIL");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		// p2pZions.PageObjects.UtilityScript.xScreenShot();
		try {
			// common.utilities.UtilityScript.xUpdateTestDetails("SKIPPED");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		// p2pZions.PageObjects.UtilityScript.xScreenShot();
		try {
			// common.utilities.UtilityScript.xUpdateTestDetails("PASS");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}