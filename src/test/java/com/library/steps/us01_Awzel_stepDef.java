package com.library.steps;

import com.library.pages.DashBoardPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class us01_Awzel_stepDef {

    String actualUserCount;
    List<String> expectedColumnNames;
    DashBoardPage dashBoardPage = new DashBoardPage();

    @Given("Establish the database connection-AZ")
    public void establish_the_database_connection_az() {
        //DB_Util.createConnection();
        // DB connected through hooks class
//        System.out.println("---------------------------------------");
//        System.out.println("-------DB CONNECTED VIA HOOKS----------");
//        System.out.println("---------------------------------------");

    }
    @When("Execute query to get all IDs from users-AZ")
    public void execute_query_to_get_all_i_ds_from_users_az() {

        String query = "select count(id) from users;";
        DB_Util.runQuery(query);

        actualUserCount = DB_Util.getFirstRowFirstColumn();

    }
    @Then("verify all users has unique ID-AZ")
    public void verify_all_users_has_unique_id_az() {
        String query = "select count(distinct id) from users;";
        DB_Util.runQuery(query);

        String expectedUserCount = DB_Util.getFirstRowFirstColumn();
        //System.out.println(expectedUserCount);
        Assert.assertEquals(expectedUserCount,actualUserCount);

        //DB_Util.destroy();
        //Db closed from Hooks class
//        System.out.println("------------------------------------------");
//        System.out.println("-------DB DISCONNECTED VIA HOOKS----------");
//        System.out.println("------------------------------------------");
    }

    @When("Execute query to get all columns-AZ")
    public void execute_query_to_get_all_columns_az() {

        String query = "select * from users;";
        DB_Util.runQuery(query);

        expectedColumnNames = DB_Util.getAllColumnNamesAsList();

    }
    @Then("verify the below columns are listed in result-AZ")
    public void verify_the_below_columns_are_listed_in_result_az(List<String> actualColumnNames) {

        Assert.assertEquals(expectedColumnNames,actualColumnNames);

    }

}
