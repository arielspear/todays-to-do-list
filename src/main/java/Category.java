import java.util.ArrayList;

public class Category {
  private static ArrayList<Category> categories = new ArrayList<Category>();

  private String mName;
  private int mId;
  private ArrayList<Task> mTasks;


  public Category(String name) {
    mName = name;
    categories.add(this);
    mId = categories.size();
    mTasks = new ArrayList<Task>();
  }

  public String getName() {
    return mName;
  }

  public int getId() {
    return mId;
  }

  public static ArrayList<Category> all() {
    return categories;
  }

  public ArrayList<Task> getTasks() {
    return mTasks;
  }

  public static void clear() {
    categories.clear();
  }

  public void addTask(Task task) {
    mTasks.add(task);
  }

  public static Category find(int id) {
    try {
      return categories.get(id - 1);
    } catch (IndexOutOfBoundsException e) {
      return null;
    }
  }
}
