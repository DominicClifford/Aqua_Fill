package com.example.aquafill;

import android.app.Person;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

//this class calls locations from server and populates an array list
public class Location_ListView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("List View");

        ListView mListView = (ListView) findViewById(R.id.loc_lists);

        //Objects (get from server)
        Location_Info McDonaldsUXB = new Location_Info("Fill", "McDonalds Uxbridge", "0.5km", "51.548227", "-0.481134");
        Location_Info McDonaldsChimes = new Location_Info("Fill", "McDonalds Chimes", "0.5km", "51.545670", "-0.477449");


        //Add the  objects to an ArrayList
        ArrayList<Location_Info> LocationArrayList = new ArrayList<>();
        LocationArrayList.add(McDonaldsChimes);
        LocationArrayList.add(McDonaldsUXB);

        //add array list to listview adapter
        Location_ListAdapter adapter = new Location_ListAdapter(this, R.layout.list_view_adapter, LocationArrayList);
        mListView.setAdapter(adapter);

        /*on click listener for each item in the array list
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_SHORT)
                        .show();
            }
        });*/

    }

}
