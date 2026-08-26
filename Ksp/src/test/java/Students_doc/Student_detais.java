package Students_doc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.concurrent.CompletableFuture;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import Students_doc.AuthUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.net.HttpURLConnection;
import java.net.URL;

public class Student_detais {
@Test
public void stddocuments() throws IOException {


    FileInputStream fileinput = new FileInputStream(
            "D:\\KSP.Recruitment\\STD_details\\APC_3064_PaidSt.xlsx");

    XSSFWorkbook workbook = new XSSFWorkbook(fileinput);
    Sheet sheet = workbook.getSheetAt(0);

    int rowcount = sheet.getPhysicalNumberOfRows();
    System.out.println("Rowcount is " + rowcount);

    Row header = sheet.getRow(0);

    // Create headers only once
    header.createCell(3).setCellValue("photoPath");
    header.createCell(4).setCellValue("photoStatus");

    header.createCell(5).setCellValue("signaturePath");
    header.createCell(6).setCellValue("signatureStatus");

    header.createCell(7).setCellValue("thumbPath");
    header.createCell(8).setCellValue("thumbStatus");

    header.createCell(9).setCellValue("identityCardPath");
    header.createCell(10).setCellValue("identityCardStatus");

    header.createCell(11).setCellValue("response");
    
    for (int i = 1; i < rowcount; i++) {

        Row row = sheet.getRow(i);

        if (row == null) {
            System.out.println(i + " Skipping empty row");
            continue;
        }

        System.out.println("Iterarion "+i);
        String statusCode = getCellValue(row.getCell(1));

        if (statusCode == null || statusCode.trim().isEmpty()) {
            row.createCell(7).setCellValue("Applicant ID is blank");
            continue;
        }

        try {

            String token = AuthUtil.getValidToken();

            RestAssured.baseURI = "https://docs2.ksp-recruitment.in";
            RestAssured.defaultParser = Parser.JSON;

            RequestSpecification req = RestAssured.given();

            req.header("Authorization", "Bearer " + token);
            req.header("Content-Type", "application/json");
            req.header("accept", "*/*");

            String endpoint = "/api/v1/Applicant/" + statusCode + "/fordisplay";

            Response res = req.request(Method.GET, endpoint);

            System.out.println("Applicant ID : " + statusCode);
            System.out.println("Status Code : " + res.getStatusCode());

            if (res.getStatusCode() == 200) {

                Object data = res.jsonPath().get("data");

                if (data != null) {

                	String photoPath = res.jsonPath().getString("data.photoPath");
                	String signaturePath = res.jsonPath().getString("data.signaturePath");
                	String thumbPath = res.jsonPath().getString("data.thumbPath");
                	String identityCardPath = res.jsonPath().getString("data.identityCardPath");

                	/* Write URLs */
                	row.createCell(3).setCellValue(photoPath);
                	row.createCell(5).setCellValue(signaturePath);
                	row.createCell(7).setCellValue(thumbPath);
                	row.createCell(9).setCellValue(identityCardPath);

                	/* Check all 4 URLs simultaneously */
                	CompletableFuture<String> photo =
                	        CompletableFuture.supplyAsync(
                	                () -> getFileStatus(photoPath));

                	CompletableFuture<String> sign =
                	        CompletableFuture.supplyAsync(
                	                () -> getFileStatus(signaturePath));

                	CompletableFuture<String> thumb =
                	        CompletableFuture.supplyAsync(
                	                () -> getFileStatus(thumbPath));

                	CompletableFuture<String> id =
                	        CompletableFuture.supplyAsync(
                	                () -> getFileStatus(identityCardPath));

                	/* Wait for all results and write to Excel */
                	row.createCell(4).setCellValue(photo.get());
                	row.createCell(6).setCellValue(sign.get());
                	row.createCell(8).setCellValue(thumb.get());
                	row.createCell(10).setCellValue(id.get());

                	row.createCell(11).setCellValue("Success");

                } else {

                    System.out.println("Data is null for Applicant : "
                            + statusCode);

                    for (int j = 3; j <= 10; j++) {
                        row.createCell(j).setCellValue("");
                    }

                    row.createCell(11).setCellValue("Data is null");

                    continue;
                }

            } else {
            	row.createCell(11).setCellValue(
            	        "HTTP Status : " + res.getStatusCode());

                continue;
            }
        } catch (Exception e) {

            System.out.println("Error for Applicant : "
                    + statusCode);

            System.out.println(e.getMessage());

            row.createCell(11).setCellValue(
                    "Error : " + e.getMessage());
        }

        /* SAVE AFTER EVERY 100 RECORDS */
        if (i % 5000 == 0) {

            try (FileOutputStream fileout = new FileOutputStream(
                    "D:\\KSP.Recruitment\\STD_details\\APC_3064_PaidSt.xlsx")) {

                workbook.write(fileout);

                System.out.println("Excel saved till row : " + i);

            } catch (Exception e) {

                System.out.println("Failed to save Excel at row : " + i);
                e.printStackTrace();
            }
        }
        }

        /* FINAL SAVE AFTER LOOP COMPLETES */
        FileOutputStream fileout = new FileOutputStream(
                "D:\\KSP.Recruitment\\STD_details\\APC_3064_PaidSt.xlsx");

        workbook.write(fileout);

        fileout.close();
        fileinput.close();
        workbook.close();

        System.out.println("Excel updated successfully.");
        }
public String getCellValue(Cell cell) {
	if (cell == null || cell.getCellType() == CellType.BLANK) {
		return ""; // Must be "" not null
	}

	switch (cell.getCellType()) {
	case STRING:
		return cell.getStringCellValue().trim();

	case NUMERIC:
		if (DateUtil.isCellDateFormatted(cell)) {
			// Convert Excel date to String
			java.util.Date date = cell.getDateCellValue();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
			return sdf.format(date);
		} else {
			double value = cell.getNumericCellValue();
			if (value == Math.floor(value)) {
				return String.valueOf((long) value); // No decimal if integer
			} else {
				return String.valueOf(value);
			}
		}

	case BOOLEAN:
		return String.valueOf(cell.getBooleanCellValue());

	case FORMULA:
		try {
			FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
			Cell evaluatedCell = evaluator.evaluateInCell(cell);
			return getCellValue(evaluatedCell); // Recursive call for result
		} catch (Exception e) {
			return "";
		}

	default:
		return "";
	}
}

public String getFileStatus(String urlString) {

    if (urlString == null || urlString.trim().isEmpty()) {
        return "URL Empty";
    }

    try {

        URL url = new URL(urlString);

        HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("HEAD");
        connection.setConnectTimeout(3000);
        connection.setReadTimeout(3000);

        int status = connection.getResponseCode();

        switch (status) {

        case 200:
            long sizeKB = connection.getContentLengthLong() / 1024;
            connection.disconnect();
            return "Accessible (" + sizeKB + " KB)";

        case 403:
            connection.disconnect();
            return "Expired";

        case 404:
            connection.disconnect();
            return "Not Found";

        default:
            connection.disconnect();
            return "HTTP " + status;
        }

    } catch (Exception e) {

        return "Invalid URL";
    }
}
}
