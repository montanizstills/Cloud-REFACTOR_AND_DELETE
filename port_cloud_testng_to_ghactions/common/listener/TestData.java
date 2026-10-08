/*###################################################################################################
Name: TestData.java 
Description: Used to set the static test data used in the functions 
Author: Sudheer Bandi
Date Created:01/17/2014
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.listener;

public class TestData extends common.listener.TestNGAssertionsCustom {

	public static final String Url = "Test";

	public static final String UserName = "qtp123";
	public static final String UserNameP = "qtp123";

	// ##################################################################################
	// Don't change any thing below
	// ####################################################
	// ##################################################################################

	// Assigned
	// variables****************************************************************
	public static final int ImplicitWait = 5;
	public static final int TimeOut10 = 10000;
	public static final int TimeOut20 = 20000;

	// Date
	// Data*************************************************************************
	// public static final String DateFormat = "dd.MM.yyyy_hh:mm a";
	public static final String DateTimeFormat = "MM/dd/yyyy_hh:mm a";

	// public static final String DateFormat = "dd.MM.yyyy";
	public static final String DateFormat = "MM/dd/yyyy";
	public static final String DateFormat1 = "MMM dd, yyyy";

	// Non Assigned variables
	// ***********************************************************

	public static String NewDate, NewTime, Script, MethodName = "", Method = "";
	public static String Temp, Temp1, sMessages = "";

}