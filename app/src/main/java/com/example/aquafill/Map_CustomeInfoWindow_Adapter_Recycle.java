package com.example.aquafill;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
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

                /*set snippet text to adaptor snippet
        String upsnippet = marker.getSnippet();
        TextView wUpSnippet = (TextView) view.findViewById(R.id.upVotes);

        //if title = not null change text to that in snippet
        if(!upsnippet.equals("")){
            wUpSnippet.setText(upsnippet);
        }*/

        /*replace Image
        ImageView recycle = (null);
        ImageView wRecycle = view.findViewById(R.id.recycle);

        if (recycle.equals(null)) {
            wRecycle.setImageResource(R.drawable.ic_recycle);
        }*/

        InfoWindowData_Recycle infoWindowDataRecycle = (InfoWindowData_Recycle) marker.getTag();

        String upVotes = infoWindowDataRecycle.getUpvotes();
        TextView wUpvotes = view.findViewById(R.id.upVotes);
        wUpvotes.setText(upVotes);


        String DownVotes = infoWindowDataRecycle.getDownvotes();
        TextView Downvotes_tv = view.findViewById(R.id.downVotes);
        Downvotes_tv.setText(DownVotes);

        //sets icon image
        Integer Image1 = InfoWindowData_Water.getImage1();

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
