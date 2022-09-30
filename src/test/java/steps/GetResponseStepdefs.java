package steps;

import com.microsoft.playwright.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tasks.UserTask;

import java.util.List;
import java.util.Map;

public class GetResponseStepdefs {
    private Playwright playwright;
    private Browser browser;
    private Page page;
    private UserTask userTask;
    private String name;
    private String pass;
    @Given("User login page")
    public void userLoginPage() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate("https://www.updiagram.com/login");
        String locatorUsername="#login-form > div > div > form > input:nth-child(1)";
        String locatorPassword="#login-form > div > div > form > input:nth-child(2)";
        String buttonLogin="#login-form > div > div > form > div > button";
        String username="buiduythanh270801@gmail.com";
        String password="Thanh270801";
        page.fill(locatorUsername,username);
        page.fill(locatorPassword,password);
        page.click(buttonLogin);
    }
    @When("User get reponse")
    public void userGetReponse(DataTable dataTable) {
        page.waitForURL("https://www.updiagram.com/app/user/dashboard");
        Response res = page.waitForResponse(response -> "https://www.updiagram.com/api/users/eac03580-3b24-11ed-8221-614681a0688f".equals(response.url()) && response.status() == 200, () -> {
            page.reload();
        });
        System.out.println("response"+res.text());
        List<Map<String,String>> rows=dataTable.asMaps(String.class,String.class);
        for (Map<String,String> attachment:rows)
        {
            name=attachment.get("username");
            pass=attachment.get("password");
        }
        System.out.println("username"+name);
    }

    @Then("User get data")
    public void userGetData() {
    }


    @Then("User get data {string},{string}")
    public void userGetData(String abc, String xyz) {
        System.out.println("data 1"+abc);
        System.out.println("data 2"+xyz);
    }
}
