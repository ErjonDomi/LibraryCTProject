package com.LibraryCT.pages;


import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class UsersPage extends BasePage {

    Faker faker = new Faker();
    String fullName = faker.name().fullName();
    String password = faker.internet().password();
    String email = faker.internet().emailAddress();
    String startDate = faker.date().toString();
    String endDate = faker.date().toString();
    String address = faker.address().fullAddress();

    @FindBy(xpath = "//a[@class='btn btn-lg btn-outline btn-primary btn-sm']")
    public WebElement addUserLink;

    @FindBy(xpath = "//input[@name='full_name']")
    public WebElement inputFullName;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement inputPassword;

    @FindBy(xpath = "//input[@name='email']")
    public WebElement inputEmail;

    @FindBy(xpath = "//select[@id='user_group_id']")
    public WebElement userGroupDropDown;

    @FindBy(xpath = "//select[@id='status']")
    public WebElement statusDropDown;

    @FindBy(xpath = "//input[@name='start_date']")
    public WebElement inputStartDate;

    @FindBy(xpath = "//input[@name='end_date']")
    public WebElement inputEndDate;

    @FindBy(id = "address")
    public WebElement textAreaAddress;

    @FindBy(xpath = "//button[.='Save changes']")
    public WebElement submitButton;

    @FindBy(xpath = "//button[.='Close']")
    public WebElement closeButton;

    @FindBy(xpath = "(//a[@class='btn btn-primary btn-sm'])[1]")
    public WebElement editUserLink;

    @FindBy(xpath = "//table[@id='tbl_users']//td[3]")
    public List<WebElement> userTable;

    @FindBy(xpath = "//select[@id='user_status']")
    public WebElement userStatusDropDown;

    public void addUser(){
        addUserLink.click();
        inputFullName.sendKeys(fullName);
        inputPassword.sendKeys(password);
        inputEmail.sendKeys(email);
        Select userGroup = new Select(userGroupDropDown);
        userGroup.selectByValue("2");
        Select status= new Select(statusDropDown);
        status.selectByValue("ACTIVE");
        inputStartDate.sendKeys(Keys.BACK_SPACE + startDate);
        inputEndDate.sendKeys(Keys.BACK_SPACE + endDate);
        textAreaAddress.sendKeys(address);
        submitButton.click();
    }

    public boolean verifyNewUser(){
        List<String> userName = new ArrayList<>();
        for (WebElement webElement : userTable) {
            userName.add(webElement.getText());
        }
        return userName.contains(fullName);
    }

    public void close(){
        addUserLink.click();
        closeButton.click();
    }

    public void editUser(){
        editUserLink.click();
        inputFullName.clear();
        inputFullName.sendKeys( fullName);
        submitButton.click();
    }

    public List<String> userStatus(){
        Select userStatus = new Select(userStatusDropDown);
        List<WebElement> webElementList = userStatus.getOptions();
        List<String> list = new ArrayList<>();
        for (WebElement webElement : webElementList) {
            list.add(webElement.getText());
        }
        return list;
    }
}
