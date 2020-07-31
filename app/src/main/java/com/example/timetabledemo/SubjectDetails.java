package com.example.timetabledemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SubjectDetails extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_details);

        setupUIViews();
        initToolbar();
        setupListView();
    }

    private void setupUIViews(){
        toolbar = (Toolbar) findViewById(R.id.ToolBarSubjectDetails);
        listView = (ListView) findViewById(R.id.lvSubjectDetails);
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(SubjectActivity.subjectPreferences.getString(SubjectActivity.SEL_SUB,null));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupListView(){
        String selected_subject = SubjectActivity.subjectPreferences.getString(SubjectActivity.SEL_SUB,null);
        String[] syllabus;
        String[] titles = getResources().getStringArray(R.array.titles);

        if(selected_subject.equalsIgnoreCase("ESO201")){
            syllabus = getResources().getStringArray(R.array.ESO201);
        }else if(selected_subject.equalsIgnoreCase("CS201")){
            syllabus = getResources().getStringArray(R.array.CS201);
        }
        else if(selected_subject.equalsIgnoreCase("CS202")){
            syllabus = getResources().getStringArray(R.array.CS201);
        }
        else if(selected_subject.equalsIgnoreCase("CS201")){
            syllabus = getResources().getStringArray(R.array.ESO207);
        }
        else if(selected_subject.equalsIgnoreCase("TA201")){
            syllabus = getResources().getStringArray(R.array.TA201);
        }else{
            syllabus = getResources().getStringArray(R.array.ENG124);
        }

        SubjectDetailsAdapter subjectDetailsAdapter = new SubjectDetailsAdapter(this,titles,syllabus);
        listView.setAdapter(subjectDetailsAdapter);


    }

    public class SubjectDetailsAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title, syllabus;
        private String[] titleArray;
        private String[] syllabusArray;

        public SubjectDetailsAdapter(Context context, String[] title, String[] syllabus){
            mContext = context;
            titleArray = title;
            syllabusArray = syllabus;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return titleArray.length;
        }

        @Override
        public Object getItem(int position) {
            return titleArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = layoutInflater.inflate(R.layout.subject_details_single_item, null);
            }

            title = (TextView) convertView.findViewById(R.id.tvSubjectTitle);
            syllabus = (TextView) convertView.findViewById(R.id.tvSyllabus);

            title.setText(titleArray[position]);
            syllabus.setText(syllabusArray[position]);

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
