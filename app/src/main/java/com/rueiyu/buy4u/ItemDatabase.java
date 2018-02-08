package com.rueiyu.buy4u;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by RueiYu on 2018/2/8.
 */

@Database(entities = {Item.class}, version = 1)
public abstract class ItemDatabase extends RoomDatabase {

    private static ItemDatabase instance;
    public abstract ItemDao itemDao();

    public static ItemDatabase getDatabase(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context,ItemDatabase.class,"Buy4u").build();
        }
        return instance;
    }
}
