package com.rueiyu.buy4u;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GroupDialogFragment.OnGroupNameListener, AdapterView.OnItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private List<Group> mGroups;
    private int mGroupId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGroupId = PreferenceManager.getDefaultSharedPreferences(this)
                .getInt("group_id",0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Cursor cursor = MyDBHelper.getInstance(this).getReadableDatabase()
                .query("groups", null, null, null, null, null, null);
        if (cursor.getCount() <= 0) {
            showGroupNameDialog();
        }
    }

    private void showGroupNameDialog() {
        GroupDialogFragment dialog = new GroupDialogFragment();
        dialog.show(getFragmentManager(), "groupDialog");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.getItem(0);
        Spinner spinner = (Spinner) item.getActionView();
        Cursor cursor = MyDBHelper.getInstance(this).getReadableDatabase()
                .query("groups",null,null,null,null,null,null,null);

        int selectedIndex = 0;
        mGroups = new ArrayList<>();
        for(int i =0;i<cursor.getCount();i++){
            cursor.moveToPosition(i);
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            mGroups.add(new Group(id,name));
            if(id == mGroupId){
                selectedIndex = i;
            }
        }
        ArrayAdapter<Group> adapter =
                new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mGroups);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        spinner.setSelection(selectedIndex);

//        List<String> names = new ArrayList<>();
//        while(cursor.moveToNext()){
//            int id = cursor.getInt(cursor.getColumnIndex("_id"));
//            String name = cursor.getString(cursor.getColumnIndex("name"));
//            names.add(name);
//        }
//        ArrayAdapter<String> adapter =
//                new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,names);
//        spinner.setAdapter(adapter);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.action_add_group){
            showGroupNameDialog();
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void groupNameInputCompleted(String name) {
        Log.d(TAG, "groupNameInputCompleted: " + name);
        ContentValues values = new ContentValues();
        values.put("name", name);
        MyDBHelper.getInstance(this).getWritableDatabase().insert("groups", null, values);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "onItemSelected: " + position);
        Group group = mGroups.get(position);
        PreferenceManager.getDefaultSharedPreferences(this)
                .edit().putInt("group_id",group.getId()).apply();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
