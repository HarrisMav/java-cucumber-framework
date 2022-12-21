Feature: Test feature

  Trying to see if the project setup works

  Background: Check if navigation works
    Given the user navigates to "https://coinmarketcap.com"
    And the user clicks "Next" button
    And the user clicks "Got it" button

  Scenario: Test 1
    And the user shows rows by "20"