package common.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class QBOCustomersTaxSearch {

	@FindBy(xpath = "//span[contains(text(),'Transactions')]")

	public WebElement transactions;

	@FindBy(xpath = "//span[contains(text(),'Sales')]")

	public WebElement sales;

	@FindBy(xpath = "//span[contains(text(),'Sales Transactions')]")

	public WebElement salesTransaction;

	@FindBy(xpath = "//button[@aria-label='Search Transactions']")

	public WebElement searchTransactionslnk;

	@FindBy(xpath = "//h3[text()='Search Transactions']/../div/div/input")

	public WebElement inputtransactions;

	@FindBy(xpath = "//button[@title='Click to go to the transaction']")

	public WebElement searchTransactionsbtn;

	@FindBy(xpath = "//div[15]/div/div[1]/span[2]")

	public WebElement cancel;

	@FindBy(xpath = "//div[1]/div[2]/div[@class='amount']")

	public WebElement balanceDue;

	@FindBy(className = "tableCell hi hi-close")

	public WebElement close;

	@FindBy(xpath = "//span[contains(text(),'Customers')]")

	public WebElement customers;

	@FindBy(id = "uniqName_55_6")

	public WebElement findCustomer;

	@FindBy(xpath = "//button[contains(text(),'Edit')]")

	public WebElement edit;

	@FindBy(name = "shippingAddressSameAsBilling")

	public WebElement shippingAddress;

	@FindBy(xpath = "//button[contains(text(),'Save')]")

	public WebElement save;

	@FindBys({

			@FindBy(xpath = "//div/div[2]/div[3]/div[2]/div[4]/div/div") })
	public List<WebElement> SearchInvoicemultiplerecords;

	@FindBy(xpath = "//a[contains(.,'Customers')]")

	public WebElement Customerlink;
	@FindBy(xpath = "//button[contains(.,'New customer')]")

	public WebElement NewCustomerbutton;
	@FindBy(xpath = "//input[@aria-label='Find a customer or company']")

	public WebElement CustomerSearch;
	@FindBy(xpath = "//button/span[contains(.,'New transaction')]")

	public WebElement NewTrasaction;
	@FindBy(xpath = "//td[contains(.,'Invoice') and @role='presentation']")

	public WebElement Invoice;
	@FindBy(xpath = "//div/div[contains(.,'Invoice') and @class='pageTitle inlineBlock']")

	public WebElement InvoiceText;
	@FindBy(xpath = "//input[@data-qbo-id='quantityField']")

	public WebElement Quantity;
	@FindBy(xpath = "//input[@data-automation-id='amountField']")

	public WebElement Amount;
	@FindBy(xpath = "//input[@data-dojo-attach-point='_taxField']/../input[@type='checkbox']")

	public WebElement Tax;
	@FindBy(xpath = "//div[1]/button[text()='Save' and @class='dark button secondary']")

	public WebElement SaveButton;
	@FindBy(xpath = "//div/a[@class='divContentStatus txnStatus']")

	public WebElement InvoiceSuccess;
	@FindBy(xpath = "//div[@class='pageSubTitle inlineBlock']/div[2]")

	public WebElement InvoiceNo;
	@FindBy(xpath = "//i[@class='tableCell hi hi-close']")

	public WebElement Close;
	@FindBy(xpath = "//td[@class='dgrid-cell dgrid-cell-padding dgrid-column-4 clickable field-quantity']")

	public WebElement QuantityField;
	@FindBy(xpath = "//a[@class='displayName detailLink']")

	public WebElement Custname;
	@FindBy(xpath = "//div[@data-dojo-attach-point='_itemOverlayCell']/div/input[1]")

	public WebElement ProductType;
	@FindBy(xpath = "//textarea[@class='customerMessage']")

	public WebElement CustomerMessage;
	@FindBy(xpath = "//div[3]/div[2]/div/div/div[@class='dropDownImage']")

	public WebElement SearchICon;
	@FindBy(xpath = "//a/span[contains(text(),'Sales')]")

	public WebElement saleslink;
	@FindBy(xpath = "//div[@class='contentBtn flex-fixed']/button[contains(.,'Edit')]")

	public WebElement Custedit;
	@FindBy(xpath = "//input[@name='shippingAddressSameAsBilling']")

	public WebElement sameshipping;
	@FindBy(xpath = "//button[contains(.,'Save')]")

	public WebElement savecust;
	@FindBy(xpath = "//button[@aria-label='Create']")

	public WebElement create;
	@FindBy(xpath = "//div/h4[contains(.,'Customers')]/../../div[2]/ul/li/a[contains(.,'Invoice')]")

	public WebElement invoicecreate;
	@FindBy(xpath = "//li[contains(.,'Customer Details')]")

	public WebElement Custdetails;

	@FindBy(xpath = "//div[contains(text(),'Where should QuickBooks Payments record your deposits')]/../../../div/span[@class='close-sprite dijitDialogCloseIcon hi hi-close']")

	public WebElement QBORECORDPOPClose;

	@FindBy(xpath = "//i[@class='tableCell hi hi-close']")

	public WebElement QBOTransactionClose;
	
	@FindBy(xpath="//input[@id='ius-userid']")
	public WebElement QBOUSERNAME;
	
	@FindBy(xpath="//input[@id='ius-password']")
	public WebElement QBOPASSWORD;
	
	@FindBy(xpath="//button[@name='SignIn']")
	public WebElement QBOLOGIN;

}
