package validation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utiles.Utilies;

public class Applying_Validation extends Utilies{
	WebDriver driver;
	WebDriverWait wait;
	Actions actions;

	@FindBy(xpath = "//h5[contains(text(),'Add details')]")
	WebElement add_details;
	
	@FindBy(xpath = "//h5[contains(.,'Applying Types Details')]")
	WebElement Module_name;
	
	@FindBy(xpath = "//div//h5[contains(text(),'Add details')]/../..//ul/li[contains(text(),'Masters')]")
	WebElement masters;
	
	@FindBy(xpath = "//li[contains(.,'Applying types')]")
	WebElement add;
	
	@FindBy(xpath = "//li[contains(.,'Add details')]")
	WebElement add_module;
	
	@FindBy(xpath = "//td[contains(text(),'Code')]")
	WebElement code;
	
	@FindBy(xpath = "//td[contains(text(),'Title')]")
	WebElement title;
	
	@FindBy(xpath = "//td[contains(text(),'Order index')]")
	WebElement orderindex;
	
	@FindBy(xpath = "//td[contains(text(),'Status')]")
	WebElement status;
	
    @FindBy(xpath = "//tbody[2]//span[contains(text(),'visibility')]") WebElement viewButton;

	@FindBy(xpath = "//h5[contains(text(),'View details')]")
	WebElement view_details;
	
	@FindBy(xpath = "//div//h5[contains(text(),'View details')]/../..//ul/li[contains(text(),'Masters')]")
	WebElement viewmasters;
	
	@FindBy(xpath = "//li[contains(.,'View details')]")
	WebElement view_module;
	
	@FindBy(xpath = "//label[contains(text(),'Code')]")
	WebElement view_code;
	
	@FindBy(xpath = "//label[contains(text(),'Title')]")
	WebElement view_title;
	
	@FindBy(xpath = "//label[contains(text(),'Order Index')]")
	WebElement view_orderindex;
	
	@FindBy(xpath = "//label[contains(text(),'Status')]")
	WebElement view_status;

	 @FindBy(xpath = "//tbody[2]//span[contains(text(),'edit')]") WebElement editButton;
	 
	@FindBy(xpath = "//h5[contains(text(),'Edit details')]")
	WebElement Edit_details;
	
	@FindBy(xpath = "//div//h5[contains(text(),'Edit details')]/../..//ul/li[contains(text(),'Masters')]")
	WebElement Edit_masters;
	
	@FindBy(xpath = "//li[contains(.,'Edit details')]")
	WebElement Edit_module;
	
	@FindBy(xpath = "//div//h5[contains(text(),'Applying Types')]/../..//ul/li[contains(text(),'Masters')]")
	WebElement Masters;
	
	@FindBy(xpath = "//h5[contains(.,'Applying Types')]")
	WebElement Module;
	
	@FindBy(xpath = "//span[contains(.,'Entries per page')]")
	WebElement Entriesperpage;
	
	@FindBy(xpath = "//label[contains(.,'Status')]")
	WebElement Tstatus;
	
	@FindBy(xpath = "search")
	WebElement search;
	
	@FindBy(xpath = "//button[contains(.,'Export')]")
	WebElement export;
	
	@FindBy(xpath = "//a[contains(.,'Add ')]")
	WebElement addbutton;
	
	@FindBy(xpath = "//span[contains(.,'Sl.No')]")
	WebElement slno;
	
	@FindBy(xpath = "//span[contains(.,'Code')]")
	WebElement Tcode;
	
	@FindBy(xpath = "//span[contains(.,'Title')]")
	WebElement Ttitle;
	
	@FindBy(xpath = "//span[contains(.,'Order index')]")
	WebElement TOD;
	
	@FindBy(xpath = "//span[contains(.,'Status')]")
	WebElement Tastatus;
	
	@FindBy(xpath = "//span[contains(.,'Action')]")
	WebElement Action;
	
	 @FindBy(xpath="//div[@id='entriesInfo']")
    WebElement showingpage;

	 @FindBy(xpath = "(//h5[contains(text(),'Applying Types')]/../..//li[contains(text(),'')])[2]")
	 WebElement Tabadd;

	 @FindBy(xpath = "//a[contains(@class, 'rounded-5') and contains(@class, 'btn-secondary')]")
		WebElement cancelButton;
	 

		@FindBy(linkText = "arrow_back")
		WebElement backButton;
	
	public Applying_Validation(WebDriver driver)
	{
		 this.driver = driver;
	     this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	     this.actions=new Actions(driver);
		 PageFactory.initElements(driver, this);
	}
	 public void openMasters() {
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Masters')]"))).click();
	        sleep(1000);
	    }

	    public void openApplyingTypes() {
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Applying Types')]"))).click();
	        sleep(1000);
	    }

	      public void clickAdd() {
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Add')]"))).click();
	        sleep(1000);
	    }
	public void alltitle() {
		FileInputStream fis = null;
  	    FileOutputStream fileOut = null;
		try
		{
			FileInputStream fileinput = new FileInputStream("D://KSP_Admin//Applying Type.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fileinput);
			for(int i=1; i<=1; i++)
	    	{
	    		Sheet sheet = workbook.getSheetAt(0);
	    		
	    		Sheet sheet3 = workbook.getSheetAt(2);
	    		
	    		Row row = sheet.getRow(i);
	    		if(row==null)
	    		{
	    			continue;
	    		}
	    	   add(sheet3);
	    	   View(sheet3);
	    		Edit(sheet3);
	    		Table(sheet3);
	    		
	    		FileOutputStream fileout = new FileOutputStream("D://KSP_Admin//Applying Type.xlsx");
	    	    workbook.write(fileout);
	    		
	    	}}
	    	catch (Exception e) {
				// TODO: handle exception
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
	
	public void add(Sheet sheet3) {
		sleep(1000);
		clickAdd();
		String Name = Module_name.getText();
		String add_Details = add_details.getText();
		String Master = masters.getText();
		String ADD = add.getText();
		String ADD_Module = add_module.getText();
		String Code = code.getText();
		String Title = title.getText();
		String OD = orderindex.getText();
		String Status = status.getText();
		String cleanText = ADD.replace("chevron_right", "").trim();
		String cleanText1 = ADD_Module.replace("chevron_right", "").trim();
		
		writeToCell(sheet3, 1, 1, Name);
		writeToCell(sheet3, 2, 1, add_Details);
		writeToCell(sheet3, 3, 1, Master);
		writeToCell(sheet3, 4, 1, cleanText);
		writeToCell(sheet3, 5, 1, cleanText1);
		writeToCell(sheet3, 6, 1, Code);
		writeToCell(sheet3, 7, 1, Title);
		writeToCell(sheet3, 8, 1, OD);
		writeToCell(sheet3, 9, 1, Status);
		clickonElement(cancelButton);
		
	
	}
	
	public void View(Sheet sheet3) {
		sleep(1000);
		clickonElement(viewButton);
		
		String Name = Module_name.getText();
		String view_Details = view_details.getText();
		String viewmaster = viewmasters.getText();
		String ADD = add.getText();
		String view_Module = view_module.getText();
		String Code = view_code.getText();
		String Title = view_title.getText();
		String OD = view_orderindex.getText();
		String Status = view_status.getText();
		String cleanText = ADD.replace("chevron_right", "").trim();
		String cleanText1 = view_Module.replace("chevron_right", "").trim();
		
		writeToCell(sheet3, 1, 3, Name);
		writeToCell(sheet3, 2, 3, view_Details);
		writeToCell(sheet3, 3, 3, viewmaster);
		writeToCell(sheet3, 4, 3, cleanText);
		writeToCell(sheet3, 5, 3, cleanText1);
		writeToCell(sheet3, 6, 3, Code);
		writeToCell(sheet3, 7, 3, Title);
		writeToCell(sheet3, 8, 3, OD);
		writeToCell(sheet3, 9, 3, Status);
		clickonElement(backButton);

	}

	public void Edit(Sheet sheet3) {
		sleep(1000);
		clickonElement(editButton);
		String Name = Module_name.getText();
		String Edit_Details = Edit_details.getText();
		String Master = Edit_masters.getText();
		String ADD = add.getText();
		String Edit_Modules = Edit_module.getText();
		String Code = code.getText();
		String Title = title.getText();
		String OD = orderindex.getText();
		String Status = status.getText();
		String cleanText = ADD.replace("chevron_right", "").trim();
		String cleanText1 = Edit_Modules.replace("chevron_right", "").trim();
		
		writeToCell(sheet3, 1, 5, Name);
		writeToCell(sheet3, 2, 5, Edit_Details);
		writeToCell(sheet3, 3, 5, Master);
		writeToCell(sheet3, 4, 5, cleanText);
		writeToCell(sheet3, 5, 5, cleanText1);
		writeToCell(sheet3, 6, 5, Code);
		writeToCell(sheet3, 7, 5, Title);
		writeToCell(sheet3, 8, 5, OD);
		writeToCell(sheet3, 9, 5, Status);
		clickonElement(cancelButton);

	}
	
	public void Table(Sheet sheet3) {
		String Namee = Module.getText();
		
		String Master = Masters.getText();
		String ADD = Tabadd.getText();
		String pageperentries = Entriesperpage.getText();
		String Tabstatus = Tstatus.getText();
	//	String Search = search.getText();
		String Export = export.getText();
		String Addbutton = addbutton.getText();
		String Slno = slno.getText();
		String TCode = Tcode.getText();
		String TTitle = Ttitle.getText();
		String TOID = TOD.getText();
		String Tablstatus = Tastatus.getText();
		String Actions = Action.getText();
		String showingpages = showingpage.getText();
		String cleanText = ADD.replace("chevron_right", "").trim();
		
		writeToCell(sheet3, 1, 7, Namee);
		writeToCell(sheet3, 2, 7, Master);
		writeToCell(sheet3, 3, 7, cleanText);
		writeToCell(sheet3, 4, 7, pageperentries);
		writeToCell(sheet3, 5, 7, Tabstatus);
		writeToCell(sheet3, 6, 7, Export);
		writeToCell(sheet3, 7, 7, Addbutton);
		writeToCell(sheet3, 8, 7, Slno);
		writeToCell(sheet3, 9, 7, TCode);
		writeToCell(sheet3, 10, 7, TTitle);
		writeToCell(sheet3, 11, 7, TOID);
		writeToCell(sheet3, 12, 7, Addbutton);
		writeToCell(sheet3, 13, 7, Tablstatus);
		writeToCell(sheet3, 14, 7, Actions);
		writeToCell(sheet3, 15, 7, showingpages);
	}                       
	 public void clickonElement(WebElement element) {
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			sleep(200);
			actions.moveToElement(element).click().perform();
		}
	 
	 public void writeToCell(Sheet sheet3, int rowNum, int colNum, String value) {
		    Row row2 = sheet3.getRow(rowNum);
		    if (row2 == null) row2 = sheet3.createRow(rowNum);

		    Cell cell = row2.getCell(colNum);
		    if (cell == null) cell = row2.createCell(colNum);

		    cell.setCellValue(value);
		}

	}


