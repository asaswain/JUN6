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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Insert your Java code between the two horizontal lines.
        //-----------------------------

        // This program input several values and then calculates how much money you will have over the course of 10 years

        // input starting amount
        double startingAmt = getDouble("Starting Amount", "What is the current value of your savings?");

        // input interest rate as a percentage
        double interestRate = getDouble("Interest Rate", "What's the percentage interest rate on your savings?");

        // input contribution per year
        double inputAmt = getDouble("Contribution Rate", "How much do you plan on contributing each year?");

        // input contribution per year
        int inputYear = getInt("Forecast", "How many years do you want to forecast?");

        // calculate amount earned for each year
        float yearlyWealth[] = InterestCalc.calcWealth(startingAmt, interestRate, inputAmt, inputYear+1);

        // create 10 TextViews to form a bar graph and put it into a LinearLayout
        ViewGroup viewGroup = (ViewGroup)findViewById(android.R.id.content);
        ViewGroup viewGroup2 = (ViewGroup)viewGroup.getChildAt(0);
        LinearLayout linearLayout = (LinearLayout)viewGroup2.getChildAt(0);


        // calculate max height of all 10 views
        float maxHeight = 0;
        for (int i = 0; i < inputYear+1; ++i) {
            if (yearlyWealth[i] > maxHeight) {
                maxHeight = yearlyWealth[i];
            }
        }

        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        float dpHeight = displayMetrics.heightPixels;
        float screenFactor = dpHeight / maxHeight;

        for (int i = 0; i < inputYear+1; ++i) {

            float height = yearlyWealth[i] * screenFactor * 0.75f; // to account for action bar

            int newHeight = Math.round(height);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, // width
                    newHeight  //height
            );

            float f = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4.0f, displayMetrics);
            int px = Math.round(f);
            layoutParams.setMargins(px, 0, px, 0);   //left, top, right, bottom

            TextView textView = new TextView(this);
            textView.setLayoutParams(layoutParams);
            textView.setPadding(px, px, px, px);   //left, top, right, bottom

            // set background color for each View
            int color;
            int tmp = i % 10;
            if (tmp == 0) {
                color = 0xFFFF0000;
            } else if (tmp == 1) {
                color = 0xFFFF8000;
            } else if (tmp == 2) {
                color = 0xFFFFFF00;
            } else if (tmp == 3) {
                color = 0xFF00FF00;
            } else if (tmp == 4) {
                color = 0xFF0000FF;
            } else if (tmp == 5) {
                color = 0xFF4B0082;
            } else if (tmp == 6) {
                color = 0xFF7F00FF;
            } else if (tmp == 7) {
                color = 0xFF808080;
            } else if (tmp == 8) {
                color = 0xFFD2B48C;
            } else {
                color = 0xFF000000;
            }
            textView.setBackgroundColor(color);

            // set font color for each View
            if (tmp < 4) {
                textView.setTextColor(0xFF000000);
            } else {
                textView.setTextColor(0xFFFFFFFF);
            }

            DecimalFormat df = new DecimalFormat("#,###.00");
            String text = df.format(yearlyWealth[i]);

            textView.setText(text);
            linearLayout.addView(textView);
        }
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
        final EditText editText = (EditText)view.findViewById(R.id.editText);
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
        final EditText editText = (EditText)view.findViewById(R.id.editText);
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
        final EditText editText = (EditText)view.findViewById(R.id.editText);
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
        final EditText editText = (EditText)view.findViewById(R.id.editText);
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
        final EditText editText = (EditText)view.findViewById(R.id.editText);
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
        final EditText editText = (EditText)view.findViewById(R.id.editText);
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
}