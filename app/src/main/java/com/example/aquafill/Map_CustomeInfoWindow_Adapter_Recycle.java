package com.example.aquafill;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class Map_CustomeInfoWindow_Adapter_Recycle implements GoogleMap.InfoWindowAdapter{

    private final View mWindow;
    private Context mContext;

    public Map_CustomeInfoWindow_Adapter_Recycle(Context context) {
        mContext = context;
        mWindow = LayoutInflater.from(context).inflate(R.layout.map_custome_window_recycle, null);
    }

    private void renderWindowText(Marker marker, View view){

        //setting text to adaptor title
        String title = marker.getTitle();
        TextView wTitle = (TextView) view.findViewById(R.id.locTitle);

        //if title = not null change text to that in title
        if(!title.equals("")){
            wTitle.setText(title);
        }

        //set snippet text to adaptor snippet
        String upsnippet = marker.getSnippet();
        TextView wUpSnippet = (TextView) view.findViewById(R.id.upVotes);

        //if title = not null change text to that in snippet
        if(!upsnippet.equals("")){
            wUpSnippet.setText(upsnippet);
        }

        //set snippet text to adaptor snippet
        String downsnippet = marker.getSnippet();
        TextView wDownSnippet = (TextView) view.findViewById(R.id.downVotes);

        //if title = not null change text to that in snippet
        if(!downsnippet.equals("")){
            wDownSnippet.setText(downsnippet);
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
