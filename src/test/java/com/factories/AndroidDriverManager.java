package com.factories;

import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class AndroidDriverManager
{
	
	private DesiredCapabilities _DesiredCapabilities;
	
	private AndroidDriver<WebElement> _AndroidDriver;
	

	public AndroidDriver<WebElement> createAndroidDriver()
	{
		
		if(_AndroidDriver==null)
		{
			
			createAndroidDriverInstance();
			
		}
		
		
		
		return _AndroidDriver;
		
	}
	
	
	
	public void quitAndroidDriver()
	{
		
		if(_AndroidDriver!=null)
		{

			_AndroidDriver.quit();
			
			_AndroidDriver=
					null;
			
		}
		
	}
	
	
	
	private void createAndroidDriverInstance()
	{
		
		_AndroidDriver=
				null;

		createDesiredCapabilities();
		
		instantiateAndroidDriver();
		
	}
	
	private void createDesiredCapabilities()
	{
		
		_DesiredCapabilities=
				new DesiredCapabilities();
		
		
		
		//Device specification
		
		/*
		 * Identification is done by capability "udid".
		 * Capability "deviceName" is not necessary for identifying the device.
		 * However, if capability "deviceName" is not getting set, the Appium server throws an error.
		 * The concrete value assigned here to the capability "deviceName" is not considered by the Appium server.
		 * An arbitrary value can be chosen.
		 */
		
		_DesiredCapabilities.setCapability(
				"udid",
				"emulator-5554");
		
		_DesiredCapabilities.setCapability(
				"deviceName",
				"pixel_3");

		
		
		//Mobile operating system specification
		
		_DesiredCapabilities.setCapability(
				"platformName",
				"android");

		_DesiredCapabilities.setCapability(
				"platformVersion",
				"7.1.1");
		
		

		//App specification
		
		_DesiredCapabilities.setCapability(
				"appPackage",
				"com.android.calculator2");

		_DesiredCapabilities.setCapability(
				"appActivity",
				".Calculator");
		
	}
	
	private void instantiateAndroidDriver()
	{
		
		try
		{
			
			_AndroidDriver=
					new AndroidDriver<WebElement>(
							new URL(
									"http://127.0.0.1:4723/wd/hub"),
							_DesiredCapabilities);
			
		}
		catch(
				Exception exception)
		{
			
			exception.printStackTrace();
			
		}
		
	}
	
}
