package com.rueiyu.buy4u;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {

    private ImageView mPhoto;
    private TextView mPrevName;
    private TextView mPrevInfo;
    private Button mBtnDone;
    private Button mSelectImg;
    private EditText mStartDate;
    private EditText mEndDate;
    private EditText mPrice;
    private EditText mQty;
    private EditText mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        findViews();
    }

    private void findViews() {
        mPhoto = findViewById(R.id.prev_photo);
        mPrevName = findViewById(R.id.prev_name);
        mPrevInfo = findViewById(R.id.prev_info);
        mBtnDone = findViewById(R.id.b_done);
        mSelectImg = findViewById(R.id.b_select_image);
        mStartDate = findViewById(R.id.start_date);
        mEndDate = findViewById(R.id.end_date);
        mPrice = findViewById(R.id.price);
        mQty = findViewById(R.id.qty);
        mName = findViewById(R.id.name);
    }
}
