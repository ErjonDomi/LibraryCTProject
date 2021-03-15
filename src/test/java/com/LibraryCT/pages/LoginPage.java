package com.LibraryCT.pages;


import com.LibraryCT.utilities.ConfigurationReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id="inputEmail")
    public WebElement inputEmail;

    @FindBy(id="inputPassword")
    public WebElement inputPassword;

    @FindBy(xpath="//button[@type='submit']")
    public WebElement submitButton;

    public void logInAsStudent(){
        inputEmail.sendKeys(ConfigurationReader.getProperty("student_email"));
        inputPassword.sendKeys(ConfigurationReader.getProperty("student_password"));
        submitButton.click();
    }

    public void logInAsLibrarian(){
        inputEmail.sendKeys(ConfigurationReader.getProperty("librarian_email"));
        inputPassword.sendKeys(ConfigurationReader.getProperty("librarian_password"));
        submitButton.click();
    }



}
