package com.util.webElement;

import org.openqa.selenium.WebElement;

public class WebElementActions
{
	
	public static void click(
			WebElement webElement)
	{
		
		if(webElement!=null)
		{
		    
			webElement.click();
			
		}
		
	}
	
	
	
	public static String getText(
			WebElement webElement)
	{
		
		String text=
				null;
		
		
		
		if(webElement!=null)
		{
			
			text=
					webElement.getText();
			
		}
		
		
		
		return text;
		
	}
	
}
