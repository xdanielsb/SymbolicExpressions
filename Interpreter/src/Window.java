import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.UIManager.*;

public class Window extends JFrame implements ActionListener {

  private JTextField expression;
  private JTextArea  result;
  private JButton process;
  
  public Window() {
    super("Order Creation");
    ConfigureLookFeel();
    Font font = new Font("Courier", Font.BOLD,19);
    
    expression = new JTextField(20);
    result = new JTextArea("");
    process = new JButton(" Get Result ");
    expression.setFont(font);
    
    JPanel elements = new JPanel(new GridLayout(1,2));
    JPanel in = new JPanel(new GridLayout(3,1));
    JTextArea aux=new JTextArea("Write your simbolic expression: \n Operations add(+) concat(&)  sub(-)  mul(*) div(/) expo(^) mod(%)");
    aux.setFont(new Font("Serif", Font.ITALIC, 16));
    result.setFont(new Font("Serif", Font.ITALIC, 16));
    aux.setLineWrap(true);
    aux.setWrapStyleWord(true);
    aux.setOpaque(false);
    aux.setEditable(false);
    in.add(aux);
    in.add(expression);
    in.add(process);
    JPanel out = new JPanel(new GridLayout(1,1));
    out.add(result);
    elements.add(in);
    elements.add(out);
    process.addActionListener(this);
    this.getContentPane().add(elements);
    this.setSize(700,300);
    this.setVisible(true);
  }
  
  public void ConfigureLookFeel() {
    try {
      for (LookAndFeelInfo info:UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    }
    catch (Exception e) {
    }
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    String exp="";
    Context ctx = new Context();
    if(expression.getText().contains(",")) {
      String ar[] =expression.getText().split(",");
      exp = ar[0];
      for(int i =1; i<ar.length; i++) {
        String el[] = ar[i].split("=");
        String var = el[0];
        Double val = Double.parseDouble(el[1]);
        ctx.add(var.trim(),val);
      }
    }else {
      exp = expression.getText();
    }
    
    Expresion ex=new Evaluator(exp);
    try {
      double res=ex.evaluate(ctx);
      result.setText(ctx+"\n Result ="+res);
    }catch(Exception ea) {
      result.setText(ea.getMessage());
    }
    
  }
}
