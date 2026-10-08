package common.libraries.miscellaneous;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.libraries.generic.CommonFunctions;
import common.pages.Oseries9;
import common.utilities.CloudData;

public class OSeries9 extends CommonFunctions {
	private WebDriver driver;

	public OSeries9(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	public String Login_Oseries9(String Testpath, String value) throws Exception {
		String status = "";
		try {
			Oseries9 oseries9 = PageFactory.initElements(driver, Oseries9.class);
			CloudData.load_data(Testpath, value);
			waitforElement(30, oseries9.USERNAME, driver);
			type(oseries9.USERNAME, CloudData.get_data("UserName"));
			type(oseries9.PASSWORD, CloudData.get_data("Password"));
			click(oseries9.LOGINBUTTON);
			if (status == "") {
				status = "Pass";
			}
		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;
	}

	public String Verify_Cert_Sync(String Testpath, String value) throws Exception {
		Oseries9 oseries9 = PageFactory.initElements(driver, Oseries9.class);
		String Verification = "", status = "";
		CloudData.load_data(Testpath,value);
		try {
			waitforElement(180, oseries9.MYENTERPRISE, driver);
			click(oseries9.MYENTERPRISE);
			waitforElement(180, oseries9.CERTIFICATES, driver);
			click(oseries9.CERTIFICATES);
			for (int i = CloudData.start_row; i <= CloudData.end_row; i++) {
				CloudData.set_data_row(i);
				Thread.sleep(5000);
				waitforElement(30, oseries9.SEARCHBUTTON, driver);
				click(oseries9.SEARCHBUTTON);
				waitforElement(30,
						"//div[@ref='eCenterContainer']/div/div[@col-id='certificateCoverages']/div/div/div[contains(.,'"
								+ CloudData.get_data("State").toUpperCase() + "')]",
						driver);
				Verification = "";
				Verification = (VerifyObject(driver,
						"//div[@ref='eCenterContainer']/div/div[@col-id='certificateCoverages']/div/div/div[contains(.,'"
								+ CloudData.get_data("State").toUpperCase() + "')]")) == true ? Verification
										: Verification + "Fail, " + CloudData.get_data("State")
												+ "Certificate not synched in Oseries;";
				status = status + Verification;

			}

			if (status.isEmpty()) {
				status = "Pass";
			}
		} catch (Exception e) {
			return e.getMessage() + status;
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		return status;
	}

	public String Logout_Oseries9() throws Exception {
		String status = "";
		try {
			Oseries9 oseries9 = PageFactory.initElements(driver, Oseries9.class);
			waitforElement(30, oseries9.LOGOUT, driver);
			click(oseries9.LOGOUT);
			if (status == "") {
				status = "Pass";
			}
		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;
	}

}
