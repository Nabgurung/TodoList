package com.nabin.todoapp.persistence;


import androidx.lifecycle.LiveData;
import android.content.Context;

import com.nabin.todoapp.async.DeleteAsyncTask;
import com.nabin.todoapp.async.InsertAsyncTask;
import com.nabin.todoapp.async.UpdateAsyncTask;
import com.nabin.todoapp.models.ToDo;

import java.util.List;

public class ToDoRepository {

    private ToDoDatabase mToDoDatabase;

    public ToDoRepository(Context context) {
        mToDoDatabase = ToDoDatabase.getInstance(context);
    }

    public void insertNoteTask(ToDo toDo){
        new InsertAsyncTask(mToDoDatabase.getNoteDao()).execute(toDo);
    }

    public void updateNoteTask(ToDo toDo){
        new UpdateAsyncTask(mToDoDatabase.getNoteDao()).execute(toDo);
    }

    public LiveData<List<ToDo>> retrieveNotesTask() {
        return mToDoDatabase.getNoteDao().getToDos();
    }

    public void deleteNoteTask(ToDo toDo){
        new DeleteAsyncTask(mToDoDatabase.getNoteDao()).execute(toDo);
    }
}













