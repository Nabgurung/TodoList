package com.nabin.todoapp.adapters;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nabin.todoapp.R;
import com.nabin.todoapp.models.ToDo;
import com.nabin.todoapp.util.Utility;


import java.util.ArrayList;

public class ToDoRecyclerAdapter extends RecyclerView.Adapter<ToDoRecyclerAdapter.ViewHolder> {

    private static final String TAG = "NotesRecyclerAdapter";

    private ArrayList<ToDo> mToDos = new ArrayList<>();
    private OnNoteListener mOnNoteListener;

    public ToDoRecyclerAdapter(ArrayList<ToDo> mToDos, OnNoteListener onNoteListener) {
        this.mToDos = mToDos;
        this.mOnNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_todo_list_item, parent, false);
        return new ViewHolder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        try{
            String month = mToDos.get(position).getTimestamp().substring(0, 2);
            month = Utility.getMonthFromNumber(month);
            String year = mToDos.get(position).getTimestamp().substring(3);
            String timestamp = month + " " + year;
            holder.timestamp.setText(timestamp);
            holder.title.setText(mToDos.get(position).getTitle());
        }catch (NullPointerException e){
            Log.e(TAG, "onBindViewHolder: Null Pointer: " + e.getMessage() );
        }
    }

    @Override
    public int getItemCount() {
        return mToDos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView timestamp, title;
        OnNoteListener mOnNoteListener;

        public ViewHolder(View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            timestamp = itemView.findViewById(R.id.note_timestamp);
            title = itemView.findViewById(R.id.note_title);
            mOnNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: " + getAdapterPosition());
            mOnNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}
















