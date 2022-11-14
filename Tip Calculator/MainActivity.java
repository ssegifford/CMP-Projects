package android.example.tipcalculator2;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements TextWatcher, SeekBar.OnSeekBarChangeListener, AdapterView.OnItemSelectedListener {
    //declare your variables for the widgets
    private EditText editTextBillAmount;
    private TextView textViewBillAmount;
    private TextView totalTextView;
    private SeekBar seekbar;
    //added
    private int spinnerLabel = 1;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    private TextView person_total;

    //declare the variables for the calculations
    private double billAmount = 0;
    private double percent    = .15;

    //set the number formats to be used for the $ amounts , and % amounts
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat  = NumberFormat.getPercentInstance();
    private TextView textViewPercent;
    private TextView tipTextView;

    @Override   //method for option menu, added
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*  Note: each View that will be retrieved using findViewById needs to map to a View with the matching id  */
        //add Listeners to Widgets ******
        editTextBillAmount = (EditText)findViewById(R.id.editText_BillAmount);
        editTextBillAmount.addTextChangedListener((TextWatcher) this);
        textViewBillAmount = (TextView)findViewById(R.id.textView_BillAmount);
        textViewPercent    = (TextView) findViewById(R.id.percent);
        tipTextView        = (TextView) findViewById(R.id.tip_amount);
        totalTextView      = (TextView) findViewById(R.id.total_amount);
        seekbar            = (SeekBar) findViewById(R.id.seekBar);
        seekbar.setOnSeekBarChangeListener(this);
        person_total       = findViewById(R.id.show_total_per);
        //ArrayAdapter needed for spinner to pass in context
        spinner            = findViewById(R.id.spinner);
        adapter            = ArrayAdapter.createFromResource(this,R.array.splitTip, android.R.layout.simple_spinner_item);

        // uses adapter to place data in spinner
        if(spinner != null){
            spinner.setOnItemSelectedListener(this);
            spinner.setAdapter(adapter);
        }
    }

    /* Note:   int i, int i1, and int i2 represent start, before, count respectively
        The charSequence is converted to a String and parsed to a double for you  */

    @Override       //editText method, implemented method from TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        Log.d("MainActivity", "inside onTextChanged method: charSequence= "+charSequence);
        //surround risky calculations with try catch (what if billAmount is 0 ?
        //charSequence is converted to a String and parsed to a double for you
        billAmount = Double.parseDouble(charSequence.toString()) / 100;
        Log.d("MainActivity", "Bill Amount = "+billAmount);
        //setText on the textView
        textViewBillAmount.setText(currencyFormat.format(billAmount));
        //perform tip and total calculation and update UI by calling calculate
        calculate();
    }

    @Override       // SeekBar method, implemented from SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        percent = progress / 100.0; //calculate percent based on seeker value
        calculate();
    }

    double tip = 0.00, total =0.00, each_person_total = 0.00;
    // calculate and display tip and total amounts
    private void calculate() {
        // format percent and display in percentTextView
        textViewPercent.setText(percentFormat.format(percent));

        tip               = billAmount * percent;   if(roundTip)   tip   = Math.ceil(tip);
        total             = (billAmount + tip);     if(roundTotal) total = Math.ceil(total);
        each_person_total = total/(double)spinnerLabel;

        //user currencyFormat instead of percentFormat to set the textViewTip & totalTextView
        person_total.setText(currencyFormat.format(each_person_total));
        tipTextView.setText(currencyFormat.format(tip));
        totalTextView.setText(currencyFormat.format(total));
    }

    //spinner method, adapter implemented method
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        /*from the adapter we get the item at the selected position and convert it to a string to be stored in spinnerLabel if using String
        spinnerLabel = adapterView.getItemAtPosition(position).toString(); */

        spinnerLabel = position + 1;    //since I'm using ints in my spinner option I can just the position index and add 1
        calculate();
    }

    boolean roundTip, roundTotal;
    //onClick method for RadioGroup
    public void roundingOptions(View view){
        //boolean to see whether radio option was checked using isChecked() interface
        boolean checked = ((RadioButton) view).isChecked();

        //use switch case with view id in order to check if that radio button was checked and then set boolean to use in calculation
        switch (view.getId())   {
            case R.id.no_radio:     if(checked)  roundTotal = false; roundTip = false;  calculate();
                                    break;
            case R.id.tip_radio:    if(checked) roundTip = true;    calculate();
                                    break;
            case R.id.total_radio:  if(checked) roundTotal = true;  calculate();
                                    break;
            default:
                break;
        }
    }

    public void shareAmountsText(MenuItem menu) {
        String share = "The bill amount is " +billAmount+ ".\nThe tip is " +tip+ ".\nThe total amount is " +total+ ".\nThe per person total is " +each_person_total+ ".";
        String mimeType = "text/plain";

        ShareCompat.IntentBuilder
                .from(this)                          //method chaining by not using ";"
                .setType(mimeType)                   //sets data type
                .setChooserTitle("Choose an app")    //sets title that you see when choosing app
                .setText(share)                     //this sets the message that will be sent
                .startChooser();                     // this starts the chooser
    }

    //dialog method, added
    public void onClickShowAlert(MenuItem menu) {
        AlertDialog.Builder myAlertBuilder = new
                AlertDialog.Builder(MainActivity.this);
        // Set the dialog title and message.
        myAlertBuilder.setTitle("Info");
        myAlertBuilder.setMessage("Use the drop menu to choose how many people the bill should be split for.\nThis total includes the tip.");
        myAlertBuilder.setPositiveButton("OK", (dialog, which) -> {});
        myAlertBuilder.show();

    }


    //rotation methods, added
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
    }


    // UNUSED METHODS
    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
    @Override
    public void afterTextChanged(Editable s) {}
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }


}