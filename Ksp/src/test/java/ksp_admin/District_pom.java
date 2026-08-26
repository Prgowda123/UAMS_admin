package ksp_admin;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

import utiles.Utilies;

public class District_pom extends Utilies {

    WebDriver driver;
    WebDriverWait wait;
    Robot robot;
    Actions actions;
    JavascriptExecutor jss;

    @FindBy(id = "District_Code")
    WebElement codeField;

    @FindBy(id = "District_Title")
    WebElement titleField;

    @FindBy(id = "District_OrderIndex")
    WebElement orderIndexDropdown;

    @FindBy(id="District_UnionStateCode")
    WebElement stateDropdown;

    @FindBy(id = "District_StatusCode")
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

    @FindBy(id="DistrictDTO_Code")
    WebElement Editcode;

    @FindBy(id="DistrictDTO_Title")
    WebElement Edittitle;


    @FindBy(xpath = "//select[@id='DistrictDTO_OrderIndex' and @name='DistrictDTO.OrderIndex']")
    WebElement Editorderindex;

    @FindBy(xpath ="//select[@id='DistrictDTO_UnionStateCode' and @name='DistrictDTO.UnionStateCode']")
    WebElement Editunionstate;

    @FindBy(xpath  = "//select[@id='DistrictDTO_StatusCode' and @name='DistrictDTO.StatusCode']")
    WebElement Editstatus;

    @FindBy(xpath = "//a[contains(@class, 'rounded-5') and contains(@class, 'btn-secondary')]")
    WebElement EditcancelButton;

    @FindBy(xpath = "//button[contains(text(),'Save')]")
    WebElement save;

    @FindBy(xpath = "//tbody[2]//button[@title='Delete']")
    WebElement delete;

    @FindBy(xpath = "//a[contains(@class, 'rounded-5') and contains(@class, 'btn-secondary')]")
    WebElement CancelEdit;

    @FindBy(xpath="//button[contains(text(),'OK') and @class='swal2-confirm swal2-styled']")
    WebElement ok;

    @FindBy(xpath="//span[contains(text(),'close')]")
    WebElement clear;

    @FindBy(xpath = "(//tbody[2]//input[@type='checkbox'])[1]")
    WebElement checkbox1;

    @FindBy(xpath = "//input[@type='checkbox' and @data-table='keyDates']")
    WebElement checkboxmulti;

    @FindBy(id = "statusSelect")
    WebElement ChangeStatus;

    @FindBy(xpath="//button[contains(text(),'Apply')]")
    WebElement Apply;

    @FindBy(xpath="//a[contains(text(),'Add')]")
    WebElement add;

    @FindBy(xpath="//div[@id='swal2-html-container']")
    WebElement popup_message;

    @FindBy(xpath = "//button[text()='Yes, delete it!']")
    WebElement ConfDelete;

    @FindBy(xpath = "//button[text()='Cancel']")
    WebElement deletecancel;

    @FindBy(id="itemsPerPage")
    WebElement entriesperpages;

    @FindBy(xpath="//th[2]//span[@data-order='desc']")
    WebElement codedesc;

    @FindBy(xpath="//th[2]//span[@data-order='asc']")
    WebElement codeasc;

    @FindBy(xpath="//th[3]//span[@data-order='desc']")
    WebElement titledesc;

    @FindBy(xpath="//th[3]//span[@data-order='asc']")
    WebElement titleasc;

    @FindBy(xpath="//th[4]//span[@data-order='desc']")
    WebElement orderindexdesc;

    @FindBy(xpath="//th[4]//span[@data-order='asc']")
    WebElement orderindexasc;

    @FindBy(xpath="//th[5]//span[@data-order='desc']")
    WebElement statusdesc;

    @FindBy(xpath="//th[5]//span[@data-order='asc']")
    WebElement statusasc;

    @FindBy(xpath="//ul[@id='pagination']//li[1]")
    WebElement paginationfirst;

    @FindBy(xpath="//ul[@id='pagination']//li[last()]")
    WebElement paginationlast;

    @FindBy(xpath="//div[@id='entriesInfo']")
    WebElement showingpage;


    public  District_pom(WebDriver driver) throws AWTException {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.robot = new Robot();
        this.actions = new Actions(driver);
        this.jss = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);

    }

    public void openMasters() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Masters')]"))).click();
        sleep(1000);
    }

    public void opendistricts() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'Districts')])[2]"))).click();
        sleep(1000);
    }

    public void switchWindow() {
        switchToNewWindow(driver);
        sleep(1000);
    }

    public void clickAdd() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Add')]"))).click();
        sleep(1000);
    }

    public void addd() {

    	 FileInputStream fis = null;
 	    FileOutputStream fileOut = null;
        try
              {
            FileInputStream fis1 = new FileInputStream("D://KSP_Admin//district.xlsx");//"D:\steno\TestDataAPC.xlsx"
	    	XSSFWorkbook workbook = new XSSFWorkbook(fis1);


            for (int i = 1; i <= 10; i++) {
            	Sheet sheet = workbook.getSheetAt(0);
    	        Sheet sheet2 = workbook.getSheetAt(1);
                Row row1 = sheet2.createRow(sheet2.getPhysicalNumberOfRows());

                Row row = sheet.getRow(i);
                if (row == null) {
					continue;
				}


                String code = getCellValue(row.getCell(1));
                String title = getCellValue(row.getCell(2));
                int orderIndex = (int) row.getCell(3).getNumericCellValue();
                 String Status = getCellValue(row.getCell(4));
                 int state = (int) row.getCell(5).getNumericCellValue();

                String codeEdit = getCellValue(row.getCell(6));
                String titleEdit = getCellValue(row.getCell(7));
                int orderIndexEdit = (int) row.getCell(8).getNumericCellValue();
                String statusEdit = getCellValue(row.getCell(9));
                int stateEdit = (int) row.getCell(10).getNumericCellValue();
                int entriesperpage = (int) row.getCell(11).getNumericCellValue();

                add.click();
                switchToNewWindow(driver);

                codeField.sendKeys(code);
                titleField.sendKeys(title);
                
                new Select(stateDropdown).selectByIndex(state);
                new Select(statusDropdown).selectByValue(Status);
                
                if(isElementClickable(driver, orderIndexDropdown))
                {
                	new Select(orderIndexDropdown).selectByIndex(orderIndex);
                }

                clickonElement(save);
                sleep(2000);
                if(isElementClickable(driver, ok)) {
                	try {
                		 clickonElement(ok);
                         String PopUp = popup_message.getText();
                         System.out.println("Add page : "+ PopUp);
                         sleep(500);
                         row1.createCell(6).setCellValue(PopUp);
                         cancelButton.click();
                         sleep(500);
                         switchToNewWindow(driver);
                	}
                	catch (Exception r) {
						// TODO: handle exception
                		// cancelButton.click();
					}
                }
                if(!isElementClickable(driver, entriesperpages))
                {try {
    			    boolean hasError = true;

    			    // List of specific error IDs (extend this as needed)
    			    String[] errorIds = {"District_Code-error", "District_Title-error", "District_OrderIndex-error", "District_UnionStateCode-error","District_StatusCode-error"
    			    };

    			    System.out.println(i + " : Iteration");
                    Reporter.log(i + " : Iteration");


    			    WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofMillis(10));
    			    String ro = i+" Failed";
    			    row1.createCell(1).setCellValue(ro);

    			    for (String errorId : errorIds) {
    			        try {
    			            WebElement errorMessage = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(errorId)));
    			            if (errorMessage != null && !errorMessage.getText().isEmpty()) {
    			                hasError = false;
    			                String er = errorMessage.getText();

    			                System.out.println("Error in field: " + errorId + " - " + er);

    			                Reporter.log(i + " iteration" + " Error in field of " + errorId + " - " + er);
    			            }
    			            if (!isElementClickable(driver, entriesperpages)) {
    			                robot.keyPress(KeyEvent.VK_ENTER);
    			                robot.keyRelease(KeyEvent.VK_ENTER);
    			            }
    			        } catch (TimeoutException e) {

    			//  System.out.println("No error for field: " + errorId);
    			        }
    			    }
    			} catch (Exception q) {
    			  //  q.printStackTrace();
    			}
                	 cancelButton.click();
                }
                sleep(1000);
                switchToNewWindow(driver);
                new Select(entriesperpages).selectByIndex(entriesperpage);
                clickonElement(searchBar);
                searchBar.sendKeys(code);

                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);

                sleep(1000);
                if(isElementClickable(driver, viewButton))
                {
                clickonElement(viewButton);

                switchToNewWindow(driver);

                String getViewCode = getAttributeValue(By.id("code"));
                String getViewTitle = getAttributeValue(By.id("title"));
                String getOrderIndex = getAttributeValue(By.id("orderIndex"));
                String getviewstate = getAttributeValue(By.id("uninon"));
                String getStatus = getAttributeValue(By.id("status"));


                clickonElement(backButton);
                switchToNewWindow(driver);


                row1.createCell(1).setCellValue(getViewCode);
                row1.createCell(2).setCellValue(getViewTitle);
                row1.createCell(3).setCellValue(getOrderIndex);
                row1.createCell(4).setCellValue(getStatus);
                row1.createCell(5).setCellValue(getviewstate);

                clickonElement(searchBar);
                searchBar.sendKeys(code);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);

                sleep(500);
                clickonElement(editButton);
                switchToNewWindow(driver);


                Editcode.clear();
                Editcode.sendKeys(codeEdit);

                Edittitle.clear();
                Edittitle.sendKeys(titleEdit);
                sleep(1000);
                if(isElementClickable(driver, Editorderindex))
                {
                   new Select(Editorderindex).selectByIndex(orderIndexEdit);
                }
                new Select(Editstatus).selectByValue(statusEdit);
                new Select(Editunionstate).selectByIndex(stateEdit);

                wait.until(ExpectedConditions.visibilityOf(save));
                save.click();
                sleep(1000);
                if(isElementClickable(driver, ok))
                {
                clickonElement(ok);
                Reporter.log(i + "Pass");
                String PopUp = popup_message.getText();
                System.out.println("Edit page :"+ PopUp);
                sleep(1000);
                row1.createCell(7).setCellValue(PopUp);
                }


                if(isElementClickable(driver, CancelEdit)) {
                	try {
                		try {
            			    boolean hasError = true;

            			    // List of specific error IDs (extend this as needed)
            			    String[] errorIds = {"DistrictDTO_Code-error", "DistrictDTO_Title-error"
            			    };

            			    System.out.println(i + " : Iteration Failed");
                            Reporter.log(i + " : Iteration Failed");


            			    WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofMillis(10));
            			    String ro = i+" Failed";
            			    row1.createCell(5).setCellValue(ro);

            			    for (String errorId : errorIds) {
            			        try {
            			            WebElement errorMessage = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(errorId)));
            			            if (errorMessage != null && !errorMessage.getText().isEmpty()) {
            			                hasError = false;
            			                String er = errorMessage.getText();

            			                System.out.println("Error Edit field: " + errorId + " - " + er);

            			                Reporter.log(i + " iteration" + " Error Edit field of " + errorId + " - " + er);
            			            }
            			            if (!isElementClickable(driver, entriesperpages)) {
            			                robot.keyPress(KeyEvent.VK_ENTER);
            			                robot.keyRelease(KeyEvent.VK_ENTER);
            			            }
            			        } catch (TimeoutException e) {

            			//  System.out.println("No error for field: " + errorId);
            			        }
            			    }
            			} catch (Exception q) {
            			  //  q.printStackTrace();
            			}
                		clickonElement(CancelEdit);


                        sleep(500);
                        switchToNewWindow(driver);
               	}
               	catch (Exception r) {
						// TODO: handle exception
               		  clickonElement(ok);

					}
               }
               switchToNewWindow(driver);
               sleep(1000);
               clickonElement(searchBar);
               searchBar.sendKeys(codeEdit);
               robot.keyPress(KeyEvent.VK_ENTER);
               robot.keyRelease(KeyEvent.VK_ENTER);

               sleep(500);


               try {
               	if(isElementClickable(driver, delete)) {
               	clickonElement(delete);
                     sleep(500);
//               	}
//               	//if(i%2==0)
//               	{
               		ConfDelete.click();
//               		 String PopUpdelete = popup_message.getText();

               		sleep(1500);
               		wait.until(ExpectedConditions.visibilityOf(ok));
               		actions.moveToElement(ok).click().perform();

               		 String PopUpdeleteok = popup_message.getText();
                        row1.createCell(9).setCellValue(PopUpdeleteok);
               	}
               	//else
               	{
               		if(isElementClickable(driver, deletecancel))
               			{
               			deletecancel.click();
               			}
                   	actions.moveToElement(searchBar).click().perform();
                       clear.click();

                       robot.keyPress(KeyEvent.VK_ENTER);
                       robot.keyRelease(KeyEvent.VK_ENTER);
               	}
               }
               catch (Exception f)  {
               }
               }
               else {
               	  clear.click();
               	  robot.keyPress(KeyEvent.VK_ENTER);
                     robot.keyRelease(KeyEvent.VK_ENTER);
               }

               sleep(1000);
               checkbox1.click();
               new Select(ChangeStatus).selectByValue(statusEdit);
               clickonElement(Apply);
               clickonElement(ok);


               checkboxmulti.click();
               new Select(ChangeStatus).selectByVisibleText("Active");
               clickonElement(Apply);
               clickonElement(ok);

               String PopUpcheck = popup_message.getText();
               System.out.println("delete : "+ PopUpcheck);
               row1.createCell(8).setCellValue(PopUpcheck);


               // Scroll click helper
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
               performClickWithPause(actions, paginationfirst, 1000);
               jss.executeScript("window.scrollBy(0,-4200)", "");

             sleep(1000);

             // Final brief pause
             Thread.sleep(800);



            	sleep(800);
            	 fileOut = new FileOutputStream("D://KSP_Admin//district.xlsx");
			    workbook.write(fileOut);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

        finally {
            try {
                if (fileOut != null) {
                    fileOut.close();
                }
                if (fis != null) {
                    fis.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }}
    }

    private String getAttributeValue(By locator) {
         return wait.until(ExpectedConditions.presenceOfElementLocated(locator)).getAttribute("value");
    }

    public void clickonElement(WebElement element) {
  		wait.until(ExpectedConditions.visibilityOf(element));
  		wait.until(ExpectedConditions.elementToBeClickable(element));
  		actions.moveToElement(element).click().perform();
  	}
}
