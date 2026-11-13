# OrangeHRM Test Automation Framework

Selenium + Java + TestNG framework for the OrangeHRM demo site, built to learn automation.

## Tech stack
Java 17, Selenium 4, TestNG, Maven, Extent Reports, Apache POI, Selenium Grid

## Features
- Page Object Model
- Config file for URL, browser and waits
- Data-driven tests from Excel
- Parallel cross-browser runs (Chrome, Firefox)
- Screenshots on failure in Extent Report
- Run locally or on Selenium Grid (remote=true)

## How to run
mvn clean test
Report: reports/ExtentReport.html