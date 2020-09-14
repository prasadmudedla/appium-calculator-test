package com.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.util.webElement.WebElementActions;

import io.appium.java_client.android.AndroidDriver;

public class CalculatorPage extends PageObject
{
	
	@FindBy(
			id=
				"digit1")
	private WebElement _Digit1Button;
	
	@FindBy(
			id=
				"plus")
	private WebElement _PlusSignButton;
	
	@FindBy(
			id=
				"equal")
	private WebElement _EqualsSignButton;
	
	@FindBy(
			xpath=
			"//*[@resource-id='com.android.calculator2:id/display']/android.widget.EditText")
	private WebElement _EquationTextField;
	
	
	
	public CalculatorPage(
			AndroidDriver<WebElement> androidDriver)
	{
		
		super(
				androidDriver);
		
	}
	
	
	
	public String calculateResultForEquation1Plus1()
	{
		
		return clickDigit1Button().clickPlusSignButton().clickDigit1Button().clickEqualsSignButton().getEquationTextFieldText();
		
	}
	
	
	
	private CalculatorPage clickDigit1Button()
	{
		
		WebElementActions.click(
	    		_Digit1Button);
		
		
		
		return this;
		
	}
	
	
	
	private CalculatorPage clickPlusSignButton()
	{
		
		WebElementActions.click(
	    		_PlusSignButton);
		
		
		
		return this;
		
	}
	
	
	
	private CalculatorPage clickEqualsSignButton()
	{
		
		WebElementActions.click(
	    		_EqualsSignButton);
		
		
		
		return this;
		
	}
	
	
	
	private String getEquationTextFieldText()
	{
		
		return WebElementActions.getText(
	    		_EquationTextField);
		
	}
	
}
