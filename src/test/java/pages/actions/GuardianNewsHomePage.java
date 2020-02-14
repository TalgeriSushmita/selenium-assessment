package pages.actions;

import org.junit.Assert;
import pages.objects.GuardianNewsHomePageObject;

public class GuardianNewsHomePage extends BasicPage {
    GuardianNewsHomePageObject guardianNewsHomePageObject;

    final String expectedUrl = "https://www.theguardian.com/uk?INTCMP=CE_UK";

    public GuardianNewsHomePage(GuardianNewsHomePageObject guardianNewsHomePageObject) {
        this.guardianNewsHomePageObject= guardianNewsHomePageObject;
    }

    public void clickOnEditionPicker() {
        waitFor(guardianNewsHomePageObject.editionPickerToggleButton).click();
    }

    public void verifyRightRegionNewsDisplayed() {
        Assert.assertTrue(getURL().equalsIgnoreCase(expectedUrl));
    }
}
