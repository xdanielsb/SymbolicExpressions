import java.util.*;

public class Context {
  private HashMap<String,Double> vars = new HashMap<>();
  public Context() {
  }
  public void add(String foo, Double val) {
    vars.put(foo,val);
  }
  public void remove(String foo, Double val) {
    vars.remove(foo);
  }
  public Double get(String foo) {
    return vars.get(foo);
  }
  public void clean() {
    vars.clear();
  }
  public boolean is(String foo) {
    return vars.containsKey(foo);
  }
  public void replace(String foo, Double val) {
    vars.replace(foo,val);
  }
  public void show() {
    System.out.print("This is the context: \n");
    for(String a: vars.keySet()) {
      System.out.format("%s = %f\n",a, vars.get(a));
    }
  }
  
}
