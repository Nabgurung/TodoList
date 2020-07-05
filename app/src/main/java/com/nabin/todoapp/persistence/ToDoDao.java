package com.nabin.todoapp.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.nabin.todoapp.models.ToDo;

import java.util.List;

@Dao
public interface ToDoDao {

    @Insert
    long[] insertNotes(ToDo... toDos);

    @Query("SELECT * FROM NOTES")
    LiveData<List<ToDo>> getToDos();

    @Delete
    int delete(ToDo... toDos);

    @Update
    int updateNotes(ToDo... toDos);
}
