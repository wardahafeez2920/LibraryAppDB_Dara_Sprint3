package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class us05_Zulpikar_stepDef {

    String actualName;

    @Given("Establish the database connection-ZA")
    public void establish_the_database_connection_za() {

        // making DB connection is done by hooks class
        System.out.println("-------------------------------------");
        System.out.println("DB connection is done by hooks class");
        System.out.println("-------------------------------------");

    }

    @When("I execute query to find most popular book genre-ZA")
    public void i_execute_query_to_find_most_popular_book_genre_za() {

        String query = "select bc.name, count(*)\n" +
                "from book_borrow bb\n" +
                "         inner join books b on bb.book_id = b.id\n" +
                "         inner join book_categories bc on b.book_category_id = bc.id\n" +
                "group by name\n" +
                "order by 2 desc;";

        DB_Util.runQuery(query);
        this.actualName = DB_Util.getFirstRowFirstColumn();
        System.out.println("Name of the most popular book genre = " + actualName);
    }



    @Then("verify {string} is the most popular book genre-ZA")
    public void verify_is_the_most_popular_book_genre_za(String string) {

        Assert.assertEquals(string, actualName);


        // closing DB connection is done by hooks class
        System.out.println("-------------------------------------");
        System.out.println("Closing DB connection is done by hooks class");
        System.out.println("-------------------------------------");
    }

}
