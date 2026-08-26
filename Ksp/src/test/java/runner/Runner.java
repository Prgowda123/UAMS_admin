package runner;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base_class.Base_class;
import ksp_admin.Army_force;
import ksp_admin.Category_pom;
import ksp_admin.District_pom;
import ksp_admin.Document_Type;
import ksp_admin.Ex_ser_qualification;
import ksp_admin.Exservice_child_Relation;
import ksp_admin.Gender;
import ksp_admin.Identity_card_type;
import ksp_admin.Kalyanna_karnataka_districts;
import ksp_admin.Kannada_paper;
import ksp_admin.Pincode;
import ksp_admin.Pincode_districts;
import ksp_admin.Pom_class;
import ksp_admin.Qualification_Board;
import ksp_admin.Recruitment_activities;
import ksp_admin.Sports_Games;
import ksp_admin.Sports_achivement_details;
import ksp_admin.Unionstate_Territories;
import ksp_admin.app_pom;
import ksp_admin.applyingType_pom;
import ksp_admin.ksp_wings;
@Listeners(utiles.ListerImplementation.class)
public class Runner extends Base_class{
	@Test(enabled = true , priority=1)
	public void Pom() throws AWTException, IOException
	{
	     Pom_class p = new Pom_class(driver);
		 p.openMasters();
		 p.openExservicemen_child_Relations();
		 p.switchWindow();
		 p.processall();
	}    
	@Test(enabled = true , priority=1)
	public void Pom1() throws AWTException, IOException
	
	{
	      app_pom a = new app_pom(driver);
		 a.openMasters();
		 a.openApplyingTypes();
		 a.switchWindow();
		 a.processall();
	}  
	@Test(enabled = true , priority=1)
	public void applyingtype() throws AWTException, IOException
	{
	    applyingType_pom p1 = new applyingType_pom(driver);
		p1.openMasters();
		p1.openApplyingTypes();
		p1.switchWindow();
		p1.processall();
		
	}

	@Test(enabled = true, priority=2)
	public void category() throws AWTException, IOException
	{
		Category_pom p2=new Category_pom(driver);
		p2.openMasters();
		p2.opencategory();
		p2.switchWindow();
		p2.processall();
	}


	@Test(enabled = true, priority=3)
	public void Districts() throws AWTException
	{
		District_pom p3 = new District_pom(driver);
		p3.openMasters();
		p3.opendistricts();
		p3.switchWindow();
		p3.addd();
	}

	@Test(enabled = true, priority=4)

	public void Exser_edu_qualification() throws AWTException, IOException
	{
		Ex_ser_qualification p4 = new Ex_ser_qualification(driver);
		p4.openMasters();
		p4.openEx_servicemen_qualification();
		p4.switchWindow();
		p4.processall();
	}

	@Test(enabled = true, priority=5)
	public void Army_Force() throws AWTException, IOException
	{
		Army_force p5 = new Army_force(driver);
		p5.openMasters();
		p5.openArmy_Force();
		p5.switchWindow();
		p5.processall();
	}

	@Test(enabled = true, priority=6)
	public void Document_Type() throws AWTException, IOException
	{
	    ksp_admin.Document_Type p6 = new Document_Type(driver);
		p6.openMasters();
		p6.openDocumentType();
		p6.switchWindow();
		p6.processall();
	}

	@Test(enabled = true, priority=7)
	public void exservice_child() throws AWTException
	{
		    Exservice_child_Relation p7 = new Exservice_child_Relation(driver);
			p7.openMasters();
			p7.openExservicemen_child_Relations();
			p7.switchWindow();
			p7.addd();
	}

	@Test(enabled = true, priority=7)
	public void gender() throws AWTException, IOException
	{
		    Gender p8 = new Gender(driver);
			p8.openMasters();
			p8.openGender();
			p8.switchWindow();
			p8.processall();
	}

	@Test(enabled = true, priority=8)
	public void Indentity_card() throws AWTException
	{
		    Identity_card_type p9 = new Identity_card_type(driver);
			p9.openMasters();
			p9.openIdentity_card_type();
			p9.switchWindow();
			p9.addd();
	}

	@Test(enabled = true, priority=8)
	public void kSPWINGS() throws AWTException
	{
		    ksp_wings p10 = new ksp_wings(driver);
			p10.openMasters();
			p10.openKSPWings();
			p10.switchWindow();
			p10.addd();
	}

	@Test(enabled = true, priority=8)
	public void unionstate() throws AWTException
	{
		    Unionstate_Territories p11 = new Unionstate_Territories(driver);
			p11.openMasters();
			p11.openUnionState_Territories();
			p11.switchWindow();
			p11.addd();
	}


	@Test(enabled = true, priority=8)
	public void Kalyanakarnataka() throws AWTException
	{
		    Kalyanna_karnataka_districts p12 = new Kalyanna_karnataka_districts(driver);
			p12.openMasters();
			p12.openKalayan_KaranatakaDistricts();
			p12.switchWindow();
			p12.addd();
	}


	@Test(enabled = true, priority=8)
	public void qualificationboard() throws AWTException
	{
		     Qualification_Board p13 = new Qualification_Board(driver);
			p13.openMasters();
			p13.openQualification_Board();
			p13.switchWindow();
			p13.addd();
	}

	@Test(enabled = true, priority=8)
	public void sportsandgames() throws AWTException
	{
		    Sports_Games p14 = new Sports_Games(driver);
			p14.openMasters();
			p14.openSportsandgames();
			p14.switchWindow();
			p14.addd();
	}

	@Test(enabled = true, priority=8)
	public void sportsachivementdeatils() throws AWTException
	{
		    Sports_achivement_details p15 = new Sports_achivement_details(driver);
			p15.openMasters();
			p15.opensportsachivements_details();
			p15.switchWindow();
			p15.addd();
	}

	@Test(enabled = true, priority=8)
	public void pincode() throws AWTException
	{
		    Pincode p16 = new Pincode(driver);
			p16.openMasters();
			p16.openpincode();
			p16.switchWindow();
			p16.addd();
	}

	@Test(enabled = true, priority=8)
	public void pincodedistricts() throws AWTException
	{
		    Pincode_districts p17 = new Pincode_districts(driver);
			p17.openMasters();
			p17.openPincodedistricts();
			p17.switchWindow();
			p17.addd();
	}

	@Test(enabled = true, priority=8)
	public void kannadapapertype() throws AWTException
	{
		    Kannada_paper p18 = new Kannada_paper(driver);
			p18.openMasters();
			p18.openkannadapaperType();
			p18.switchWindow();
			p18.addd();
	}

	@Test(enabled = true, priority=8)
	public void RecruitmentActivities() throws AWTException
	{
		    Recruitment_activities p19 = new Recruitment_activities(driver);
			p19.openMasters();
			p19.openRecruitment_Activities();
			p19.switchWindow();
			p19.addd();
	}
}

//	@Test(enabled = false, priority=8)
//	public void extra() throws AWTException
//	{
//		     Extra p12 = new Extra(driver);
//			p12.openMasters();
//			p12.opendistricts();;
//			p12.switchWindow();
//			p12.addd();
//
//}}
