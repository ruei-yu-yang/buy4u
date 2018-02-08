package com.rueiyu.buy4u;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by RueiYu on 2018/2/8.
 */

@Dao
public interface ItemDao {
    @Query("select * from " + Item.TABLE)
    public List<Item> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Item item);

    @Delete
    public void delete(Item item);
}
