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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utiles.Utilies;

public class Extra extends Utilies {

    WebDriver driver;
    WebDriverWait wait;
    Robot robot;
    Actions actions;


    @FindBy(id = "KannadaPaperType_Code")
    WebElement codeField;

    @FindBy(id = "KannadaPaperType_Title")
    WebElement titleField;

    @FindBy(xpath = "//select[@id='KannadaPaperType_OrderIndex' and @name ='KannadaPaperType.OrderIndex']")
    WebElement orderIndexDropdown;

    @FindBy(xpath = "//select[@id='KannadaPaperType_StatusCode' and @name ='KannadaPaperType.StatusCode']")
    WebElement statusDropdown;

    @FindBy(xpath = "//button[@class='btn btn-secondary rounded-5 text-center d-flex align-items-center text-decoration-none']")
    WebElement cancelButton;

    @FindBy(id = "search-bar")
    WebElement searchBar;

    @FindBy(xpath = "//tr/td[contains(text(),'No Genders found.')]")
    WebElement Noelement;

    @FindBy(xpath = "//tbody[2]//td//a//span[@class='material-symbols-outlined' and contains(text(),'visibility')]")
    WebElement viewButton;

    @FindBy(xpath = "//tbody[2]//span[@class='material-symbols-outlined' and contains(text(),'edit')]")
    WebElement editButton;

    @FindBy(linkText = "arrow_back")
    WebElement backButton;

    @FindBy(id="GenderDTO_Code")
    WebElement Editcode;

    @FindBy(id="GenderDTO_Title")
    WebElement Edittitle;

    @FindBy(xpath="//select[@id='GenderDTO_OrderIndex' and @ name='GenderDTO.OrderIndex']")
    WebElement Editorderindex;

    @FindBy(xpath="//select[@id='StatusDropdown' and @ name='GenderDTO.StatusCode']")
    WebElement Editstatus;

    @FindBy(xpath = "//a[contains(text(),'Cancel')]")
    WebElement CancelEdit;

    @FindBy(xpath = "//button[contains(text(),'Save')]")
    WebElement save;

    @FindBy(xpath = "//tbody[2]//button//span[contains(text(),'delete')]")
    WebElement delete;

    @FindBy(xpath="//button[contains(text(),'OK')]")
    WebElement ok;

    @FindBy(xpath="//span[contains(text(),'close')]")
    WebElement clear;

    @FindBy(xpath = "(//input[@type='checkbox'])[2]")
    WebElement checkbox1;

    @FindBy(xpath = "(//input[@type='checkbox'])[1]")
    WebElement checkboxmulti;

    @FindBy(id = "statusSelect")
    WebElement Status;

    @FindBy(xpath="//button[contains(text(),'Apply')]")
    WebElement Apply;

    @FindBy(xpath="//a[contains(text(),'Add')]")
    WebElement add;

    @FindBy(xpath="//div[@id='swal2-html-container']")
    WebElement popup_message;


    @FindBy(xpath = "//button[@class='swal2-confirm swal2-styled swal2-default-outline']")
    WebElement ConfDelete;

    @FindBy(xpath = "//button[@class='swal2-cancel swal2-styled swal2-default-outline']")
    WebElement deletecancel;

    public Extra(WebDriver driver) throws AWTException {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.robot = new Robot();
        this.actions = new Actions(driver);

        PageFactory.initElements(driver, this);

    }

    public void openMasters() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Masters')]"))).click();
        sleep(1000);
    }

    public void openKannada_PaperTypes() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Kannada Paper Types')]"))).click();
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
            FileInputStream fis1 = new FileInputStream("D://KSP_Admin//Gender.xlsx");//"D:\steno\TestDataAPC.xlsx"
	    	XSSFWorkbook workbook = new XSSFWorkbook(fis1);

            for (int i = 1; i <= 3; i++) {
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
                int status = (int) row.getCell(4).getNumericCellValue();

                String codeEdit = getCellValue(row.getCell(6));
                String titleEdit = getCellValue(row.getCell(7));
                int orderIndexEdit = (int) row.getCell(8).getNumericCellValue();
                int statusEdit = (int) row.getCell(9).getNumericCellValue();

                add.click();
                switchToNewWindow(driver);

                codeField.sendKeys(code);
                titleField.sendKeys(title);
                new Select(orderIndexDropdown).selectByIndex(orderIndex);
                new Select(statusDropdown).selectByIndex(status);

                actions.moveToElement(save).click().perform();
                sleep(2000);

                if(isElementClickable(driver, ok)) {
                	try {
                		 wait.until(ExpectedConditions.visibilityOf(ok));
                         ok.click();
                         String PopUp = popup_message.getText();
                         System.out.println("Add page : "+ PopUp);
                         row1.createCell(5).setCellValue(PopUp);

                         sleep(1000);
                         cancelButton.click();
                         sleep(500);
                         switchToNewWindow(driver);
                	}
                	catch (Exception r) {
						// TODO: handle exception
					}
                }
                sleep(1000);
                switchToNewWindow(driver);
                actions.moveToElement(searchBar).click().perform();
                searchBar.sendKeys(code);

                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                if(isElementClickable(driver, Noelement))
                {
                	clear.click();
                	 robot.keyPress(KeyEvent.VK_ENTER);
                     robot.keyRelease(KeyEvent.VK_ENTER);
                }

                sleep(1000);
                wait.until(ExpectedConditions.visibilityOf(viewButton));
                actions.moveToElement(viewButton).click().perform();

                switchToNewWindow(driver);


                String getViewCode = getAttributeValue(By.id("code"));
                String getViewTitle = getAttributeValue(By.id("title"));
                String getOrderIndex = getAttributeValue(By.id("orderIndex"));
                String getStatus = getAttributeValue(By.id("status"));


                actions.moveToElement(backButton).click().perform();
                switchToNewWindow(driver);


                row1.createCell(1).setCellValue(getViewCode);
                row1.createCell(2).setCellValue(getViewTitle);
                row1.createCell(3).setCellValue(getOrderIndex);
                row1.createCell(4).setCellValue(getStatus);


                actions.moveToElement(searchBar).click().perform();
                searchBar.sendKeys(code);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);

                sleep(500);
                actions.moveToElement(editButton).click().perform();
                switchToNewWindow(driver);


                Editcode.clear();
                Editcode.sendKeys(codeEdit);

                Edittitle.clear();
                Edittitle.sendKeys(titleEdit);
                sleep(1000);
                if(isElementClickable(driver, Editorderindex))
               	{
                Select s = new Select(Editorderindex);
                s.selectByIndex(orderIndexEdit);
                sleep(1000);
                		}

                new Select(Editstatus).selectByIndex(statusEdit);

                wait.until(ExpectedConditions.visibilityOf(save));
                save.click();

                wait.until(ExpectedConditions.visibilityOf(ok));
                ok.click();
                String PopUp = popup_message.getText();
                System.out.println("Edit page :"+ PopUp);
                sleep(1000);
                row1.createCell(6).setCellValue(PopUp);
                if(isElementClickable(driver, cancelButton)) {
                	try {

                         sleep(500);
                         cancelButton.click();
                         sleep(500);
                         switchToNewWindow(driver);
                	}
                	catch (Exception r) {
						// TODO: handle exception
                		  wait.until(ExpectedConditions.visibilityOf(ok));
                          ok.click();
					}
                }
                switchToNewWindow(driver);
                sleep(1000);
                wait.until(ExpectedConditions.visibilityOf(searchBar));
                actions.moveToElement(searchBar).click().perform();
                searchBar.sendKeys(codeEdit);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);

                sleep(500);
                wait.until(ExpectedConditions.visibilityOf(delete));
                actions.moveToElement(delete).click().perform();
                sleep(500);
                try {
                	  wait.until(ExpectedConditions.visibilityOf(delete));
                      actions.moveToElement(delete).click().perform();
                      sleep(500);
                	if(i%2==0)
                	{
                		ConfDelete.click();
                		 String PopUpdelete = popup_message.getText();
                		 System.out.println();
                         row1.createCell(8).setCellValue(PopUpdelete);
                		sleep(500);
                		ok.click();
                		 String PopUpdeleteok = popup_message.getText();
                         row1.createCell(9).setCellValue(PopUpdeleteok);
                	}
                	else {
                		deletecancel.click();
                	}
                } catch (Exception f)  {

                	actions.moveToElement(searchBar).click().perform();
                    clear.click();
                    robot.keyPress(KeyEvent.VK_ENTER);
                    robot.keyRelease(KeyEvent.VK_ENTER);

                }
                sleep(1000);
                checkbox1.click();
                new Select(Status).selectByIndex(2);
                Apply.click();
                wait.until(ExpectedConditions.visibilityOf(ok));
                ok.click();

                checkboxmulti.click();
                new Select(Status).selectByIndex(1);
                Apply.click();
                wait.until(ExpectedConditions.visibilityOf(ok));
                ok.click();
                String PopUpcheck = popup_message.getText();
                System.out.println("delete : "+ PopUpcheck);

                System.out.println(i+" iteration completed");

            	 fileOut = new FileOutputStream("D://KSP_Admin//Gender.xlsx");
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
}
