package edu.nyu.scps.JUN6;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    InterestCalc myDialog = new InterestCalc();

    /**
     * This method displays the dialog box so the user can re-run the program
     *
     * @param v - View info
     */
    public void Recalculate(View v) {
        myDialog.displayDialog();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Insert your Java code between the two horizontal lines.
        //-----------------------------
        myDialog.displayDialog();
        //-----------------------------
    }

    private int intResult;

    private int getInt(String title, String message) {

        //A builder object can create a dialog object.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);

        //This inflater reads the dialog.xml and creates the objects described therein.
        //Pass null as the parent view because it's going in the dialog layout.
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);
        //Must be final to be mentioned in the anonymous inner class.
        final EditText editText = (EditText) view.findViewById(R.id.editText);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
        builder.setView(view);

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN
                        && keyCode == KeyEvent.KEYCODE_ENTER) {

                    Editable editable = editText.getText();
                    String string = editable.toString();
                    try {
                        intResult = Integer.parseInt(string);
                    } catch (NumberFormatException numberFormatException) {
                        Toast toast = Toast.makeText(MainActivity.this, "bad integer " + string, Toast.LENGTH_LONG);
                        toast.show();
                        intResult = 0;
                    }

                    //Sending this message will break us out of the loop below.
                    Message message = handler.obtainMessage();
                    handler.sendMessage(message);
                }
                return false;
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        //Loop until the user presses the EditText's Done button.
        try {
            Looper.loop();
        } catch (RuntimeException runtimeException) {
            // do nothing
        }

        alertDialog.dismiss();
        return intResult;
    }

    private long longResult;

    private long getLong(String title, String message) {

        //A builder object can create a dialog object.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);

        //This inflater reads the dialog.xml and creates the objects described therein.
        //Pass null as the parent view because it's going in the dialog layout.
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);
        //Must be final to be mentioned in the anonymous inner class.
        final EditText editText = (EditText) view.findViewById(R.id.editText);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
        builder.setView(view);

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN
                        && keyCode == KeyEvent.KEYCODE_ENTER) {

                    Editable editable = editText.getText();
                    String string = editable.toString();
                    try {
                        longResult = Long.parseLong(string);
                    } catch (NumberFormatException numberFormatException) {
                        Toast toast = Toast.makeText(MainActivity.this, "bad long " + string, Toast.LENGTH_LONG);
                        toast.show();
                        longResult = 0L;
                    }

                    //Sending this message will break us out of the loop below.
                    Message message = handler.obtainMessage();
                    handler.sendMessage(message);
                }
                return false;
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        //Loop until the user presses the EditText's Done button.
        try {
            Looper.loop();
        } catch (RuntimeException runtimeException) {
            // do nothing
        }

        alertDialog.dismiss();
        return longResult;
    }

    private boolean booleanResult;

    private boolean getBoolean(String title, String message) {

        //A builder object can create a dialog object.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);

        //This inflater reads the dialog.xml and creates the objects described therein.
        //Pass null as the parent view because it's going in the dialog layout.
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);
        //Must be final to be mentioned in the anonymous inner class.
        final EditText editText = (EditText) view.findViewById(R.id.editText);
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(view);

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN
                        && keyCode == KeyEvent.KEYCODE_ENTER) {

                    Editable editable = editText.getText();
                    String string = editable.toString();
                    try {
                        booleanResult = Boolean.parseBoolean(string);
                    } catch (NumberFormatException numberFormatException) {
                        Toast toast = Toast.makeText(MainActivity.this, "bad boolean " + string, Toast.LENGTH_LONG);
                        toast.show();
                        booleanResult = false;
                    }

                    //Sending this message will break us out of the loop below.
                    Message message = handler.obtainMessage();
                    handler.sendMessage(message);
                }
                return false;
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        //Loop until the user presses the EditText's Done button.
        try {
            Looper.loop();
        } catch (RuntimeException runtimeException) {
            // do nothing
        }

        alertDialog.dismiss();
        return booleanResult;
    }

    private float floatResult;

    private float getFloat(String title, String message) {

        //A builder object can create a dialog object.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);

        //This inflater reads the dialog.xml and creates the objects described therein.
        //Pass null as the parent view because it's going in the dialog layout.
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);
        //Must be final to be mentioned in the anonymous inner class.
        final EditText editText = (EditText) view.findViewById(R.id.editText);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED
                | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        builder.setView(view);

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN
                        && keyCode == KeyEvent.KEYCODE_ENTER) {

                    Editable editable = editText.getText();
                    String string = editable.toString();
                    try {
                        floatResult = Float.parseFloat(string);
                    } catch (NumberFormatException numberFormatException) {
                        Toast toast = Toast.makeText(MainActivity.this, "bad float " + string, Toast.LENGTH_LONG);
                        toast.show();
                        floatResult = 0.0f;
                    }

                    //Sending this message will break us out of the loop below.
                    Message message = handler.obtainMessage();
                    handler.sendMessage(message);
                }
                return false;
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        //Loop until the user presses the EditText's Done button.
        try {
            Looper.loop();
        } catch (RuntimeException runtimeException) {
            // do nothing
        }

        alertDialog.dismiss();
        return floatResult;
    }


    private double doubleResult;

    private double getDouble(String title, String message) {

        //A builder object can create a dialog object.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);

        //This inflater reads the dialog.xml and creates the objects described therein.
        //Pass null as the parent view because it's going in the dialog layout.
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);
        //Must be final to be mentioned in the anonymous inner class.
        final EditText editText = (EditText) view.findViewById(R.id.editText);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED
                | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        builder.setView(view);

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN
                        && keyCode == KeyEvent.KEYCODE_ENTER) {

                    Editable editable = editText.getText();
                    String string = editable.toString();
                    try {
                        doubleResult = Double.parseDouble(string);
                    } catch (NumberFormatException numberFormatException) {
                        Toast toast = Toast.makeText(MainActivity.this, "bad double " + string, Toast.LENGTH_LONG);
                        toast.show();
                        doubleResult = 0.0;
                    }

                    //Sending this message will break us out of the loop below.
                    Message message = handler.obtainMessage();
                    handler.sendMessage(message);
                }
                return false;
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        //Loop until the user presses the EditText's Done button.
        try {
            Looper.loop();
        } catch (RuntimeException runtimeException) {
            // do nothing
        }

        alertDialog.dismiss();
        return doubleResult;
    }

    private String stringResult;

    private String getString(String title, String message) {

        //A builder object can create a dialog object.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);

        //This inflater reads the dialog.xml and creates the objects described therein.
        //Pass null as the parent view because it's going in the dialog layout.
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);
        //Must be final to be mentioned in the anonymous inner class.
        final EditText editText = (EditText) view.findViewById(R.id.editText);
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(view);

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN
                        && keyCode == KeyEvent.KEYCODE_ENTER) {

                    Editable editable = editText.getText();
                    stringResult = editable.toString();

                    //Sending this message will break us out of the loop below.
                    Message message = handler.obtainMessage();
                    handler.sendMessage(message);
                }
                return false;
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        //Loop until the user presses the EditText's Done button.
        try {
            Looper.loop();
        } catch (RuntimeException runtimeException) {
            // do nothing
        }

        alertDialog.dismiss();
        return stringResult;
    }

    //The handleMessage method of this object will be called when we call the sendMessage method of
    //this object.  It throws an exception to break us out of the infinite loops below.

    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            throw new RuntimeException();
        }
    };

    private void display(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Sending this message will break us out of the loop below.
                Message message = handler.obtainMessage();
                handler.sendMessage(message);
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        //Loop until the user presses the EditText's Done button.
        try {
            Looper.loop();
        } catch (RuntimeException runtimeException) {
            // do nothing
        }
    }

    private void display(String title, int i) {
        display(title, Integer.toString(i));
    }

    private void display(String title, long l) {
        display(title, Long.toString(l));
    }

    private void display(String title, boolean b) {
        display(title, Boolean.toString(b));
    }

    private void display(String title, float f) {
        display(title, Float.toString(f));
    }

    public void display(String title, double d) {
        display(title, Double.toString(d));
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

    /**
     * This private class displays a dialog box prompting user to enter financial data and then displays a bar graph
     * showing the value of their savings for eahc year in the future
     */
    private class InterestCalc {

        /**
         * This displays a series of dialog boxes prompting the user to input financial data
         */
        private void displayDialog() {
            // This program input several values and then calculates how much money you will have over the course of 10 years

            // input starting amount
            double startingAmt = getDouble(getString(R.string.get_starting_amount_title), getString(R.string.get_starting_amount_msg));

            // input interest rate as a percentage
            double interestRate = getDouble(getString(R.string.get_interest_title), getString(R.string.get_interest_msg));

            // input contribution per year
            double inputAmt = getDouble(getString(R.string.get_annual_contribution_title), getString(R.string.get_annual_contribution_msg));

            // input contribution per year
            int inputYear = -1;
            while (inputYear < 0) {
                inputYear = getInt(getString(R.string.get_year_count_title), getString(R.string.get_year_count_msg));
            }

            // calculate amount earned for each year
            float yearlyWealth[] = calcWealth(startingAmt, interestRate, inputAmt, inputYear + 1);

            // build bar charts
            buildChart(yearlyWealth, inputYear);
        }

        /**
         * This method displays a series of views in a LinearLayout to form a bar graph showing the amount of money the user will have each year in the future.
         * The chart is scaled so the biggest bar is equal to the available size of the screen window
         *
         * @param yearlyWealth - a string numbers with how much money we have each year (starting with current value of savings in year 0)
         * @param yearCnt      - the number of years to forecast in the future
         */
        private void buildChart(float[] yearlyWealth, int yearCnt) {

            // create TextViews to form a bar graph and put it into a LinearLayout
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.barGraph);

            // erase previous bar graph if it exists
            if (((LinearLayout) linearLayout).getChildCount() > 0) {
                ((LinearLayout) linearLayout).removeAllViews();
            }

            // calculate max height of all views
            float maxHeight = 0;
            for (int i = 0; i < yearCnt + 1; ++i) {
                if (yearlyWealth[i] > maxHeight) {
                    maxHeight = yearlyWealth[i];
                }
            }

            Resources resources = getResources();
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            float dpHeight = displayMetrics.heightPixels;
            float screenFactor = dpHeight / maxHeight;

            // build views for each bar of the bar graph
            for (int i = 0; i < yearCnt + 1; ++i) {

                float height = yearlyWealth[i] * screenFactor * 0.6f; // to account for action bar
                int newHeight = Math.round(height);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, // width
                        newHeight  //height
                );

                float f = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4.0f, displayMetrics);
                int px = Math.round(f);
                layoutParams.setMargins(px, 0, px, 0);   //left, top, right, bottom
                TextView textView = new TextView(MainActivity.this);
                textView.setLayoutParams(layoutParams);
                textView.setPadding(px, px, px, px);   //left, top, right, bottom

                // set background color for each View
                textView.setBackgroundColor(getBackgroundColor(i));
                // set font color for each View
                textView.setTextColor(getFontColor(i));

                DecimalFormat df = new DecimalFormat("#,###.00");
                String text = df.format(yearlyWealth[i]);

                textView.setText(text);
                linearLayout.addView(textView);
            }
        }

        /**
         * Get the textcolor to use for each bar in the bar graph
         *
         * @param yearCnt - the number of the current year
         * @return - a hex font color code
         */
        private int getFontColor(int yearCnt) {
            Resources resources = getResources();
            int color;
            int counter = yearCnt % 10;
            if (counter < 4) {
                color = resources.getColor(R.color.black);
            } else {
                color = resources.getColor(R.color.white);
            }
            return color;
        }

        /**
         * Get the background color to use for each bar in the bar graph
         *
         * @param yearCnt - the number of the current year
         * @return - a hex background color code
         */
        private int getBackgroundColor(int yearCnt) {
            Resources resources = getResources();
            int colorList[] = {
                    resources.getColor(R.color.red),
                    resources.getColor(R.color.orange),
                    resources.getColor(R.color.yellow),
                    resources.getColor(R.color.green),
                    resources.getColor(R.color.blue),
                    resources.getColor(R.color.purple),
                    resources.getColor(R.color.indigo),
                    resources.getColor(R.color.lightgrey),
                    resources.getColor(R.color.darkgrey),
                    resources.getColor(R.color.black),
            };
            int counter = yearCnt % 10;
            return colorList[counter];
        }

        /**
         * Calculate wealth saved over a series of years, based on a starting amount of money, the average interest rate for each year,
         * the amount the user will contribute each year, and the number of year to forecast
         *
         * @param startingAmt     - the starting amount of money
         * @param interestRate    - the average interest rate of your investments
         * @param contributionAmt - the amount you contribute each year
         * @param yearCnt         - the number of years to run this calculation for
         * @return - a float array of the amount of money you have after each year, with the 0 index being the starting amount of money
         */
        private float[] calcWealth(double startingAmt, double interestRate, double contributionAmt, int yearCnt) {
            float yearlyWealth[] = new float[yearCnt + 1];
            yearlyWealth[0] = (float) startingAmt;

            for (int year = 1; year < yearCnt + 1; ++year) {
                double newAmt = yearlyWealth[year - 1];
                newAmt *= (1 + interestRate / 100);
                newAmt += contributionAmt;
                yearlyWealth[year] = (float) newAmt;
            }

            return yearlyWealth;
        }
    }
}
