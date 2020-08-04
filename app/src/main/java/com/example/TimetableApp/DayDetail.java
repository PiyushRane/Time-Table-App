package com.example.TimetableApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.example.TimetableApp.Utils.LetterImageView;

public class DayDetail extends AppCompatActivity {

    private ListView listView;
    private Toolbar toolbar;

//    public static String[] Monday;
//    public static String[] Tuesday;
//    public static String[] Wednesday;
//    public static String[] Thursday;
//    public static String[] Friday;
//    public static String[] Saturday;
//    public static String[] Time1;
//    public static String[] Time2;
//    public static String[] Time3;
//    public static String[] Time4;
//    public static String[] Time5;
//    public static String[] Time6;

    private String[] PreferredDay;
    private String[] PreferredTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_detail);

        setupUIViews();
        initToolbar();
        setupListView();
    }

    private void setupUIViews(){
        toolbar = (Toolbar) findViewById(R.id.ToolBarDayDetail);
        listView = (ListView) findViewById(R.id.lvDayDetail);
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(WeekActivity.sharedPreferences.getString(WeekActivity.SEL_DAY,null));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupListView(){
//        Monday = getResources().getStringArray(R.array.Monday);
//        Tuesday = getResources().getStringArray(R.array.Tuesday);
//        Wednesday = getResources().getStringArray(R.array.Wednesday);
//        Thursday = getResources().getStringArray(R.array.Thursday);
//        Friday = getResources().getStringArray(R.array.Friday);
//        Saturday = getResources().getStringArray(R.array.Saturday);
//
//        Time1 = getResources().getStringArray(R.array.time1);
//        Time2 = getResources().getStringArray(R.array.time2);
//        Time3 = getResources().getStringArray(R.array.time3);
//        Time4 = getResources().getStringArray(R.array.time4);
//        Time5 = getResources().getStringArray(R.array.time5);
//        Time6 = getResources().getStringArray(R.array.time6);

        String selected_day = WeekActivity.sharedPreferences.getString(WeekActivity.SEL_DAY, null);

        if(selected_day.equalsIgnoreCase("Monday")){
            PreferredDay = getResources().getStringArray(R.array.Monday);
            PreferredTime = getResources().getStringArray(R.array.time1);
        } else if(selected_day.equalsIgnoreCase("Tuesday")){
            PreferredDay = getResources().getStringArray(R.array.Tuesday);
            PreferredTime = getResources().getStringArray(R.array.time2);
        }else if(selected_day.equalsIgnoreCase("Wednesday")){
            PreferredDay = getResources().getStringArray(R.array.Wednesday);
            PreferredTime = getResources().getStringArray(R.array.time3);
        }else if(selected_day.equalsIgnoreCase("Thursday")){
            PreferredDay = getResources().getStringArray(R.array.Thursday);
            PreferredTime = getResources().getStringArray(R.array.time4);
        }else if(selected_day.equalsIgnoreCase("Friday")){
            PreferredDay = getResources().getStringArray(R.array.Friday);
            PreferredTime = getResources().getStringArray(R.array.time5);
        }else {
            PreferredDay = getResources().getStringArray(R.array.Saturday);
            PreferredTime = getResources().getStringArray(R.array.time6);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,PreferredDay,PreferredTime);
        listView.setAdapter(simpleAdapter);
    }

    public class SimpleAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView subject, time;
        private String[] subjectArray;
        private String[] timeArray;
        private LetterImageView letterImageView;

        public SimpleAdapter(Context context, String[] subjects, String[] times){
            mContext = context;
            subjectArray = subjects;
            timeArray = times;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return subjectArray.length;
        }

        @Override
        public Object getItem(int position) {
            return subjectArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = layoutInflater.inflate(R.layout.day_detail_single_item, null);
            }

            subject = (TextView) convertView.findViewById(R.id.tvSubjectDayDetails);
            time = (TextView) convertView.findViewById(R.id.tvTimeDayDetails);
            letterImageView = (LetterImageView) convertView.findViewById(R.id.ivDayDetails);

            subject.setText(subjectArray[position]);
            time.setText(timeArray[position]);

            letterImageView.setOval(true);
            letterImageView.setLetter(subjectArray[position].charAt(0));

            return convertView;
        }
    }

    @Override                                               // This will take from week to back home
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home : {
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
