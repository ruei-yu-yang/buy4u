package com.rueiyu.buy4u;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.model.TakePhotoOptions;
import com.rueiyu.buy4u.databinding.ActivityAddBinding;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddActivity extends TakePhotoActivity {

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
    private String mImagePath;

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

        mSelectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickPhoto();
            }
        });

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
                item.setPhotoPath(mImagePath);

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

    private void pickPhoto() {
        File file = new File(Environment.getExternalStorageDirectory(),
                "/buy4u/" + System.currentTimeMillis()+".jpg");
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        Uri uri = Uri.fromFile(file);
        TakePhoto takePhoto = getTakePhoto();
        takePhoto.setTakePhotoOptions(new TakePhotoOptions.Builder()
                .setWithOwnGallery(true)
                .create());
        takePhoto.onPickFromGalleryWithCrop(uri, getCropOptions());
    }

    private CropOptions getCropOptions() {
        int width = 600;
        int height = 720;
        CropOptions.Builder builder = new CropOptions.Builder()
                .setAspectX(width)
                .setAspectY(height)
                .setWithOwnCrop(true);
        return builder.create();
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        mImagePath = result.getImage().getOriginalPath();
        Glide.with(this)
                .load(mImagePath)
                .into(mPhoto);
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
    }
}
