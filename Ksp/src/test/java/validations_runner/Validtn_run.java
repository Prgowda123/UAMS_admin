package validations_runner;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base_class.Base_class;
import ksp_admin.Pom_class;
import validation.Applying_Validation;

public class Validtn_run {
	@Listeners(utiles.ListerImplementation.class)
	public class Runner extends Base_class{
		@Test(enabled = true , priority=1)
		public void Pom() throws AWTException, IOException
		{
		     Applying_Validation p = new Applying_Validation(driver);
			 p.openMasters();
			 p.openApplyingTypes();
			 p.alltitle();
		}   
}
}