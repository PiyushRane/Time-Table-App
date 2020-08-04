package com.example.TimetableApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class FacultyDetails extends AppCompatActivity {

    private CircleImageView facultyImage;
    private Toolbar toolbar;
    private TextView facultyName,phoneNumber,email,place;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_details);

        setupUIViews();
        initToolbar();
        setupDetails();
    }

    private void setupUIViews(){
        toolbar = (Toolbar) findViewById(R.id.ToolBarFacultyDetails);
        facultyImage = (CircleImageView)findViewById(R.id.ivFaculty);
        facultyName = (TextView)findViewById(R.id.tvFacultySelected);
        phoneNumber = (TextView)findViewById(R.id.tvPhoneNumber);
        email = (TextView)findViewById(R.id.tvEmail);
        place = (TextView)findViewById(R.id.tvPlace);
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Faculty Details");
        // uncomment below 3 lines to get toolbar title as name of faculty
//        int faculty_pos = FacultyActivity.sharedPreferences.getInt(FacultyActivity.SEL_FACULTY,0);
//        String[] facultyNames = getResources().getStringArray(R.array.faculty);
//        getSupportActionBar().setTitle(facultyNames[faculty_pos]);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupDetails(){
        int faculty_pos = FacultyActivity.sharedPreferences.getInt(FacultyActivity.SEL_FACULTY,0);
        String[] facultyNames = getResources().getStringArray(R.array.faculty);
        int[] facultyImages = new int[]{R.drawable.sameer_khandekar,R.drawable.karkare,R.drawable.seth,R.drawable.deba,R.drawable.mainakc};
        int[] facultyArray = new int[]{R.array.faculty1,R.array.faculty2,R.array.faculty3,R.array.faculty4,R.array.faculty5};
        String[] facultyDetails = getResources().getStringArray(facultyArray[faculty_pos]);

        facultyImage.setImageResource(facultyImages[faculty_pos]);
        facultyName.setText(facultyNames[faculty_pos]);
        phoneNumber.setText(facultyDetails[0]);
        email.setText(facultyDetails[1]);
        place.setText(facultyDetails[2]);
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
