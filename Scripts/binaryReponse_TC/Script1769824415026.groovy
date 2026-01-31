import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Key

import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import java.io.FileOutputStream
import com.kms.katalon.core.configuration.RunConfiguration

// 1. Initialize the API Request
CloseableHttpClient httpClient = HttpClients.createDefault()
String url = "https://httpbin.org/image/png"

try {
	// 2. Create API Request
	HttpGet request = new HttpGet(url)
	
	// 3. Execute Request
	CloseableHttpResponse response = httpClient.execute(request)
	
	try {
		if (response.getStatusLine().getStatusCode() == 200) {
			// Get the byte response
			byte[] binaryData = EntityUtils.toByteArray(response.getEntity())
			
			// 4. Save file
			String filePath = RunConfiguration.getProjectDir() + "/raw_download_fix.png"
			FileOutputStream fos = new FileOutputStream(filePath)
			fos.write(binaryData)
			fos.close()
			
			println "Successfully! The file has been saved at: " + filePath
			println "The file size: " + binaryData.length + " bytes"
		}
	} finally {
		response.close()
	}
} finally {
	httpClient.close()
}