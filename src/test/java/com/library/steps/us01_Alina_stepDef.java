package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class us01_Alina_stepDef {

    String actualUserCount;
    List<String> expectedColumnNames;
    @Given("Establish the database connection-AA")
    public void establish_the_database_connection_aa() {

    }

    @When("Execute query to get all IDs from users-AA")
    public void execute_query_to_get_all_i_ds_from_users_aa() {

        String query = "select count(id) from users;";
        DB_Util.runQuery(query);
        actualUserCount = DB_Util.getFirstRowFirstColumn();
    }
    @Then("verify all users has unique ID-AA")
    public void verify_all_users_has_unique_id_aa() {
        String query = "select count(distinct id) from users;";
        DB_Util.runQuery(query);

        String expectedUserCount = DB_Util.getFirstRowFirstColumn();
        Assert.assertEquals(expectedUserCount,actualUserCount);
    }

    @When("Execute query to get all columns-AA")
    public void execute_query_to_get_all_columns_aa() {
        String query = "select * from users;";
        DB_Util.runQuery(query);
        expectedColumnNames = DB_Util.getAllColumnNamesAsList();
    }

    @Then("verify the below columns are listed in result-AA")
    public void verify_the_below_columns_are_listed_in_result_aa(List<String> actualColumnNames) {

        Assert.assertEquals(expectedColumnNames,actualColumnNames);
        // throw new io.cucumber.java.PendingException();
    }


}
