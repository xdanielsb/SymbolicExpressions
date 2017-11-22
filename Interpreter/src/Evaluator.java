import java.util.*;

public class Evaluator extends Expresion {
  private static Stack<Double> values=new Stack<Double>();
  private static Stack<Character> ops=new Stack<Character>();
  private String expr;
  public Evaluator(String expr) {
    this.expr=expr;
  }
  @Override
  public Double evaluate(Context c) throws Exception{
    ops.clear();
    values.clear();
    char e[]=expr.toCharArray();
    int len=e.length;
    for (int i=0; i<len; i++) {
      if (' '==e[i]) continue;
      else if (Character.isLetter(e[i])) {
        if(c.is(e[i]+"")) values.push(c.get(e[i]+""));
        else {
          throw new Exception(" -> Bad expresion, unknown identifier '"+e[i]+"'\n");
        }
      }
      else if (Character.isDigit(e[i])) {
        String num="";
        while (true) {
          num+=e[i];
          if (i<len-1&&Character.isDigit(e[i+1])) i++;
          else break;
        }
        values.push(Double.parseDouble(num));
      }
      else if ('('==e[i]) {
        ops.push('(');
      }
      else if (')'==e[i]) {
        while ('('!=ops.peek())
          process(c);
        ops.pop();
      }
      else if (isOperator(e[i])) {
        while (!ops.empty()&&hasPrecedence(e[i],ops.peek())) {
          process(c);
        }
        ops.push(e[i]);
      }
    }
    while (!ops.empty())
      process(c);
    return values.pop();
  }

  public static double apply(double b, double a, char op, Context c) throws Exception {
    Expresion aux;
    if ('+'==op) aux=new AddExpresion(a,b);
    else if ('*'==op) aux=new MulExpresion(a,b);
    else if ('/'==op) aux=new DivExpresion(a,b);
    else if ('-'==op) aux=new SubExpresion(a,b);
    else if ('%'==op) aux=new ModExpresion(a,b);
    else if ('&'==op) aux=new ConcExpresion(a,b);
    else if ('^'==op) aux=new ExpoExpresion(a,b);
    else {
      throw new Exception("The operation "+op+"  is not defined");
    }
    // System.out.format("\n %f %c %f",a, op, b);
    return aux.evaluate(c);
  }
  public static void process(Context c) throws Exception {
    if(values.size()<=1) {
      throw new Exception(" Bad Expression.");
    }
    values.push(apply(values.pop(),values.pop(),ops.pop(),c));
  }
  public static boolean isOperator(char a) {
    if ('*'==a||'/'==a||'-'==a||'+'==a||'%'==a||'&'==a||'^'==a) return true;
    return false;
  }
  public boolean hasPrecedence(char a, char b) {
    if ('('==b||')'==b) return false;
    else if ((a=='^'|| a=='&' || a=='%')&&(b=='+'||b=='-'||b=='/'||b=='*')) return false;
    else if ((a=='*'||a=='/')&&(b=='+'||b=='-')) return false;
    return true;
  }
  public String toString() {
    return this.expr;
  }
}
