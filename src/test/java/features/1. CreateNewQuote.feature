Feature: Create new quote in Sales/Quotes

  Scenario Outline: User creates a new quote in Sales/Quotes
    Given the user logged in to the application with '<EmailAddress>' and '<Password>'
    And the user is on the Quotes page
    When the user creates a new quote with '<Description>' and '<Quantity>' and '<Individual Price>'
    Then the quote is successfully created with the correct values for '<Description>' and '<Quantity>' and '<Individual Price>'

    Examples:
      | EmailAddress                | Password | Description | Quantity | Individual Price |
      | stanemirdanstefan@gmail.com | B3x!oAG1 | new quote   | 10       | 12               |


