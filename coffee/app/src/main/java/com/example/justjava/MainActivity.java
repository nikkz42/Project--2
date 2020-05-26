package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    public int noOfcoffee = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        EditText input = (EditText) findViewById(R.id.input);
        String name = input.getText().toString();
        CheckBox checking = (CheckBox) findViewById(R.id.check);
        CheckBox chocoing = (CheckBox) findViewById(R.id.choco);
        Boolean hasWhippedCream = checking.isChecked();
        Boolean hasChoco = chocoing.isChecked();
        int price;
        if (checking.isChecked()) {
            price = 2 + price();
        } else if (chocoing.isChecked()) {
            price = 4 + price();
        } else if (checking.isChecked() && checking.isChecked()) {
            price = 6 + price();
        } else {
            price = price();
        }

        String message = orderSummary(name, hasWhippedCream, hasChoco);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "coffee order");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }

    public void Increment(View view) {
        if (noOfcoffee <= 100) {
            noOfcoffee += 1;

        } else {
            Toast.makeText(this, "MAXIMUM 100 COFFEES CAN BE ORDERED", Toast.LENGTH_SHORT).show();
        }
        display(noOfcoffee);
    }

    public void Decrement(View view) {
        if (noOfcoffee != 1) {
            noOfcoffee -= 1;
        } else {
            Toast.makeText(this, "MINIMUM NUMBER OF COFFEES SHOULD BE ONE", Toast.LENGTH_SHORT).show();
        }
        display(noOfcoffee);
    }

    public int price() {
        int total = 0;
        total = noOfcoffee * 10;
        return total;
    }

    public String orderSummary(String name, Boolean hasWhippedCream, Boolean hasChoco) {
        String message = "NAME : " + name +'\n' + " WHIPPED CREAM REQUIRED : " + hasWhippedCream+'\n' + " CHOCOLATE REQUIRED : " + hasChoco;
        return message;
    }



    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.Quantity_view);
        quantityTextView.setText("" + number);
    }

}

