Feature: Change status of a quote

  Scenario Outline: User changes status of a quote from draft to pending
    Given the user logged in to the application with "<EmailAddress>" and "<Password>"
    And the user is on the Quotes page
    And there is a quote in "<QuoteState>"
    When the user marks the quote as "<NewState>"
    Then the quote status should change to "<NewState>"

    Examples:

      |EmailAddress|Password|QuoteState|NewState|
      |stanemirdanstefan@gmail.com|B3x!oAG1|Draft|Pending|
