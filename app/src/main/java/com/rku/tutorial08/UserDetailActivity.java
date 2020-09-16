package com.rku.tutorial08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class UserDetailActivity extends AppCompatActivity {

    DatabaseHelper helper;
    TextView userDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        helper = new DatabaseHelper(this);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String userdata = "";
        userDetail = findViewById(R.id.txtUserDetail);

        Cursor cursor = helper.getSingleUserData(username);
        cursor.moveToFirst();
        userdata += "First Name : " + cursor.getString(1);
        userdata += "\nLast Name : " + cursor.getString(2);
        userdata += "\nUsername : " + cursor.getString(3);
        userdata += "\nPassword : " + cursor.getString(4);
        userdata += "\nBranch : " + cursor.getString(5);
        userdata += "\nGender : " + cursor.getString(6);
        userdata += "\nCity : " + cursor.getString(7);
        userdata += "\nStatus : " + cursor.getString(8);

        userDetail.setText(userdata);
    }
}