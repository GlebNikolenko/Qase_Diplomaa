package pages;

import com.codeborne.selenide.Condition;
import dto.Project;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class ProjectsPage extends BasePage {

    public final String PROJECT_NAME = "//a[contains(text(),'%s')]";
    public final String CREATE_NEW_PROJECT_BUTTON = "#createButton";

    @Step("Opening the 'Project Page'")
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

    @Step("Clicking on the 'Create new project' button")
    public CreateNewProjectPage clickOnCreateNewProjectButton() {
        log.info("Click button 'Create new project' by locator: " + CREATE_NEW_PROJECT_BUTTON);
        $(CREATE_NEW_PROJECT_BUTTON).click();
        return new CreateNewProjectPage();
    }

    @Step("Checking that the '{project.title}' project is existed")
    public ProjectsPage verifyIsProjectExist(Project project) {
        log.info("Checking that the project '{}' is existed", project.getTitle());
        $x(String.format(PROJECT_NAME, project.getTitle())).shouldBe(Condition.visible);
        return this;
    }

    @Step("Checking that the '{project.title}' project is deleted")
    public ProjectsPage verifyIfProjectDelete(Project project) {
        log.info("Checking that the project '{}' is deleted", project.getTitle());
        $x(String.format(PROJECT_NAME, project.getTitle())).shouldNotBe(Condition.visible);
        return this;
    }

    @Step("Validate that 'Project page' was opened")
    public boolean isProjectsPageOpened() {
        log.info("Checking if the Projects page is opened");
        return $(CREATE_NEW_PROJECT_BUTTON).isDisplayed();
    }

    @Step("Choose project by '{project.title}' name and click on it")
    public RepositoryPage chooseProjectByName(Project project) {
        log.info("Choose project by name '{}' and click on it", project.getTitle());
        $x(String.format(PROJECT_NAME, project.getTitle())).shouldBe(Condition.visible).click();
        return new RepositoryPage();
    }

}
