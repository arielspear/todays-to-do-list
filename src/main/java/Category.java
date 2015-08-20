import java.util.ArrayList;

public class Category {
  private static ArrayList<Category> categories = new ArrayList<Category>();

  private String mName;
  private int mId;


  public Category(String name) {
    mName = name;
    categories.add(this);
    mId = categories.size();
  }

  public String getName() {
    return mName;
  }





  // public static Category find(int id) {
  //   try {
  //     return instances.get(id - 1);
  //   } catch (IndexOutOfBoundsException exception) {
  //     return null;
  //   }
  // }
}
