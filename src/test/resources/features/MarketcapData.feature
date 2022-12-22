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
    And debug
    #    And the user clicks "Show results" button