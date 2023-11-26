@nina
Feature: As a data consumer, I want UI and DB book information are match.
  @ui @db
  Scenario: Verify book information with DB
    Given the "librarian" on the home page
    And the user navigates to "Books" page-NN
    When the user searches for "Clean Code" book-NN
    And  the user clicks edit book button-NN
    Then book information must match the Database-NN