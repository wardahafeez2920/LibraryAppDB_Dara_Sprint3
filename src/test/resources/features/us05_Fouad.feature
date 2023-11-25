
Feature: As a data consumer, I want to know genre of books are being borrowed the most
   @fo @db
  Scenario: verify the the common book genre that’s being borrowed
    Given Establish the database connection-FT
    When I execute query to find most popular book genre-FT
    Then verify "Fantasy" is the most popular book genre-FT