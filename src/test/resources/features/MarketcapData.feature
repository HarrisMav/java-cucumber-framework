Feature: Comparing Marketcap data

  Background: Check if navigation works
    Given the user navigates to "https://coinmarketcap.com"
    And the user clicks "Next" button
    And the user clicks "Got it" button

  Scenario: Test 1
    And the user shows rows by "20"
    And the user captures Crypto data for 20 rows with headers
      | # | Name | Price | Market Cap |
    When the user clicks "Filters" button
    And the user clicks "Algorithm" filter button
    And the user selects "PoW" list item
    And the user clicks "+ Add Filter" filter button
    And the user clicks "mineable" slider
    And the user clicks "All Cryptocurrencies" from more filters
    And the user clicks "Coins" menu button
    And the user clicks "Price" from more filters
    And the user sets manual price range from "100" to "10000"
    And the user clicks "Apply Filter" button
    And the user clicks "Show results" button
    Then compare the newly filtered results with the first set for 20 rows
      | # | Name | Price | Market Cap |