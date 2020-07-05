package com.nabin.todoapp.async;

import android.os.AsyncTask;

import com.nabin.todoapp.models.ToDo;
import com.nabin.todoapp.persistence.ToDoDao;

public class InsertAsyncTask extends AsyncTask<ToDo, Void, Void> {

    private ToDoDao mToDoDao;

    public InsertAsyncTask(ToDoDao dao) {
        mToDoDao = dao;
    }

    @Override
    protected Void doInBackground(ToDo... toDos) {
        mToDoDao.insertNotes(toDos);
        return null;
    }

}