package com.kathmandu.datecheckin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

     TextView tvIDate, tvODate, txtTotal, txtVat, txtGTotal;
     Spinner tvSpinner;
     EditText idAdult, idChildren, idRoom;
     Button btnSave;
    Date CheckIn, CheckOut;
    Integer total;
    double vat;
    double gtotal;
    Integer numofday,roomcost,numofroom;
    AlertDialog.Builder builder;

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
                dateCheckin();
            }
        });

        tvODate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateCheckout();
            }
        });

        //Spinner

        String RoomType[] = {"Deluxe - 4000", "Residental - 5000", "Premium - 6000"};
        ArrayAdapter adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                RoomType
        );
        tvSpinner.setAdapter(adapter);
        //------------------------Spinner End----------------
        //Button on set
         btnSave.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                if (tvSpinner.getSelectedItem().toString() == "Deluxe - 4000") {
                    roomcost = 4000;}
                    else if (tvSpinner.getSelectedItem().toString() == "Residental - 5000"){
                     roomcost = 5000;
                    }
                    else if (tvSpinner.getSelectedItem().toString() == "Premium - 6000"){
                        roomcost = 6000;
                }



                    numofroom = Integer.parseInt(idRoom.getText().toString());
                    total = numofday*roomcost*numofroom;
                    vat = (0.13 * total);
                    gtotal = total + vat;

                 String setTotal = "Total : " + total;
                 String setVat = "Vat (13%) : " + vat;
                 String setGrandTotal = "Gross Total : " + gtotal;
                 txtTotal.setText(setTotal);
                 txtVat.setText(setVat);
                 txtGTotal.setText(setGrandTotal);

                }

         });

    }

    //CHECHKIN DATE
    private void dateCheckin(){
        //Set it as current date of today

        final Calendar d1 = Calendar.getInstance();
        int year = d1.get(Calendar.YEAR);
        int day = d1.get(Calendar.DAY_OF_MONTH);
        int month = d1.get(Calendar.MONTH);

        DatePickerDialog datePickerCheckin = new DatePickerDialog(
                this, this, year, month, day);
        datePickerCheckin.show();
    }




//------------END of CheckIn-------------------------------------------------------------------

    //CHECKOUT DATE
    private void dateCheckout(){
        //Set it as current date of today

        final Calendar d2 = Calendar.getInstance();
        int year = d2.get(Calendar.YEAR);
        int day = d2.get(Calendar.DAY_OF_MONTH);
        int month = d2.get(Calendar.MONTH);

        DatePickerDialog datePickerCheckout = new DatePickerDialog(
                this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                String date = dayOfMonth + "-" + month + "-" + year;
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    CheckOut = format.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                tvODate.setText(date);
            }
        }, year, month, day);
        datePickerCheckout.show();


    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month = month + 1;
        String date = dayOfMonth + "-" + month + "-" + year;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            CheckIn = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        tvIDate.setText(date);
    }




//    long msDiff = Calendar.getInstance().getTimeInMillis() - testCalendar.getTimeInMillis();
//    long daysDiff = TimeUnit.MILLISECONDS.toDays(msDiff);

//--------------------End of Check out




}


