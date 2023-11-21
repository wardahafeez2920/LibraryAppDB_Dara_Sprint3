package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.DashBoardPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class us06_Sabina_stepDef {

    DashBoardPage dashboardPage = new DashBoardPage();
    BookPage bookPage = new BookPage();


    @Given("the user navigates to {string} page-SS")
    public void the_user_navigates_to_page_ss(String moduleName) {
        // this method dynamically can navigate to provided name module
        dashboardPage.navigateModule(moduleName);
    }

    @When("the librarian click to add book-SS")
    public void the_librarian_click_to_add_book_ss() {
        bookPage.addBook.click();
    }

    @When("the librarian enter book name {string}-SS")
    public void the_librarian_enter_book_name_ss(String bookName) {
        bookPage.bookName.sendKeys(bookName);
    }

    @When("the librarian enter ISBN {string}-SS")
    public void the_librarian_enter_isbn_ss(String isbnNum) {
        bookPage.isbn.sendKeys(isbnNum);
    }

    @When("the librarian enter year {string}-SS")
    public void the_librarian_enter_year_ss(String year) {
        bookPage.year.sendKeys(year);
    }

    @When("the librarian enter author {string}-SS")
    public void the_librarian_enter_author_ss(String author) {
        bookPage.author.sendKeys(author);
    }

    @When("the librarian choose the book category {string}-SS")
    public void the_librarian_choose_the_book_category_ss(String bookCategory) {
        Select bookCatSelect = new Select(bookPage.categoryDropdown);

        for (WebElement each : bookCatSelect.getOptions()) {
            if (each.getText().equals(bookCategory)) {
                each.click();
            }
        }
        System.out.println("bookCatSelect.getFirstSelectedOption() = " + bookCatSelect.getFirstSelectedOption().getText());
    }

    @When("the librarian click to save changes-SS")
    public void the_librarian_click_to_save_changes_ss() {
        bookPage.saveChanges.click();
       // bookPage.closeBtn.click();
    }

    @Then("verify {string} message is displayed-SS")
    public void verify_message_is_displayed_ss(String msg) {
        System.out.println("bookPage.toastMessage.isDisplayed() = " + bookPage.toastMessage.isDisplayed());
    }

    @Then("verify {string} information must match with DB-SS")
    public void verify_information_must_match_with_db_ss(String bookName) {
        DB_Util.runQuery("select * from books where name = '" + bookName + "'");

        List<String> actualBookEntry = DB_Util.getRowDataAsList(1);

        System.out.println("actualBookEntry = " + actualBookEntry);

        DB_Util.runQuery("select name from books where name = '" + bookName + "'");


    }


}
