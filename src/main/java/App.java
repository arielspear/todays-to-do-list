import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;

public class App {
  public static void main(String[] args) {
  staticFileLocation("/public");
  String layout = "templates/layout.vtl";

  get("/", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("tasks", request.session().attribute("tasks"));
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/tasks", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("tasks", Task.all());
    model.put("template", "templates/tasks.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("tasks/new", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("template", "templates/category-tasks-form.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  // post("/tasks", (request, response) -> {
  //   HashMap<String, Object> model = new HashMap<String, Object>();
  //   String description = request.queryParams("description");
  //   Task newTask = new Task(description);
  //   model.put("task", newTask);
  //   model.put("template", "templates/success.vtl");
  //   return new ModelAndView(model, layout);
  // },  new VelocityTemplateEngine());

  post("/tasks", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Category category = Category.find(Integer.parseInt(request.queryParams("categoryId")));

    String description = request.queryParams("description");
    Task newTask = new Task(description);
    category.addTask(newTask);
    //category.addTask(newTask);

    model.put("tasks", category.getTasks());
    model.put("category", category);
    model.put("template", "templates/category.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

 get("/tasks/:id", (request, response) -> {
   HashMap<String, Object> model = new HashMap<String, Object>();
   Task task = Task.find(Integer.parseInt(request.params(":id")));
   model.put("task", task);
   model.put("template", "templates/task.vtl");
   return new ModelAndView(model, layout);
 }, new VelocityTemplateEngine());

  get("/categories", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("categories", Category.all());
    model.put("template", "templates/categories.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("categories/new", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("template", "templates/category-form.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/categories", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    String name = request.queryParams("name");
    Category newCategory = new Category(name);
    model.put("category", newCategory);
    model.put("template", "templates/success.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/categories/:id", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Category category = Category.find(Integer.parseInt(request.params(":id")));
    model.put("category", category);

    model.put("template", "templates/category.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/categories/:id/tasks/new", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Category category = Category.find(Integer.parseInt(request.params(":id")));
    ArrayList<Task> tasks = category.getTasks();

    model.put("category", category);
    model.put("tasks", tasks);
    model.put("template", "templates/category-tasks-form.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());


 }
}
