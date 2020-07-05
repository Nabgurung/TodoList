package com.nabin.todoapp.async;

import android.os.AsyncTask;

import com.nabin.todoapp.models.ToDo;
import com.nabin.todoapp.persistence.ToDoDao;

public class DeleteAsyncTask extends AsyncTask<ToDo, Void, Void> {

    private ToDoDao mToDoDao;

    public DeleteAsyncTask(ToDoDao dao) {
        mToDoDao = dao;
    }

    @Override
    protected Void doInBackground(ToDo... toDos) {
        mToDoDao.delete(toDos);
        return null;
    }

}