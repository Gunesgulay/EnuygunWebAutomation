# EnuygunWebAutomation

Overview
This project automates the main user journeys of "www.enuygun.com " using Java, Selenium, and TestNG.  
It covers critical functionalities such as searching for a flight, selecting departure date, and validating user flows on the website.
All tests are developed using the Page Object Model (POM) structure for readability and maintainability.

Used Tools & Technologies
Java (JDK 21) — programming language  
Maven — build and dependency management  
Selenium WebDriver — browser automation  
TestNG — test framework and annotations  
Extent Reports — test reporting with HTML output  
Log4j2 — logging test execution steps  
Properties File — configuration management  
IntelliJ IDEA — development environment  
Git / GitHub — version control

Project Highlights
- Implemented Page Object Model (BasePage, BaseTest, HomePage, FlightListPage)
- Dynamic date selection on Enuygun’s calendar widget (supports different months and years)
- Detailed Extent Report generated after every run
- Custom Log4j2 logs for each test step
- Screenshots captured automatically for failed test cases
- Parameterized test data (departure city, destination city, dates)

You can change the browser from testng.xml files

Script for running project: mvn clean test

