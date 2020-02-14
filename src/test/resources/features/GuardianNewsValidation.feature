Feature: Validation of Guardian website links and content

  @web
  Scenario: TC_001 Launch and validate contents of the Guardian homepage
    Given the user is on the guardian news website homepage
    When the user confirms the UK edition
    Then the user must be able to view the headlines for UK region

#    TODO
    @Pending
  Scenario: TC_002 Search Google to validate particular news article is geniune on Guardian website
    When the user cross checks for news from Guardian on google search
    Then the user should see matching news listings from search result