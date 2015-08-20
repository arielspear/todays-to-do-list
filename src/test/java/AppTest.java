import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import java.util.ArrayList;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;


public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public ClearRule clearRule = new ClearRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Task List");
  }

  @Test
  public void taskIsCreatedTest() {
      goTo("http://localhost:4567/");
      click("a", withText("Add a new task"));
      fill("#description").with("Mow the lawn");
      submit(".btn");
      assertThat(pageSource()).contains("Your task has been saved.");
    }

  @Test
  public void taskIsDisplayedTest() {
      goTo("http://localhost:4567/tasks/new");
      fill("#description").with("Mow the lawn");
      submit(".btn");
      click("a", withText("View all tasks"));
      assertThat(pageSource()).contains("Mow the lawn");

  }
   @Test
   public void multipleTasksAreDisplayedTest() {
     goTo("http://localhost:4567/tasks/new");
     fill("#description").with("Mow the lawn");
     submit(".btn");
     goTo("http://localhost:4567/tasks/new");
     fill("#description").with("Buy groceries");
     submit(".btn");
     click("a", withText("View all tasks"));
     assertThat(pageSource()).contains("Mow the lawn");
     assertThat(pageSource()).contains("Buy groceries");
   }

  @Test
  public void taskShowPageDisplaysDescription(){
    goTo("http://localhost:4567/tasks/new");
    fill("#description").with("Mow the lawn");
    submit(".btn");
    click("a", withText("View all tasks"));
    click("a", withText("Mow the lawn"));
    assertThat(pageSource()).contains("Mow the lawn");
  }

}
