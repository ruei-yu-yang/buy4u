package com.rueiyu.buy4u;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private ImageView mPhoto;
    private TextView mName;
    private TextView mInfo;
    private TextView mStart;
    private TextView mEnd;
    private TextView mPrice;
    private TextView mQty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        findViews();
    }

    private void findViews() {
        mPhoto = findViewById(R.id.prev_photo);
        mName = findViewById(R.id.prev_name);
        mInfo = findViewById(R.id.prev_info);
        mStart = findViewById(R.id.start_date);
        mEnd = findViewById(R.id.end_date);
        mPrice = findViewById(R.id.price);
        mQty = findViewById(R.id.qty);
    }
}
