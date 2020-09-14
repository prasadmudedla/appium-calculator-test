package com.testCases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.factories.AndroidDriverManager;
import com.factories.AndroidDriverManagerFactory;
import com.pageObjects.CalculatorPage;
import com.util.misc.string.StringContent;

import io.appium.java_client.android.AndroidDriver;

/*
 * Before running the test case the Appium server and the mobile device must be up and running and be connected.
 */

public class CalculateEquationResultTestCase
{
	
    private String _ExpectedEquationResult=
    		"2";
    
    private String _ComputedEquationResult;
    
    private AndroidDriverManagerFactory _AndroidDriverManagerFactory;
    private AndroidDriverManager _AndroidDriverManager;
    private AndroidDriver<WebElement> _AndroidDriver;
    
    
    
    public CalculateEquationResultTestCase()
    {
    	 
        _AndroidDriverManagerFactory=
        		new AndroidDriverManagerFactory();
        
        _AndroidDriverManager=
        		new AndroidDriverManager();
    	
    	
    }

    
    
    @BeforeMethod
	public void beforeMethod()
    {
    	
		_AndroidDriverManager=
    			_AndroidDriverManagerFactory.createAndroidDriverManager();
		
    	if(_AndroidDriverManager!=null)
    	{
    		
    		_AndroidDriver=
    				_AndroidDriverManager.createAndroidDriver();
        	
    	}
    	
    }

    
    
    @AfterMethod
	public void afterMethod()
    {
    	
    	if(_AndroidDriverManager!=null)
    	{
    		
        	_AndroidDriverManager.quitAndroidDriver();
        	
    	}
    	
    }
    
    
    
	@Test()
	public void testCalculateResultOfEquation()
	{
		
		_ComputedEquationResult=
				new CalculatorPage(
						_AndroidDriver).calculateResultForEquation1Plus1();
		
		
		
		Assert.assertTrue(
				isComputedEquationResultEqualToExpectedEquationResult(),
				"Expected result: " +
				_ExpectedEquationResult +
				"\n" +
				"Computed result: " +
				_ComputedEquationResult);
		
	}
	
	
	
    private boolean isComputedEquationResultEqualToExpectedEquationResult()
    {
    	
    	boolean isComputedEquationResultEqualToExpectedEquationResult=
    			false;
    	
    	
		
		if(_ComputedEquationResult!=null)
		{
			
			isComputedEquationResultEqualToExpectedEquationResult=
					StringContent.isString1EqualToString2IgnoreCase(
							_ComputedEquationResult,
							_ExpectedEquationResult);
			
		}
		
		
		
		return isComputedEquationResultEqualToExpectedEquationResult;
    	
    }
	
}
