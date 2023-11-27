package com.library.steps;

import com.library.pages.DashBoardPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class us02_Jawad_stepDef {

    DashBoardPage Boston = new DashBoardPage();
    String actualResult;
    String expectedResult;
    @When("the librarian gets borrowed books number-JD")
    public void the_librarian_gets_borrowed_books_number_jd() {
        actualResult = Boston.borrowedBooksNumber.getText();
     BrowserUtil.waitFor(2);

    }
    @Then("borrowed books number information must match with DB-JD")
    public void borrowed_books_number_information_must_match_with_db_jd() {
    String querie = "select count(*) from book_borrow where is_returned=0";

    DB_Util.runQuery(querie);
      expectedResult=  DB_Util.getFirstRowFirstColumn();
        Assert.assertEquals(expectedResult,actualResult);


    }



}
