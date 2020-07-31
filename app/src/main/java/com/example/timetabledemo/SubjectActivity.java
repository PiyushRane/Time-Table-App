package com.example.timetabledemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.timetabledemo.Utils.LetterImageView;

public class SubjectActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;
    public static SharedPreferences subjectPreferences;
    public static String SEL_SUB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        setupUIViews();
        initToolbar();
        setupListView();
    }

    private void setupUIViews(){
        toolbar = (Toolbar) findViewById(R.id.ToolBarSubject);
        listView = (ListView) findViewById(R.id.lvSubject);
        subjectPreferences = getSharedPreferences("Subjects",MODE_PRIVATE);
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Subjects");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupListView(){
        String[] subjects = getResources().getStringArray(R.array.Subject);
        final SubjectAdapter subjectAdapter = new SubjectAdapter(this,R.layout.subject_single_item,subjects);
        listView.setAdapter(subjectAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0: {
                        subjectPreferences.edit().putString(SEL_SUB,"ESO201").apply();
                        startActivity(new Intent(SubjectActivity.this,SubjectDetails.class));
                        break;
                    }
                    case 1: {
                        subjectPreferences.edit().putString(SEL_SUB,"CS201").apply();
                        startActivity(new Intent(SubjectActivity.this,SubjectDetails.class));
                        break;
                    }
                    case 2: {
                        subjectPreferences.edit().putString(SEL_SUB,"CS202").apply();
                        startActivity(new Intent(SubjectActivity.this,SubjectDetails.class));
                        break;
                    }
                    case 3: {
                        subjectPreferences.edit().putString(SEL_SUB,"ESO207").apply();
                        startActivity(new Intent(SubjectActivity.this,SubjectDetails.class));
                        break;
                    }
                    case 4: {
                        subjectPreferences.edit().putString(SEL_SUB,"TA201").apply();
                        startActivity(new Intent(SubjectActivity.this,SubjectDetails.class));
                        break;
                    }
                    case 5: {
                        subjectPreferences.edit().putString(SEL_SUB,"ENG124").apply();
                        startActivity(new Intent(SubjectActivity.this,SubjectDetails.class));
                        break;
                    }
                    default:break;
                }
            }
        });

    }

    public class SubjectAdapter extends ArrayAdapter {

        private int resource;
        private LayoutInflater layoutInflater;
        private String[] Subjects;

        public SubjectAdapter(Context context, int resource, String[] objects) {
            super(context, resource, objects);
            this.resource = resource;
            this.Subjects = objects;
            layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder;
            if(convertView == null){
                holder = new ViewHolder();
                convertView = layoutInflater.inflate(resource,null);
                holder.ivLogo = (LetterImageView)convertView.findViewById(R.id.ivLetterSubject);
                holder.tvSubject = (TextView)convertView.findViewById(R.id.tvSubject);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }

            holder.ivLogo.setOval(true);
            holder.ivLogo.setLetter(Subjects[position].charAt(0));
            holder.tvSubject.setText(Subjects[position]);

            return convertView;
        }

        class ViewHolder{
            private LetterImageView ivLogo;
            private TextView tvSubject;

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
