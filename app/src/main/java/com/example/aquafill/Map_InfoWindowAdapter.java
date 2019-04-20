package com.example.aquafill;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class Map_InfoWindowAdapter implements GoogleMap.InfoWindowAdapter{


    private final View mWindow;
    private Context mContext;

    public Map_InfoWindowAdapter(Context context) {
        mContext = context;
        mWindow = LayoutInflater.from(context).inflate(R.layout.map_custome_window, null);
    }

    private void renderWindowText(Marker marker, View view){

        //setting text to adaptor title
        String title = marker.getTitle();
        TextView wTitle = view.findViewById(R.id.locTitle);

        //if title = not null change text to that in title
        if(!title.equals("")){
            wTitle.setText(title);
        }

        Map_InfoWindowData infoWindowDataWater = (Map_InfoWindowData) marker.getTag();

        //sets upvotes
        String upVotes1 = infoWindowDataWater.getUpvotes1();
        TextView wUpvotes1 = view.findViewById(R.id.upVotes1);
        wUpvotes1.setText(upVotes1);

        //sets downvotes
        String DownVotes1 = Map_InfoWindowData.getDownvotes1();
        TextView Downvotes1 = view.findViewById(R.id.downVotes1);
        Downvotes1.setText(DownVotes1);


        //sets icon image
        Integer Image1 = Map_InfoWindowData.getImage1();

        if (Image1 == 2) {
            ImageView refill = view.findViewById(R.id.water);
            refill.setImageResource(R.drawable.ic_refill);
        } else {
            ImageView refill = view.findViewById(R.id.water);
            refill.setImageResource(R.drawable.ic_recycle);
        }

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
