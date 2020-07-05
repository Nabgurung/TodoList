package com.nabin.todoapp;

import androidx.lifecycle.Observer;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.nabin.todoapp.adapters.ToDoRecyclerAdapter;
import com.nabin.todoapp.models.ToDo;
import com.nabin.todoapp.persistence.ToDoRepository;
import com.nabin.todoapp.util.VerticalSpacingItemDecorator;

import java.util.ArrayList;
import java.util.List;

public class ToDosListActivity extends AppCompatActivity implements
        ToDoRecyclerAdapter.OnNoteListener,
        FloatingActionButton.OnClickListener
{

    private static final String TAG = "NotesListActivity";

    // ui components
    private RecyclerView mRecyclerView;
    AlertDialog.Builder mAlertdialog;
    ToDo mToDo;

    // vars
    private ArrayList<ToDo> mToDos = new ArrayList<>();
    private ToDoRecyclerAdapter mNoteRecyclerAdapter;
    private ToDoRepository mToDoRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos_list);
        mRecyclerView = findViewById(R.id.recyclerView);

        findViewById(R.id.fab).setOnClickListener(this);

        initRecyclerView();
        mToDoRepository = new ToDoRepository(this);
        retrieveNotes();
//        insertFakeNotes();

        setSupportActionBar((Toolbar)findViewById(R.id.notes_toolbar));
        setTitle("Notes");
    }


    private void retrieveNotes() {
        mToDoRepository.retrieveNotesTask().observe(this, new Observer<List<ToDo>>() {
            @Override
            public void onChanged(@Nullable List<ToDo> toDos) {
                if(mToDos.size() > 0){
                    mToDos.clear();
                }
                if(toDos != null){
                    mToDos.addAll(toDos);
                }
                mNoteRecyclerAdapter.notifyDataSetChanged();
            }
        });
    }

    private void insertFakeNotes(){
        for(int i = 0; i < 1000; i++){
            ToDo toDo = new ToDo();
            toDo.setTitle("title #" + i);
            toDo.setContent("content #: " + i);
            toDo.setTimestamp("Jan 2019");
            mToDos.add(toDo);
        }
        mNoteRecyclerAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(itemDecorator);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);
        mNoteRecyclerAdapter = new ToDoRecyclerAdapter(mToDos, this);
        mRecyclerView.setAdapter(mNoteRecyclerAdapter);
    }


    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(this, ToDoActivity.class);
        intent.putExtra("selected_note", mToDos.get(position));
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, ToDoActivity.class);
        startActivity(intent);
    }

    private void deleteNote(ToDo toDo) {
        mToDos.remove(toDo);
        mNoteRecyclerAdapter.notifyDataSetChanged();

        mToDoRepository.deleteNoteTask(toDo);
    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            deleteNote(mToDos.get(viewHolder.getAdapterPosition()));
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout_menu:
                mAlertdialog = new AlertDialog.Builder(ToDosListActivity.this);
                mAlertdialog.setMessage(getString(R.string.logout_app)).setCancelable(false).setTitle(getString(R.string.app_name)).setIcon(R.mipmap.ic_launcher);
                mAlertdialog.setPositiveButton(getString(R.string.btn_logout), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences preferences = getApplicationContext().getSharedPreferences("todo_pref ",0);
                        SharedPreferences.Editor editor= preferences.edit();
                        editor.clear();
                        editor.commit();
                        Intent intent= new Intent(ToDosListActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });
                mAlertdialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                mAlertdialog.show();



                break;

        }

        return super.onOptionsItemSelected(item);
    }
}


















