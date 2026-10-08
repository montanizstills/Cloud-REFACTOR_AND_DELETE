/*###################################################################################################
Name: DocumentCenter.java 
Description: Having Premium Document Center Client and Admin functionality 
Author: Venkat Reddy Kandula
Date Created:04/08/2020
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.libraries.admin;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import common.libraries.customer.PortalFunctions;
import common.libraries.customer.Transactions;
import common.libraries.generic.CommonFunctions;
import common.pages.ConfigurePage;
import common.pages.Documentcenter;

public class DocumentCenter extends CommonFunctions {
	private WebDriver driver;

	PortalFunctions DownloadFiles = PageFactory.initElements(driver, PortalFunctions.class);

	public DocumentCenter(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	public void UploadNotices(String Jurisdiction, String FilingPeriod, String DueDate, String Filename, String Comment)
			throws Exception {

		Documentcenter DC = PageFactory.initElements(driver, Documentcenter.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		String AutoITpath = xAutoIT();
		String AdjustmentFilePath = xUploadAdjustmentFile("AdjustmentFiles");
		JavaScriptclick(driver, DC.DOCUMENTCENTER);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, DC.CLIENTMONTHLY, driver);
		JavaScriptclick(driver, DC.CLIENTNOTICES);
		waitforElement(180, DC.Jurisdictions, driver);
		Thread.sleep(2000);
		if (Jurisdiction != "") {
			selectByValue(DC.Jurisdictions, Jurisdiction);
		}
		if (FilingPeriod != "") {
			selectByValue(DC.FilingPeriod, FilingPeriod);
		}
		if (DueDate != "") {
			type(DC.DueDate, DueDate);
		}
		Thread.sleep(4000);
		if (Filename != "") {
			JavaScriptclick(driver, DC.NoticeBrowseButton);
			Thread.sleep(4000);
			System.out.println();
			
			switch (Browser) {
			case "IE":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadIE.exe " + Filename);
				} else {
					Runtime.getRuntime()
							.exec(AutoITpath + "\\FileUploadIE.exe " + AdjustmentFilePath + "\\" + Filename + ".csv");
				}
				break;
			case "Firefox":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadFirefox.exe " + Filename);
				} else {
					Runtime.getRuntime().exec(
							AutoITpath + "\\FileUploadFirefox.exe " + AdjustmentFilePath + "\\" + Filename + ".csv");
				}
				break;
			case "Chrome":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadChrome.exe " + Filename);
				} else {
					Runtime.getRuntime().exec(
							AutoITpath + "\\FileUploadChrome.exe " + AdjustmentFilePath + "\\" + Filename + ".csv");
				}
				break;
			case "Safari":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadSafari.exe " + Filename);
				} else {
					Runtime.getRuntime().exec(
							AutoITpath + "\\FileUploadSafari.exe " + AdjustmentFilePath + "\\" + Filename + ".csv");
				}
				break;
			default:
			}
			Thread.sleep(4000);
		}
		if (Comment != "") {
			type(DC.Comments, Comment);
		}
		Thread.sleep(2000);
		JavaScriptclick(driver, DC.NoticeUpload);
		Thread.sleep(3000);
		if (Filename != "") {
			assertTrue(DC.CLIENTNOTICESALERT.isDisplayed());
			Thread.sleep(3000);
			if (Comment != "") {
				String commentslen = driver
						.findElement(By.xpath("//table/tbody/tr/td[contains(text(),'" + Filename + "')]/../td[6]"))
						.getText();
				assertTrue(Comment.length() >= commentslen.length());
			}
		} else {
			assertTrue(DC.AlertMessage.isDisplayed());
		}
	}

	public void ViewDocumentCenterComments(String ClientName, String FileType, String Filename, String Comment)
			throws Exception {

		Documentcenter DC = PageFactory.initElements(driver, Documentcenter.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		JavaScriptclick(driver, DC.AdminDocumentCenter);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, DC.ClientLabel, driver);
		selectByValue(DC.ClientDD, ClientName);
		Thread.sleep(4000);
		selectByValue(DC.FileTypeDD, FileType);
		Thread.sleep(3000);
		waitforElement(60, "//td[contains(.,'" + FileType + "')]", driver);
		Thread.sleep(2000);
		WebElement element = driver
				.findElement(By.xpath("//td[contains(.,'" + Filename + "')]/../td[contains(.,'" + Comment + "')]"));
		assertTrue(element.isDisplayed());
	}

	public void ClientUploadDC(String File_Name_Extn, String File_Type, String Jurisdiction, String FilingPeriod,
			String End_Date, String Period_Month, String Upload, String UploadError_Verify, String Parent_File,
			String Download_Click) throws Exception {
		Documentcenter DC = PageFactory.initElements(driver, Documentcenter.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		String AdjustmentFilePath = xUploadAdjustmentFile("DocumentCenter");
		xDownloadFiles("DocumentCenter");
		String AutoITpath = xAutoIT();
		String date = xGetDTAP();
		click(DC.DOCUMENTCENTER);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, DC.CLIENTNOTICES, driver);
		boolean Error = false;
		JavaScriptclick(driver, DC.CLIENTNOTICES);
		Thread.sleep(5000);
		String Fullfilename = File_Name_Extn;
		if (File_Type.equalsIgnoreCase("Notices")) {
			selectByValue(DC.JURISDICTIONSELECT, Jurisdiction);
			Thread.sleep(3000);
			selectByValue(DC.FILING_PERIOD, FilingPeriod);
			Thread.sleep(3000);
			type(DC.DUEDATE, End_Date);
			sendkeysTab(DC.DUEDATE);
		}
		if (Period_Month != "") {
			selectByValue(DC.PERIODSELECT, Period_Month);
			Thread.sleep(3000);
		}

		if ((Upload.equalsIgnoreCase("Admin")) || (Upload.equalsIgnoreCase("ClientNotices"))
				|| (Upload.equalsIgnoreCase("ClientReturns"))) {
			if ((Browser.equalsIgnoreCase("Chrome"))) {
				click(DC.NOTICESBROWSE);
			} else {
				JavaScriptclick(driver, DC.NOTICESBROWSE);
			}
		}

		else {
			if ((Browser.equalsIgnoreCase("Chrome"))) {
				click(DC.CLIENTBROWSE);
			} else {
				JavaScriptclick(driver, DC.CLIENTBROWSE);
			}
		}
		Thread.sleep(5000);
		//Filename = CloudData.get_data("File_Name");
		// Calling AutoIT Browser function
		fileUploadDialog(driver, Browser, AutoITpath, AdjustmentFilePath, File_Name_Extn, "");
		Thread.sleep(5000);
		String Uploadto = Upload;
		switch (Uploadto) {
		case "Admin":
		case "ClientNotices":
		case "ClientReturns":
			/*
			 * if (CloudData.get_data("Download_Checked").equalsIgnoreCase("Yes")){
			 * click(DC.DOWNLOADCHECK); Thread.sleep(1000); }
			 */
			JavaScriptclick(driver, DC.NOTICESUPLOAD);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforText(180, DC.NOTICESUPLOADSUC, driver);
			Thread.sleep(2000);
			if (VerifyObject(driver, DC.UPLOADERROR)) {
				waitforElement(180, DC.UPLOADERROR, driver);
				if (!UploadError_Verify.equalsIgnoreCase("Yes")) {
					assertTrue(true);
				} else {
					assertFalse(false);
				}
				driver.navigate().refresh();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, DC.CLIENTNOTICES, driver);
				Error = true;
			} else {
				driver.navigate().refresh();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, DC.CLIENTNOTICES, driver);
				Error = true;
			}

			if (!Error == true) {
				// Verifying File name related to Admin files upload
				if (VerifyObject(driver, DC.NOTICESFILENAME)) {
					waitforElement(180, DC.NOTICESFILENAME, driver);
					assertTrue(getText(DC.NOTICESFILENAME).equals(Fullfilename));
				}
				if (VerifyObject(driver, "//table/tbody/tr[2]/td[text()='" + Fullfilename + "']")) {
					assertTrue(driver.findElement(By.xpath("//table/tbody/tr[2]/td[text()='" + Fullfilename + "']"))
							.getText().equalsIgnoreCase(File_Name_Extn));
				}
				// Verifying Jurisdiction Name
				if (VerifyObject(driver, "///table/tbody/tr[2]/td[text()='" + Fullfilename + "']/../td[2]")) {
					assertTrue(driver
							.findElement(By.xpath("//table/tbody/tr[2]/td[text()='" + Fullfilename + "']/../td[2]"))
							.getText().equalsIgnoreCase(Jurisdiction));
				}
				// Verifying FilingPeriod
				if (VerifyObject(driver, "//table/tbody/tr[2]/td[text()='" + Fullfilename + "']/../td[3]")) {
					assertTrue(driver
							.findElement(By.xpath("//table/tbody/tr[2]/td[text()='" + Fullfilename + "']/../td[3]"))
							.getText().equalsIgnoreCase(FilingPeriod));
				}
				// Verifying Duedate
				if (VerifyObject(driver, "//table/tbody/tr[2]/td[text()='" + Fullfilename + "']/../td[4]")) {
					assertTrue(driver
							.findElement(By.xpath("//table/tbody/tr[2]/td[text()='" + Fullfilename + "']/../td[4]"))
							.getText().equalsIgnoreCase(End_Date));
				}
			}
			break;
		case "Client":
			if (Parent_File.equalsIgnoreCase("Yes")) {
				selectByValue(DC.PARENTFILESELECT, File_Name_Extn);
				Thread.sleep(1000);
			}
			click(DC.CLIENTUPLOAD);
			Thread.sleep(2000);
			if (VerifyObject(driver, DC.UPLOADERROR)) {
				driver.navigate().refresh();
			}
			// Verifying File name related to Client files upload
			assertTrue(getText(DC.CLIENTFILENAME).equals(Fullfilename));

			// Verifying Download link related to client files upload with respect to file
			// name
			if (VerifyObject(driver, "//div[4]/div[1]/div/table/tbody/tr[@class='ng-scope']/td[text()='" + Fullfilename
					+ "']/../td/a[text()='Download']")) {
				if (Download_Click.equalsIgnoreCase("Yes")) {
					driver.findElement(By.xpath("//div[4]/div[1]/div/table/tbody/tr[@class='ng-scope']/td[text()='"
							+ Fullfilename + "']/../td/a[text()='Download']")).click();
					if (Browser.equalsIgnoreCase("Chrome")) {
						Thread.sleep(5000);
						File directory = new File(".//");
						String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
								+ "DocumentCenter";
						System.out.println("Path" + path);
						MoveLatestDownloadFileFromOnedirtoAnother(path);
						Thread.sleep(10000);
						LatestDownloadedFile("DocumentCenter", true);

					}
					if (Browser.equalsIgnoreCase("Firefox")) {
						Thread.sleep(5000);
						LatestDownloadedFile("DocumentCenter", false);
					}
				}
			} else {
			}
			Thread.sleep(5000);
			// Verifying Date time related to client files upload with respect to file name
			assertTrue(driver.findElement(By.xpath(
					"//div[4]/div[1]/div/table/tbody/tr[@class='ng-scope']/td[text()='" + Fullfilename + "']/../td[2]"))
					.getText().endsWith(date));
			break;
		}
	}

	public static String fileUploadDialog(WebDriver driver, String browser, String AutoITpath,
			String AdjustmentFilePath, String Filename, String fileformat) throws Exception {
		String status = "";
		try {
			switch (browser) {
			case "IE":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadIE.exe " + Filename);
				} else {
					Runtime.getRuntime().exec(
							AutoITpath + "\\FileUploadIE.exe " + AdjustmentFilePath + "\\" + Filename + fileformat);
				}
				break;
			case "Firefox":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadFirefox.exe " + Filename);
				} else {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadFirefox.exe " + AdjustmentFilePath + "\\"
							+ Filename + fileformat);
				}
				break;
			case "Chrome":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadChrome.exe " + Filename);
				} else {
					Runtime.getRuntime().exec(
							AutoITpath + "\\FileUploadChrome.exe " + AdjustmentFilePath + "\\" + Filename + fileformat);
				}
				break;
			case "Safari":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadSafari.exe " + Filename);
				} else {
					Runtime.getRuntime().exec(
							AutoITpath + "\\FileUploadSafari.exe " + AdjustmentFilePath + "\\" + Filename + fileformat);
				}
				break;
			}
		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;
	}

	public void AdminDocumentcenter(String ClientName, String File_Name_Extn, String File_Type, String Period_Month,
			String Upload, String UploadError_Verify, String Parent_File, String Download_Click, String DeleteDocument)
			throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		String AutoITpath = xAutoIT();
		xGetDTAP();
		String AdjustmentFilePath = xUploadAdjustmentFile("DocumentCenter");
		xDownloadFiles("DocumentCenter");
		Documentcenter DC = PageFactory.initElements(driver, Documentcenter.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		Transactions transactions = PageFactory.initElements(driver, Transactions.class);
		JavaScriptclick(driver, DC.DOCUMENTCENTER);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, DC.CLIENTSELECT, driver);
		boolean Error = false;
		selectByValue(DC.CLIENTSELECT, ClientName);
		Thread.sleep(2000);
		selectByValue(DC.FILETYPESELECT, File_Type);
		// Values changes depends upon selection
		Thread.sleep(2000);
		if (Period_Month != "") {
			selectByValue(DC.PERIODSELECT, Period_Month);
			Thread.sleep(1000);
		}

		if ((Upload.equalsIgnoreCase("Admin")) || (Upload.equalsIgnoreCase("ClientNotices"))
				|| (Upload.equalsIgnoreCase("ClientReturns"))) {
			if ((Browser.equalsIgnoreCase("Chrome"))) {
				click(DC.ADMINBROWSE);
			} else {
				JavaScriptclick(driver, DC.ADMINBROWSE);
			}
		}
		// verifying no browse button for client upload
		if (!VerifyObject(driver, DC.CLIENTBROWSE)) {
			assertTrue(true);
		}

		transactions.AutoItBrowser(driver, Browser, AutoITpath, AdjustmentFilePath, File_Name_Extn, "");
		// Autoit wait
		Thread.sleep(3000);
		switch (Upload) {
		case "Admin":
		case "ClientNotices":
		case "ClientReturns":
			JavaScriptclick(driver, DC.ADMINUPLOAD);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, DC.UPLOADOK, driver);
			assertTrue(driver.findElement(By.xpath(
					"//p[text()='Please confirm upload of \"" + File_Name_Extn + "\" for client " + ClientName + "']"))
					.isDisplayed());
			click(DC.UPLOADOK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);

			if (VerifyObject(driver, DC.UPLOADERROR)) {
				waitforElement(180, DC.UPLOADERROR, driver);
				if (!UploadError_Verify.equalsIgnoreCase("Yes")) {
					driver.navigate().refresh();
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(180, DC.FILETYPESELECT, driver);
					Error = true;
				} else {
					driver.navigate().refresh();
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(180, DC.FILETYPESELECT, driver);
					Error = true;
				}
			}
			if (!Error == true) {
				waitforElement(180, DC.FILEUPLOADSUCCESS, driver);
				selectByValue(DC.PAGINATIOS, "15 per page");
				waitforElement(180,
						"//div[3]/div[1]/div/table/tbody/tr[@class='ng-scope']/td[text()='" + File_Name_Extn + "']",
						driver);
				// Verifying File name related to Admin files upload
				if (VerifyObject(driver, DC.ADMINFILENAME)) {
					assertTrue(getText(DC.ADMINFILENAME).equals(File_Name_Extn));
				}
				if (VerifyObject(driver, "//div[3]/div[1]/div/table/tbody/tr[@class='ng-scope']/td[text()='"
						+ File_Name_Extn + "']/../td/a[text()='Download']")) {
					if (Download_Click.equalsIgnoreCase("Yes")) {
						if ((Browser.equalsIgnoreCase("Chrome"))) {
							driver.findElement(
									By.xpath("//div[3]/div[1]/div/table/tbody/tr[@class='ng-scope']/td[text()='"
											+ File_Name_Extn + "']/../td/a[text()='Download']"))
									.click();
						} else {
							WebElement Download = driver.findElement(
									By.xpath("//div[3]/div[1]/div/table/tbody/tr[@class='ng-scope']/td[text()='"
											+ File_Name_Extn + "']/../td/a[text()='Download']"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", Download);
							// sometimes file taking longer time to download
							Thread.sleep(2000);
						}
						if (Browser.equalsIgnoreCase("Chrome")) {
							File directory = new File(".//");
							String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual";
							MoveLatestDownloadFileFromOnedirtoAnother(path);
							Thread.sleep(10000);
							DownloadFiles.LatestDownloadedFile("DocumentCenter", true);
						}
						if (Browser.equalsIgnoreCase("Firefox")) {
							LatestDownloadedFile("DocumentCenter", false);
						}
					}
				} else {
					assertTrue(!(driver
							.findElement(By.xpath("//div[3]/div[1]/div/table/tbody/tr[@class='ng-scope']/td[text()='"
									+ File_Name_Extn + "']/../td/a[text()='Download']"))).isDisplayed());
				}
			}
			break;
		case "ClientName":
			if (Parent_File.equalsIgnoreCase("Yes")) {
				selectByValue(DC.PARENTFILESELECT, File_Name_Extn);
				Thread.sleep(1000);
			}
			JavaScriptclick(driver, DC.CLIENTUPLOAD);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			if (VerifyObject(driver, DC.UPLOADERROR)) {
				waitforElement(180, DC.UPLOADERROR, driver);
				driver.navigate().refresh();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, DC.FILETYPESELECT, driver);
			}
			waitforElement(180,
					"//div[4]/div[1]/div/table/tbody/tr[@class='ng-scope']/td[text()='" + File_Name_Extn + "']",
					driver);

			// Verifying File name related to Client files upload
			assertTrue(getText(DC.CLIENTFILENAME).equals(File_Name_Extn));
			// Verifying Download link related to client files upload with respect to file
			// name
			Thread.sleep(2000);
			if (VerifyObject(driver, "//div[4]/div[1]/div/table/tbody/tr[@class='ng-scope']/td[text()='"
					+ File_Name_Extn + "']/../td/a[text()='Download']")) {
				if (Download_Click.equalsIgnoreCase("Yes")) {
					driver.findElement(By.xpath("//div[4]/div[1]/div/table/tbody/tr[@class='ng-scope']/td[text()='"
							+ File_Name_Extn + "']/../td/a[text()='Download']")).click();
					// file download
					Thread.sleep(5000);
					if (Browser.equalsIgnoreCase("Chrome")) {
						File directory = new File(".//");
						String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual";
						MoveLatestDownloadFileFromOnedirtoAnother(path);
						Thread.sleep(10000);
						DownloadFiles.LatestDownloadedFile("DocumentCenter", true);
					}
					if (Browser.equalsIgnoreCase("Firefox")) {
						LatestDownloadedFile("DocumentCenter", false);
					}
				}
			} else {
				assertTrue(!(driver
						.findElement(By.xpath("//div[4]/div[1]/div/table/tbody/tr[@class='ng-scope']/td[text()='"
								+ File_Name_Extn + "']/../td/a[text()='Download']"))).isDisplayed());

			}
			// Verifying Date time related to client files upload with respect to file name
			break;
		}
		// Delete Documents
		if (DeleteDocument.equalsIgnoreCase("YES"))

		{
			String file_type = File_Type;
			switch (file_type) {
			case "ONBOARDING":
				driver.findElement(By.xpath(
						"//table/tbody/tr/td[contains(.,'" + File_Name_Extn + "')]/../td/a[contains(.,'Delete')]"))
						.click();
				waitforElement(180, DC.DELETE, driver);
				JavaScriptclick(driver, DC.DELETE);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				break;
			case "Monthly Files":
				waitforElement(180, DC.PAGINATIOS, driver);
				selectByValue(DC.PAGINATIOS, "15 per page");
				Thread.sleep(2000);
				driver.findElement(By.xpath(
						"//table/tbody/tr/td[contains(.,'" + File_Name_Extn + "')]/../td/a[contains(.,'Delete')]"))
						.click();
				waitforElement(180, DC.DELETE, driver);
				JavaScriptclick(driver, DC.DELETE);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				break;
			case "POA":
				driver.findElement(By.xpath(
						"//table/tbody/tr/td[contains(.,'" + File_Name_Extn + "')]/../td/a[contains(.,'Delete')]"))
						.click();
				waitforElement(180, DC.DELETE, driver);
				JavaScriptclick(driver, DC.DELETE);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				break;
			}
		}
	}

}
