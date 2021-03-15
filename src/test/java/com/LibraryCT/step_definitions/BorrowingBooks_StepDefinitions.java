package com.LibraryCT.step_definitions;

import com.LibraryCT.pages.BooksPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class BorrowingBooks_StepDefinitions {

    BooksPage booksPage = new BooksPage();

    @When("the user click Borrowing Books module")
    public void the_user_click_borrowing_books_module() {
        booksPage.borrowingBooksLink.click();
    }

    @Then("the user should see the following column names:")
    public void the_user_should_see_the_following_column_names(List<String> expectedHeaderList) {
        Assert.assertEquals(booksPage.borrowingBooksHeadersList(), expectedHeaderList);
    }
}
