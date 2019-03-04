package com.example.aquafill;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;


//this class populates the list adaptor with all items from array

public class Location_ListAdapter extends ArrayAdapter<Location_Info> {

    private Context mContext;
    private int mResource;

    public Location_ListAdapter(Context context, int resource, ArrayList<Location_Info> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get location information
        String locType = getItem(position).getLocType();
        String locName = getItem(position).getLocName();
        String locDistance = getItem(position).getLocDistance();
        String locLatitude = getItem(position).getLocLatatude();
        String locLongitude = getItem(position).getLocLongatude();

        //creates location object with the information
        Location_Info location = new Location_Info(locType, locName, locDistance, locLatitude, locLatitude);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvResName = (TextView) convertView.findViewById(R.id.icon);
        TextView tvResStyle = (TextView) convertView.findViewById(R.id.location_name);
        TextView tvResOvRate = (TextView) convertView.findViewById(R.id.distance);

        tvResName.setText(locType);
        tvResStyle.setText(locName);
        tvResOvRate.setText(locDistance);

        return convertView;

    }
}