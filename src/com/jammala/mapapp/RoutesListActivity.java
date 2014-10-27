package com.jammala.mapapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

public class RoutesListActivity extends Activity {

    public static String ROUTE_SELECTED = "com.jammala.app.ROUTE_SELECTED";
    
    private ExpandableListAdapter expandableListAdapter;
    private ExpandableListView expandableListView;
    ArrayList<DirectionsResult> directions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes_list);
        Intent intent = getIntent();
        directions = (ArrayList<DirectionsResult>) intent.getSerializableExtra(MainActivity.ROUTES_EXTRA);
        
        expandableListView = (ExpandableListView) findViewById(R.id.routeExpandableList);
        expandableListAdapter = new ExpandableListAdapter(this, directions);
        expandableListView.setIndicatorBounds(0, 20);
        expandableListView.setAdapter(expandableListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.routes_list, menu);
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

    public void gatherInfo(View view){
        Intent gatherInfoIntent = new Intent(this, GatherInfoActivity.class);
        DirectionsResult selectedDirectionsResult = directions.get(Integer.parseInt(view.getTag().toString()));
        gatherInfoIntent.putExtra(ROUTE_SELECTED, selectedDirectionsResult);
        startActivity(gatherInfoIntent);
    }

}
