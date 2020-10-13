#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@activity1_2
Feature: Searching for jobs using XPath

Scenario: Searching for jobs and applying to them using XPath
    Given Open browser with Alchemy Jobs site​ and navigate to Jobs page
    And Find the Keywords search input field
    Then Type in keywords to search for jobs and change the Job type
    And Find the filter using XPath and filter job type to show only Full Time jobs
    Then Find a job listing using XPath and it to see job details
    And Find the title of the job listing using XPath and print it to the console
    Then Find and Click on the “Apply for job” button
    And Close the Browser