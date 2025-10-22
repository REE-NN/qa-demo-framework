package ui.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.BaseUiTest;
import ui.pages.AutomationPage;

@Epic("UI Tests")
@Feature("UltimateQA")
@Story("Navigation to Simple Elements page")
@Owner("Ekaterina QA")
public class UiSmokeTest extends BaseUiTest {

    @Test
    @DisplayName("Главная Restful-Booker открывается")
    void openHomeAndAssertWelcome() {
        new AutomationPage()
                .open()
                .assertWelcome();
    }
}
