package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.actions.GuardianNewsHomePage;

import static junit.framework.TestCase.assertTrue;

public class GuardianNewsHomePageStepDefs {

    private GuardianNewsHomePage guardianNewsHomePage;

    public GuardianNewsHomePageStepDefs(GuardianNewsHomePage guardianNewsHomePage) {
        this.guardianNewsHomePage = guardianNewsHomePage;
    }

    @Given("the user is on the guardian news website homepage")
    public void theUserIsOnTheGuardianNewsWebsiteHomepage() {
        assertTrue("News page loaded and all checks validated", guardianNewsHomePage.isDisplayed());
    }

    @When("the user confirms the UK edition")
    public void theUserConfirmsTheUKEdition() {
        guardianNewsHomePage.clickOnEditionPicker();
    }

    @Then("the user must be able to view the headlines for UK region")
    public void theUserMustBeAbleToViewTheHeadlinesForUKRegion() {
        guardianNewsHomePage.verifyRightRegionNewsDisplayed();
    }
}
