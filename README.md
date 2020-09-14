# Automated Mobile App Test with Appium
## Test Case: Calculator Operation

##### Table of Contents
- [Testing Paradigms / Test Setup Specifications](#testing-paradigms--test-setup-specifications)
- [System / Software Environment](#system--software-environment)
  - [Third Party Software](#third-party-software)
  - [Execution / Deployment](#execution--deployment)
  - [General Notes](#general-notes)
- [User Guide](#user-guide)
  - [Test Case](#test-case)
  - [Implementation](#implementation)
  - [Execution / Deployment](#execution--deployment-1)

## Testing Paradigms / Test Setup Specifications

- automated testing
- mobile application software (App) testing
- distributed test execution (Appium server)

## System / Software Environment

### Third Party Software

#### Programming Language

- Java

#### Computing Platform

- JDK 1.8.0_121 (32 bit, 64 bit)

#### App Automation Frameworks

- Selenium 3.4.0
- Appium Java-Client 5.0.0-BETA8

#### Test Distribution Framework

- Appium Desktop 1.1.0-beta.1

#### Testing Framework

- TestNG 6.11

#### Mobile Application Software

- Calculator 4.4.2 (Android system app)

#### Mobile Operating System

- Android 4.4.2

#### Build / Deployment Tools

- Eclipse Neon
- Maven 3.5.0

#### Operating Systems (OS)

- Windows 10 (64 bit)

### Execution / Deployment

- Java Application
- TestNG Suite
- Maven build

### General Notes

#### Third Party Software Files

The third party software files are not part of this project's file repository. However, the project's setup contains notations of paths and file names of that software to be used for the project setup. With the help of these notations, after obtaining the files, a project user can place them in the respective directories.

#### File Directory Structure

The file directories are set up in a way to facilitate the use of Maven.

Within the "resources" directory there are two subdirectories:

- "int"

"int" stands for "internal". This directory contains all the files whose content is customized by the project user and which are therefore related to the internal setup of the project.

#### Utility Files

This projects java implementations contain a number of files serving as utilities for common tasks that are not explicitly related to the specific test scope. Therefore, in this documentation, these files are only described more in detail if relevant for the understanding of the test topic.

## User Guide

### Test Case

The project comprehends one single test case.

#### Test Sequence

1. start Calculator app
2. enter mathematical equation
3. let equation result be calculated
4. retrieve equation result from app
5. execute test assert

#### Test Assert Textual Phrasing

Variables:

- `A`: computed equation result
- `X`: expected equation result

Assert:

_Computed equation result `A` is equal to expected equation result `X`._

The concrete test assert implementation is given in a different segment of this documentation.

### Implementation

The project contains one single test class `CalculateEquationResultTestCase` which again contains one single test method `testCalculateResultOfEquation`.

#### Test Data

#### *Common Test Data*

`CalculateEquationResultTestCase` contains test data:

- expected equation result `X` for assert

```
String _ExpectedEquationResult=
	"2"
```

The variable `_ExpectedEquationResult` is of data type `String`. This allows the test to be adapted for other test cases that expect a non-numerical equation result, i.e. when testing for division by zero.

#### Test class `CalculateEquationResultTestCase`

The `beforeMethod` takes care of instantiating a `AndroidDriverManagerFactory` which is used to retrieve a `AndroidDriver` instance.

```
_AndroidDriverManager=
	_AndroidDriverManagerFactory.createAndroidDriverManager();
	
	if(_AndroidDriverManager!=null)
	{
		
		_AndroidDriver=
			_AndroidDriverManager.createAndroidDriver();
		
	}
```

The test method `testCalculateResultOfEquation` manages the realization of the test sequence.

First, the equation gets entered. Then the result is being calculated and retrieved.
```
_ComputedEquationResult=
	new CalculatorPage(
		_AndroidDriver).calculateResultForEquation1Plus1();
```

The variable `_ComputedEquationResult` is of data type `String` for the same reasons as the variable `_ExpectedEquationResult`, which are described in a different segment of this documentation.

In the end the assertion is called. A specific error message is set for when an assertion error gets thrown.

```
Assert.assertTrue(
	isComputedEquationResultEqualToExpectedEquationResult(),
	"Expected result: " +
	_ExpectedEquationResult +
	"\n" +
	"Computed result: " +
	_ComputedEquationResult);
```

The auxiliary method `isComputedEquationResultEqualToExpectedEquationResult` determines if the computed equation result is equal to the expected equation result. This value gets used by the assert.

The `afterMethod` takes care of closing the Calculator app.

#### `AndroidDriver` Handling Overview

Browser Executable and `AndroidDriver` handling in this project happens in a three level system.

1. `AndroidDriverManagerFactory`
2. `AndroidDriverManager`
4. Appium server

The `AndroidDriverManagerFactory` instantiates the `AndroidDriverManager`.

This project setup works on the basis of distributed test execution, based on a Appium server. The project setup contains a server environment working on a single computer - i.e. without a physical server architecture. The Appium server and the mobile device are being run and connected on the localhost. This setup is supposed to demonstrate how distributed test execution is implemented under Appium server. The project's scope in this regard is mainly on the actual test execution setup. Other server related topics, like network settings, are not within that scope.

#### *Appium server setup*

Before any 'AndroidDriver' can be instantiated, the Appium server and the mobile device must be up and running. The mobile device needs to be connected to the Appium server.

*Appium Server*

The Appium server is started by executing the Appium desktop application and following the steps indicated there.

*Mobile Device*

A mobile device needs to be running and be available for the Appium server. Setting up and connecting the device to the server can be achieved in a variety of different ways. The mobile device setup is not within the scope of this documentation.

#### *`AndroidDriver` Handling Overview for `AndroidDriverManager`*

Instantiation of the `AndroidDriver` is handled in `AndroidDriverManager`.

#### `DesiredCapabilities` Handling

The `AndroidDriverManager` class sets values in the `DesiredCapabilities`. The `DesiredCapabilities` set here can be grouped into three categories:

- device specification
- mobile operating system specification
- app specification

```
_DesiredCapabilities=
	new DesiredCapabilities();
				
//Device specification

_DesiredCapabilities.setCapability(
	"udid",
	"emulator-5554");

_DesiredCapabilities.setCapability(
	"deviceName",
	".");

//Mobile operating system specification

_DesiredCapabilities.setCapability(
	"platformName",
	"Android");

_DesiredCapabilities.setCapability(
	"platformVersion",
	"4.4");

//App specification

_DesiredCapabilities.setCapability(
	"appPackage",
	"com.android.calculator2");

_DesiredCapabilities.setCapability(
	"appActivity",
	".Calculator");
```

The device specification serves the purpose of identifying the mobile device to be used for the test in case there is more than one mobile device connected to the Appium server. The identification is achieved by setting the `DesiredCapabilities` capability `udid`. The capability `deviceName` is not necessary for identifying the device. However, if this capability is not getting set, the Appium server throws an error when executing the test. Therefore, the capability `deviceName` must be set. The concrete value assigned to it here is not considered by the Appium server. An arbitrary value can be chosen.

#### `AndroidDriver` handling details

The `AndroidDriver`'s constructor takes the URL of the Appium server and the `DesiredCapabilities` as arguments.

```
_AndroidDriver=
	new AndroidDriver<WebElement>(
		new URL(
			"http://127.0.0.1:4723/wd/hub"),
		_DesiredCapabilities);
```

#### App Automation

Following are the details of the app automation parts of this project's specific test case described in a different segment of this documentation.

#### *Page Objects*

The test case covers one user interface:

- Calculator main screen

For implementation of the `CalculatorPage` class the page object pattern was applied.

##### *`PageObject`* class

`PageObject` is an abstract class implementing common behaviour of all `[...]Page` classes.

##### *`CalculatorPage`* class

The `CalculatorPage` class represents the Calculator app main screen. Following are the web elements relevant to the test case:

- digit "1" button
- "plus" sign button
- "equals" sign button
- equation text field

```
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
```

#### *Test Sequence App Automation*

The test sequence is executed by the `beforeMethod` and `testCalculateResultOfEquation` methods of the `CalculateEquationResultTestCase` class. Following are the test sequence parts concerning app automation.

*`beforeMethod`*
```
_AndroidDriver=
	_AndroidDriverManager.createAndroidDriver();
```

*`testCalculateResultOfEquation`*
```
_ComputedEquationResult=
	new CalculatorPage(
		_AndroidDriver).calculateResultForEquation1Plus1();
```

##### *Start App*

The Calculator app is getting started by instantiating the `AndroidDriver` in the `AndroidDriverManager` class.

```
_AndroidDriver=
	_AndroidDriverManager.createAndroidDriver();
```

The implementation for instantiating the `AndroidDriver` is shown in a different segment of this documentation.

##### *Enter Equation, Calculate & Retrieve Result*

```
_ComputedEquationResult=
	new CalculatorPage(
		_AndroidDriver).calculateResultForEquation1Plus1();
```

First the buttons for digit "1", "plus", again digit "1" and finally "equals" symbol are clicked in order. The computed result is retrieved from the calculators equation text field. The sequence of these steps is defined in the `CalculatorPage` class.

```
public String calculateResultForEquation1Plus1()
{
	
	return clickDigit1Button().clickPlusSignButton().clickDigit1Button().clickEqualsSignButton().getEquationTextFieldText();
	
}
```

The concrete actions are implemented in the `WebElementActions` class.

```
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
```

#### Test Assert Implementation

The test assert is executed by the `testCalculateResultOfEquation` method of the `CalculateEquationResultTestCase` class.

The test assert textual phrasing is given in a different segment of this documentation.

The assert states that the computed equation result `A` is equal to expected equation result `X`.

The value of `X` is set in the `CalculateEquationResultTestCase` class as `_ExpectedEquationResult`.

The boolean value indicating whether or not the the computed equation result is equal to the expected equation result needs to be retrieved for the assert. This is done by the auxiliary method `isComputedEquationResultEqualToExpectedEquationResult`.

```
Assert.assertTrue(
	isComputedEquationResultEqualToExpectedEquationResult(),
	"Expected result: " +
	_ExpectedEquationResult +
	"\n" +
	"Computed result: " +
	_ComputedEquationResult);
```

### Execution / Deployment

#### Java Application

For convenience the project contains the runnable java class `Launcher`. Its purpose is to initiate test execution directly from a java application.

Test execution is prepared by instantiating a `TestNG` object and setting the `CalculateEquationResultTestCase.xml` TestNG XML as the reference file for the test. Test execution is then started by calling the `TestNG` `run` method.

```
TestNG testNG=
	new TestNG();

List<String> suiteList=
	Lists.newArrayList();

suiteList.add(
	PathRetrieve.retrieveAbsolutePathStringFromRelativePathString(
		this,
		"/int/testng/xml/CalculateEquationResultTestCase.xml"));

testNG.setTestSuites(
	suiteList);

testNG.run();
```

Additionally user information about the application status is displayed.

```
InfoAlert infoAlert=
	new InfoAlert();

infoAlert.createAlert(
	"Executing Test[...]");

[...]

infoAlert.closeAlert();

infoAlert.createAlert(
	"Showing Test Results[...]");

[...]

infoAlert.closeAlert();
```

After test execution the test results are being opened in a browser window.

```
BrowserShowFile.showFileInBrowser(
	".\\test-output\\emailable-report.html");
```

#### TestNG Suite

`CalculateEquationResultTestCase.xml` is the TestNG XML file which can be run directly.

#### Maven build

The projects directory structure is suited to be used with a Maven build. `pom.xml` is the respective Maven XML file. The Maven build can be used to compile the source, copy the resources and run the test by using the Maven goal `test`.
