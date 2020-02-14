package enums;

public enum Browsers {

    CHROME("CHROME"),
    FIREFOX("FIREFOX)"),
    SAFARI("SAFARI");

    private final String browser;

    Browsers(final String browser) {
        this.browser = browser;
    }

    public String getBrowser() {
        return browser;
    }

}
