package com.nabin.todoapp.async;

import android.os.AsyncTask;

import com.nabin.todoapp.models.ToDo;
import com.nabin.todoapp.persistence.ToDoDao;

public class UpdateAsyncTask extends AsyncTask<ToDo, Void, Void> {

    private ToDoDao mToDoDao;

    public UpdateAsyncTask(ToDoDao dao) {
        mToDoDao = dao;
    }

    @Override
    protected Void doInBackground(ToDo... toDos) {
        mToDoDao.updateNotes(toDos);
        return null;
    }

}