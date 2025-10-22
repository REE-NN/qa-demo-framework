package ui;

import com.codeborne.selenide.Configuration;
import common.config.ConfigLoader;
import common.config.UiConfig;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseUiTest {
    protected static UiConfig ui;

    @BeforeAll
    static void setUpUi() {
        ui = ConfigLoader.create(UiConfig.class); // см. метод ниже
        Configuration.baseUrl = ui.uiBaseUrl();
        Configuration.browser = ui.browser();
        Configuration.headless = ui.headless();
        Configuration.timeout = ui.timeoutMs();
        Configuration.pageLoadStrategy = "eager";
        Configuration.savePageSource = false;
        Configuration.screenshots = false;
    }
}
