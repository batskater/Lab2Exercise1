package th.ac.tu.siit.its333.lab2exercise1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    // expr = the current string to be calculated
    StringBuffer expr;
    int mem = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expr = new StringBuffer();
        updateExprDisplay();
    }

    public void updateExprDisplay() {
        TextView tvExpr = (TextView)findViewById(R.id.tvExpr);
        tvExpr.setText(expr.toString());
    }
    public void AnsDisplay(String s) {
        TextView ansExpr = (TextView)findViewById(R.id.tvAns);
        ansExpr.setText(s);
    }
    public void recalculate() {
        //Calculate the expression and display the output

        //Split expr into numbers and operators
        //e.g. 123+45/3 --> ["123", "+", "45", "/", "3"]
        //reference: http://stackoverflow.com/questions/2206378/how-to-split-a-string-but-also-keep-the-delimiters
        String e = expr.toString();
        String[] tokens = e.split("((?<=\\+)|(?=\\+))|((?<=\\-)|(?=\\-))|((?<=\\*)|(?=\\*))|((?<=/)|(?=/))");

        int result = 0;
        String op = "";
        if(tokens[0] != "") {
            for (int i = 0; i < tokens.length; i++) {
                if (tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("*") || tokens[i].equals("/")) {
                    op = tokens[i];
                } else {
                    if (op.equals("+"))
                        result += Integer.parseInt(tokens[i]);
                    else if (op.equals("-"))
                        result -= Integer.parseInt(tokens[i]);
                    else if (op.equals("*"))
                        result *= Integer.parseInt(tokens[i]);
                    else if (op.equals("/"))
                        result /= Integer.parseInt(tokens[i]);
                    else if (op.equals(""))
                        result = Integer.parseInt(tokens[i]);
                }

            }
        }
        else{
            AnsDisplay("0");
        }

        /***
        result = Integer.parseInt(tokens[0]);

        for (int i=1;i<tokens.length-1;i++){
            if(tokens[i].equals("+"))
                  result += Integer.parseInt(tokens[i+1]);
            else if(tokens[i].equals("-"))
                result -= Integer.parseInt(tokens[i+1]);
            else if(tokens[i].equals("*"))
                result *= Integer.parseInt(tokens[i+1]);
            else if(tokens[i].equals("/"))
                result /= Integer.parseInt(tokens[i + 1]);

        }
        ***/

        AnsDisplay(Integer.toString(result));
    }

    public void equalClicked(View v) {
        expr = new StringBuffer();
        TextView tvAns = (TextView)findViewById(R.id.tvAns);
        String d = tvAns.getText().toString();
        expr.append(d);
        updateExprDisplay();
        AnsDisplay("0");
    }

    public void maddClicked(View v) {
        TextView tvAns = (TextView)findViewById(R.id.tvAns);
        String d = tvAns.getText().toString();
        mem += Integer.parseInt(d);
        Toast t = Toast.makeText(this.getApplicationContext(),
        mem + " has been added to memory.",Toast.LENGTH_SHORT);
        t.show();
    }

    public void mminusClicked(View v) {
        TextView tvAns = (TextView)findViewById(R.id.tvAns);
        String d = tvAns.getText().toString();
        mem -= Integer.parseInt(d);
        Toast t = Toast.makeText(this.getApplicationContext(),
        mem + " has been added to memory.",Toast.LENGTH_SHORT);
        t.show();
    }

    public void mrClicked(View v){
        expr = new StringBuffer();
        expr.append(Integer.toString(mem));
        updateExprDisplay();
        AnsDisplay(Integer.toString(mem));
    }

    public void mcClicked(View v){
        mem = 0;
        Toast t = Toast.makeText(this.getApplicationContext(),
        "Memory Cleared",Toast.LENGTH_SHORT);
        t.show();
    }

    public void digitClicked(View v) {
        //d = the label of the digit button
        String d = ((TextView)v).getText().toString();
        //append the clicked digit to expr
        expr.append(d);
        //update tvExpr
        updateExprDisplay();
        //calculate the result if possible and update tvAns
        recalculate();
    }

    public void operatorClicked(View v) {
        //IF the last character in expr is not an operator and expr is not "",
        if(!isOperand(expr.charAt(expr.length()-1)) && (!expr.toString().isEmpty())) {
            String d = ((TextView)v).getText().toString();
            //append the clicked digit to expr
            expr.append(d);
            //update tvExpr
            updateExprDisplay();
        }
        else{

        }
    }

    public boolean isOperand(char c){
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public void ACClicked(View v) {
        //Clear expr and updateExprDisplay
        expr = new StringBuffer();
        updateExprDisplay();
        //Display a toast that the value is cleared
        Toast t = Toast.makeText(this.getApplicationContext(),
                "All cleared", Toast.LENGTH_SHORT);
        t.show();
    }

    public void BSClicked(View v) {
        //Remove the last character from expr, and updateExprDisplay
        if (expr.length() > 0) {
            expr.deleteCharAt(expr.length()-1);
            updateExprDisplay();
            recalculate();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
