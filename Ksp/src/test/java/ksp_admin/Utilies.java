//package ksp_admin;
//
//import java.text.SimpleDateFormat;
//import java.time.Duration;
//import java.util.Set;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.DateUtil;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class Utilies {
//
//	WebDriver driver;
//
//	  public boolean isElementClickable(WebDriver driver, WebElement element) {
//	        try {
//	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
//	            wait.until(ExpectedConditions.elementToBeClickable(element));
//	            return true;
//	        } catch (Exception e) {
//	            return false;
//	        }
//	    }
//
////
//	    public String getCellValue(Cell cell) {
//	        if (cell == null) {
//	            return "";
//	        }
//	        switch (cell.getCellType()) {
//	            case STRING:
//	                return cell.getStringCellValue();
//	            case NUMERIC:
//	                if (DateUtil.isCellDateFormatted(cell)) {
//	                    // If the cell contains a date, convert it to a string in the desired format
//	                    java.util.Date date = cell.getDateCellValue();
//	                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy"); // Customize format
//	                    return sdf.format(date);
//	                } else {
//	                    // Handle numeric values as needed
//	                    return String.valueOf(cell.getNumericCellValue());
//	                }
//	            case BOOLEAN:
//	                return String.valueOf(cell.getBooleanCellValue());
//	            default:
//	                return "";
//	        }
//	    }
////
////
//
//
//	    public void switchToNewWindow(WebDriver driver) {
//	    	Set<String> windowHandles = driver.getWindowHandles();
//	    	for (String windowHandle : windowHandles) {
//	    	    driver.switchTo().window(windowHandle);
//	    	}}
//	    public void sleep(int milliseconds) {
//	        try {
//	            Thread.sleep(milliseconds);
//	        } catch (InterruptedException e) {
//	            e.printStackTrace();
//	        }
//	    }
//}

