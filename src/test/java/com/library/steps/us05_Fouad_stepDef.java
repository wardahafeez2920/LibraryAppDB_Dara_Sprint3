package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class us05_Fouad_stepDef {
    String actualMostPopularGenre;

    @Given("Establish the database connection-FT")
    public void establish_the_database_connection_ft() {

    }

    @When("I execute query to find most popular book genre-FT")
    public void i_execute_query_to_find_most_popular_book_genre_ft() {
        String query = "select name from book_categories " +
                "where id = (select book_category_id from books " +
                "where id = (select book_id from book_borrow group by book_id order by count(*) desc limit 1));";
        DB_Util.runQuery(query);
        actualMostPopularGenre = DB_Util.getFirstRowFirstColumn();
    }
    @Then("verify {string} is the most popular book genre-FT")
    public void verify_is_the_most_popular_book_genre_ft(String expectedGenre) {

        Assert.assertEquals(expectedGenre, actualMostPopularGenre);


    }
}





