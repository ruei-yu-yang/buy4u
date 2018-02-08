package com.rueiyu.buy4u;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.rueiyu.buy4u.Item.TABLE;

/**
 * Created by RueiYu on 2018/2/8.
 */

@Entity(tableName = TABLE)
@TypeConverters({DateTypeConverter.class})
public class Item {
    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
    public static final String TABLE = "items";
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int id;
    private int groupId;
    private String name;
    private String description;
    private String photoPath;
    private Date start;
    @Ignore
    private String startFormatted;
    private Date end;
    private int price;
    @Ignore
    String priceString;
    private int qty;

    public String getPriceString() {
        if (price != 0) {
            priceString = String.valueOf(price);
        }
        return priceString;
    }

    public void setPriceString(String priceString) {
        this.priceString = priceString;
        try {
            price = Integer.parseInt(priceString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            price = 0;
        }
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", photoPath='" + photoPath + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", price=" + price +
                ", qty=" + qty +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart() {
        if (start == null){
            if (TextUtils.isEmpty(startFormatted)){
                start = new Date();
            }else{
                try {
                    start = SDF.parse(startFormatted);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getStartFormatted() {
        startFormatted = SDF.format(getStart());
        return startFormatted;
    }

    public void setStartFormatted(String startFormatted) {
        this.startFormatted = startFormatted;
    }
}
