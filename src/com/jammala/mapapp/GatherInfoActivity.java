package com.jammala.mapapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class GatherInfoActivity extends Activity {

    DirectionsResult direction;
    ArrayList<String> brands;
    ArrayList<String> models;
    SQLiteCarsHelper sqLiteCarsHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gather_info);
        direction = (DirectionsResult)getIntent().getSerializableExtra(RoutesListActivity.ROUTE_SELECTED);
        TextView originDestinationView = (TextView) findViewById(R.id.originDestinationLabel);
        TextView distanceView = (TextView) findViewById(R.id.distanceLabel);
        originDestinationView.setText(direction.origin.split(",")[0] + " to " + direction.destination.split(",")[0]);
        distanceView.setText("Distance: " + direction.distanceText);

        TextView litersView = (TextView) findViewById(R.id.litersText);
        litersView.setText("10 liters");
        TextView costView = (TextView) findViewById(R.id.costText);
        costView.setText("1000 dollars");

        sqLiteCarsHelper = new SQLiteCarsHelper(this);
        populateBrandSpinner();
        
        Spinner brandSpinner = (Spinner) findViewById(R.id.carBrandSpinner);
        brandSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    String selectedItem = arg0.getItemAtPosition(arg2).toString();
                    populateModelSpinner(selectedItem);
                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                }
        });
        
        Spinner modelSpinner = (Spinner) findViewById(R.id.carModelSpinner);
        modelSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                String selectedItem = arg0.getItemAtPosition(arg2).toString();
                //populateModelSpinner(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
    });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.gather_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void populateBrandSpinner(){
        brands = sqLiteCarsHelper.getBrands(); 
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.simple_spinner_item, brands);
        Spinner brandSpinner = (Spinner) findViewById(R.id.carBrandSpinner);
        brandSpinner.setAdapter(arrayAdapter);
    }
    
    private void populateModelSpinner(String brand){
        models = sqLiteCarsHelper.getModels(brand); 
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.simple_spinner_item, models);
        Spinner modelSpinner = (Spinner) findViewById(R.id.carModelSpinner);
        modelSpinner.setAdapter(arrayAdapter);
    }
}
