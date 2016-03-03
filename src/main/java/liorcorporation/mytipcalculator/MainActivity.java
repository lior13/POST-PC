package liorcorporation.mytipcalculator;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtBillAmount, txtTipResult;
    EditText edtBillAmount;
    CheckBox chkRound;
    Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edtBillAmount = (EditText) findViewById(R.id.edtBillAmount);
        txtBillAmount = (TextView) findViewById(R.id.txtBillAmount);
        txtTipResult = (TextView) findViewById(R.id.txtTipResult);
        chkRound = (CheckBox) findViewById(R.id.chkRound);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);

        btnCalculate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String tip;
                if (!edtBillAmount.getText().toString().isEmpty() || isInteger(edtBillAmount.getText().toString()))
                {
                    if (chkRound.isChecked())
                    {
                        tip = "Tip: " + String.valueOf(Math.round(Double.parseDouble(edtBillAmount.getText().toString()) * 0.12)) + "$";
                        txtTipResult.setText(tip);
                    }
                    else
                    {
                        tip = "Tip: " + String.valueOf(Math.round(Double.parseDouble(edtBillAmount.getText().toString()) * 12) / 100.0) + "$";
                        txtTipResult.setText(tip);
                    }
                    Snackbar.make(view, "Don't forget to say thanks :)", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else {
                    Snackbar.make(view, "Check your input, buddy!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    tip = "Tip: 0.00$";
                    txtTipResult.setText(tip);
                }
            }

            public boolean isInteger(String s) {
                if(s.isEmpty()) return false;
                for(int i = 0; i < s.length(); i++) {
                    if(i == 0 && s.charAt(i) == '-') {
                        if(s.length() == 1) return false;
                        else continue;
                    }
                    if (s.charAt(i) == '.')
                    {
                        continue;
                    }
                    if(Character.digit(s.charAt(i), 10) < 0)
                    {
                        return false;
                    }
                }
                return (s.indexOf('.') == s.lastIndexOf('.'));
            }
        });
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
