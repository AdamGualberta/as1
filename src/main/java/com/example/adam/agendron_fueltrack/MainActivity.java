package com.example.adam.agendron_fueltrack;
/*
*Created by Adam Gendron
* Last Modification: Feb.1,2016
* Created Jan,2016
* Assignment 1 CMPUT301
 */

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public ArrayList<dataEntry> dataArray = new ArrayList<dataEntry>();
    //create a new dataArray which will house the list of data entries.
    //class in a class to try to separate duties of the methods.
    //modular construction for future improvement possibilities.
    protected int index = 0;
    public ListView logView; //create a ListView object for use with a clickable list
    public ArrayAdapter<dataEntry> adapter; //for displaying the clickable list

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);//making the nice toolbar - I wanted to place a menu button
            //here sometime

        Button addButton = (Button)findViewById(R.id.buttonAdd);
        Button logButton = (Button)findViewById(R.id.buttonLogs);
        //adding the buttons so that a listener can be created to interact with app

        addButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                setResult(RESULT_OK);
                /*
                this section is where the dataArray gets its content. Since all the objects have
                their own classes, there is a need for an extensive setter section. The goal here
                is to hopefully have all classes take care of themselves, and use the dataArray
                to only store them.
                 */
                //getting the text from the fields.
                EditText latestDate = (EditText)findViewById(R.id.editTextDate);
                EditText latestStation = (EditText)findViewById(R.id.editTextStation);
                EditText latestOdometer = (EditText)findViewById(R.id.editTextOdometer);
                EditText latestGrade = (EditText)findViewById(R.id.editTextGrade);
                EditText latestAmount = (EditText)findViewById(R.id.editTextAmount);
                EditText latestUnitcost = (EditText)findViewById(R.id.editTextUnitCost);
                TextView finalCost = (TextView)findViewById(R.id.textViewfinalcostCalc);
                TextView logs = (TextView)findViewById(R.id.textViewLogs);/////////////////////////
                dataEntry latestData = new dataEntry();
                //creating the new data entry in the ArrayList
                //setter section
                latestData.setIndex(index);
                latestData.setDate(latestDate.getText().toString());
                latestData.setStation(latestStation.getText().toString());
                latestData.setOdometer(Float.parseFloat(latestOdometer.getText().toString()));
                latestData.setGrade(latestGrade.getText().toString());
                latestData.setAmount(Float.parseFloat(latestAmount.getText().toString()));
                latestData.setUnitcost(Float.parseFloat(latestUnitcost.getText().toString()));
                //a separate class is here to do the fuel cost calculation. It returns its value
                //as well as stores it for future use if need be.
                calculateCost latestCost = new calculateCost(latestData.newAmount, latestData.newUnitcost);
                latestData.setFinalcost(latestCost.getCost());
                dataArray.add(latestData);
                finalCost.setText(String.format("%2f", latestData.finalcostCalc));
                //reset the text input fields
                latestDate.setText("");
                latestStation.setText("");
                latestOdometer.setText("");
                latestGrade.setText("");
                latestAmount.setText("");
                latestUnitcost.setText("");
                index++;
                logs.setText(String.format("%1d", latestData.getIndex()));
            }
        });
        logButton.setOnClickListener(new View.OnClickListener(){
            //this intent is used to view the array of Data from the main activity.
            //I haven't been able to pass the array to the new activity quite yet, so it is
            //empty.
            public void onClick(View v){
                setResult(RESULT_OK);
                Intent logIntent = new Intent(MainActivity.this, viewLogs.class);
                //logIntent.putParcelableArrayListExtra("list", dataArray)
                startActivity(logIntent);
            }
        });

    }

    protected void onStart(){
        super.onStart();
    }

}
