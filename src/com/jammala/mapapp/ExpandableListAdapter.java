package com.jammala.mapapp;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.android.gms.maps.model.Polyline;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter{

    Context context;
    ArrayList<DirectionsResult> directions;
    
    public ExpandableListAdapter(Context context, ArrayList<DirectionsResult> directions){
        
        this.context = context;
        this.directions = directions;
    }
    @Override
    public Object getChild(int arg0, int arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getChildId(int arg0, int arg1) {
        // TODO Auto-generated method stub
        return arg1;
    }

    @Override
    public View getChildView(int goupPosition, int childPosition, boolean isLastChild, View convertView,
            ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.routes_list_goup_items, parent, false);
            Button selectBtn = (Button)convertView.findViewById(R.id.btnSelect);
            Button showDirectionsBtn = (Button)convertView.findViewById(R.id.btnShowDirections);
            selectBtn.setTag(goupPosition);
            showDirectionsBtn.setTag(goupPosition);
        }
        
        return convertView;
    }

    @Override
    public int getChildrenCount(int arg0) {
        // TODO Auto-generated method stub
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        // TODO Auto-generated method stub
        return this.directions.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub
        return this.directions.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        // TODO Auto-generated method stub
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        
        if(convertView == null){
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.routes_list_group_header, null);
        }
        String headerTitle = "Route " + groupPosition;
        TextView headerTitleView = (TextView)convertView.findViewById(R.id.routeExpandableListHeaderName);
        headerTitleView.setText(headerTitle);
        headerTitleView.setTypeface(null, Typeface.BOLD);
        TextView headerTitleDistanceView = (TextView)convertView.findViewById(R.id.routeExpandableListHeaderDistance);
        DirectionsResult direction = this.directions.get(groupPosition);
        headerTitleDistanceView.setText(direction.distanceText);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isChildSelectable(int arg0, int arg1) {
        // TODO Auto-generated method stub
        return false;
    }

}
