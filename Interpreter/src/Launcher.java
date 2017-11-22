public class Launcher {
  public static void main(String args[]) {
    String tests[]={
        "(a*b-(a^c)^a*4)",
        "(1*(4+2)-9)+(1*3)+8+a/b*c",
        "1&2+8%2+p",
        "1&2&3&4&5+1&2",
        "1++2",
        "((2+5",
        "1+3+b&43423^1&1"
    };
    Context c=new Context();
    c.add("a",1.0);
    c.add("b",3.0);
    c.add("c",2.0);
    
    for (String s:tests) {
      Expresion ex=new Evaluator(s);
      System.out.print("\n\t Statement: "+ex);
      try {
        double res=ex.evaluate(c);
        System.out.format(" = %.2f\n",res);
      }catch(Exception e) {
      }
    }
  }
}
