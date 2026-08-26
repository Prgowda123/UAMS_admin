package ksp_admin;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import utiles.Utilies;
public class Army_force extends Utilies {

	WebDriver driver;
	WebDriverWait wait;
	Robot robot;
	Actions actions;
	JavascriptExecutor jss = (JavascriptExecutor) driver;

	@FindBy(id = "ArmyForce_Code")
	WebElement codeField;

	@FindBy(id = "ArmyForce_Title")
	WebElement titleField;

	@FindBy(id = "ArmyForce_OrderIndex")
	WebElement orderIndexDropdown;

	@FindBy(id = "ArmyForce_StatusCode")
	WebElement statusDropdown;

	@FindBy(xpath = "//a[contains(@class, 'rounded-5') and contains(@class, 'btn-secondary')]")
	WebElement cancelButton;

	@FindBy(id = "search-bar")
	WebElement searchBar;

	@FindBy(xpath = "//tbody[2]//span[contains(text(),'visibility')]")
	WebElement viewButton;

	@FindBy(xpath = "//tbody[2]//span[contains(text(),'edit')]")
	WebElement editButton;

	@FindBy(linkText = "arrow_back")
	WebElement backButton;

	@FindBy(id = "ArmyForceDTO_Code")
	WebElement Editcode;

	@FindBy(id = "ArmyForceDTO_Title")
	WebElement Edittitle;

	@FindBy(id = "ArmyForceDTO_OrderIndex")
	WebElement Editorderindex;

	@FindBy(id = "ArmyForceDTO_StatusCode")
	WebElement Editstatus;

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	WebElement save;

	@FindBy(xpath = "//tbody[2]//button//span[contains(text(),'delete')]")
	WebElement delete;

	@FindBy(xpath = "//a[contains(@class, 'rounded-5') and contains(@class, 'btn-secondary')]")
	WebElement CancelEdit;

	@FindBy(xpath = "//button[contains(text(),'OK') and @class='swal2-confirm swal2-styled']")
	WebElement ok;

	@FindBy(xpath = "//span[contains(text(),'close')]")
	WebElement clear;

	@FindBy(xpath = "(//tbody[2]//input[@type='checkbox'])[1]")
	WebElement checkbox1;

	@FindBy(xpath = "//input[@type='checkbox' and @data-table='armyForces']")
	WebElement checkboxmulti;

	@FindBy(id = "statusSelect")
	WebElement ChangeStatus;

	@FindBy(xpath = "//button[contains(text(),'Apply') and @id='submitStatusBtn']")
	WebElement Apply;

	@FindBy(xpath = "//a[contains(text(),'Add')]")
	WebElement add;

	@FindBy(xpath = "//div[@id='swal2-html-container']")
	WebElement popup_message;

	@FindBy(xpath = "//button[text()='Yes, delete it!']")
	WebElement ConfDelete;

	@FindBy(xpath = "//button[text()='Cancel']")
	WebElement deletecancel;

	@FindBy(id = "itemsPerPage")
	WebElement entriesperpages;

	@FindBy(xpath = "//th[2]//span[@data-order='desc']")
	WebElement codedesc;

	@FindBy(xpath = "//th[2]//span[@data-order='asc']")
	WebElement codeasc;

	@FindBy(xpath = "//th[3]//span[@data-order='desc']")
	WebElement titledesc;

	@FindBy(xpath = "//th[3]//span[@data-order='asc']")
	WebElement titleasc;

	@FindBy(xpath = "//th[4]//span[@data-order='desc']")
	WebElement orderindexdesc;

	@FindBy(xpath = "//th[4]//span[@data-order='asc']")
	WebElement orderindexasc;

	@FindBy(xpath = "//th[5]//span[@data-order='desc']")
	WebElement statusdesc;

	@FindBy(xpath = "//th[5]//span[@data-order='asc']")
	WebElement statusasc;

	@FindBy(xpath = "//ul[@id='pagination']//li[1]")
	WebElement paginationfirst;

	@FindBy(xpath = "//ul[@id='pagination']//li[last()]")
	WebElement paginationlast;

	@FindBy(xpath = "//div[@id='entriesInfo']")
	WebElement showingpage;
	
	@FindBy(xpath = "//div[@id='swal2-html-container']")
	WebElement success;

	public Army_force(WebDriver driver) throws AWTException {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		this.robot = new Robot();
		this.actions = new Actions(driver);
		this.jss = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);

	}

	public void openMasters() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Masters')]"))).click();
		sleep(1000);
	}

	public void openArmy_Force() {
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Army Forces"))).click();
		sleep(1000);
	}


	public void switchWindow() {
		switchToNewWindow(driver);
		sleep(1000);
	}

	public void clickAdd() {
		try {
			WebElement add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Add')]")));
			wait.until(ExpectedConditions.elementToBeClickable(add));
			sleep(200);
			actions.moveToElement(add).click().perform();
		} catch (StaleElementReferenceException e) {
			driver.findElement(By.xpath("//a[contains(text(),'Add')]"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void evenCheckbox() {
		List<WebElement> Checkbox = driver
				.findElements(By.xpath("((//tbody[2]//input[@type='checkbox']))[position() mod 2=1]"));
		wait.until(ExpectedConditions.visibilityOfAllElements(Checkbox));

		for (WebElement check : Checkbox) {
			try {
				if (!check.isSelected()) {
					jss.executeScript("arguments[0].scrollIntoView(true);", check);
					jss.executeScript("arguments[0].click();", check);
				}
			} catch (Exception e) {
				System.out.println("Could not click checkbox: " + e.getMessage());
			}
		}

	}

	public void close() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='clearSearch']"))).click();
	}

	public void processall() throws IOException {
		FileInputStream fis = null;
		FileOutputStream fileOut = null;
		try {
			FileInputStream fileinput = new FileInputStream("D://KSP_Admin//army_v1.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fileinput);
	
			Sheet sheet = workbook.getSheetAt(0);
			Sheet sheet2 = workbook.getSheetAt(1);
			Sheet sheet3 = workbook.getSheetAt(2);
			Sheet sheet4 = workbook.getSheetAt(3);
			Sheet sheet5 = workbook.getSheetAt(4);
			Sheet sheet6 = workbook.getSheetAt(5);
			// Row row1 = sheet2.createRow(sheet2.getPhysicalNumberOfRows());
			int lastRow = sheet.getLastRowNum();
			for (int i = 1; i <= lastRow; i++) {
				Row row = sheet.getRow(i);
				if (row == null)
					continue;

			Addpage1(row, i, sheet5);// ✅ row4 will be created inside
			}

			int Editlastrow = sheet2.getLastRowNum();
			for (int i = 76; i <= Editlastrow; i++) {

				Row row1 = sheet2.getRow(i);
				if (row1 == null) {
					continue;
				}
			 EditPage(row1, i, sheet6);
			}

			int Viewlastrow = sheet3.getLastRowNum();
			for (int i = 1; i <= Viewlastrow; i++) {
				Row row2 = sheet3.getRow(i);
				if (row2 == null) {
					continue;
				}
         	Viewpage1(row2);
			}
			int Delete = sheet4.getLastRowNum();
			for (int i = 1; i <= Delete; i++) {
				Row row3 = sheet4.getRow(i);
				if (row3 == null) {
					continue;
				}

	    	Delete(row3, i);
			}
			FileOutputStream fileout = new FileOutputStream("D://KSP_Admin//army_v1.xlsx");
			workbook.write(fileout);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (fileOut != null) {
					fileOut.close();
				}
				if (fis != null) {
					fis.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	
	public void Addpage1(Row row, int i, Sheet sheet5) {

		String code = getCellValue(row.getCell(3));
		String title = getCellValue(row.getCell(4));
		String orderindex = getCellValue(row.getCell(5));
		String status = getCellValue(row.getCell(6));
		if (i == 1) {
			
			try {
			    new Select(entriesperpages).selectByVisibleText("50");
			    Thread.sleep(100); // Wait for dropdown action to reflect
			} catch (InterruptedException e) {
			    e.printStackTrace();
			}
		}
		driver.navigate().refresh();

		clickAdd();
		if (isElementClickable(driver, add)) {
			clickonElement(getFreshElement(add));
		}
		driver.navigate().refresh();

		if (i != 24) {
			 sleep(100);
			 
			safeClearAndType(By.id("ArmyForce_Code"), code);
			sleep(150);
			safeClearAndType(By.id("ArmyForce_Title"), title);
			 sleep(100);
			selectDropdownByValue(By.id("ArmyForce_StatusCode"), status);

			if (!status.equalsIgnoreCase("I") && isElementClickable(driver, orderIndexDropdown)
					&& orderIndexDropdown.isEnabled()) {
				// sleep(100);
				Select dropdown = new Select(orderIndexDropdown);
				String value = orderindex.trim();

				try {
					if (value.equalsIgnoreCase("Last")) {
						int lastIndex = dropdown.getOptions().size() - 1;
						dropdown.selectByIndex(lastIndex);
					} else if (value.equalsIgnoreCase("Slast")) {
						int secondLastIndex = dropdown.getOptions().size() - 2;
						dropdown.selectByIndex(secondLastIndex);
					} else {
						dropdown.selectByVisibleText(value);
					}
				} catch (NoSuchElementException | ElementNotInteractableException e) {
					System.out.println("Dropdown selection failed for value: " + value);
				}
			}

			String codeText = getAttributeValue(By.id("ArmyForce_Code"));
			String Titletext = getAttributeValue(By.id("ArmyForce_Title"));
			String statustext = getAttributeValue(By.id("ArmyForce_StatusCode"));
			String ODtext = getAttributeValue(By.id("ArmyForce_OrderIndex"));

			row.createCell(9).setCellValue(codeText);
			row.createCell(11).setCellValue(Titletext);
			row.createCell(15).setCellValue(statustext);
			row.createCell(13).setCellValue(ODtext);

		}

		clickonElement(save);
		if (isElementClickable(driver, save)) {
			clickonElement(save);
		}

		System.out.println(i + " Iteration");
		if (isElementClickable(driver, ok)) {
			try {
				String PopUp = success.getText();
				sleep(500);
				row.createCell(7).setCellValue(PopUp);
				clickonElement(ok);
				sleep(100);
				cancelButton.click();
				if (isElementClickable(driver, cancelButton)) {
					clickonElement(cancelButton);
				}
				switchToNewWindow(driver);
			} catch (Exception r) {
				// Optionally log or handle
			}
		}
		if (isElementClickable(driver, searchBar)) {
			searchBar.sendKeys(title);
			sleep(500);

			//Get the updated data after success
			
			if (isElementClickable(driver, editButton)) {
				updateddata(row);
			}
			
			clear.click();
			if (isElementClickable(driver, clear)) {
				close();
			}

		}
		//write Table after success
		writeTableDataToExcel(sheet5, i);

		// if error messages present
		if (!isElementClickable(driver, entriesperpages)) {

			// capture the error store in excel in json format

			captureFormErrorsAsJson(driver, row, 8, robot, entriesperpages);

			cancelButton.click();
			if(isElementClickable(driver, cancelButton)){
				clickonElement(cancelButton);
			}
			if (isElementClickable(driver, searchBar)) {
				searchBar.sendKeys(title);
				sleep(100);
           //get the updated data in table
				if (isElementClickable(driver, editButton)) {
					updateddata(row);
				}
				
				clickonElement(clear);
				if (isElementClickable(driver, clear)) {
					close();
				}

			}
			//write the Table after error
			driver.navigate().refresh();

			writeTableDataToExcel(sheet5, i);
		}

		sleep(1000);
	}

    public void Viewpage(Row row1) {
		try {

			sleep(1000);
			if (isElementClickable(driver, viewButton)) {
				clickonElement(viewButton);

				driver.navigate().refresh();
				String getViewCode = getAttributeValue(By.id("code"));
				String getViewTitle = getAttributeValue(By.id("title"));
				String getOrderIndex = getAttributeValue(By.id("ArmyForce_OrderIndex"));
				String getStatus = getAttributeValue(By.xpath("//div//label[contains(text(),'Status')]/following-sibling::div//input"));

				clickonElement(backButton);

				row1.createCell(17).setCellValue(getViewCode);
				row1.createCell(18).setCellValue(getViewTitle);
				row1.createCell(19).setCellValue(getOrderIndex);
				row1.createCell(20).setCellValue(getStatus);
			} else {
				clickonElement(clear);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

    public void Viewpage1(Row row2) {
	try {
		String code = getCellValue(row2.getCell(1));
		clickonElement(searchBar);
		searchBar.sendKeys(code);

		sleep(1000);
		if (isElementClickable(driver, viewButton)) {
			clickonElement(viewButton);

			String getViewCode = getAttributeValue(By.id("code"));
			String getViewTitle = getAttributeValue(By.id("title"));
			String getOrderIndex = getAttributeValue(By.id("ArmyForce_OrderIndex"));
			String getStatus = getAttributeValue(By.xpath("//div//label[contains(text(),'Status')]/following-sibling::div//input"));

			clickonElement(backButton);

			row2.createCell(2).setCellValue(getViewCode);
			row2.createCell(3).setCellValue(getViewTitle);
			row2.createCell(4).setCellValue(getOrderIndex);
			row2.createCell(5).setCellValue(getStatus);
		} else {
			clickonElement(clear);
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
}

	public void EditPage(Row row1, int i,Sheet sheet6) {
		try {
			String title = getCellValue(row1.getCell(5));
			String codeEdit = getCellValue(row1.getCell(4));
			String titleEdit = getCellValue(row1.getCell(6));
			String orderindexEdit = getCellValue(row1.getCell(8));
			String statusEdit = getCellValue(row1.getCell(10));
			
			
		
			if (i == 1 || i == 26 || i == 47) {
				clickonElement(getFreshElement(searchBar));
				getFreshElement(searchBar).clear();
				getFreshElement(searchBar).sendKeys(title);
				try {
				    new Select(entriesperpages).selectByVisibleText("50");
				    Thread.sleep(100); // Wait for dropdown action to reflect
				} catch (InterruptedException e) {
				    e.printStackTrace();
				}
			}

			
			if (isElementClickable(driver, editButton)) {
				sleep(100);
				ClickOnElement(By.xpath("//tbody[2]//span[contains(text(),'edit')]"));
				switchToNewWindow(driver);

				driver.navigate().refresh();
				sleep(100);
				getFreshElement(codeField).clear();
				if (i != 47) {
					getFreshElement(codeField).sendKeys(codeEdit);
				}

				sleep(200);
				getFreshElement(titleField).clear();
				if (i != 47) {
					getFreshElement(titleField).sendKeys(titleEdit);
				}

				sleep(500);
				selectDropdownByValue(By.id("ArmyForce_StatusCode"), statusEdit);

				JavascriptExecutor js = (JavascriptExecutor) driver;
				boolean isEnabled = (Boolean) js.executeScript("return arguments[0].disabled === false;",
						orderIndexDropdown);

				if (!statusEdit.equalsIgnoreCase("I") && !statusEdit.equalsIgnoreCase("D")
						&& isElementClickable(driver, orderIndexDropdown) && isEnabled) {
					sleep(100);
					Select dropdown = new Select(getFreshElement(orderIndexDropdown));
					String value = orderindexEdit.trim();

					try {
						if (value.equalsIgnoreCase("Last")) {
							dropdown.selectByIndex(dropdown.getOptions().size() - 1);
						} else if (value.equalsIgnoreCase("Slast")) {
							dropdown.selectByIndex(dropdown.getOptions().size() - 2);
						} else {
							double doubleValue = Double.parseDouble(value);
							int intValue = (int) doubleValue;
							dropdown.selectByVisibleText(String.valueOf(intValue));
						}
					} catch (NumberFormatException e) {
						System.out.println("Invalid index value: " + value);
					} catch (Exception e) {
						System.out.println("Error selecting dropdown: " + e.getMessage());
					}
				}

				System.out.println(i + " Iteration");

				// for edit details fetching
				String codeText = getAttributeValue(By.id("ArmyForce_Code"));
				String Titletext = getAttributeValue(By.id("ArmyForce_Title"));
				String statustext = getAttributeValue(By.id("ArmyForce_StatusCode"));
				String ODtext = getAttributeValue(By.id("ArmyForce_OrderIndex"));

				row1.createCell(13).setCellValue(codeText);
				row1.createCell(14).setCellValue(Titletext);
				row1.createCell(15).setCellValue(statustext);
				row1.createCell(16).setCellValue(ODtext);

				

				if (isElementClickable(driver, save)) {
					clickonElement(save);

				}
				try {
					WebElement popup = new WebDriverWait(driver, Duration.ofSeconds(3))
							.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-html-container")));
					String PopUp = popup.getText();
					Reporter.log(i + " Pass");
					row1.createCell(11).setCellValue(PopUp);

					clickonElement(ok);
					if (isElementClickable(driver, ok)) {
						WebElement okBtn = wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//button[@type='button' and text()='OK']")));
						getFreshElement(okBtn).click();

					}
					
					
					if (isElementClickable(driver, searchBar)) {
						clickonElement(getFreshElement(searchBar));
						actions.moveToElement(getFreshElement(searchBar)).sendKeys(titleEdit).perform();
						Viewpage(row1);
						writeTableDataToExcel(sheet6, i);
						driver.navigate().refresh();
						clickonElement(getFreshElement(searchBar));
						actions.moveToElement(getFreshElement(searchBar)).sendKeys(titleEdit).perform();
					}

				} catch (TimeoutException e) {
					row1.createCell(11).setCellValue("Error message");
				}

				if (isElementClickable(driver, CancelEdit)) {
					captureFormErrorsAsJson(driver, row1, 12, robot, entriesperpages);
					//getFreshElement(cancelButton).click();
					cancelButton.click();
					if (isElementClickable(driver, cancelButton)) {
						ClickOnElement(By.xpath("//a[contains(@class, 'rounded-5') and contains(@class, 'btn-secondary')]"));

					}
					driver.navigate().refresh();
					clickonElement(getFreshElement(searchBar));
					if (isElementClickable(driver, searchBar)) {
						searchBar.click();

					}
					actions.moveToElement(getFreshElement(searchBar)).sendKeys(titleEdit).perform();
					Viewpage(row1);
					//if error present
					writeTableDataToExcel(sheet6, i);
					sleep(100);
					driver.navigate().refresh();
					
					searchBar.click();
					actions.moveToElement(getFreshElement(searchBar)).sendKeys(title).perform();

				}

			} else {
				System.out.println("Edit button not found, retrying search");
			    ClickOnElement(By.xpath("//a[contains(@class, 'rounded-5') and contains(@class, 'btn-secondary')]"));
				if (isElementClickable(driver, cancelButton)) {
					getFreshElement(cancelButton).click();
				}
				clickonElement(getFreshElement(clear));
				if (isElementClickable(driver, clear)) {
					close();
				}
				clickonElement(getFreshElement(searchBar));
				driver.navigate().refresh();
				
//				actions.moveToElement(getFreshElement(searchBar)).sendKeys(titleEdit).perform();
//				Viewpage(row1);
				writeTableDataToExcel(sheet6, i);
				driver.navigate().refresh();
				searchBar.click();
				actions.moveToElement(getFreshElement(searchBar)).sendKeys(title).perform();

			}
		} catch (Exception e) {
			System.out.println("Error during edit page execution: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void Delete(Row row3, int i) {
		String codeEdit = getCellValue(row3.getCell(1));
		String statusEdit = getCellValue(row3.getCell(3));
		String entriesperpage = getCellValue(row3.getCell(2));
		String checkboxPositions = getCellValue(row3.getCell(4));
		try {
			clickonElement(searchBar);
			actions.moveToElement(searchBar).sendKeys(codeEdit).perform();
			sleep(200);
			System.out.println(i + " Iteration");
			try {
				if (isElementClickable(driver, delete)) {
					clickonElement(delete);
					sleep(500);
					if(i%2==1)
					{
						clickonElement(deletecancel);
						actions.moveToElement(searchBar).click().perform();
						clear.click();
					}
					
					String PopUpdelete = popup_message.getText();
					row3.createCell(5).setCellValue(PopUpdelete);
					clickonElement(ConfDelete);

					
					sleep(1500);
					String PopUpdeleteok = success.getText();
					row3.createCell(6).setCellValue(PopUpdeleteok);
					clickonElement(ok);

				}

				{
					if (isElementClickable(driver, deletecancel)) {
						deletecancel.click();
					}
					actions.moveToElement(searchBar).click().perform();
					clear.click();

				}
			} catch (Exception f) {

			}
			new Select(entriesperpages).selectByValue(entriesperpage);
			sleep(100);
			// evenCheckbox();
			sleep(200);

			// Split the string by comma
			clickCheckboxesFromExcel(row3);
			Changestatus(statusEdit);
			driver.navigate().refresh();
			checkboxmulti.click();
			new Select(ChangeStatus).selectByValue(statusEdit);
			clickonElement(Apply);
			
			sleep(500);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='swal2-html-container']")));
			String PopUpcheck = success.getText();
			System.out.println("Popup message: " + PopUpcheck);
		//	String PopUpcheck = success.getText();
			sleep(500);
			clickonElement(ok);
			// System.out.println("delete : "+ PopUpcheck);
			row3.createCell(7).setCellValue(PopUpcheck);

			performClickWithPause(actions, codedesc);
			performClickWithPause(actions, codeasc);

			performClickWithPause(actions, titledesc);
			performClickWithPause(actions, titleasc);

			performClickWithPause(actions, orderindexdesc);
			performClickWithPause(actions, orderindexasc);

			performClickWithPause(actions, statusdesc);
			performClickWithPause(actions, Apply, 200);

			jss.executeScript("arguments[0].scrollIntoView(true);", showingpage);
			Thread.sleep(500);

			// Navigate pagination
			performClickWithPause(actions, paginationlast, 1000);
			clickCheckboxesFromExcel(row3);
			sleep(200);
			Changestatus(statusEdit);
			performClickWithPause(actions, paginationfirst, 1000);
			jss.executeScript("window.scrollBy(0,-4200)", "");
			// jss.executeScript("arguments[0].scrollIntoView(true);", add);
			sleep(1000);

		}

		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private String getAttributeValue(By locator) {
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator)).getAttribute("value");
	}

	public void clickonElement(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			sleep(200);
			actions.moveToElement(element).click().perform();
		} catch (StaleElementReferenceException e) {
			// System.out.println("Dropdown went stale, retrying...");
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			sleep(200);
			actions.moveToElement(element).click().perform();
		} catch (Exception e) {

		}
	}

	public void ClickOnElement(By locator) {
		try {
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			sleep(200);
			actions.moveToElement(element).click().perform();
		} catch (StaleElementReferenceException e) {
			// System.out.println("Element went stale, retrying...");
			WebElement element = wait
					.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)));
			actions.moveToElement(element).click().perform();
		} catch (Exception e) {
			System.out.println("Click failed: " + e.getMessage());
		}
	}

	public void Changestatus(String Cell) {
		jss.executeScript("arguments[0].scrollIntoView(true);", ChangeStatus);
		jss.executeScript("window.scrollBy(0, -500)");

		new Select(ChangeStatus).selectByValue(Cell);
		jss.executeScript("window.scrollBy(0, -500)");

		if (isElementClickable(driver, Apply)) {
			sleep(200);
			jss.executeScript("arguments[0].scrollIntoView(true);", Apply);
			jss.executeScript("arguments[0].click();", Apply);
		}

		clickonElement(ok);
	}

	public void selectDropdownByValue(By locator, String value) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			wait.until(ExpectedConditions.elementToBeClickable(dropdown));

			new Select(dropdown).selectByValue(value);
		} catch (StaleElementReferenceException e) {
			// System.out.println("Dropdown went stale, retrying...");
			WebElement dropdown = driver.findElement(locator);
			new Select(dropdown).selectByValue(value);
		} catch (Exception e) {

		}
	}

	public void selectDropdownByIndex(By locator, int index) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			wait.until(ExpectedConditions.elementToBeClickable(dropdown));

			new Select(dropdown).selectByIndex(index);
		} catch (StaleElementReferenceException e) {
			// System.out.println("Dropdown went stale, retrying...");
			WebElement dropdown = driver.findElement(locator);
			new Select(dropdown).selectByIndex(index);
		} catch (Exception e) {

		}
	}

	public void safeClearAndType(By locator, String text) {
		try {
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			element = wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
			element.click();
			element.sendKeys(text);
		} catch (StaleElementReferenceException e) {
			System.out.println("StaleElement caught, retrying...");

			// Retry once
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			element = wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
			element.click();
			element.sendKeys(text);
		} catch (Exception e) {
			System.out.println("Error in safeClearAndType: " + e.getMessage());
		}
	}

	public WebElement getFreshElement(WebElement element) 
	{
		return wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
	}

	public void fetchTableToExcel(WebDriver driver, WebDriverWait wait, Row row4) {
		try {

			// Fetch all rows in tbody[2]
			List<WebElement> rows = driver.findElements(By.xpath("//tbody[2]//tr"));

			int excelRowIndex = 1;
			for (WebElement rowElement : rows) {
				try {
					// Extract text from each column
					String slno = rowElement.findElement(By.xpath("//tbody[2]//tr[td//span[@class='slno-text']]/td[1]"))
							.getText();
					String code = rowElement.findElement(By.xpath("//tbody[2]//tr[td//span[@class='slno-text']]/td[2]"))
							.getText();
					String title = rowElement
							.findElement(By.xpath("//tbody[2]//tr[td//span[@class='slno-text']]/td[3]")).getText();
					String orderIdx = rowElement
							.findElement(By.xpath("//tbody[2]//tr[td//span[@class='slno-text']]/td[4]")).getText();
					String status = rowElement
							.findElement(By.xpath("//tbody[2]//tr[td//span[@class='slno-text']]/td[5]")).getText();

					row4.createCell(1).setCellValue(excelRowIndex);
					row4.createCell(1).setCellValue(slno);
					row4.createCell(2).setCellValue(code);
					row4.createCell(3).setCellValue(title);
					row4.createCell(4).setCellValue(orderIdx);
					row4.createCell(5).setCellValue(status);

					excelRowIndex++;

				} catch (Exception rowEx) {
					System.out.println("Skipping row " + excelRowIndex + ": " + rowEx.getMessage());
				}
			}

			System.out.println("Fetched " + (excelRowIndex - 1) + " rows successfully.");

		} catch (Exception e) {
			System.out.println("Failed to fetch table data: " + e.getMessage());
		}
	}

	public void writeTableDataToExcel(Sheet sheet, int i) {
		try {
			List<WebElement> tableRows = driver.findElements(By.xpath("//tbody[2]//tr"));
			int excelRowIndex = sheet.getLastRowNum() + 1;

			for (WebElement rowElement : tableRows) {
				try {
					String slno = rowElement.findElement(By.xpath(".//td[1]//*[contains(@class,'slno-text')]"))
							.getText();
					String code1 = rowElement.findElement(By.xpath(".//td[2]")).getText();
					String title1 = rowElement.findElement(By.xpath(".//td[3]")).getText();
					String orderIdx = rowElement.findElement(By.xpath(".//td[4]")).getText();
					String status1 = rowElement.findElement(By.xpath(".//td[5]")).getText();

					Row excelRow = sheet.createRow(excelRowIndex++);
					excelRow.createCell(0).setCellValue(i); // ✅ iteration column
					excelRow.createCell(1).setCellValue(slno);
					excelRow.createCell(2).setCellValue(code1);
					excelRow.createCell(3).setCellValue(title1);
					excelRow.createCell(4).setCellValue(orderIdx);
					excelRow.createCell(5).setCellValue(status1);

				} catch (Exception e) {
					System.out.println("❌ Error writing table row: " + e.getMessage());
				}
			}

			System.out.println("✅ Table data written for iteration: " + i);

		} catch (Exception e) {
			System.out.println("❌ Error fetching table: " + e.getMessage());
		}
	}

	public void captureFormErrorsAsJson(WebDriver driver, Row row, int cellIndex, Robot robot, WebElement entriesperpages) {
	    try {
	        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofMillis(2000));

	        String[] errorIds = {
	        		"ArmyForce_Code-error", "ArmyForce_Title-error", "ArmyForce_OrderIndex-error",
					"ArmyForce_StatusCode-error" 
			    };

			    // ✅ Map for clean keys
			    Map<String, String> cleanFieldNames = new HashMap<>();
			    cleanFieldNames.put("ArmyForce_Code-error", "Code");
			    cleanFieldNames.put("ArmyForce_Title-error", "Title");
			    cleanFieldNames.put("ArmyForce_OrderIndex-error", "OrderIndex");
			    cleanFieldNames.put("ArmyForce_StatusCode-error", "Status");

	        Map<String, String> errorJson = new LinkedHashMap<>();

	        for (String errorId : errorIds) {
	            try {
	                WebElement errorMessage = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(errorId)));
	                if (errorMessage != null && !errorMessage.getText().isEmpty()) {
	                    String errorText = errorMessage.getText();
	                    String cleanKey = cleanFieldNames.getOrDefault(errorId, errorId);
	                    errorJson.put(cleanKey, errorText);
	                }
	            } catch (TimeoutException e) {
	                // No error found for this field
	            }
	        }

	        // Convert to pretty JSON
	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        String prettyJson = gson.toJson(errorJson);

	        // Write to Excel cell
	        row.createCell(cellIndex).setCellValue(prettyJson);

	        // Handle non-clickable dropdown
	        if (!isElementClickable(driver, entriesperpages)) {
	            robot.keyPress(KeyEvent.VK_ENTER);
	            robot.keyRelease(KeyEvent.VK_ENTER);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public void updateddata(Row row) {
		WebElement orderindexofadd = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//tbody[2]//tr[td//span[@class='slno-text' and text()='1']]/td[4]")));
		String od = orderindexofadd.getText();
		WebElement statusADD = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//tbody[2]//tr[td//span[@class='slno-text' and text()='1']]/td[5]")));
		String statusADd = statusADD.getText();
		WebElement titleadd = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//tbody[2]//tr[td//span[@class='slno-text' and text()='1']]/td[3]")));
		String titleadD = titleadd.getText();
		WebElement codeadd = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//tbody[2]//tr[td//span[@class='slno-text' and text()='1']]/td[2]")));
		String codeaddd = codeadd.getText();
		row.createCell(14).setCellValue(od);
		row.createCell(10).setCellValue(codeaddd);
		row.createCell(12).setCellValue(titleadD);
		row.createCell(16).setCellValue(statusADd);
	}

	public void clickCheckboxesFromExcel(Row row3) {

		String checkboxPositions = getCellValue(row3.getCell(4));
		// Split the string by comma
		String[] positions = checkboxPositions.split(",");
		for (String pos : positions) {
			pos = pos.trim();

			String path = "(//tbody[2]//input[@type='checkbox'])[" + pos + "]";
			try {
				WebElement checkbox = driver.findElement(By.xpath(path));
				if (!checkbox.isSelected() && checkbox.isDisplayed() && checkbox.isEnabled()) {
					checkbox.click();
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Checkbox at position " + pos + " not found or not clickable.");
			}

		}

	}
}
