package com.example.myapplication;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DisplayLogActivity extends Activity {

    private ListView lv;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_log);
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
        lv.setAdapter(new DisplayLogAdapter(this, LogManager.getLogEntries()));
            lv.setTextFilterEnabled(true);

    }


    /** Listener for back button click **/
    public void closeActivity(View view) {
        finish();
    }
}
