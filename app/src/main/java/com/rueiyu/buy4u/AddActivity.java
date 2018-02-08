package com.rueiyu.buy4u;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.rueiyu.buy4u.databinding.ActivityAddBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
    private ActivityAddBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add);
        Item item = new Item();
        item.setName("北海道巧克力");
        item.setPrice(300);
        binding.setItem(item);

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

        mBtnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d(TAG, "onClick: "+ binding.getItem());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                final Item item = new Item();
                item.setName(mName.getText().toString());
                try {
                    item.setStart(sdf.parse(mStartDate.getText().toString()));
                    item.setEnd(sdf.parse(mEndDate.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                item.setPrice(Integer.parseInt(mPrice.getText().toString()));
                item.setQty(Integer.parseInt(mQty.getText().toString()));

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ItemDatabase.getDatabase(AddActivity.this)
                                .itemDao().insert(item);
                    }
                }).start();
            }
        });
    }
}
