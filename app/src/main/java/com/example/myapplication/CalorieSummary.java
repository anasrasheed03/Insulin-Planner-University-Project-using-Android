package com.example.myapplication;

import android.app.Activity;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class CalorieSummary {
    private float totalcalories1;
    private int totalCalories;
    private float totalDays;
    private float averageCalories;
    private boolean entry;

    public CalorieSummary(List<LogEntry> logEntries) {
        calculateSummary(logEntries);
    }


    /** Calculates the total calories, overall consumption time, and
     * average calories consumed per day.
     * @param logEntries The list of calorie entries.
     *
     *
     */




    public void calculateSummary(List<LogEntry> logEntries) {
        totalCalories = 0;
        totalcalories1 = 0;
        totalDays = averageCalories = 0;


        for   ( LogEntry l : logEntries) {
            if (logEntries.size() <= 0) {
                totalcalories1 = 0;
            } else{ totalcalories1 = l.getTotalCalories();}
        }


        for (LogEntry l : logEntries)
            totalCalories += l.getTotalCalories();

        if (logEntries.size() > 1) {
            Date max = getMaxDate(logEntries);
            Date min = getMinDate(logEntries);
            totalDays = ( max.getTime() - min.getTime() ) / ( 1000 * 60 * 60 * 24 );
        }
        if (logEntries.size() != 0)
            averageCalories = totalCalories / logEntries.size();
    }

    /** Displays the information calculated on the provided Activity **/
    public void display(Activity activity) {
        TextView textView = (TextView) activity.findViewById(R.id.total_cals);
        textView.setText("Total calorie consumption: "
                + Integer.toString(totalCalories) + " cal");

        textView = (TextView) activity.findViewById(R.id.total_time);
        textView.setText("Total overall recording days: "
                + String.format("%.1f", totalDays) + " days");

        textView = (TextView) activity.findViewById(R.id.suggest);
        textView.setText("Your average consumption is: " + String.format("%.1f", averageCalories));

    }

    /** Retrieve the latest date in a given list of log entries **/
    public Date getMaxDate(List<LogEntry> logEntries) {
        Date max;
        if (logEntries.size() < 1)
            return null;

        max = logEntries.get(0).getDate();
        for (int i = 1; i < logEntries.size(); i++) {
            if (logEntries.get(i).getDate().compareTo(max) > 0)
                max = logEntries.get(i).getDate();
        }
        return max;
    }

    /** Retrieve the earliest date in a given list of log entries **/
    public Date getMinDate(List<LogEntry> logEntries) {
        Date min;
        if (logEntries.size() < 1)
            return null;

        min = logEntries.get(0).getDate();
        for (int i = 1; i < logEntries.size(); i++) {
            if (logEntries.get(i).getDate().compareTo(min) < 0)
                min = logEntries.get(i).getDate();
        }
        return min;
    }

    public float getTotalcalories1() {
        return totalcalories1;
    }

    public void setTotalcalories1(float totalcalories1) {
        this.totalcalories1 = totalcalories1;
    }

    public boolean isEntry() {
        return entry;
    }

    public void setEntry(boolean entry) {
        this.entry = entry;
    }

    /** Getters and Setters **/


    public float getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(int totalCalories) {
        this.totalCalories = totalCalories;
    }

    public float getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(float totalDays) {
        this.totalDays = totalDays;
    }

    public float getAverageCalories() {
        return averageCalories;
    }

    public void setAverageCalories(float averageCalories) {
        this.averageCalories = averageCalories;
    }
}


    //}
