public abstract class Expresion {
  double a,b;
  public Expresion() {
  }
  public  Expresion(double a, double b) {
    this.a = a;
    this.b = b;
  }
  public Double evaluate(Context c)  throws Exception {
    return null;//not allowed
  }
}

class MulExpresion extends Expresion {
  public MulExpresion(double a, double b) {
    super(a,b);
  }
  @Override
  public Double evaluate(Context c) {
    return a*b;
  }
}
class AddExpresion extends Expresion {
  public AddExpresion(double a, double b) {
    super(a,b);
    // TODO Auto-generated constructor stub
  }
  @Override
  public Double evaluate(Context c) {
    return a+b;
  }
}
class DivExpresion extends Expresion {
  public DivExpresion(double a, double b) {
    super(a,b);
  }
  @Override
  public Double evaluate(Context c) throws Exception {
    if(b!=0) return a/b;
    else throw new Exception(" Division by 0, not allowed "+a +" / "+b);
  }
}
class SubExpresion extends Expresion {
  public SubExpresion(double a, double b) {
    super(a,b);
  }
  @Override
  public Double evaluate(Context c) throws Exception {
    return a-b;
  }
}
class ModExpresion extends Expresion {
  public ModExpresion(double a, double b) {
    super(a,b);
  }
  @Override
  public Double evaluate(Context c) {
     return a%b;
  }
}
class ConcExpresion extends Expresion {
  public ConcExpresion(double a, double b) {
    super(a,b);
  }

  @Override
  public Double evaluate(Context c) { 
    Double aux = Double.parseDouble((int)a+""+(int)b);
    return aux;
  }
}
class ExpoExpresion extends Expresion {
  public ExpoExpresion(double a, double b) {
    super(a,b);
  }
  @Override
  public Double evaluate(Context c) {
     return Math.pow(a,b);
  }
}