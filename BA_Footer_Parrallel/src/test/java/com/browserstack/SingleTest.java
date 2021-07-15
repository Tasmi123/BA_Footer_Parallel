package com.browserstack;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SingleTest extends BrowserStackTestNGTest {

    @Test
    public void test() throws InterruptedException, IOException {
    	String baseUrl = "http://18.134.60.157/";
		driver.get(baseUrl);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String title = driver.getTitle();
		System.out.println("Title of the page is: " + title);
		
		String currentUrl = driver.getCurrentUrl();
		System.out.println("Current URL is: " + currentUrl);
		
		WebElement footer= driver.findElement(By.className("ba-footer-link"));
		System.out.println(footer.findElements(By.tagName("a")).size()) ;
		  
		  int i = footer.findElements(By.tagName("a")).size(); //Get number of links

		  for(int j = 0;j<i;j++){    //create loop based upon number of links to traverse all links
		   footer= driver.findElement(By.className("ba-footer-link"));   // We are re-creating "footer" webelement as DOM changes after navigate.back()
		   WebElement elem =footer.findElements(By.tagName("a")).get(j);
		   js.executeScript("arguments[0].click();", elem);
		      Thread.sleep(3000);
		   System.out.println(driver.getTitle());
		      if(driver.getTitle().contains("404")) {
		       String link=driver.getCurrentUrl();;
		       System.out.println(link);
		       String filename = getRandomString(10) + ".png";
				String directory = System.getProperty("user.dir") + "//screenshots//";
				File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(sourceFile, new File(directory + filename));
				}
		        // driver.navigate().back()
		        js.executeScript("history.go(-1)");}
		   Thread.sleep(50000);
		  }
		 
	

	public static String getRandomString(int length) {
		StringBuilder sb = new StringBuilder();
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		for (int i = 0; i < length; i++) {
			int index = (int)(Math.random() * characters.length());
			sb.append(characters.charAt(index));
		}
		return sb.toString();
	}

		

}
