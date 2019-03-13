package com.example.aquafill;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class Map_CustomeInfoWindow_Adapter_Water implements GoogleMap.InfoWindowAdapter{

    private final View mWindow;
    private Context mContext;

    public Map_CustomeInfoWindow_Adapter_Water(Context context) {
        mContext = context;
        mWindow = LayoutInflater.from(context).inflate(R.layout.map_custome_window_water, null);
    }

    private void renderWindowText(Marker marker, View view){

        //setting text to adaptor title
        String title = marker.getTitle();
        TextView wTitle = view.findViewById(R.id.locTitle);

        //if title = not null change text to that in title
        if(!title.equals("")){
            wTitle.setText(title);
        }

        /*replace Image
        ImageView refill = null;
        ImageView wRefill = view.findViewById(R.id.water);

        if (refill.equals(null)) {
            wRefill.setImageResource(R.drawable.ic_refill);
        }*/

        InfoWindowData_Water infoWindowDataWater = (InfoWindowData_Water) marker.getTag();

        String upVotes1 = infoWindowDataWater.getUpvotes1();
        TextView wUpvotes1 = view.findViewById(R.id.upVotes1);
        wUpvotes1.setText(upVotes1);


        String DownVotes1 = InfoWindowData_Water.getDownvotes1();
        TextView Downvotes1 = view.findViewById(R.id.downVotes1);
        Downvotes1.setText(DownVotes1);


    }


    @Override
    public View getInfoWindow(Marker marker) {
        renderWindowText(marker, mWindow);
        return mWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        renderWindowText(marker, mWindow);
        return mWindow;

    }
}
