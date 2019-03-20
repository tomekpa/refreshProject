package cucumberTests;

import cucumber.api.java.en.When;

public class SimpleDomainSteps {

    @When("^Some other step")
    public void i_ask_whether_it_s_Friday_yet() {
        System.out.println("Some other step");
    }
}
