package com.example.myapplication.adapters;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.db.LogManager;
import com.example.myapplication.R;

public class DisplayLogActivity extends Activity {

    private ListView lv;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_log);
//        if(lv.getCount() == 0){
//            Toast.makeText(DisplayLogActivity.this,"No data to show", Toast.LENGTH_SHORT).show();;
//        }
        lv = (ListView) findViewById(R.id.log_list);
        title = (TextView) findViewById(R.id.title);
        lv.setEmptyView(findViewById(R.id.noData));


    }


    protected void onResume() {
        super.onResume();
        displayLogEntries();
    }

    /** Displays the calorie log entry data **/
    private void displayLogEntries() {
        if(LogManager.getLogEntries().size() != 0 ) {
            lv.setAdapter(new DisplayLogAdapter(this, LogManager.getLogEntries()));
            lv.setTextFilterEnabled(true);
        }
        else
        {
            Toast.makeText(DisplayLogActivity.this,"No data to show", Toast.LENGTH_SHORT).show();
        }
    }


    /** Listener for back button click **/
    public void closeActivity(View view) {
        finish();
    }
}
