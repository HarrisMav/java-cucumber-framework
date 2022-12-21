Feature: Comparing Marketcap data

  Background: Check if navigation works
    Given the user navigates to "https://coinmarketcap.com"
    And the user clicks "Next" button
    And the user clicks "Got it" button
#    And the user clicks "Maybe later" button

  Scenario: Test 1
    And the user shows rows by "20"
    And the user captures Crypto data for
      | # | Name | Price | Market Cap |