package com.nabin.todoapp.persistence;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.nabin.todoapp.models.ToDo;

@Database(entities = {ToDo.class}, version = 1)
public abstract class ToDoDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "notes_db";

    private static ToDoDatabase instance;

    static ToDoDatabase getInstance(final Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ToDoDatabase.class,
                    DATABASE_NAME
            ).build();
        }
        return instance;
    }

    public abstract ToDoDao getNoteDao();
}