package com.rku.tutorial08;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    DatabaseHelper helper;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ListView listView;
    String UserData[] = {"TEST 1","TEST 2"};
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        preferences = getSharedPreferences("login",MODE_PRIVATE);
        editor = preferences.edit();
        helper = new DatabaseHelper(this);

        listView = findViewById(R.id.userDataList);
        adapter = new ArrayAdapter<String>(
                WelcomeActivity.this,
                android.R.layout.simple_list_item_1,
                helper.getUserList());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String listItem = ((TextView)view).getText().toString();
                Intent intent = new Intent(WelcomeActivity.this,UserDetailActivity.class);
                intent.putExtra("username",listItem);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnuLogout:
                editor.putString("username","");
                editor.commit();
                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}