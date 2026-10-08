/*###################################################################################################
Name: TestListenerAdapter.java 
Description: Used for reporting using TestNG
Author: Sudheer Bandi
Date Created:01/17/2014
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.listener;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class TestListenerAdapter implements IInvokedMethodListener {

	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
	}

	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
	}
}