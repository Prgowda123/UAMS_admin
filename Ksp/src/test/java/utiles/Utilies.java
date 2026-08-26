package utiles;



import java.time.Duration;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilies {

	WebDriver driver;

	  public boolean isElementClickable(WebDriver driver, WebElement element) {
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
	            wait.until(ExpectedConditions.elementToBeClickable(element));
	            return true;
	        } catch (Exception e) {
	            return false;
	        }
	    }

	  public String getCellValue(Cell cell) {
		    if (cell == null || cell.getCellType() == CellType.BLANK) {
		        return "";
		    }

		    switch (cell.getCellType()) {
		        case STRING:
		            return cell.getStringCellValue().trim();

		        case NUMERIC:
		            if (DateUtil.isCellDateFormatted(cell)) {
		                java.util.Date date = cell.getDateCellValue();
		                return date.toString();  // No formatting applied
		            } else {
		                double value = cell.getNumericCellValue();
		                if (value == Math.floor(value)) {
		                    return String.valueOf((long) value);
		                } else {
		                    return String.valueOf(value);
		                }
		            }

		        case BOOLEAN:
		            return String.valueOf(cell.getBooleanCellValue());

		        case FORMULA:
		            try {
		                FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
		                return getCellValue(evaluator.evaluateInCell(cell));
		            } catch (Exception e) {
		                return "";
		            }

		        default:
		            return "";
		    }
		
}

	
		// ---------- HELPER METHOD ----------

		public static void performClickWithPause(Actions actions, WebElement element) throws InterruptedException {
			performClickWithPause(actions, element, 200); // default delay
		}

		public static void performClickWithPause(Actions actions, WebElement element, int pauseMillis)
				throws InterruptedException {
			actions.moveToElement(element).click().perform();
			Thread.sleep(pauseMillis);
		}


	    public void switchToNewWindow(WebDriver driver) {
	    	Set<String> windowHandles = driver.getWindowHandles();
	    	for (String windowHandle : windowHandles) {
	    	    driver.switchTo().window(windowHandle);
	    	}}
	    public void sleep(int milliseconds) {
	        try {
	            Thread.sleep(milliseconds);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	
	  
}
