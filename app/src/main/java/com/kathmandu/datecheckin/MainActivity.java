package com.kathmandu.datecheckin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private TextView tvIDate, tvODate, txtTotal, txtVat, txtGTotal;
    private Spinner tvSpinner;
    private EditText idAdult, idChildren, idRoom;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvIDate = findViewById(R.id.tvIDate);
        tvODate = findViewById(R.id.tvODate);
        txtTotal = findViewById(R.id.txtTotal);
        txtGTotal = findViewById(R.id.txtGTotal);
        txtVat= findViewById(R.id.txtVat);
        tvSpinner = findViewById(R.id.tvSpinner);
        idAdult = findViewById(R.id.idAdult);
        idChildren = findViewById(R.id.idChildren);
        idRoom = findViewById(R.id.idRoom);
        btnSave = findViewById(R.id.btnSave);



        tvIDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatePicker();
            }
        });
    }

    //CHECHKIN DATE
    private void loadDatePicker(){
        //Set it as current date of today

        final Calendar c = Calendar.getInstance();
        int yearOne = c.get(Calendar.YEAR);
        int dayOne = c.get(Calendar.DAY_OF_MONTH);
        int monthOne = c.get(Calendar.MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, this, yearOne, monthOne, dayOne);
        datePickerDialog.show();
    }


    @Override
    public void onDateSet(DatePicker view, int yearOne, int monthOfYear, int dayOfMonth) {
        monthOne++;
        String date = "Day/Month/Year : " + dayOfMonth + "/" + monthOne + "/" + yearOne;
        tvIDate.setText(date);
    }

//------------END of CheckIn-------------------------------------------------------------------
    //CHECKOUT DATE
    private void loadDatePickerTwo(){
        //Set it as current date of today

        final Calendar c = Calendar.getInstance();
        int yearTwo = c.get(Calendar.YEAR);
        int dayTwo = c.get(Calendar.DAY_OF_MONTH);
        int monthTwo = c.get(Calendar.MONTH);

        DatePickerDialog datePickerCheckout = new DatePickerDialog(
                this, this, yearTwo, monthTwo, dayTwo);
        datePickerCheckout.show();
    }


    @Override
    public void onDateSet(DatePicker view, int yearTwo, int monthTwo, int dayOfMonth) {
        monthTwo++;
        String date = "Day/Month/Year : " + dayOfMonth + "/" + monthTwo + "/" + yearTwo;
        tvODate.setText(date);
    }
}


