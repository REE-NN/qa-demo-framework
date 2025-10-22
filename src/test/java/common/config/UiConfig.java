package common.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "system:properties",
        "classpath:test.properties"
})
public interface UiConfig extends Config {
    @Key("ui.base.url")      @DefaultValue("https://ultimateqa.com")
    String uiBaseUrl();

    @Key("ui.browser")       @DefaultValue("chrome")
    String browser();

    @Key("ui.headless")      @DefaultValue("true")
    boolean headless();

    @Key("ui.timeout.ms")    @DefaultValue("6000")
    long timeoutMs();
}
