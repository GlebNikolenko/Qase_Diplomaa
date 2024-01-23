package pages;

import com.codeborne.selenide.Condition;
import dto.Project;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;

@Log4j2
public class ProjectsPage {

    public final String PROJECT_NAME = "//a[contains(text(),'%s')]";
    public final String CREATE_NEW_PROJECT_BUTTON = "#createButton";

    @Step("Open 'Project Page'")
    public ProjectsPage openPage() {
        log.info("Open 'Project Page' by link: " + baseUrl + "/login");
        open("/projects");
        return this;
    }

    @Step("Waiting till the project page is opened")
    public ProjectsPage waitTillOpened() {
        $(CREATE_NEW_PROJECT_BUTTON).shouldBe(Condition.visible);
        return this;
    }

    @Step("Click on 'Create new project' button")
    public CreateNewProjectPage clickOnCreateNewProjectButton() {
        log.info("Click button 'Create new project' by locator: " + CREATE_NEW_PROJECT_BUTTON);
        $(CREATE_NEW_PROJECT_BUTTON).click();
        return new CreateNewProjectPage();
    }

    public ProjectsPage verifyIsProjectExist(Project project) {
        $x(String.format(PROJECT_NAME, project.getProjectName())).shouldBe(Condition.visible);
        return this;
    }


    public void isRepositoryCreated() {
        assertEquals("BLA repository", $(By.xpath("//*[text()=' repository']")).getText());
    }

}